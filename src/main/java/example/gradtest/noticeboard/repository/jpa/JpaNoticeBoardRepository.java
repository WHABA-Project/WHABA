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

    @Override
    public List<NoticeBoard> arrayNoticeList(String region, String travelData, String finishTravelDate, String order) {
        StringBuilder jpql = new StringBuilder("SELECT n FROM NoticeBoard n");
        boolean hasCondition = false;

        if (region != null || travelData != null || finishTravelDate != null) {
            jpql.append(" WHERE ");
            if (region != null) {
                jpql.append("n.region = :region");
                hasCondition = true;
            }
            if (travelData != null) {
                if (hasCondition) jpql.append(" AND ");
                jpql.append("n.travelDate = :travelDate");
                hasCondition = true;
            }
            if (finishTravelDate != null) {
                if (hasCondition) jpql.append(" AND ");
                jpql.append("n.finishTravelDate = :finishTravelDate");
            }
        }

        if (order != null && !order.isBlank()) {
            jpql.append(" ORDER BY n.").append(order); // 사용자가 전달한 order 값은 신뢰할 수 있는 값이어야 함!
        }

        TypedQuery<NoticeBoard> query = em.createQuery(jpql.toString(), NoticeBoard.class);

        if (region != null) query.setParameter("region", region);
        if (travelData != null) query.setParameter("travelDate", travelData);
        if (finishTravelDate != null) query.setParameter("finishTravelDate", finishTravelDate);

        return query.getResultList();

    }

    @Override
    public List<NoticeBoard> searchByKeyword(String searchWord) {
        String jpql = "select n from NoticeBoard n where n.title like :searchWord";
        List<NoticeBoard> resultList = em.createQuery(jpql, NoticeBoard.class)
                .setParameter("searchWord", searchWord)
                .getResultList();
        return resultList;
    }
}
