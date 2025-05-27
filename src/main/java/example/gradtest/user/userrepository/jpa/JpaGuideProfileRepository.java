package example.gradtest.user.userrepository.jpa;

import example.gradtest.user.userprofile.GuideProfile;
import example.gradtest.user.userrepository.GuideProfileRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


}
