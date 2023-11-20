package qa.devstatistics.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import qa.devstatistics.dao.Account;
import qa.devstatistics.helpers.Status;
import qa.devstatistics.services.ProjectService;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.09.2023
 */

@Controller
public class ProjectController {

    private String message = "";

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project/{id}")
    public String getProjectPage(@PathVariable long id,
                              @AuthenticationPrincipal Account account,
                              ModelMap model) {
        model.addAttribute("project", projectService.getProject(id));
        model.addAttribute("projectList", projectService.getAllProjects());
        model.addAttribute("account", account);
        model.addAttribute("message", message.isEmpty() ? null : message);
        return "project";
    }

    @GetMapping("/projects")
    public String getProjectsPage(@AuthenticationPrincipal Account account,
                              ModelMap model) {
        model.addAttribute("projectList", projectService.getAllProjects());
        model.addAttribute("account", account);
        model.addAttribute("message", message.isEmpty() ? null : message);
        return "projects";
    }

    @PostMapping("/projects")
    public String createNewProject(@ModelAttribute("projectName") String name) {

        Status addNewProjectStatus = projectService.addNewProject(name);
        message = addNewProjectStatus.getMessage();
        return "redirect:/projects";
    }

    @PostMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable long id) {
        Status deleteProjectStatus = projectService.deleteCurrentProject(id);
        message = deleteProjectStatus.getMessage();
        return "redirect:/projects";
    }
}
