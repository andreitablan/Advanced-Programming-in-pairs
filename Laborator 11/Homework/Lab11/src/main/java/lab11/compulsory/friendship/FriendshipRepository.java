package lab11.compulsory.friendship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship1, Long> {

    /**
     * Adds a new entry in the friendship database.
     * @param id The friendship's id.
     * @param idPerson1 The first person's id.
     * @param idPerson2 The second person's id.
     */
    @Modifying
    @Query(value = "insert into Friendship1 (id,id_Person1,id_Person2) values (:id, :idPerson1, :idPerson2)", nativeQuery = true)
    @Transactional
    void logFriendship(@Param("id") Long id, @Param("idPerson1") Long idPerson1, @Param("idPerson2") Long idPerson2);
}
