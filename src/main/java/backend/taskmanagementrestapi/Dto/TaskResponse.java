package backend.taskmanagementrestapi.Dto;

import backend.taskmanagementrestapi.Constant.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@ToString
public class TaskResponse {

    private Long id;
    @NotBlank(message = "*TITLE FIELD IS REQUIRED*")
    private String title;
    private String description;
    private TaskStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm a")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm a")
    private LocalDateTime updatedAt;

}
