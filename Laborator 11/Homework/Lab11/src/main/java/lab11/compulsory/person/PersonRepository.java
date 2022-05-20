package lab11.compulsory.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT T FROM Person T WHERE T.name=?1")
    public abstract Person findByName(String name);
    @Query("SELECT T FROM Person T WHERE T.id=?1")
    public abstract Person findById(long id);
}