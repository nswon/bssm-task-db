package taskdb.taskdb.domain.notification.entity;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "NOTIFICATION")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "notification", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private User user;

    private String token;

    protected Notification() {
    }

    @Builder
    public Notification(String token) {
        this.token = token;
    }

    public void confirmUser(User user) {
        this.user = user;
        user.addNotification(this);
    }
}
