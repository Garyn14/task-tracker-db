package roadmap.tast_tracker.cli.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import roadmap.tast_tracker.cli.enums.TaskStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class Task {
    private Long id;
    private static Long nextId = 0L;
    private String description;
    private TaskStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    public Task(String description) {
        this.id = ++nextId;
        this.description = description;
        this.status = TaskStatus.TO_DO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "\nTask{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt.format(formatter) +
                ", updatedAt=" + updatedAt.format(formatter) +
                "}";
    }
}
