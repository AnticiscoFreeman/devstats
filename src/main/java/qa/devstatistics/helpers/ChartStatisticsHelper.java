package qa.devstatistics.helpers;

import lombok.Getter;
import org.springframework.stereotype.Component;
import qa.devstatistics.dao.Task;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 02.10.2023
 */

@Getter
@Component
public class ChartStatisticsHelper {

    private final List<String> allYearsList = new ArrayList<>();

    private final List<String> countTask = new ArrayList<>();
    private final List<String> countAllDefect = new ArrayList<>();
    private final List<String> countAllReturn = new ArrayList<>();
    private final List<String> countAllRevert = new ArrayList<>();
    private final List<String> countAllNoDefect = new ArrayList<>();

    private final List<Task> filterTasks = new ArrayList<>();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void calculateDataForChart(String year, List<Task> allTaskList) {
        countTask.clear();
        countAllDefect.clear();
        countAllReturn.clear();
        countAllRevert.clear();
        countAllNoDefect.clear();

        for (int i = 0; i < 11; i++) {
            String startDate = getCurrentDate(Integer.parseInt(year), i + 1);
            String endDate = getCurrentDate(Integer.parseInt(year), i + 2);

            LocalDateTime timeStart = LocalDateTime.parse(startDate, dateTimeFormatter);
            LocalDateTime timeEnd = LocalDateTime.parse(endDate, dateTimeFormatter);

            List<Task> tasks = getFilteredTaskByDate(allTaskList, timeStart, timeEnd);

            int countDefect = 0;
            int countReturn = 0;
            int countReReturn = 0;
            int countNoDefect = 0;

            for (int j = 0; j < tasks.size(); j++) {
                countDefect += tasks.get(j).getCountDefect();
                countReturn += tasks.get(j).getCountReturn();
                countReReturn += tasks.get(j).getCountRevert();
                countNoDefect += tasks.get(j).isWithoutDefect() ? 1 : 0;
            }

            countTask.add(String.valueOf(tasks.size()));
            countAllDefect.add(String.valueOf(countDefect));
            countAllReturn.add(String.valueOf(countReturn));
            countAllRevert.add(String.valueOf(countReReturn));
            countAllNoDefect.add(String.valueOf(countNoDefect));
        }
    }

    private String getCurrentDate(int year, int month) {
        String format = String.format("%s-%s-01 00:00:00",
                year,
                month < 10 ? "0" + month : month);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    private List<Task> getFilteredTaskByDate(List<Task> list, LocalDateTime date1, LocalDateTime date2) {
        filterTasks.clear();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.getTime().isAfter(date1) && task.getTime().isBefore(date2)) {
                filterTasks.add(list.get(i));
            }
        }
        return filterTasks;
    }

}
