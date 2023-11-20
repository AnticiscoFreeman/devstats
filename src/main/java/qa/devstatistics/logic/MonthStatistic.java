package qa.devstatistics.logic;

import lombok.Getter;
import qa.devstatistics.dao.Developer;
import qa.devstatistics.dao.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 26.09.2023
 */

@Getter
public class MonthStatistic {

    private final String dateStart;
    private final String dateEnd;

    private final List<Developer> developers;
    private final List<TaskStatistic> taskStatistics = new ArrayList<>();

    private final List<Task> filterTask = new ArrayList<>();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final LocalDateTime timeStart;
    private final LocalDateTime timeEnd;

    public MonthStatistic(List<Developer> developers, String year, int month) {
        this.developers = developers;
        this.dateStart = String.format("%s-%s-01 00:00:00", year, month < 10 ? String.format("0%s", month) : month);
        this.dateEnd = String.format("%s-%s-01 00:00:00", year, month + 1 < 10 ? String.format("0%s", month + 1) : month + 1);
        this.timeStart = LocalDateTime.parse(dateStart, dateTimeFormatter);
        this.timeEnd = LocalDateTime.parse(dateEnd, dateTimeFormatter);
    }

    public void calculate() {
        taskStatistics.clear();
        for (int i = 0; i < developers.size(); i++) {
            Developer developer = developers.get(i);
            TaskStatistic statistic = new TaskStatistic(developer, dateStart, dateEnd);
            statistic.calculate(
                    getFilteredTaskByDate(
                            developer.getTasksList(),
                            timeStart,
                            timeEnd)
            );
            taskStatistics.add(statistic);
        }
    }

    public String getPeriod() {
        return String.format("%s - %s",
                dateStart.replace(" 00:00:00", ""),
                dateEnd.replace(" 00:00:00", "")
        );
    }

    private List<Task> getFilteredTaskByDate(List<Task> allTask, LocalDateTime date1, LocalDateTime date2) {
        filterTask.clear();
        for (int i = 0; i < allTask.size(); i++) {
            if (allTask.get(i).getTime().isAfter(date1) && allTask.get(i).getTime().isBefore(date2)) {
                filterTask.add(allTask.get(i));
            }
        }
        return filterTask;
    }
}
