package example.gradtest.user.userprofile;

import example.gradtest.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "traveler_profile")
@Getter
@Setter
public class TravelerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private String travelStyle;

    @Column
    private String GuideStyle;

    @Column
    private String instagramId;

    @Column
    private int koreanSpeckLevel;

    @Column
    private int koreanListenLevel;

    @Column
    private int koreanWriteLevel;

    @Column
    private int KoreanLevel;

    public TravelerProfile() {} // JPA 기본 생성자

    public TravelerProfile(String travelStyle, String guideStyle, int koreanSpeckLevel, int koreanListenLevel, int koreanWriteLevel) {
        this.travelStyle = travelStyle;
        this.GuideStyle = guideStyle;
        this.instagramId = "인스타그램 아이디를 작성해주세요";
        this.koreanSpeckLevel = koreanSpeckLevel;
        this.koreanListenLevel = koreanListenLevel;
        this.koreanWriteLevel = koreanWriteLevel;
    }
}
