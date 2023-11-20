package qa.devstatistics.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 29.09.2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Status {

    private boolean isOk;
    private String message;

    public Status goodStatus(String message) {
        return new Status(true, message);
    }

    public Status badStatus(String message) {
        return new Status(false, message);
    }

}
