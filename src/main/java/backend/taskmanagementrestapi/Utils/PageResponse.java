package backend.taskmanagementrestapi.Utils;

import backend.taskmanagementrestapi.Dto.TaskResponse;
import java.util.List;

public record PageResponse<T>(
        List<T> content,
        Meta meta,
        Links links
) {

    public record Meta(
            int page,
            int size,
            long totalElements,
            int totalPages,
            boolean hasNext,
            boolean hasPrevious
    ){}

    public record Links(
            String next,
            String previous
    ){}

}
