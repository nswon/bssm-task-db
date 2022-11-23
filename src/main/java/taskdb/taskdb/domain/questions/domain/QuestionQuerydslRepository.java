package taskdb.taskdb.domain.questions.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static taskdb.taskdb.domain.questions.domain.QQuestion.question;

@Repository
@RequiredArgsConstructor
public class QuestionQuerydslRepository {
    private final JPAQueryFactory factory;

    public List<Question> getQuestionByTitleOrId(Object keyword) {
        return factory
                .selectFrom(question)
                .where(titleEq(keyword), idEq(keyword))
                .fetch();
    }

    private BooleanExpression titleEq(Object keyword) {
        if(keyword == null) {
            return null;
        }
        String titleKeyword = String.valueOf(keyword);
        return question.title.contains(titleKeyword);
    }

    private BooleanExpression idEq(Object keyword) {
        if(keyword == null) {
            return null;
        }
        Long id = (Long) keyword;
        return question.id.eq(id);
    }
}