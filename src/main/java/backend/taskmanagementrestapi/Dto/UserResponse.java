package backend.taskmanagementrestapi.Dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResponse {
    private String name;
    private String email;
}
