package taskdb.taskdb.adapter.question.out.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.QuestionStatus;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static taskdb.taskdb.domain.question.entity.QQuestion.question;

@Repository
@RequiredArgsConstructor
public class QuestionCustom implements QuestionCustomRepository {
    private static final Pattern ONLY_NUMBER_REGEX = Pattern.compile("^[0-9]*$");
    private final JPAQueryFactory factory;

    @Override
    public List<Question> getQuestionByKeyword(String keyword) {
        return factory
                .selectFrom(question)
                .where(isTitleContainsOrIdEq(keyword))
                .fetch();
    }

    private BooleanExpression isTitleContainsOrIdEq(String keyword) {
        if(ONLY_NUMBER_REGEX.matcher(keyword).matches()) {
            return question.id.eq(UUID.fromString(keyword));
        }
        return question.title.value.contains(keyword);
    }

    @Override
    public List<Question> getQuestionByGrade(int grade) {
        return factory
                .selectFrom(question)
                .where(checkGrade(grade))
                .fetch();
    }

    private BooleanExpression checkGrade(int grade) {
        if(grade == 0) {
            return null;
        }
        return question.user.grade.value.eq(grade);
    }

    @Override
    public List<Question> getQuestionByStatus(QuestionStatus status) {
        return factory
                .selectFrom(question)
                .where(question.questionStatus.eq(status))
                .fetch();
    }
}
