package roadmap.tast_tracker.cli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import roadmap.tast_tracker.cli.enums.TaskStatus;
import roadmap.tast_tracker.cli.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(TaskStatus status);
}
