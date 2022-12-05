package taskdb.taskdb.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.user.domain.*;
import taskdb.taskdb.domain.user.exception.UserNotFoundException;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.dto.UserJoinRequestDto;
import taskdb.taskdb.domain.user.dto.UserProfileRequestDto;
import taskdb.taskdb.domain.user.dto.UserResponseDto;
import taskdb.taskdb.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
                .bio(Bio.createDefault())
                .password(Password.of(passwordEncoder, requestDto.getPassword()))
                .build();
        user.updateImage(Image.createDefault());
        user.addUserAuthority();
        userRepository.save(user);
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::new)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserResponseDto getUser() {
        User user = userFacade.getCurrentUser();
        return new UserResponseDto(user);
    }

    @Transactional
    public void updateUser(UserProfileRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        validateCustomImage(user);
        Image image = imageService.create(requestDto.getFile());
        user.updateImage(image);
        Nickname nickname = Nickname.of(requestDto.getNickname());
        user.updateNickname(nickname);
        Bio bio = Bio.of(requestDto.getBio());
        user.updateBio(bio);
    }

    private void validateCustomImage(User user) {
        if(user.canDeleteImage()) {
            imageService.delete(user.getImagePath());
        }
    }
}
