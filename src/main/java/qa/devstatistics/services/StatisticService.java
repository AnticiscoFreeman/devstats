package qa.devstatistics.services;

import org.springframework.stereotype.Service;
import qa.devstatistics.dao.Task;
import qa.devstatistics.helpers.ChartStatisticsHelper;
import qa.devstatistics.repos.TaskRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 02.10.2023
 */
@Service
public class StatisticService {

    private final TaskRepo taskRepo;
    private final ChartStatisticsHelper chartStatisticsHelper;

    private List<Task> allTask;

    private final List<String> allYearsList = new ArrayList<>();

    private boolean isView;

    public StatisticService(TaskRepo taskRepo, ChartStatisticsHelper chartStatisticsHelper) {
        this.taskRepo = taskRepo;
        this.chartStatisticsHelper = chartStatisticsHelper;
    }

    public void generateNoDataChartPage() {
        isView = false;
        allTask = taskRepo.findAll();
        collectAvailableYearsFromTasks();
    }

    public ChartStatisticsHelper generateDataChartPage(String selectedYear) {
        isView = true;
        allTask = taskRepo.findAll();
        collectAvailableYearsFromTasks();
        chartStatisticsHelper.calculateDataForChart(selectedYear, allTask);
        return chartStatisticsHelper;
    }

    private void collectAvailableYearsFromTasks() {
        allYearsList.clear();
        List<String> tmpList = new ArrayList<>();
        for (int i = 0; i < allTask.size(); i++) {
            Task task = allTask.get(i);
            tmpList.add(String.valueOf(task.getTaskYear()));
        }
        allYearsList.addAll(tmpList.stream().distinct().toList());
    }

    public boolean isView() {
        return isView;
    }

    public List<String> getAvailableYears() {
        return allYearsList;
    }
}
