package example.gradtest.user.userrepository;

import example.gradtest.user.userprofile.GuideProfile;

public interface GuideProfileRepository {

    GuideProfile save(GuideProfile guideProfile);

    void changeLanguageLevel(String userId, String languageLevel);

    void changeAddress(String userId, String address);

    void changeInstagramId(String userId, String instagramId);

    void setStar(String userId, int star);
}
