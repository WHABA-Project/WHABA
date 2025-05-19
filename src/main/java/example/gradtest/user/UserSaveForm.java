package example.gradtest.user;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private LocalDateTime createDate;
}
