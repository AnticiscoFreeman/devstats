package qa.devstatistics.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import qa.devstatistics.dao.Task;

import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.09.2023
 */
@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {

    List<Task> findAll();

    Page<Task> findAll(Pageable pageable);

    Task findTaskById(long id);

    Page<Task> findByNumberContainingIgnoreCase(String name, Pageable pageable);

    @Query(value = "select * from task where time >= :dateBefore and time <= :dateAfter", nativeQuery = true)
    Page<Task> findTaskByPeriod(String dateBefore, String dateAfter, Pageable pageable);

    @Query(value = "SELECT t.*" +
            "FROM developer AS dev " +
            "LEFT JOIN developer_tasks_list dtl ON dev.id = dtl.developer_id " +
            "LEFT JOIN task t on t.id = dtl.tasks_list_id " +
            "WHERE project = :projectName" +
            " AND time >= :dateBefore AND time <= :dateAfter", nativeQuery = true)
    Page<Task> findTaskByPeriodAndProjectFilter(String projectName, String dateBefore, String dateAfter, Pageable pageable);

    Page<Task> findByProjectEqualsIgnoreCase(String projectName, Pageable pageable);

    @Query(value = "SELECT t.*" +
            "FROM developer AS dev " +
            "LEFT JOIN developer_tasks_list dtl ON dev.id = dtl.developer_id " +
            "LEFT JOIN task t on t.id = dtl.tasks_list_id " +
            "WHERE dev.id = :developerId" +
            " AND time >= :dateBefore AND time <= :dateAfter", nativeQuery = true)
    List<Task> findTaskByPeriodAndDevFilter(long developerId, String dateBefore, String dateAfter);

    @Query(value = "SELECT t.*" +
            "FROM developer AS dev " +
            "LEFT JOIN developer_tasks_list dtl ON dev.id = dtl.developer_id " +
            "LEFT JOIN task t on t.id = dtl.tasks_list_id " +
            "WHERE dev.id = :developerId", nativeQuery = true)
    List<Task> findTaskByDevFilter(long developerId);

    Page<Task> findTaskByDeveloperIgnoreCase(String developerName, Pageable pageable);

    @Query(value = "select * from task where time >= :start and time < :end", nativeQuery = true)
    List<Task> findTaskByMonth(String start, String end);

}
