package taskdb.taskdb.application.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.user.dto.*;
import taskdb.taskdb.application.user.port.in.GetUserUseCase;
import taskdb.taskdb.application.user.port.in.UserJoinUseCase;
import taskdb.taskdb.application.user.port.in.UserRankUseCase;
import taskdb.taskdb.application.user.port.in.UserUpdateUserCase;
import taskdb.taskdb.domain.user.entity.Bio;
import taskdb.taskdb.domain.user.entity.Image;
import taskdb.taskdb.domain.user.entity.Nickname;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.domain.user.exception.InvalidAuthCodeException;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.application.user.port.out.SaveUserPort;
import taskdb.taskdb.application.auth.service.EmailService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserJoinUseCase, GetUserUseCase, UserUpdateUserCase, UserRankUseCase {
    private final EmailService emailService;
    private final ImageService imageService;
    private final SaveUserPort saveUserPort;
    private final GetUserPort getUserPort;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void join(UserJoinRequestDto requestDto) {
        checkEmailCode(requestDto.getCheckCode());
        User user = userMapper.of(requestDto);
        saveUserPort.save(user);
    }

    private void checkEmailCode(String code) {
        if(emailService.verityCode(code)) {
            throw new InvalidAuthCodeException();
        }
    }

    @Override
    public UserResponseDto getUser(Long id) {
        var user = getUserPort.getUser(id);
        return userMapper.of(user);
    }

    @Override
    public UserResponseDto getUser() {
        var user = getUserPort.getCurrentUser();
        return userMapper.of(user);
    }

    @Override
    @Transactional
    public void update(UserUpdateRequestDto requestDto) {
        var user = getUserPort.getCurrentUser();
        checkImage(user);
        update(user, requestDto);
    }

    private void checkImage(User user) {
        if(user.canDeleteImage()) {
            imageService.delete(user.getImagePath());
        }
    }

    private void update(User user, UserUpdateRequestDto requestDto) {
        Image image = imageService.create(requestDto.getFile());
        Nickname nickname = Nickname.of(requestDto.getNickname());
        Bio bio = Bio.of(requestDto.getBio());
        user.updateImage(image);
        user.updateNickname(nickname);
        user.updateBio(bio);
    }

    @Override
    public List<UsersRankResponseDto> rank() {
        List<User> users = getUserPort.getUser();
        return IntStream.range(0, users.size())
                .boxed()
                .map(rank -> new UsersRankResponseDto(rank+1, users.get(rank)))
                .collect(Collectors.toList());
    }
}
