package example;

import example.gradtest.Interceptor.GuideInterceptor;
import example.gradtest.Interceptor.LoginInterceptor;
import example.gradtest.Interceptor.TravelerInterceptor;
import example.gradtest.user.userrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final UserRepository userRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/*", "/error-page/**");

        registry.addInterceptor(new TravelerInterceptor(userRepository))
                .order(2)
                .addPathPatterns("/notice/save");

        registry.addInterceptor(new GuideInterceptor(userRepository))
                .order(2)
                .addPathPatterns("");
    }
}
