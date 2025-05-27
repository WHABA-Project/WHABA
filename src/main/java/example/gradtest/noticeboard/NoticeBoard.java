package example.gradtest.noticeboard;

import example.gradtest.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "notice_board")
@Getter
@Setter
public class NoticeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeBoardId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @NotBlank
    private String title;

    @Column
    @NotBlank
    private String detail;

    @Column
    private int koreanLevel;

    @Column
    private String instagramId;

    @Column
    private String writingDate;

    @Column
    private String region;

    @Column
    private String travelDate;

    @Column
    private String finishTravelDate;

    public NoticeBoard() {
    }

    public NoticeBoard(String title, String detail, String instagramId, int koreanLevel, String region, String travelDate, String finishTravelDate) {
        this.title = title;
        this.detail = detail;
        this.instagramId = instagramId;
        this.koreanLevel = koreanLevel;
        this.region = region;
        this.travelDate = travelDate;
        this.finishTravelDate = finishTravelDate;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.writingDate = LocalDateTime.now().format(dateTimeFormatter);
    }
}
