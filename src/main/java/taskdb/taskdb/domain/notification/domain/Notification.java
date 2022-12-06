package taskdb.taskdb.domain.notification.domain;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "NOTIFICATION")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String token;

    protected Notification() {
    }

    @Builder
    public Notification(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
