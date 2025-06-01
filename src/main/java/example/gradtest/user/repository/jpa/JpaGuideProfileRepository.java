package example.gradtest.user.repository.jpa;

import example.gradtest.user.userprofile.GuideProfile;
import example.gradtest.user.repository.GuideProfileRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaGuideProfileRepository implements GuideProfileRepository {

    private final EntityManager em;

    @Override
    public GuideProfile save(GuideProfile guideProfile) {
        em.persist(guideProfile);
        return guideProfile;
    }

    @Override
    public void changeLanguageLevel(String userId, String languageLevel) {
        GuideProfile findGuideProfile = em.find(GuideProfile.class, userId);
        findGuideProfile.setLanguageLevel(languageLevel);
    }

    @Override
    public void changeAddress(String userId, String address) {
        GuideProfile findGuideProfile = em.find(GuideProfile.class, userId);
        findGuideProfile.setAddress(address);
    }

    @Override
    public void changeInstagramId(String userId, String instagramId) {
        GuideProfile findGuideProfile = em.find(GuideProfile.class, userId);
        findGuideProfile.setInstagramId(instagramId);
    }

    @Override
    public void setStar(String userId, int star) {
        GuideProfile findGuideProfile = em.find(GuideProfile.class, userId);
        findGuideProfile.setStar(star);
    }

    @Override
    public GuideProfile findByUserId(String userId) {
        GuideProfile findGuideProfile = em.find(GuideProfile.class, userId);
        return findGuideProfile;
    }

    @Override
    public List<GuideProfile> findAll() {
        String jpql = "select g from guideProfile";
        List resultList = em.createQuery(jpql).getResultList();
        return resultList;
    }


}
