package example.gradtest.user;

import example.gradtest.user.userform.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="userTraveler")
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
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column
    private String gender;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "login_time")
    private LocalDateTime loginTime;

    public User() {
    }

    public User(String userid, String useremail, String password, String name, String birth, String nationality, String gender, UserRole userRole) {
        this.userid = userid;
        this.useremail = useremail;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.nationality = nationality;
        this.gender = gender;
        this.userRole = userRole;
        this.createDate = LocalDateTime.now();
    }
}
