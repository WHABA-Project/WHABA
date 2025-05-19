package example.gradtest.user.userform;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginForm {

    @NotBlank
    private String userid;

    @NotBlank
    private String password;
}
