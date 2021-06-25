package finger.edu.habicastle.feature.domain;

import finger.edu.habicastle.core.utils.BaseTimeEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userKey;

    @Column(length = 50, unique = true, nullable = false)
    private String userId;

    @Column(length = 100, nullable = false)
    private String userPassword;

    @Column(length = 50, nullable = false)
    private String userName;

    public void setUserPassword(String password) { this.userPassword = password; }

    public void setUserName(String name) { this.userName = name; }

    @Builder
    public User(String userId, String userPassword, String userName) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
    }
}
