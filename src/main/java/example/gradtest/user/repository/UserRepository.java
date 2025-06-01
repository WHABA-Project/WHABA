package example.gradtest.user.repository;

import example.gradtest.user.User;

import java.util.List;

public interface UserRepository {

    public User save(User user);

    public User findByUserId(String userId);

    public List<User> findAll();

    public String findUserId(String name, String email);

    public void ChangePassword(User user, String password);
}
