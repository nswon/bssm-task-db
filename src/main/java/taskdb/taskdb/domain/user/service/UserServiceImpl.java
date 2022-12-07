package taskdb.taskdb.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.auth.service.EmailService;
import taskdb.taskdb.domain.user.domain.*;
import taskdb.taskdb.domain.user.dto.UserJoinRequestDto;
import taskdb.taskdb.domain.user.dto.UserProfileRequestDto;
import taskdb.taskdb.domain.user.dto.UserResponseDto;
import taskdb.taskdb.domain.user.exception.InvalidAuthCodeException;
import taskdb.taskdb.domain.user.port.UserReader;
import taskdb.taskdb.domain.user.port.UserStore;
import taskdb.taskdb.mapper.UserMapper;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final EmailService emailService;
    private final ImageService imageService;
    private final UserStore userStore;
    private final UserReader userReader;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void join(UserJoinRequestDto requestDto) {
        checkEmailCode(requestDto.getCheckCode());
        User user = userMapper.of(requestDto);
        userStore.store(user);
    }

    private void checkEmailCode(String code) {
        if(emailService.verityCode(code)) {
            throw new InvalidAuthCodeException();
        }
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        var user = userReader.getUser(id);
        return userMapper.of(user);
    }

    @Override
    public UserResponseDto getUser() {
        User user = userReader.getCurrentUser();
        return userMapper.of(user);
    }

    @Override
    @Transactional
    public void update(UserProfileRequestDto requestDto) {
        User user = userReader.getCurrentUser();
        checkImage(user);
        update(user, requestDto);
    }

    private void checkImage(User user) {
        if(user.canDeleteImage()) {
            imageService.delete(user.getImagePath());
        }
    }

    private void update(User user, UserProfileRequestDto requestDto) {
        Image image = imageService.create(requestDto.getFile());
        Nickname nickname = Nickname.of(requestDto.getNickname());
        Bio bio = Bio.of(requestDto.getBio());
        user.updateImage(image);
        user.updateNickname(nickname);
        user.updateBio(bio);
    }
}
