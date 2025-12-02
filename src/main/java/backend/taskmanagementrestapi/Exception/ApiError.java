package backend.taskmanagementrestapi.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String message;
    private int status;
    private Map<String, Object> errors = new HashMap<>();

}
