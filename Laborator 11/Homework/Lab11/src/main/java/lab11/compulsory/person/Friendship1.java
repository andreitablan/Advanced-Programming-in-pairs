package lab11.compulsory.person;

import javax.persistence.*;

@Entity
@Table(name="friendship1")
public class Friendship1 {
    @Id
    private Long id;
    @Column
    private Long idPerson1;
    @Column
    private Long idPerson2;

    public Friendship1() {
    }

    public Friendship1(Long idPerson1, Long idPerson2) {
        this.idPerson1 = idPerson1;
        this.idPerson2 = idPerson2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPerson1() {
        return idPerson1;
    }

    public void setIdPerson1(Long idPerson1) {
        this.idPerson1 = idPerson1;
    }

    public Long getIdPerson2() {
        return idPerson2;
    }

    public void setIdPerson2(Long idPerson2) {
        this.idPerson2 = idPerson2;
    }
}
