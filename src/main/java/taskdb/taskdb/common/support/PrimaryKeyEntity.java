package taskdb.taskdb.common.support;

import com.github.f4b6a3.ulid.UlidCreator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
abstract public class PrimaryKeyEntity implements Persistable<UUID> {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @Transient
    private boolean isNew = true;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PostPersist
    public void load() {
        isNew = false;
    }
}
