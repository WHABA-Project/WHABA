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
    private String id;

    @OneToOne
    @JoinColumn(name = "userId")
    @MapsId
    private User user;

    @Column
    private String travelStyle;

    @Column
    private String GuideStyle;

    @Column
    private int koreanSpeckLevel;

    @Column
    private int koreanListenLevel;

    @Column
    private int koreanWriteLevel;

    public TravelerProfile() {} // JPA 기본 생성자

    public TravelerProfile(String id, User user, String travelStyle, String guideStyle, int koreanSpeckLevel, int koreanListenLevel, int koreanWriteLevel) {
        this.id = id;
        this.user = user;
        this.travelStyle = travelStyle;
        GuideStyle = guideStyle;
        this.koreanSpeckLevel = koreanSpeckLevel;
        this.koreanListenLevel = koreanListenLevel;
        this.koreanWriteLevel = koreanWriteLevel;
    }
}
