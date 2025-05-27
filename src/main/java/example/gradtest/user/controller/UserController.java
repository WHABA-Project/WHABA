package example.gradtest.user.controller;

import example.gradtest.user.User;
import example.gradtest.user.userform.*;
import example.gradtest.user.userprofile.GuideProfile;
import example.gradtest.user.userprofile.TravelerProfile;
import example.gradtest.user.userrepository.GuideProfileRepository;
import example.gradtest.user.userrepository.TravelerProfileRepository;
import example.gradtest.user.userrepository.UserRepository;
import example.gradtest.user.userservice.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final GuideProfileRepository guideProfileRepository;
    private final TravelerProfileRepository travelerProfileRepository;

    @GetMapping("/choose-role")
    public String chooseRole() {
        return "user/choose-role";
    }

    @GetMapping("/save-traveler")
    public String saveTraveler() {
        return "user/join";
    }

    @PostMapping("/save-traveler")
    public String saveUserTraveler(@Validated @ModelAttribute UserTravelerSaveForm userTravelerSaveForm, BindingResult bindingResult, HttpServletRequest request) {

        boolean isOkPassword = userService.checkPassword(userTravelerSaveForm.getPassword());
        boolean isSamePassword = userService.isSamePassword(userTravelerSaveForm.getPassword(), userTravelerSaveForm.getCheckPassword());

        if (!isOkPassword) {
            bindingResult.rejectValue("password", "checkPasswordForm");
        }

        if (!isSamePassword) {
            bindingResult.rejectValue("password", "isSamePassword");
        }

        if (bindingResult.hasErrors()) {
            return "user/join";
        }

        User saveUser = new User(userTravelerSaveForm.getUserid(), userTravelerSaveForm.getUseremail(), userTravelerSaveForm.getPassword(),
                userTravelerSaveForm.getName(), userTravelerSaveForm.getBirth(), userTravelerSaveForm.getNationality(), userTravelerSaveForm.getGender(), UserRole.Traveler);
        userRepository.save(saveUser);

        HttpSession session = request.getSession();
        session.setAttribute("user", saveUser);

        return "redirect:/save-traveler-2";
    }

    @GetMapping("/save-traveler-2")
    public String userStyle() {
        return "user/choose-style";
    }

    @PostMapping("/save-traveler-2")
    public String userStyleChoose(@ModelAttribute TravelerProfile travelerProfile, BindingResult bindingResult, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        TravelerProfile saveTravelerProfile = new TravelerProfile(travelerProfile.getTravelStyle(),
                travelerProfile.getGuideStyle(), travelerProfile.getKoreanSpeckLevel(), travelerProfile.getKoreanListenLevel(), travelerProfile.getKoreanWriteLevel());

        saveTravelerProfile.setUser(user);

        int koreanLevel = userService.koreanLevel(travelerProfile.getKoreanSpeckLevel(), travelerProfile.getKoreanWriteLevel(), travelerProfile.getKoreanListenLevel());
        saveTravelerProfile.setKoreanLevel(koreanLevel);

        travelerProfileRepository.save(saveTravelerProfile);
        return "redirect:/home";
    }

    @GetMapping("/save-guide")
    public String saveGuide() {
        return "user/join";
    }

    @PostMapping("/save-guide")
    public String saveUserGuide(@Validated @ModelAttribute UserGuideSaveForm userGuideSaveForm, BindingResult bindingResult, Model model) {

        boolean isOkPassword = userService.checkPassword(userGuideSaveForm.getPassword());
        boolean isSamePassword = userService.isSamePassword(userGuideSaveForm.getPassword(), userGuideSaveForm.getCheckPassword());

        if (!isOkPassword) {
            bindingResult.rejectValue("password", "checkPasswordForm");
        }

        if (!isSamePassword) {
            bindingResult.rejectValue("password", "isSamePassword");
        }

        if (bindingResult.hasErrors()) {
            return "user/join";
        }

        User saveUser = new User(userGuideSaveForm.getUserid(), userGuideSaveForm.getUseremail(), userGuideSaveForm.getPassword(),
                userGuideSaveForm.getName(), userGuideSaveForm.getBirth(), userGuideSaveForm.getNationality(), userGuideSaveForm.getGender(), UserRole.Guide);
        userRepository.save(saveUser);

        GuideProfile guideProfile = new GuideProfile(userGuideSaveForm.getEnglishLevel());
        guideProfile.setUser(saveUser);
        guideProfileRepository.save(guideProfile);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("user") UserLoginForm userLoginForm, BindingResult bindingResult, HttpServletRequest request) {
        User loginUser = userService.loginCheck(userLoginForm.getUserid(), userLoginForm.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginError");
        }

        if (bindingResult.hasErrors()) {
            return "user/login";
        }

        loginUser.setLoginTime(LocalDateTime.now());
        HttpSession session = request.getSession();
        session.setAttribute("userId", loginUser.getUserid());
        return "redirect:/home";
    }

    @GetMapping("/login/id-find")
    public String idFind() {
        return "user/id-find";
    }

    @PostMapping("/login/id-find")
    public String idFindUser(@Validated @ModelAttribute UserIdFindForm userIdFindForm, BindingResult bindingResult, Model model) {
        String userId = userRepository.findUserId(userIdFindForm.getName(), userIdFindForm.getEmail());

        if (userId == null) {
            bindingResult.reject("wrongUser", "이메일과 이름에 일치하는 계정이 없습니다.");
        }

        model.addAttribute("userId", userId);
        return "user/id-find-final";
    }

    @GetMapping("/login/forget-password")
    public String forgetPassword() {
        return "user/forget-password-form";
    }

    @PostMapping("/login/forget-password")
    public String userForgetPassword(@Validated @ModelAttribute UserCheckPwChangeForm userCheckPwChangeForm, BindingResult bindingResult,
                                     HttpServletRequest request, Model model) {
        String userId = userRepository.findUserId(userCheckPwChangeForm.getName(), userCheckPwChangeForm.getEmail());
        HttpSession session = request.getSession();
        Boolean isMatch = (Boolean) session.getAttribute("isMatch");

        if (isMatch == null || !isMatch) {
            bindingResult.reject("noMatch", "인증이 잘못되었습니다.");
        }

        if (userId == null) {
            bindingResult.reject("wrongUser", "이메일과 이름에 일치하는 계정이 없습니다.");
        }

        if (bindingResult.hasErrors()) {
            return "user/forget-password-form";
        }

        model.addAttribute("userPwChangeForm", userCheckPwChangeForm); // 기존 데이터 유지
        session.setAttribute("userId", userId);
        return "redirect:/login/change-password";
    }

    @GetMapping("/login/change-password")
    public String changePassword() {
        return "user/change-password-form";
    }

    @PostMapping("/login/change-password")
    public String userChangePassword(@Validated @ModelAttribute UserChangePwForm userChangePwForm, BindingResult bindingResult, HttpServletRequest request) {

        boolean isOkPassword = userService.checkPassword(userChangePwForm.getPassword());
        boolean isSamePassword = userService.isSamePassword(userChangePwForm.getPassword(), userChangePwForm.getCheckPassword());
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        User findUser = userRepository.findByUserId(userId);

        if (!isOkPassword) {
            bindingResult.rejectValue("password", "checkPasswordForm");
        }

        if (!isSamePassword) {
            bindingResult.rejectValue("password", "isSamePassword");
        }

        if (bindingResult.hasErrors()) {
            return "user/change-password-form";
        }

        userRepository.ChangePassword(findUser, userChangePwForm.getPassword());
        return "redirect:/home";
    }
}
