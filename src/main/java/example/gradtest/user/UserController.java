package example.gradtest.user;

import example.gradtest.user.userrepository.UserRepository;
import example.gradtest.user.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/save")
    public String save() {
        return "user/join";
    }

    @PostMapping("/save")
    public String saveUser(@Validated @ModelAttribute UserSaveForm userSaveForm, BindingResult bindingResult, Model model) {

        boolean isOkPassword = userService.checkPassword(userSaveForm.getPassword());
        boolean isSamePassword = userService.isSamePassword(userSaveForm.getPassword(), userSaveForm.getCheckPassword());

        if (!isOkPassword) {
            bindingResult.rejectValue("password", "checkPasswordForm");
        }

        if (!isSamePassword) {
            bindingResult.rejectValue("password", "isSamePassword");
        }

        if (bindingResult.hasErrors()) {
            return "user/join";
        }

        User saveUser = new User(userSaveForm.getUserid(), userSaveForm.getUseremail(), userSaveForm.getPassword(),
                userSaveForm.getName(), userSaveForm.getCreateDate());
        userRepository.save(saveUser);

        return "redirect:/login";
    }

    @GetMapping("/login/id-find")
    public String idFind() {
        return "user/id-find";
    }

    @PostMapping("/login/id-find")
    public String idFindUser(@Validated @ModelAttribute UserIdFindForm userIdFindForm, BindingResult bindingResult, Model model) {
        String userId = userRepository.findUserId(userIdFindForm.getName(), userIdFindForm.getEmail());
        model.addAttribute("userId", userId);

        return "user/id-find-final";
    }
}
