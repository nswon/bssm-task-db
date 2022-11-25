package taskdb.taskdb.domain.user.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.store.presentation.dto.response.StoreQuestionsResponseDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.domain.UserRepository;
import taskdb.taskdb.domain.user.exception.UserException;
import taskdb.taskdb.domain.user.exception.UserExceptionType;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.presentation.dto.user.request.UserJoinRequestDto;
import taskdb.taskdb.domain.user.presentation.dto.user.response.UserResponseDto;
import taskdb.taskdb.domain.user.presentation.dto.user.response.UsersRankResponseDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    @Transactional
    public boolean join(UserJoinRequestDto requestDto) {
        String email = requestDto.getEmail();
        userFacade.checkAvailableEmail(email);
        String emailVerificationCode = requestDto.getCheckCode();
        userFacade.checkCorrectEmailCheckCode(emailVerificationCode);

        User user = requestDto.toEntity();
        user.setGradeByParseEmail();
        userRepository.save(user);
        user.encodedPassword(passwordEncoder);
        user.addUserAuthority();
        return true;
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::new)
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_USER));
    }

    public List<QuestionsResponseDto> getQuestions() {
        User user = userFacade.getCurrentUser();
        return user.toQuestionsResponseDto();
    }

    public List<StoreQuestionsResponseDto> getSavedQuestions() {
        User user = userFacade.getCurrentUser();
        return user.toStoreQuestionsResponseDto();
    }

    public List<UsersRankResponseDto> rank() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getContributionLevel).reversed().thenComparing(User::getAnswerCount))
                .limit(10)
                .map(UsersRankResponseDto::new)
                .collect(Collectors.toList());
    }
}
