package backend.taskmanagementrestapi.Dto;

import backend.taskmanagementrestapi.Constant.TaskStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaskRequest {

    @NotBlank(message = "*TITLE FIELD IS REQUIRED*")
    private String title;

    private String description;
    private TaskStatus status = TaskStatus.TODO;

}
