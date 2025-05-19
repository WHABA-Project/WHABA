package example.gradtest.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "userid", length = 50)
    private String userid;    // PK

    @Column(name = "useremail", length = 255, unique = true, nullable = false)
    private String useremail; // DB 컬럼이 useremail 이므로

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name= "birth")
    private String birth;

    @Column
    private String nationality;

    @Column
    private String gender;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "login_time")
    private LocalDateTime loginTime;

    public User(String userid, String useremail, String password, String name, LocalDateTime createDate) {
        this.userid = userid;
        this.useremail = useremail;
        this.password = password;
        this.name = name;
        this.createDate = LocalDateTime.now();
    }
}
