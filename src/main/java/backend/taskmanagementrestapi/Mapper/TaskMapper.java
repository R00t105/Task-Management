package backend.taskmanagementrestapi.Mapper;

import backend.taskmanagementrestapi.Dto.TaskRequest;
import backend.taskmanagementrestapi.Dto.TaskResponse;
import backend.taskmanagementrestapi.Entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    Task toTask(TaskRequest taskRequest);
    TaskRequest toTaskRequest(Task task);

    TaskResponse toTaskResponse(Task task);

}
