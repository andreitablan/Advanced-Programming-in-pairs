package compulsory;

import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    private final List<Person> persons = new ArrayList<>();


    @Autowired
    public Controller() {
        persons.add(new Person(1, "Dan"));
        persons.add(new Person(2, "Adrian"));
    }


    @RequestMapping("/person/all")
    public List<Person> getPersons() {
        return persons;
    }

    @PostMapping("/person/insert")
    public Person insertPerson(@RequestParam(name = "name") String name) {
        int id = persons.size() + 1;
        Person person = new Person(id, name);
        persons.add(person);
        return person;
    }

    @PutMapping("/person/update")
    public void updatePerson(@RequestParam(name = "id") int id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "updatedName") String updatedName) {
        for (Person person : persons) {
            if(person.getName().equals(name) && person.getId() == id)
                person.setName(updatedName);
        }
    }

    @DeleteMapping("/person/delete")
    public void deletePerson(@RequestParam(name="id") int id) {
        persons.removeIf(person -> person.getId() == id);
    }
}
