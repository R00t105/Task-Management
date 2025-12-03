package backend.taskmanagementrestapi.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLogin {

    @NotBlank(message = "EMAIL FIELD IS REQUIRED")
    @Email(message = "ENTER A VALID EMAIL")
    private String email;

    @NotBlank(message = "PASSWORD FIELD IS REQUIRED")
    private String password;

}
