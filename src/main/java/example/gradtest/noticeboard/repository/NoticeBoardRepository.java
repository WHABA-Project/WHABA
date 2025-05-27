package example.gradtest.noticeboard.repository;

import example.gradtest.noticeboard.NoticeBoard;
import example.gradtest.noticeboard.noticeboardform.EditNoticeBoardForm;

import java.util.List;

public interface NoticeBoardRepository {

    NoticeBoard save(NoticeBoard noticeBoard);

    NoticeBoard findById(Long id);

    List<NoticeBoard> findByUserId(String userId);

    void deleteById(Long id);

    NoticeBoard edit(Long noticeBoardId, EditNoticeBoardForm editNoticeBoardForm);

    List<NoticeBoard> findAll();

    List<NoticeBoard> arrayNoticeList(String region, String travelData, String finishTravelDate, String order);
}
