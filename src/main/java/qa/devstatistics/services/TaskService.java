package qa.devstatistics.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import qa.devstatistics.dao.Developer;
import qa.devstatistics.dao.Task;
import qa.devstatistics.dto.UpdateTaskData;
import qa.devstatistics.helpers.Status;
import qa.devstatistics.repos.TaskRepo;

import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 29.09.2023
 */

@Service
public class TaskService {

    private final Status status;

    private final TaskRepo taskRepo;

    private final int TASKS_PER_PAGE = 15;

    public TaskService(Status status, TaskRepo taskRepo) {
        this.status = status;
        this.taskRepo = taskRepo;
    }

    public void saveTaskInDB(Task task) {
        taskRepo.save(task);
    }

    public Task getTask(long id) {
        return taskRepo.findTaskById(id);
    }

    public Status updateTaskData(UpdateTaskData updateTaskData) {
        Task task = taskRepo.findTaskById(updateTaskData.getTaskId());

        if (!updateTaskData.isValidData()) {
            return status.badStatus(
                    String.format("%s not update! Cause: Input Defect Value not be Zero Or Re-Return must be Active!",
                            task.getNumber())
            );
        }

        if (updateTaskData.isAboveZero()) {
            task.increaseDefect(updateTaskData.getCountDefect());
            task.increaseReturn();
        }

        if (updateTaskData.isRevert()) {
            task.increaseRevert();
        }

        task.setDevDescription(updateTaskData.getHelpStatus());
        taskRepo.save(task);

        return status.goodStatus(
                String.format("%s update in %s! Status: %s%s[%s]",
                        task.getNumber(),
                        task.getProject(),
                        updateTaskData.isAboveZero() ? String.format("[Defect: %s]", updateTaskData.getCountDefect()) : "",
                        updateTaskData.isRevert() ? "[Task Revert]" : "",
                        updateTaskData.getHelpStatusToString())
        );
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Page<Task> getSearchTasksByName(String name, Pageable pageable) {
        return taskRepo.findByNumberContainingIgnoreCase(name, pageable);
    }

    public Page<Task> getTasksByPeriodAndProject(String projectName,
                                                 String startDate,
                                                 String endDate,
                                                 Pageable pageable) {
        return taskRepo.findTaskByPeriodAndProjectFilter(projectName, startDate, endDate, pageable);
    }

    public Page<Task> getTasksByPeriod(String startDate, String endDate, Pageable pageable) {
        return taskRepo.findTaskByPeriod(startDate, endDate, pageable);
    }

    public Page<Task> getTasksByProject(String projectName, Pageable pageable) {
        return taskRepo.findByProjectEqualsIgnoreCase(projectName, pageable);
    }

    public Page<Task> getSortingByPageTasks(Pageable pageable) {
        return taskRepo.findAll(pageable);
    }

    public List<Task> getAllTasksByDeveloper(long developerId) {
        return taskRepo.findTaskByDevFilter(developerId);
    }

    public Page<Task> getAllTasksByDeveloper(Developer developer, Pageable pageable) {
        return taskRepo.findTaskByDeveloperIgnoreCase(developer.getFullName(), pageable);
    }

    public List<Task> getAllTasksByPeriodAndDeveloper(long developerId, String startDate, String endDate) {
        return taskRepo.findTaskByPeriodAndDevFilter(developerId, startDate, endDate);
    }

    public int getCountTasksPages() {
        return (int) taskRepo.count() / TASKS_PER_PAGE;
    }


}
