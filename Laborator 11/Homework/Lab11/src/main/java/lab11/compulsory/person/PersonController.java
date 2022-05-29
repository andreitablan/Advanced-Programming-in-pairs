package lab11.compulsory.person;

import lab11.compulsory.friendship.Friendship1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get/hello")
    public String gethello() {
        return "Hello";
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

    @PostMapping("add/friend")
    public void addFriend(@RequestParam(name="id") long id,@RequestParam(name="id1") long id1,@RequestParam(name="id2") long id2){

        Person person1= personService.findById(id1);
        Person person2= personService.findById(id2);
        personService.save(id,person1,person2);
    }

    @GetMapping("get/friend")
    public List<Friendship1> getFriendships(){return personService.findAllFriendships();}

    @GetMapping("getPopular/friend")
    public List<Optional<Person>> getPopular(@RequestParam(name="popularPeople") int popularPeople){ return personService.findMostPopular(popularPeople);}

}