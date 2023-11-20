package qa.devstatistics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 02.10.2023
 */

@AllArgsConstructor
@Getter
public class AccountFormHelper {

    private String username;
    private String password;
    private String name;
    private long id;
    private Map<String, String> formData;
}
