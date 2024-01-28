package hexlet.code.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;
}
