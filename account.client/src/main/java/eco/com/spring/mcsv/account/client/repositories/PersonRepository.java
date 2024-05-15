package eco.com.spring.mcsv.account.client.repositories;

import eco.com.spring.mcsv.account.client.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Repository

public interface PersonRepository extends CrudRepository<Person,String> {

}
