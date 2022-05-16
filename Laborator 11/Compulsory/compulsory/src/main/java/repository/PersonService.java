package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    public PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }
}
