package example.gradtest.noticeboard.repository.jpa;

import example.gradtest.noticeboard.NoticeBoard;
import example.gradtest.noticeboard.noticeboardform.EditNoticeBoardForm;
import example.gradtest.noticeboard.repository.NoticeBoardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaNoticeBoardRepository implements NoticeBoardRepository {

    private final EntityManager em;

    @Override
    public NoticeBoard save(NoticeBoard noticeBoard) {
        em.persist(noticeBoard);
        return noticeBoard;
    }

    @Override
    public NoticeBoard findById(Long id) {
        NoticeBoard findNoticeBoard = em.find(NoticeBoard.class, id);
        return findNoticeBoard;
    }

    @Override
    public List<NoticeBoard> findByUserId(String userId) {
        String jpql = "select n from NoticeBoard n where n.user.userid = :userid order by n.noticeBoardId desc";
        List<NoticeBoard> noticeBoardList = em.createQuery(jpql, NoticeBoard.class).setParameter("userid", userId).getResultList();
        return noticeBoardList;
    }

    @Override
    public void deleteById(Long id) {
        NoticeBoard findNoticeBoard = em.find(NoticeBoard.class, id);

        if (findNoticeBoard != null) {
            em.remove(findNoticeBoard);
        }
    }

    @Override
    public NoticeBoard edit(Long noticeBoardId, EditNoticeBoardForm editNoticeBoardForm) {
        NoticeBoard findNoticeBoard = em.find(NoticeBoard.class, noticeBoardId);
        findNoticeBoard.setDetail(editNoticeBoardForm.getEditDetail());
        return findNoticeBoard;
    }

    @Override
    public List<NoticeBoard> findAll() {
        String jpql = "select n from NoticeBoard n";
        List<NoticeBoard> resultList = em.createQuery(jpql, NoticeBoard.class).getResultList();
        return resultList;
    }
}
