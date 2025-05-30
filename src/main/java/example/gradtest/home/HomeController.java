package example.gradtest.home;

import example.gradtest.noticeboard.NoticeBoard;
import example.gradtest.noticeboard.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NoticeBoardRepository noticeBoardRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/search")
    public String search(@RequestParam String searchWord, Model model) {
        List<NoticeBoard> noticeList = noticeBoardRepository.searchByKeyword(searchWord);
        model.addAttribute("noticeList", noticeList);

        return "notice/main";
    }
}
