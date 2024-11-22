package taskModule.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import taskModule.domain.Task;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TaskRepository {

    @PersistenceContext
    EntityManager entityManager;

    // Create a new task
    @Transactional
    public Task save(Task task) {
        entityManager.persist(task);
        return task;
    }

    // Update an existing task
    @Transactional
    public Task update(Task task) {
        return entityManager.merge(task);
    }

    // Find a task by its ID
    public Optional<Task> findById(Long id) {
        Task task = entityManager.find(Task.class, id);
        return Optional.ofNullable(task);
    }

    // Get a list of all tasks
    public List<Task> findAll() {
        return entityManager.createQuery("SELECT t FROM Task t", Task.class)
                            .getResultList();
    }

    // Delete a task by its ID
    @Transactional
    public boolean deleteById(Long id) {
        Task task = entityManager.find(Task.class, id);
        if (task != null) {
            entityManager.remove(task);
            return true;
        }
        return false;
    }
}
