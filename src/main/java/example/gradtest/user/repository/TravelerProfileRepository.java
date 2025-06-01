package example.gradtest.user.repository;

import example.gradtest.user.userprofile.TravelerProfile;

public interface TravelerProfileRepository {

    TravelerProfile save(TravelerProfile travelerProfile);

    TravelerProfile findByUserId(String userId);
}
