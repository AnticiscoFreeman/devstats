package qa.devstatistics.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import qa.devstatistics.dao.Account;
import qa.devstatistics.services.MainService;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 27.09.2023
 */

@Controller
@RequestMapping
public class HomeController {

    private final MainService mainService;

    public HomeController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String getHomePage(@AuthenticationPrincipal Account account,
                              ModelMap model) {
        model.addAttribute("projectList", mainService.getProjectService().getAllProjects());
        model.addAttribute("account", account);
        return "home";
    }
}
