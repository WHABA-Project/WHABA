package example.gradtest.guideIntro.controller;

import example.gradtest.guideIntro.service.GuideIntroService;
import example.gradtest.user.repository.GuideProfileRepository;
import example.gradtest.user.repository.UserRepository;
import example.gradtest.user.userprofile.GuideProfile;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuideIntroController {

    private final GuideProfileRepository guideProfileRepository;
    private final UserRepository userRepository;
    private final GuideIntroService guideIntroService;

    @GetMapping("/guide-introduce")
    public String guideIntroduce(HttpServletRequest request, Model model) {
        List<GuideProfile> allGuideIntro = guideProfileRepository.findAll();
        model.addAttribute("guideIntroList", allGuideIntro);
        return "guide/guideList";
    }
}
