package qa.devstatistics.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import qa.devstatistics.dao.Account;
import qa.devstatistics.dao.Task;
import qa.devstatistics.dto.TaskFilterHelper;
import qa.devstatistics.dto.UpdateTaskData;
import qa.devstatistics.helpers.Status;
import qa.devstatistics.services.MainService;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.09.2023
 */

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private String message = "";

    private final MainService mainService;

    public TasksController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping
    public String getTasksPage(@AuthenticationPrincipal Account account,
                               @PageableDefault(sort = {"id"},
                                       direction = Sort.Direction.DESC,
                                       size = 15) Pageable pageable,
                               ModelMap model) {

        Page<Task> currentPageTasks = mainService.getTaskService().getSortingByPageTasks(pageable);

        model.addAttribute("projectList", mainService.getProjectService().getAllProjects());
        model.addAttribute("tasksList", currentPageTasks);
        model.addAttribute("pageUrl", "/tasks");
        model.addAttribute("message", message.isEmpty() ? null : message);
        model.addAttribute("account", account);
        return "tasks";
    }

    @PostMapping
    public String getTasksFilterPage(@AuthenticationPrincipal Account account,
                                     @RequestParam(name = "start") String startDate,
                                     @RequestParam(name = "end") String endDate,
                                     @RequestParam(name = "project") long projectId,
                                     @PageableDefault(sort = {"id"},
                                             direction = Sort.Direction.DESC,
                                             size = 50) Pageable pageable,
                                     ModelMap model) {

        Page<Task> filteredTasks;
        TaskFilterHelper taskFilterHelper = new TaskFilterHelper(startDate, endDate, projectId);

        if (taskFilterHelper.isFilterReset()) {
            message = "Task List Filter Reset";
            return "redirect:/tasks";
        }

        filteredTasks = mainService.getFilterService().getFilteredTaskList(taskFilterHelper, pageable);
        message = mainService.getFilterService().getCurrentFilterMessage();

        model.addAttribute("projectList", mainService.getProjectService().getAllProjects());
        model.addAttribute("tasksList", filteredTasks);
        model.addAttribute("pageUrl", "/tasks");
        model.addAttribute("account", account);
        model.addAttribute("message", message.isEmpty() ? null : message);
        return "tasks";
    }

    @PostMapping("/search")
    public String searchTasksFilterPage(@AuthenticationPrincipal Account account,
                                        @RequestParam(name = "searchTask") String taskName,
                                        @PageableDefault(sort = {"id"},
                                                direction = Sort.Direction.DESC,
                                                size = 15) Pageable pageable,
                                        ModelMap model) {
        Page<Task> searchTasks = (taskName == null || taskName.isEmpty()) ?
                mainService.getTaskService().getSortingByPageTasks(pageable) :
                mainService.getTaskService().getSearchTasksByName(taskName, pageable);

        message = String.format("Searching result [%s]. Found %s tasks", taskName, searchTasks.getContent().size());
        model.addAttribute("projectList", mainService.getProjectService().getAllProjects());
        model.addAttribute("tasksList", searchTasks);
        model.addAttribute("pageUrl", "/tasks");
        model.addAttribute("message", message.isEmpty() ? null : message);
        model.addAttribute("account", account);
        return "tasks";
    }

    @PostMapping("/update/{taskId}")
    public String updateTask(@PathVariable long taskId,
                             @ModelAttribute("defectValue") int defectValue,
                             @ModelAttribute("returnValue") String returnValue,
                             @ModelAttribute("helpValue") int helpValue) {

        UpdateTaskData updateTaskData = new UpdateTaskData(taskId, defectValue, returnValue, helpValue);
        Status updateTaskStatus = mainService.getTaskService().updateTaskData(updateTaskData);
        message = updateTaskStatus.getMessage();
        return "redirect:/tasks";
    }

}
