package example.gradtest.user.userform;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCheckPwChangeForm {

    @NotBlank
    private String name;

    @NotBlank
    private String email;
}
