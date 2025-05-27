package example.gradtest.Interceptor;

import example.gradtest.user.User;
import example.gradtest.user.userform.UserRole;
import example.gradtest.user.userrepository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class GuideInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        String userId = (String) session.getAttribute("userId");

        User findUser = userRepository.findByUserId(userId);
        if (findUser.getUserRole() == UserRole.Traveler) {
            return false;
        }

        return true;
    }
}
