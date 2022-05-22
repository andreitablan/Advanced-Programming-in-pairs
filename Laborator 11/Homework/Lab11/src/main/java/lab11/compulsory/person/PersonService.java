package lab11.compulsory.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final FriendshipRepository friendshipRepository;
    private HashMap<Long, Integer> numberOfFriendships = new HashMap<Long, Integer>();

    @Autowired
    public PersonService(PersonRepository personRepository, FriendshipRepository friendshipRepository) {
        this.personRepository = personRepository;
        this.friendshipRepository = friendshipRepository;
    }

    public List<Optional<Person>> findMostPopular(int popularPeople) {
        List<Friendship1> friendshipList = friendshipRepository.findAll();
        for (Friendship1 friendship1 : friendshipList) {
            long idPerson1 = friendship1.getIdPerson1();
            long idPerson2 = friendship1.getIdPerson2();
            if (!numberOfFriendships.containsKey(idPerson1)) {
                numberOfFriendships.put(idPerson1, 1);
            } else {
                numberOfFriendships.put(idPerson1, numberOfFriendships.get(idPerson1) + 1);
            }
            if (!numberOfFriendships.containsKey(idPerson2)) {
                numberOfFriendships.put(idPerson2, 1);
            } else {
                numberOfFriendships.put(idPerson2, numberOfFriendships.get(idPerson2) + 1);
            }
        }
        List<HashMap.Entry<Long, Integer>> list = new LinkedList<HashMap.Entry<Long, Integer>>(numberOfFriendships.entrySet());
        Collections.sort(list, new Comparator<HashMap.Entry<Long, Integer>>() {
            public int compare(HashMap.Entry<Long, Integer> object1,
                               HashMap.Entry<Long, Integer> object2) {
                return (object1.getValue()).compareTo(object2.getValue());
            }
        });
        Collections.reverse(list);
        List<Optional<Person>> popularPerson = new ArrayList<>();
        for (int index = 0; index < popularPeople; index++)
            popularPerson.add(personRepository.findById(list.get(index).getKey()));
        return popularPerson;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findByName(String name) {
        return personRepository.findByName(name);
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public Person findById(long id) {
        return personRepository.findById(id);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    public void save(long id, Person person1, Person person2) {
        friendshipRepository.logFriendship(id, person1.getId(), person2.getId());
    }


    public List<Friendship1> findAllFriendships() {
        return friendshipRepository.findAll();
    }
}