package qa.devstatistics.dto;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created: Aleksandr Gladkov (Anticisco Freeman)
 */

@Getter
public class TaskFilterHelper {
    
    private final String inputStartData;
    private final String inputEndDate;
    private final long inputProjectId;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TaskFilterHelper(String inputStartData, String inputEndDate, long inputProjectId) {
        this.inputStartData = inputStartData;
        this.inputEndDate = inputEndDate;
        this.inputProjectId = inputProjectId;
    }

    public boolean isFilterByDateAndProject() {
        return !inputStartData.isEmpty() && inputProjectId != 0;
    }

    public boolean isFilterByDate() {
        return !inputStartData.isEmpty() && inputProjectId == 0;
    }

    public boolean isFilterReset() {
        return inputStartData.isEmpty() && inputProjectId == 0;
    }

    public String getStartDate() {
        return inputStartData + " 00:00:00";
    }

    public String getEndDate() {
        return inputEndDate.isEmpty() ? getCurrentDate() : inputEndDate + " 23:59:59";
    }

    private String getCurrentDate() {
        return dateFormat.format(new Date());
    }
}
