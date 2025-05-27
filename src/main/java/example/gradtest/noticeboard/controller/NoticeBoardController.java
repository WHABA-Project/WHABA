package example.gradtest.noticeboard.controller;

import example.gradtest.noticeboard.NoticeBoard;
import example.gradtest.noticeboard.noticeboardform.EditNoticeBoardForm;
import example.gradtest.noticeboard.repository.NoticeBoardRepository;
import example.gradtest.user.User;
import example.gradtest.user.userform.UserRole;
import example.gradtest.user.userprofile.GuideProfile;
import example.gradtest.user.userprofile.TravelerProfile;
import example.gradtest.user.userrepository.GuideProfileRepository;
import example.gradtest.user.userrepository.TravelerProfileRepository;
import example.gradtest.user.userrepository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notice-board")
public class NoticeBoardController {

    private final NoticeBoardRepository noticeBoardRepository;
    private final UserRepository userRepository;
    private final GuideProfileRepository guideProfileRepository;
    private final TravelerProfileRepository travelerProfileRepository;

    @GetMapping
    public String noticeBoardMain(Model model, HttpServletRequest request) {
        List<NoticeBoard> noticeList = noticeBoardRepository.findAll();
        model.addAttribute("noticeList", noticeList);
        return "notice/main";
    }

    @GetMapping("/save")
    public String noticeSaveForm() {
        return "notice/save";
    }

    @PostMapping("/save")
    public String noticeSave(@Validated @ModelAttribute NoticeBoard noticeBoard, BindingResult bindingResult, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");

        TravelerProfile findUserProfile = travelerProfileRepository.findByUserId(userId);

        if (findUserProfile.getInstagramId() == null) {
            bindingResult.reject("instagramId", "인스타그램 아이디를 먼저 지정해주세요!");
        }

        NoticeBoard saveNoticeBoard1 = new NoticeBoard(noticeBoard.getTitle(), noticeBoard.getDetail(), findUserProfile.getInstagramId(), findUserProfile.getKoreanLevel());
        NoticeBoard savenoticeBoard = noticeBoardRepository.save(saveNoticeBoard1);
        model.addAttribute("noticeBoard", savenoticeBoard);

        return "redirect:/my-page/notice-board";
    }

    @GetMapping("/{noticeBoardId}")
    public String notice(@PathVariable Long noticeBoardId, Model model) {
        NoticeBoard findNotice = noticeBoardRepository.findById(noticeBoardId);
        model.addAttribute("notice", findNotice);
        return "notice/noticePage";
    }

    @GetMapping("/{noticeBoardId}/edit")
    public String noticeEditForm(@PathVariable Long noticeBoardId, Model model) {
        NoticeBoard findNotice = noticeBoardRepository.findById(noticeBoardId);
        model.addAttribute("notice", findNotice);
        return "notice/edit-form";
    }

    @PostMapping("/{noticeBoardId}/edit")
    public String noticeEdit(@PathVariable Long noticeBoardId, @ModelAttribute EditNoticeBoardForm editNoticeBoardForm) {
        noticeBoardRepository.edit(noticeBoardId, editNoticeBoardForm);
        return "redirect:/notice/" + noticeBoardId;
    }

    // 매칭 버튼 기능 만들어야함
}
