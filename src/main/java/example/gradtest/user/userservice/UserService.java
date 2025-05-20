package example.gradtest.user.userservice;

import example.gradtest.user.User;
import example.gradtest.user.userrepository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JpaUserRepository userRepository;

    public User loginCheck(String userId, String password) {
        User findUser = userRepository.findByUserId(userId);

        if (findUser.getPassword().equals(password)) {
            return findUser;
        } else {
            return null;
        }
    }

    public boolean checkPassword(String password) {
        if (!password.matches("[a-zA-Z0-9!@#$%^&*()+=\\-_<>,.?/]*")) {
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()+=\\-_<>,.?/].*")) {
            return false;
        }

        if (password.length() < 10) {
            return false;
        }

        return true;
    }

    public boolean isSamePassword(String password, String checkPassword) {
        if (password.equals(checkPassword)) {
            return true;
        }
        return false;
    }
}
