package dataBase;

import java.util.List;

public class AnswersRepository extends AbstractRepository<AnswersEntity, String> {

    public String findById(int id) {
        return Manager.getInstance().getManager().find(AnswersEntity.class, id).getAnswer();
    }

    public List<AnswersEntity> findAll() {
        return Manager.getInstance().getManager().createNamedQuery("AnswersEntity.findAll", AnswersEntity.class).getResultList();
    }
}

