package dataBase;

import java.util.List;

public class AnswersRepository {

    public String findById(int id){
       return Manager.getInstance().getManager().find(AnswersEntity.class,id).getAnswer();
    }

    public String findByQuestion(String question){
        return Manager.getInstance().getManager().createQuery("select a from AnswersEntity a where a.question='" + question + "'", AnswersEntity.class).getResultList().get(0).getAnswer();
    }
    public List<AnswersEntity> findAll(){
        return Manager.getInstance().getManager().createNamedQuery("AnswersEntity.findAll",AnswersEntity.class).getResultList();
    }
}
