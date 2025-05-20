package example.gradtest.user.userform;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePwForm {

    @NotBlank
    private String password;

    @NotBlank
    private String checkPassword;
}
