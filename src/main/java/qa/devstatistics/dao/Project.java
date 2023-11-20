package qa.devstatistics.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 12.09.2023
 */

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany
    private List<Task> tasksList;

    public Project(String name) {
        this.name = name;
    }

    public void addTask(Task task) {
        tasksList.add(task);
    }

    public int getCountTasks() {
        return tasksList == null ? 0 : tasksList.size();
    }

    public boolean isEmpty() {
        return tasksList == null || tasksList.isEmpty();
    }

}
