package qa.devstatistics.logic;

import lombok.NoArgsConstructor;
import qa.devstatistics.dao.Developer;
import qa.devstatistics.dao.Task;

import java.util.List;

/**
 * Created: Aleksandr Gladkov (Anticisco Freeman)
 */

@NoArgsConstructor
public class TaskStatistic {

    private int countTask;
    private int countReturn;
    private int countRevert;
    private int countDefect;
    private int countHelpYes;
    private int countHelpNo;
    private int countHelpNone;
    private int countNoDefectTask;

    private Developer developer;

    private String dateStart;
    private String dateEnd;

    public TaskStatistic(Developer developer, String d1, String d2) {
        this.dateStart = d1;
        this.dateEnd = d2;
        this.developer = developer;
    }

    public void calculate(List<Task> taskList) {
        this.countTask = taskList.size();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task == null) {
                countTask = 0;
                return;
            }

            countReturn += task.getCountReturn();
            countRevert += task.getCountRevert();
            countDefect += task.getCountDefect();

            if (task.isWithoutDefect()) {
                countNoDefectTask++;
            }

            switch (task.getDevDescription()) {
                case Task.DEV_NO_HELP -> countHelpNo += 1;
                case Task.NO_NEED_HELP -> countHelpNone += 1;
                case Task.DEV_HELP -> countHelpYes += 1;
            }
        }
    }

    public String getDeveloperName() {
        return developer.getFullName();
    }

    public String getCountTask() {
        return String.valueOf(countTask);
    }

    public String getCountReturn() {
        return String.valueOf(countReturn);
    }

    public String getCountReverts() {
        return String.valueOf(countRevert);
    }

    public String getCountDefect() {
        return String.valueOf(countDefect);
    }

    public String getCountHelpYes() {
        return String.valueOf(countHelpYes);
    }

    public String getCountHelpNo() {
        return String.valueOf(countHelpNo);
    }

    public String getCountHelpNone() {
        return String.valueOf(countHelpNone);
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getCountNoDefectTask() {
        return String.valueOf(countNoDefectTask);
    }
}
