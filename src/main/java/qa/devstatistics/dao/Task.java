package qa.devstatistics.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created: Aleksandr Gladkov (Anticisco Freeman)
 */

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {

    public static final int DEV_NO_HELP = -1;
    public static final int NO_NEED_HELP = 0;
    public static final int DEV_HELP = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;
    private int countReturn;
    private int countDefect;
    private int devDescription;
    private int countRevert;
    private String project;
    private String developer;
    private LocalDateTime time;

    public void setupNewTask() {
        this.countReturn = 0;
        this.devDescription = NO_NEED_HELP;
        this.countDefect = 0;
        this.countRevert = 0;
        this.time = LocalDateTime.now();
    }

    public String getTaskDate() {
        return time.format(DateTimeFormatter.ISO_DATE);
    }

    public Integer getTaskYear() {
        return Integer.valueOf(time.format(DateTimeFormatter.ofPattern("yyyy")));
    }


    public void increaseReturn() {
        this.countReturn++;
    }

    public void increaseRevert() {
        this.countRevert++;
    }

    public void increaseDefect(int count) {
        this.countDefect += count;
    }

    public boolean isWithoutDefect() {
        return countDefect == 0;
    }

}
