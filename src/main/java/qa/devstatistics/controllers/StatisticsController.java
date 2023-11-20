package qa.devstatistics.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import qa.devstatistics.dao.Account;
import qa.devstatistics.helpers.ChartStatisticsHelper;
import qa.devstatistics.services.MainService;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.09.2023
 */

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final MainService mainService;

    public StatisticsController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/chart")
    public String getChartPage(@AuthenticationPrincipal Account account,
                               ModelMap model) {
        mainService.getStatisticService().generateNoDataChartPage();
        model.addAttribute("availableYear", mainService.getStatisticService().getAvailableYears());
        model.addAttribute("isView", mainService.getStatisticService().isView());
        model.addAttribute("projectList", mainService.getProjectService().getAllProjects());
        model.addAttribute("account", account);
        return "chartStatistic";
    }

    @GetMapping("/chart/{selectedYear}")
    public String getChartSelectedYearPage(@AuthenticationPrincipal Account account,
                                @PathVariable String selectedYear,
                                ModelMap model) {

        ChartStatisticsHelper chartStatisticsHelper = mainService.getStatisticService().generateDataChartPage(selectedYear);
        model.addAttribute("chartStatisticsHelper", chartStatisticsHelper);
        model.addAttribute("availableYear", mainService.getStatisticService().getAvailableYears());
        model.addAttribute("projectList", mainService.getProjectService().getAllProjects());
        model.addAttribute("selectedYear", selectedYear);
        model.addAttribute("isView", mainService.getStatisticService().isView());
        model.addAttribute("account", account);
        return "chartStatistic";
    }

}
