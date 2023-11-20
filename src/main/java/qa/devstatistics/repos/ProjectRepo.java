package qa.devstatistics.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import qa.devstatistics.dao.Project;

import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.09.2023
 */
@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {

    List<Project> findAll();

    Project findProjectById(long id);

    boolean existsProjectByNameIgnoreCase(String name);
}
