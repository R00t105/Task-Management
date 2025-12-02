package backend.taskmanagementrestapi.Service;

import backend.taskmanagementrestapi.Dto.TaskRequest;
import backend.taskmanagementrestapi.Dto.TaskResponse;
import backend.taskmanagementrestapi.Entity.Task;
import backend.taskmanagementrestapi.Exception.TaskNotFoundException;
import backend.taskmanagementrestapi.Mapper.TaskMapper;
import backend.taskmanagementrestapi.Repository.TaskRepository;
import backend.taskmanagementrestapi.Utils.PageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public PageResponse<TaskResponse> getAllTasks(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        List<TaskResponse> content = tasks.getContent().stream().map(taskMapper::toTaskResponse).toList();
        PageResponse.Meta meta = new PageResponse.Meta(
                tasks.getNumber(),
                tasks.getSize(),
                tasks.getTotalElements(),
                tasks.getTotalPages(),
                tasks.hasNext(),
                tasks.hasPrevious()
        );
        PageResponse.Links links = new PageResponse.Links(
                tasks.hasNext() ? buildPageLink(pageable.getPageNumber() + 1, pageable.getPageSize(), pageable.getSort()) : null,
                tasks.hasPrevious() ? buildPageLink(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort()) : null
        );
            return new PageResponse<>(content, meta, links);
    }

    public String buildPageLink(int page, int size, Sort sortBy){
        String sortText = sortBy
                .stream()
                .findFirst()
                .map(order -> "&orderBy=" + order.getProperty() + "&direction=" + order.getDirection().name().toLowerCase())
                .orElse("");
        return "http://localhost:8080/tasks?page=" + page + "&size=" + size + sortText;
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException( id));
        return taskMapper.toTaskResponse(task);
    }

    @Transactional
    public TaskResponse addTask(TaskRequest taskRequest) {
        return taskMapper.toTaskResponse(taskRepository.save(taskMapper.toTask(taskRequest)));
    }

    @Transactional
    public TaskResponse updateTaskById(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException( id));
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        if(taskRequest.getStatus() != null)
            task.setStatus(taskRequest.getStatus());

        return taskMapper.toTaskResponse(taskRepository.save(task));
    }

    @Transactional
    public void deleteTaskById(Long id) {
        if (taskRepository.findById(id).isPresent())
            taskRepository.deleteById(id);

        else
            throw new TaskNotFoundException( id);

    }

    public List<TaskResponse> searchTasks(String keyword) {
        List<Task> tasks = taskRepository.findAllByTitleContainingIgnoreCase(keyword);
        return  tasks.stream().map(taskMapper::toTaskResponse).toList();
    }

}
