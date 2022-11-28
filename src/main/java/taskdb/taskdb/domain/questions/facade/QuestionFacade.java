package taskdb.taskdb.domain.questions.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.domain.QuestionRepository;
import taskdb.taskdb.domain.questions.exception.QuestionException;
import taskdb.taskdb.domain.questions.exception.QuestionExceptionType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class QuestionFacade {
    private static final String VISIT_COOKIE_NAME = "visit_cookie";
    private final QuestionRepository questionRepository;

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionException(QuestionExceptionType.NOT_FOUND_QUESTION));
    }

    public boolean canAddViewCount(HttpServletRequest request, Long id) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return true;
        }
        return Arrays.stream(cookies)
                .noneMatch(cookie -> cookie.getName().equals(VISIT_COOKIE_NAME) &&
                        cookie.getValue().contains(toString(id)));
    }

    public Cookie createCookie(Long id) {
        Cookie cookie = new Cookie(VISIT_COOKIE_NAME, toString(id));
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        return cookie;
    }

    private String toString(Long id) {
        return String.valueOf(id);
    }
}
