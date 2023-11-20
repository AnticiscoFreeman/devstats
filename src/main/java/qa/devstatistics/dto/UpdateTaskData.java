package qa.devstatistics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 29.09.2023
 */

@AllArgsConstructor
@Getter
public class UpdateTaskData {

    private final int DEV_HELP_OK = 1;
    private final int DEV_HELP_NO = -1;
    private final int DEV_HELP_NONE = 0;

    private long taskId;
    private int countDefect;
    private String revertStatus;
    private int helpStatus;

    public boolean isAboveZero() {
        return countDefect > 0;
    }

    public boolean isRevert() {
        return revertStatus.equals("on");
    }

    public boolean isValidData() {
        return countDefect != 0 || revertStatus.equals("on");
    }

    public String getHelpStatusToString() {
        switch (helpStatus) {
            case DEV_HELP_OK: {
                return "Developer help with task";
            }
            case DEV_HELP_NO: {
                return "Developer No help with task";
            }
        }
        return "No need help this task";
    }

}
