package example.gradtest.user.userrepository.jpa;

import example.gradtest.user.userprofile.TravelerProfile;
import example.gradtest.user.userrepository.TravelerProfileRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaTravelerProfileRepository implements TravelerProfileRepository {

    private final EntityManager em;

    @Override
    public TravelerProfile save(TravelerProfile travelerProfile) {
        em.persist(travelerProfile);
        return travelerProfile;
    }
}
