package lab11.compulsory.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get/person")
    public List<Person> getPersons() {
        return personService.findAll();
    }

    @PostMapping("/add/person")
    public boolean addPerson(@RequestParam(name="name") String name) {
        Person person = personService.findByName(name);
        if (person != null) {
            return false;
        }
        person = new Person(name);
        personService.save(person);
        return true;
    }

    @PutMapping("/update/person")
    public boolean updatePerson(@RequestParam(name="id") long id, @RequestParam(name="name") String name) {
        Person person = personService.findById(id);
        if (person == null) {
            return false;
        } else {
            person.setName(name);
            personService.save(person);
            return true;
        }
    }

    @DeleteMapping("/delete/person")
    public boolean deletePerson(@RequestParam(name="id") long id) {
        Person person = personService.findById(id);
        if (person == null) {
            return false;
        } else {

            personService.deletePerson(person);
            return true;
        }
    }

}