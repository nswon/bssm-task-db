package taskdb.taskdb.adapter.question.out.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.QuestionStatus;

import java.util.List;
import java.util.regex.Pattern;

import static taskdb.taskdb.domain.question.domain.QQuestion.question;

@Repository
@RequiredArgsConstructor
public class QuestionQuerydsl implements QuestionQuerydslRepository {
    private static final Pattern ONLY_NUMBER_REGEX = Pattern.compile("^[0-9]*$");
    private final JPAQueryFactory factory;

    @Override
    public List<Question> getQuestionByKeyword(String keyword) {
        return factory
                .selectFrom(question)
                .where(isTitleContainsOrIdEq(keyword))
                .fetch();
    }

    @Override
    public List<Question> getQuestionByGrade(int grade) {
        return factory
                .selectFrom(question)
                .where(isJunior(grade), isSophomore(grade))
                .fetch();
    }

    @Override
    public List<Question> getQuestionByStatus(QuestionStatus status) {
        return factory
                .selectFrom(question)
                .where(question.questionStatus.eq(status))
                .fetch();
    }

    private BooleanExpression isTitleContainsOrIdEq(String keyword) {
        if(ONLY_NUMBER_REGEX.matcher(keyword).matches()) {
            return question.id.eq(Long.valueOf(keyword));
        }
        return question.title.value.contains(keyword);
    }

    private BooleanExpression isJunior(int grade) {
        if(grade == 0) {
            return null;
        }
        return question.user.grade.value.eq(grade);
    }

    private BooleanExpression isSophomore(int grade) {
        if(grade == 0) {
            return null;
        }
        return question.user.grade.value.eq(grade);
    }
}
