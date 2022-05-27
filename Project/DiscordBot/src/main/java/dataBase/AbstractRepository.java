package dataBase;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.io.Serializable;

public abstract class AbstractRepository <T extends AbstractEntity,K extends Serializable>{
    protected EntityManager manager;

    public AbstractRepository(){
        Manager managerInstance=Manager.getInstance();
        manager=managerInstance.getManager();
    }

    public String findByQuestion(K question){
        return manager.createQuery("select a from AnswersEntity a where a.question='" + question + "'", AnswersEntity.class).getResultList().get(0).getAnswer();
    }

}
