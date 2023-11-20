package qa.devstatistics.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import qa.devstatistics.dao.Developer;

import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.09.2023
 */
@Repository
public interface DeveloperRepo extends CrudRepository<Developer, Long> {

    List<Developer> findAll();

    Page<Developer> findAll(Pageable pageable);

    List<Developer> findAllByDismissFalse();

    List<Developer> findAllByDismissTrue();

    Developer findDeveloperById(long id);

    boolean existsDeveloperBySurnameIgnoreCaseAndNameIgnoreCase(String surname, String name);
}
