package lab11.compulsory.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final FriendshipRepository friendshipRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, FriendshipRepository friendshipRepository) {
        this.personRepository = personRepository;
        this.friendshipRepository = friendshipRepository;
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

    public void save(Person person1, Person person2){
        friendshipRepository.logFriendship(person1.getId(),person2.getId());
    }

    public List<Friendship1> findAllFriendships(){
        return friendshipRepository.findAll();}
}