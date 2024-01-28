package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}