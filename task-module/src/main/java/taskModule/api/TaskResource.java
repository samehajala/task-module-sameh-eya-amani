package taskModule.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import taskModule.dto.TaskDTO;
import taskModule.service.TaskService;

import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskService taskService;

    // Create a new task
    @POST
    public TaskDTO createTask(TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    // Get a task by its ID
    @GET
    @Path("/{id}")
    public TaskDTO getTaskById(@PathParam("id") Long id) {
        return taskService.getTaskById(id)
                .orElseThrow(() -> new NotFoundException("Task with ID " + id + " not found"));
    }

    // Get all tasks
    @GET
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Update an existing task
    @PUT
    @Path("/{id}")
    public TaskDTO updateTask(@PathParam("id") Long id, TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }

    // Delete a task by its ID
    @DELETE
    @Path("/{id}")
    public void deleteTask(@PathParam("id") Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (!deleted) {
            throw new NotFoundException("Task with ID " + id + " not found");
        }
    }
}
