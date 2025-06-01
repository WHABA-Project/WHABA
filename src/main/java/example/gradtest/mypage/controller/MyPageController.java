package example.gradtest.mypage.controller;

import example.gradtest.noticeboard.NoticeBoard;
import example.gradtest.noticeboard.repository.NoticeBoardRepository;
import example.gradtest.user.User;
import example.gradtest.user.userform.UserRole;
import example.gradtest.user.userprofile.GuideProfile;
import example.gradtest.user.userprofile.TravelerProfile;
import example.gradtest.user.repository.GuideProfileRepository;
import example.gradtest.user.repository.TravelerProfileRepository;
import example.gradtest.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class MyPageController {

    private final GuideProfileRepository guideProfileRepository;
    private final TravelerProfileRepository travelerProfileRepository;
    private final UserRepository userRepository;
    private final NoticeBoardRepository noticeBoardRepository;

    @GetMapping
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");

        User findUser = userRepository.findByUserId(userId);

        if (findUser.getUserRole() == UserRole.Guide) {
            GuideProfile findGuideProfile = guideProfileRepository.findByUserId(userId);
            model.addAttribute("guideProfile", findGuideProfile);
        } else if (findUser.getUserRole() == UserRole.Traveler) {
            TravelerProfile findTravelerProfile = travelerProfileRepository.findByUserId(userId);
            model.addAttribute("travelerProfile", findTravelerProfile);
        }

        return "my-page/main";
    }

    @GetMapping("/my-notice-board")
    public String myNoticeBoard(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");

        List<NoticeBoard> noticeList = noticeBoardRepository.findByUserId(userId);
        model.addAttribute("noticeList", noticeList);

        return "my-page/notice-board";
    }

    @GetMapping("/my-review")
    public String myReview(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");

        return "";
    }

    // 수정하는 페이지 만들어줘야함
}
