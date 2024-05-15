package eco.com.spring.mcsv.account.client.services.impl;

import eco.com.spring.mcsv.account.client.models.Person;
import eco.com.spring.mcsv.account.client.repositories.PersonRepository;
import eco.com.spring.mcsv.account.client.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public void insertPerson(Person person) {
        personRepository.save(person);
    }
}
