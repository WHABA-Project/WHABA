package example.gradtest.user.repository;

import example.gradtest.user.userprofile.GuideProfile;

import java.util.List;

public interface GuideProfileRepository {

    GuideProfile save(GuideProfile guideProfile);

    void changeLanguageLevel(String userId, String languageLevel);

    void changeAddress(String userId, String address);

    void changeInstagramId(String userId, String instagramId);

    void setStar(String userId, int star);

    GuideProfile findByUserId(String userId);

    List<GuideProfile> findAll();
}
