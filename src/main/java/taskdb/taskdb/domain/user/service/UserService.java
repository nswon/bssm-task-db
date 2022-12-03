package taskdb.taskdb.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.user.domain.*;
import taskdb.taskdb.domain.user.exception.UserNotFoundException;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.dto.UserJoinRequestDto;
import taskdb.taskdb.domain.user.dto.UserProfileRequestDto;
import taskdb.taskdb.domain.user.dto.UserResponseDto;
import taskdb.taskdb.domain.user.dto.UsersRankResponseDto;
import taskdb.taskdb.domain.user.repository.UserRepository;
import taskdb.taskdb.domain.user.dto.ImageResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;
    private final ImageService imageService;

    @Transactional
    public void join(UserJoinRequestDto requestDto) {
        userFacade.validate(requestDto);

        User user = User.builder()
                .email(Email.of(requestDto.getEmail()))
                .grade(Grade.of(requestDto.getGrade()))
                .nickname(Nickname.of(requestDto.getNickname()))
                .password(Password.of(passwordEncoder, requestDto.getPassword()))
                .build();
        userRepository.save(user);
        user.addUserAuthority();
        user.updateImage(Image.createDefault());
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::new)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void updateUser(UserProfileRequestDto requestDto) throws IOException {
        User user = userFacade.getCurrentUser();
        if(user.canDeleteImage()) {
            imageService.delete(user.getImagePath());
        }

        ImageResponse imageResponse = imageService.create(requestDto.getFile());
        Image image = Image.of(imageResponse.getImgPath(), imageResponse.getImgUrl());
        user.updateImage(image);
        Nickname nickname = Nickname.of(requestDto.getNickname());
        user.updateNickname(nickname);
    }

    public List<UsersRankResponseDto> rank() {
        List<User> usersRank = userFacade.getUsersByContributionLevel();
       return IntStream.range(0, usersRank.size())
                .boxed()
               .map(count -> new UsersRankResponseDto(count+1, usersRank.get(count)))
                .collect(Collectors.toList());
    }
}
