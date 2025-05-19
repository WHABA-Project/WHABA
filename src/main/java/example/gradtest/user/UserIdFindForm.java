package example.gradtest.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdFindForm {

    @NotBlank
    private String name;

    @NotBlank
    private String email;
}
