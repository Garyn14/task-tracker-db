package roadmap.tast_tracker.cli.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roadmap.tast_tracker.cli.enums.TaskStatus;
import roadmap.tast_tracker.cli.exception.NotFoundTaskException;
import roadmap.tast_tracker.cli.model.Task;
import roadmap.tast_tracker.cli.repository.TaskRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ObjectMapper objectMapper) {
        this.taskRepository = taskRepository;
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    @Transactional
    public String addTask(String description) {
        Task task = taskRepository.save(new Task(description));
        return "Task added successfully (ID: " + task.getId() + ")";
    }

    @Override
    @Transactional
    public String updateTaskDescription(Long id, String description) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundTaskException(id));
            task.setDescription(description);
            return "Task description updated successfully";
        } catch (NotFoundTaskException e) {
            return e.getMessage();
        }
    }

    @Override
    @Transactional
    public String updateTaskStatus(Long id, TaskStatus status) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundTaskException(id));
            task.setStatus(status);
            return "Task status updated successfully";
        } catch (NotFoundTaskException e) {
            return e.getMessage();
        }
    }

    @Override
    @Transactional
    public String deleteTask(Long id) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundTaskException(id));
            taskRepository.delete(task);
            return "Task deleted successfully";
        } catch (NotFoundTaskException e) {
            return e.getMessage();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public String downloadTasks() {
        String userHome = System.getProperty("user.home");
        String downloadPath = Paths.get(userHome, "Downloads", "tasks.json").toString();
        File file = new File(downloadPath);

        try{
            if (file.exists() && file.canWrite()) 
                objectMapper.writeValue(Paths.get(downloadPath).toFile(), taskRepository.findAll());
            else {
                downloadPath = Paths.get(userHome, "tasks.json").toString();
                objectMapper.writeValue(Paths.get(downloadPath).toFile(), taskRepository.findAll());
            }
            return "Tasks downloaded successfully to " + downloadPath;
        } catch (IOException  e) {
            return "Error downloading tasks: " + e.getMessage();
        }
    }
}
