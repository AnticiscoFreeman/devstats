package qa.devstatistics.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qa.devstatistics.dao.Account;
import qa.devstatistics.dao.Developer;
import qa.devstatistics.dao.Task;
import qa.devstatistics.logic.MonthStatistic;
import qa.devstatistics.repos.DeveloperRepo;
import qa.devstatistics.repos.ProjectRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.09.2023
 */

@Controller
@RequestMapping("/statistics")
public class MainStatisticController {

    private final DeveloperRepo developerRepo;
    private final ProjectRepo projectRepo;

    private final List<MonthStatistic> monthStatistics = new ArrayList<>();

    private final List<String> allYearsList = new ArrayList<>();
    private String inputYear = "";

    private boolean onlyActiveDevs = true;

    private List<Developer> developerList;

    public MainStatisticController(DeveloperRepo developerRepo, ProjectRepo projectRepo) {
        this.developerRepo = developerRepo;
        this.projectRepo = projectRepo;
    }

    @GetMapping("/main")
    public String getMainStatisticPage(@AuthenticationPrincipal Account account,
                                       ModelMap model) {
        developerList = onlyActiveDevs ? developerRepo.findAllByDismissFalse() : developerRepo.findAll();
        calculate(inputYear.isEmpty() ? getCurrentYear() : inputYear);
        extractYears();
        model.addAttribute("monthStatistics", monthStatistics);
        model.addAttribute("projectList", projectRepo.findAll());
        model.addAttribute("availableYear", allYearsList);
        model.addAttribute("account", account);
        model.addAttribute("selectedYear", inputYear.isEmpty() ? getCurrentYear() : inputYear);
        model.addAttribute("onlyActiveDevs", onlyActiveDevs);
        return "mainStatistic";
    }

    @GetMapping("/main/filter")
    public String getStatisticFilterPage(@ModelAttribute("selectYear") String year,
                                         @RequestParam Map<String, String> formData) {

        for (String value : formData.values()) {
            if (value.equals("activeDevs")) {
                onlyActiveDevs = true;
                break;
            }
            onlyActiveDevs = false;
        }

        developerList = onlyActiveDevs ? developerRepo.findAllByDismissFalse() : developerRepo.findAll();

        System.err.println("onlyActiveDevs > " + onlyActiveDevs);

        if (!inputYear.equals(year)) {
            inputYear = year;
            calculate(inputYear);
        }
        return "redirect:/statistics/main";
    }

    private void calculate(String year) {
        monthStatistics.clear();
        for (int i = 0; i < 11; i++) {
            MonthStatistic monthStatistic = new MonthStatistic(developerList, year, i + 1);
            monthStatistic.calculate();
            monthStatistics.add(monthStatistic);
        }
    }

    private void extractYears() {
        allYearsList.clear();
        List<String> tmpList = new ArrayList<>();
        for (int i = 0; i < developerList.size(); i++) {
            Developer dev = developerList.get(i);
            for (int j = 0; j < dev.getTasksList().size(); j++) {
                Task task = dev.getTasksList().get(j);
                tmpList.add(String.valueOf(task.getTaskYear()));
            }
        }
        allYearsList.addAll(tmpList.stream().distinct().toList());
    }

    private String getCurrentYear() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
    }
}
