package backend.taskmanagementrestapi.Controller;

import backend.taskmanagementrestapi.Constant.OrderDirection;
import backend.taskmanagementrestapi.Dto.TaskRequest;
import backend.taskmanagementrestapi.Dto.TaskResponse;
import backend.taskmanagementrestapi.Service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "APIs for managing tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Returns paginated list of tasks")
    public ResponseEntity<?> getAllTasks(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(value = "direction", defaultValue = "desc") OrderDirection direction
            ) {
        Sort sort = direction == OrderDirection.desc ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Task By ID", description = "Returns Task Info By Its ID" )
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
    }

    @PostMapping
    @Operation(summary = "Add Task", description = "Add Task & returns its Info" )
    public ResponseEntity<?> addTask(@Valid @RequestBody TaskRequest task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(task));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edit Task", description = "Edit Task & returns the New Task Updated" )
    public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest task) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTaskById(id, task));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Task", description = "Returns No Content after Delete the task by ID" )
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
    }

    @GetMapping("/search")
    @Operation(summary = "Search Task", description = "Returns List Of Tasks that contain a keyword user provided" )
    public ResponseEntity<?> getTasksByName(@RequestParam(name = "name", defaultValue = "") String name) {
        List<TaskResponse> tasks = taskService.searchTasks(name);
        return tasks.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Items") :  ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

}
