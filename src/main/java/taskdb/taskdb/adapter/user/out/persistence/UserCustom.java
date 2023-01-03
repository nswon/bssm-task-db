package taskdb.taskdb.adapter.user.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taskdb.taskdb.domain.user.entity.User;

import java.util.Optional;
import java.util.UUID;

import static taskdb.taskdb.domain.user.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class UserCustom implements UserCustomRepository {
    private final JPAQueryFactory factory;

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(factory
                .selectFrom(user)
                .leftJoin(user.questions).fetchJoin()
                .leftJoin(user.questionStores).fetchJoin()
                .where(user.id.eq(id))
                .fetchOne());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(factory
                .selectFrom(user)
                .leftJoin(user.questions).fetchJoin()
                .leftJoin(user.questionStores).fetchJoin()
                .where(user.email.value.eq(email))
                .fetchOne()
        );
    }
}
