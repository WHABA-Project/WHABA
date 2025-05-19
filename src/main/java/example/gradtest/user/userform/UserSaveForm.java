package example.gradtest.user.userform;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserSaveForm {

    @NotBlank
    private String userid;

    @Email
    @NotBlank
    private String useremail;

    @NotBlank
    private String password;

    @NotBlank
    private String checkPassword;

    @NotBlank
    private String name;

    @NotBlank
    private String birth;

    @NotBlank
    private String nationality;

    @NotBlank
    private String gender;

    @NotBlank
    private LocalDateTime createDate;
}
