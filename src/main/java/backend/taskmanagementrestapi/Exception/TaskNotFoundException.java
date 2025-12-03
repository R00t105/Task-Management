package backend.taskmanagementrestapi.Exception;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException(Long id) {
        super("Task not found with ID: " + id);
    }
}
