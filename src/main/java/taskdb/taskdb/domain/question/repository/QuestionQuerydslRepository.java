package taskdb.taskdb.domain.question.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taskdb.taskdb.domain.question.domain.Question;

import java.util.List;
import java.util.regex.Pattern;

import static taskdb.taskdb.domain.question.domain.QQuestion.question;

@Repository
@RequiredArgsConstructor
public class QuestionQuerydslRepository {
    private static final Pattern ONLY_NUMBER_REGEX = Pattern.compile("^[0-9]*$");
    private final JPAQueryFactory factory;

    public List<Question> getQuestionByTitleOrId(String keyword) {
        return factory
                .selectFrom(question)
                .where(isTitleContainsOrIdEq(keyword))
                .fetch();
    }

    public List<Question> getQuestionByGrade(int grade) {
        return factory
                .selectFrom(question)
                .where(isJunior(grade), isSophomore(grade))
                .fetch();
    }

    private BooleanExpression isTitleContainsOrIdEq(String keyword) {
        if(ONLY_NUMBER_REGEX.matcher(keyword).matches()) {
            return question.id.eq(Long.valueOf(keyword));
        }
        return question.title.contains(keyword);
    }

    private BooleanExpression isJunior(int grade) {
        if(grade == 0) {
            return null;
        }
        return question.user.grade.grade.eq(grade);
    }

    private BooleanExpression isSophomore(int grade) {
        if(grade == 0) {
            return null;
        }
        return question.user.grade.grade.eq(grade);
    }
}
