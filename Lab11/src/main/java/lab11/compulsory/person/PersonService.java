package lab11.compulsory.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findByName(String name){
        return personRepository.findByName(name);
    }

    public void save(Person person){
        personRepository.save(person);
    }

    public Person findById(long id){
        return personRepository.findById(id);
    }

    public void deletePerson(Person person){
        personRepository.delete(person);
    }
}