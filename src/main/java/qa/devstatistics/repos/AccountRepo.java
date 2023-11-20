package qa.devstatistics.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import qa.devstatistics.dao.Account;

import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 28.09.2023
 */

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {

    Account findByUsername(String username);

    Account findById(long id);

    List<Account> findAll();
}
