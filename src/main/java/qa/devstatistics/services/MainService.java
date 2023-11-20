package qa.devstatistics.services;

import lombok.Getter;
import org.springframework.stereotype.Service;
import qa.devstatistics.dao.Developer;
import qa.devstatistics.dao.Project;
import qa.devstatistics.dao.Task;
import qa.devstatistics.helpers.Status;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 29.09.2023
 */

@Getter
@Service
public class MainService {

    private final Status status;

    private final TaskService taskService;
    private final ProjectService projectService;
    private final DeveloperService developerService;
    private final FilterService filterService;
    private final StatisticService statisticService;

    public MainService(TaskService taskService, ProjectService projectService, DeveloperService developerService, Status status, FilterService filterService, StatisticService statisticService) {
        this.status = status;
        this.taskService = taskService;
        this.projectService = projectService;
        this.developerService = developerService;
        this.filterService = filterService;
        this.statisticService = statisticService;
    }

    public Status addNewTaskToSystem(long projectId, long developerId, String taskNumber) {
        Project project = projectService.getProject(projectId);
        Developer developer = developerService.findDeveloper(developerId);

        for (int i = 0; i < developer.getTasksList().size(); i++) {
            Task developerTask = developer.getTasksList().get(i);
            if (developerTask.getNumber().equalsIgnoreCase(taskNumber) &&
                    developerTask.getProject().equalsIgnoreCase(project.getName())) {
                return status.badStatus(String.format("%s already exist", taskNumber));
            }
        }

        createNewTask(project, developer, taskNumber);
        return status.goodStatus(String.format("%s save in %s", taskNumber, project.getName()));
    }

    private void createNewTask(Project project, Developer developer, String taskNumber) {
        Task task = new Task();
        task.setNumber(taskNumber);
        task.setProject(project.getName());
        task.setDeveloper(developer.getFullName());
        task.setupNewTask();

        developer.addTask(task);
        project.addTask(task);

        taskService.saveTaskInDB(task);
        developerService.saveDeveloperInDB(developer);
        projectService.saveProjectInDB(project);
    }


}
