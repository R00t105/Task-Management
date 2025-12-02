package backend.taskmanagementrestapi.Repository;

import backend.taskmanagementrestapi.Entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAll(Pageable pageable);
    List<Task> findAllByTitleContainingIgnoreCase(String title);
}
