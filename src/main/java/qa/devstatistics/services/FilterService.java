package qa.devstatistics.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import qa.devstatistics.dao.Project;
import qa.devstatistics.dao.Task;
import qa.devstatistics.dto.TaskFilterHelper;
import qa.devstatistics.helpers.Status;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 29.09.2023
 */

@Service
public class FilterService {

    private final Status status;

    private final ProjectService projectService;
    private final TaskService taskService;

    private final StringBuilder filterMessage;

    public FilterService(Status status, ProjectService projectService, TaskService taskService) {
        this.status = status;
        this.projectService = projectService;
        this.taskService = taskService;
        this.filterMessage = new StringBuilder();
    }

    public Page<Task> getFilteredTaskList(TaskFilterHelper filter, Pageable pageable) {
        Project project;
        if (filter.isFilterByDateAndProject()) {
            project = projectService.getProject(filter.getInputProjectId());
            setupMessage(
                    String.format("Task List Filter By Period [%s - %s] and Project [%s]",
                            filter.getInputStartData(),
                            filter.getInputEndDate(),
                            project.getName())
            );
            return taskService.getTasksByPeriodAndProject(
                    project.getName(),
                    filter.getStartDate(),
                    filter.getEndDate(),
                    pageable
            );
        }

        if (filter.isFilterByDate()) {
            setupMessage(
                    String.format("Task List Filter By Period [%s - %s]",
                            filter.getInputStartData(),
                            filter.getInputEndDate().isEmpty() ? "Now" : filter.getInputEndDate())
            );
            return taskService.getTasksByPeriod(
                    filter.getStartDate(),
                    filter.getEndDate(),
                    pageable);
        }

        project = projectService.getProject(filter.getInputProjectId());
        setupMessage(
                String.format("Task List Filter By Project [%s]", project.getName())
        );
        return taskService.getTasksByProject(project.getName(), pageable);
    }

    private void setupMessage(String message) {
        filterMessage.delete(0, filterMessage.length());
        filterMessage.append(message);
    }

    public String getCurrentFilterMessage() {
        return filterMessage.toString();
    }
}
