package qa.devstatistics.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Created: Aleksandr Gladkov (Anticisco Freeman)
 */

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String type;
    private boolean dismiss;

    @OneToMany
    private List<Task> tasksList;

    public Developer(String name, String surname, String type) {
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.dismiss = false;
    }

    public void addTask(Task task) {
        tasksList.add(task);
    }

    public String getFullName() {
        return String.format("%s %s", surname, name);
    }

    public int getCountTasks() {
        return tasksList == null ? 0 : tasksList.size();
    }

    public void setDismiss() {
        this.dismiss = true;
    }

    public void setUnDismiss() {
        this.dismiss = false;
    }
}
