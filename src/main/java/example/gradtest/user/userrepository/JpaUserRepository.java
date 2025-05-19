package example.gradtest.user.userrepository;

import example.gradtest.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User findByUserId(String userId) {
        User findUser = em.find(User.class, userId);
        return findUser;
    }

    @Override
    public List<User> findAll() {
        String jpql = "select u from User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }

    @Override
    public String findUserId(String name, String email) {
        String jpql = "select u from User u Where u.name= :name and u.email= :email";

        try {
            User findUser = em.createQuery(jpql, User.class)
                    .setParameter("name", name)
                    .setParameter("email", email)
                    .getSingleResult();
            return findUser.getUserid();
        } catch (NoResultException e) {
            return null;
        }

    }
}
