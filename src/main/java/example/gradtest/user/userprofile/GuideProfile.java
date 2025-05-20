package example.gradtest.user.userprofile;

import example.gradtest.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "guide_profiles")
@Getter
@Setter
public class GuideProfile {

    @Id
    private String id; // 보통 user_id와 동일한 값으로 설정

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @MapsId // user_id = GuideProfile의 id
    private User user;

    @Column(name = "language_level")
    private String languageLevel;

    @Column
    private String address;

    @Column(name = "instagram_id")
    private String instagramId;

    @Column(name = "star")
    private int star;

    // 기본 생성자
    public GuideProfile() {}

    public GuideProfile(String id, User user, String languageLevel) {
        this.id = id;
        this.user = user;
        this.languageLevel = languageLevel;
    }
}
