package taskdb.taskdb.adapter.store.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.user.entity.User;

import java.util.Optional;
import java.util.UUID;

import static taskdb.taskdb.domain.store.entity.QQuestionStore.questionStore;

@Repository
@RequiredArgsConstructor
public class QuestionStoreCustom implements QuestionStoreCustomRepository {
    private final JPAQueryFactory factory;

    @Override
    public Optional<QuestionStore> getQuestionStoreByUser(User user, UUID id) {
        return Optional.ofNullable(factory
                .selectFrom(questionStore)
                .where(questionStore.user.id.eq(user.getId()).and(questionStore.id.eq(id)))
                .fetchOne());
    }
}
