package taskdb.taskdb.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.domain.UserRepository;
import taskdb.taskdb.domain.user.exception.UserException;
import taskdb.taskdb.domain.user.exception.UserExceptionType;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.presentation.dto.request.UserJoinRequestDto;
import taskdb.taskdb.domain.user.presentation.dto.request.UserProfileRequestDto;
import taskdb.taskdb.domain.user.presentation.dto.response.UserResponseDto;
import taskdb.taskdb.domain.user.presentation.dto.response.UsersRankResponseDto;
import taskdb.taskdb.domain.user.service.dto.ImageDto;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private static final String DEFAULT_IMAGE_PATH = "de1d0432-0d45-4872-b27c-8ac19f701837_THUMBNAIL_60_60_icon_rep_box.gif";
    private static final String DEFAULT_IMAGE_URL = "https://taskdb.s3.ap-northeast-2.amazonaws.com/c80a5160-2d8c-42c1-a17b-1f87fd0f58be_THUMBNAIL_60_60_icon_rep_box.gif";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;
    private final ImageService imageService;

    @Transactional
    public boolean join(UserJoinRequestDto requestDto) {
        String email = requestDto.getEmail();
        userFacade.checkAvailableEmail(email);
        String emailVerificationCode = requestDto.getCheckCode();
        userFacade.checkCorrectEmailCheckCode(emailVerificationCode);

        User user = requestDto.toEntity();
        user.generateGrade();
        userRepository.save(user);
        user.encodedPassword(passwordEncoder);
        user.addUserAuthority();
        user.upload(DEFAULT_IMAGE_PATH, DEFAULT_IMAGE_URL);
        return true;
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::new)
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_USER));
    }

    @Transactional
    public void updateProfile(UserProfileRequestDto requestDto) throws IOException {
        User user = userFacade.getCurrentUser();
        if(!user.getImgPath().isEmpty() && !user.getImgPath().equals(DEFAULT_IMAGE_PATH)) {
            imageService.delete(user.getImgPath());
        }

        ImageDto imageDto = imageService.create(requestDto.getFile());
        user.upload(imageDto.getImgPath(), imageDto.getImgUrl());
        user.update(requestDto.getNickname());
    }

    public List<UsersRankResponseDto> rank() {
        List<User> usersRank = userFacade.getUsersByContributionLevel();
       return IntStream.range(0, usersRank.size())
                .boxed()
               .map(count -> new UsersRankResponseDto(count+1, usersRank.get(count)))
                .collect(Collectors.toList());
    }
}
