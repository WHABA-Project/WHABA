package example.gradtest.user.userprofile;

import example.gradtest.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "guide_profile")
@Getter
@Setter
public class GuideProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userid")
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

    public GuideProfile(String languageLevel) {
        this.languageLevel = languageLevel;
        this.address = "주소를 작성해주세요.";
        this.instagramId = "인스타그램 아이디를 작성해주세요.";
        this.star = 0;
    }
}
