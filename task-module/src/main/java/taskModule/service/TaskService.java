package taskModule.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import taskModule.domain.Task;
import taskModule.dto.TaskDTO;
import taskModule.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class TaskService {

    @Inject
    TaskRepository taskRepository;

    // Create a new task
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = toEntity(taskDTO);
        Task savedTask = taskRepository.save(task);
        return toDTO(savedTask);
    }

    // Update an existing task
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = toEntity(taskDTO);
        task.setId(id); // Ensure ID is set correctly
        Task updatedTask = taskRepository.update(task);
        return toDTO(updatedTask);
    }

    // Find a task by its ID
    public Optional<TaskDTO> getTaskById(Long id) {
        return taskRepository.findById(id).map(this::toDTO);
    }

    // Get a list of all tasks
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                             .map(this::toDTO)
                             .collect(Collectors.toList());
    }

    // Delete a task by its ID
    public boolean deleteTask(Long id) {
        return taskRepository.deleteById(id);
    }

    // Convert Task to TaskDTO
    private TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription());
    }

    // Convert TaskDTO to Task
    private Task toEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return task;
    }
}
