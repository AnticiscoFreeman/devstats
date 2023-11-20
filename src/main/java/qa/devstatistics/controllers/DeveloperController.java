package qa.devstatistics.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import qa.devstatistics.dao.Account;
import qa.devstatistics.dao.Developer;
import qa.devstatistics.dao.Task;
import qa.devstatistics.dto.UpdateTaskData;
import qa.devstatistics.helpers.Status;
import qa.devstatistics.services.MainService;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.09.2023
 */

@Controller
public class DeveloperController {

    private String message = "";

    private final MainService mainService;

    public DeveloperController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/developers")
    public String getDevelopersPage(@AuthenticationPrincipal Account account,
                                    @PageableDefault(sort = {"dismiss"},
                                            direction = Sort.Direction.ASC,
                                            size = 15) Pageable pageable,
                                    ModelMap model) {
        Page<Developer> sortingDevelopers = mainService.getDeveloperService().getAllDevelopers(pageable);
        model.addAttribute("developersList", sortingDevelopers);
        model.addAttribute("pageUrl", "/developers");
        model.addAttribute("projectList", mainService.getProjectService().getAllProjects());
        model.addAttribute("developersType", mainService.getDeveloperService().getDevelopersType());
        model.addAttribute("account", account);
        model.addAttribute("message", message.isEmpty() ? null : message);
        return "developers";
    }

    @GetMapping("/developer/{id}")
    public String getDeveloperPage(@PathVariable long id,
                                   @AuthenticationPrincipal Account account,
                                   @PageableDefault(sort = {"id"},
                                           direction = Sort.Direction.DESC,
                                           size = 15) Pageable pageable,
                                   ModelMap model) {

        Developer developer = mainService.getDeveloperService().findDeveloper(id);
        Page<Task> developerTasks = mainService.getTaskService().getAllTasksByDeveloper(developer, pageable);

        model.addAttribute("developer", developer);
        model.addAttribute("developerTasks", developerTasks);
        model.addAttribute("projectList", mainService.getProjectService().getAllProjects());
        model.addAttribute("pageUrl", String.format("/developer/%s", id));
        model.addAttribute("account", account);
        model.addAttribute("message", message.isEmpty() ? null : message);
        return "developer";
    }

    @PostMapping("/developer/{developerId}")
    public String addNewTask(@PathVariable long developerId,
                             @ModelAttribute("number") String taskNumber,
                             @ModelAttribute("project") long projectId) {

        Status addTaskStatus = mainService.addNewTaskToSystem(projectId, developerId, taskNumber);
        message = addTaskStatus.getMessage();
        return "redirect:/developer/" + developerId;
    }

    @PostMapping("/developer/{developerId}/dismiss")
    public String dismissCurrentDeveloper(@PathVariable long developerId) {

        Status addTaskStatus = mainService.getDeveloperService().dismissCurrentDeveloper(developerId);
        message = addTaskStatus.getMessage();
        return "redirect:/developer/" + developerId;
    }

    @PostMapping("/developer/{developerId}/updateTask/{taskId}")
    public String updateTask(@PathVariable long developerId,
                             @ModelAttribute(name = "defectValue") int defectValue,
                             @ModelAttribute(name = "returnValue") String returnValue,
                             @ModelAttribute(name = "helpValue") int helpValue,
                             @PathVariable long taskId) {

        UpdateTaskData updateTaskData = new UpdateTaskData(taskId, defectValue, returnValue, helpValue);
        Status updateTaskStatus = mainService.getTaskService().updateTaskData(updateTaskData);
        message = updateTaskStatus.getMessage();
        return "redirect:/developer/" + developerId;
    }

    @PostMapping("/developers/add")
    public String createNewDeveloper(
            @ModelAttribute("name") String name,
            @ModelAttribute("surname") String surname,
            @ModelAttribute("devType") String type) {

        Status createDeveloperStatus = mainService
                .getDeveloperService()
                .addNewDeveloper(name, surname, type);

        message = createDeveloperStatus.getMessage();
        return "redirect:/developers";
    }

    @PostMapping("/developers/delete/{id}")
    public String deleteDeveloper(@PathVariable long id) {

        Status deleteDeveloperStatus = mainService
                .getDeveloperService()
                .deleteCurrentDeveloper(id);

        message = deleteDeveloperStatus.getMessage();
        return "redirect:/developers";
    }
}
