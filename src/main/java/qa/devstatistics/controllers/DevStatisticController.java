package qa.devstatistics.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import qa.devstatistics.dao.Account;
import qa.devstatistics.dao.Developer;
import qa.devstatistics.dao.Task;
import qa.devstatistics.logic.TaskStatistic;
import qa.devstatistics.services.MainService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.09.2023
 */

@Controller
@RequestMapping("/statistics")
public class DevStatisticController {

    private String message = "";

    private final MainService mainService;

    public DevStatisticController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/devs")
    public String getSelfPage(@AuthenticationPrincipal Account account,
                              ModelMap model) {
        List<Developer> developers = mainService.getDeveloperService().getAllDevelopers();
        model.addAttribute("developersList", developers.isEmpty() ? null : developers);
        model.addAttribute("projectList", mainService.getProjectService().getAllProjects());
        model.addAttribute("account", account);
        return "devStatistic";
    }

    @PostMapping("/devs")
    public String getStatistic(
            @AuthenticationPrincipal Account account,
            @ModelAttribute("date1StartFilter") String dateStart1,
            @ModelAttribute("date1EndFilter") String dateEnd1,
            @ModelAttribute("date2StartFilter") String dateStart2,
            @ModelAttribute("date2EndFilter") String dateEnd2,
            @ModelAttribute("devList") long developerId,
            ModelMap model) {

        List<Developer> developersList = mainService.getDeveloperService().getAllDevelopers();

        boolean isPeriodAfterReady = !dateStart2.isEmpty() && !dateEnd2.isEmpty();

        if (dateStart1.isEmpty() && !isPeriodAfterReady) {

            Developer developer = mainService.getDeveloperService().findDeveloper(developerId);
            TaskStatistic taskStatisticOnlyDev = new TaskStatistic(developer, "-", "-");

            List<Task> devTasks = mainService.getTaskService().getAllTasksByDeveloper(developerId);
            taskStatisticOnlyDev.calculate(devTasks);

            message = String.format("Filter complete for Developer [%s %s]",
                    developer.getSurname(), developer.getName());

            model.addAttribute("message", message.isEmpty() ? null : message);
            model.addAttribute("developer", developer);
            model.addAttribute("developersList", developersList);
            model.addAttribute("taskStatisticBefore", taskStatisticOnlyDev);
            model.addAttribute("account", account);
            return "devStatistic";
        }

        Developer developer = mainService.getDeveloperService().findDeveloper(developerId);
        TaskStatistic taskStatisticBefore = new TaskStatistic(developer, dateStart1, dateEnd1);
        List<Task> tasksBefore = mainService.getTaskService().getAllTasksByPeriodAndDeveloper(
                developerId,
                getFullStartDate(dateStart1),
                dateEnd1.isEmpty() ? getCurrentDate() : getFullEndDate(dateEnd1));
        taskStatisticBefore.calculate(tasksBefore);

        if (isPeriodAfterReady) {
            TaskStatistic taskStatisticAfter = new TaskStatistic(developer, dateStart2, dateEnd2);
            List<Task> tasksAfter = mainService.getTaskService().getAllTasksByPeriodAndDeveloper(
                    developerId,
                    getFullStartDate(dateStart2),
                    dateEnd2.isEmpty() ? getCurrentDate() : getFullEndDate(dateEnd2));
            taskStatisticAfter.calculate(tasksAfter);
            model.addAttribute("tasksAfter", tasksAfter);
            model.addAttribute("taskStatisticAfter", taskStatisticAfter);
        }

        message = String.format("Filter complete for Developer [%s %s] and choose period",
                developer.getSurname(), developer.getName());

        model.addAttribute("message", message.isEmpty() ? null : message);
        model.addAttribute("developer", developer);
        model.addAttribute("developersList", developersList);
        model.addAttribute("tasksBefore", tasksBefore);
        model.addAttribute("taskStatisticBefore", taskStatisticBefore);
        model.addAttribute("account", account);
        return "devStatistic";
    }

    private String getFullStartDate(String date) {
        return date + " 00:00:00";
    }

    private String getFullEndDate(String date) {
        return date + " 23:59:59";
    }

    private String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
