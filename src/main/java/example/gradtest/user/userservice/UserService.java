package example.gradtest.user.userservice;

import org.springframework.stereotype.Service;

@Service
public class UserService {

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
