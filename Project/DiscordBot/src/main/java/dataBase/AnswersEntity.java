package dataBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="answers")
/*
@NamedQueries({
        @NamedQuery(name="AnswerEntity.findAll",
                query = "select e from AnswerEntity e"),
})*/
public class AnswersEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="question")
    private String question;
    @Column(name="answer")
    private String answer;

    public AnswersEntity() {
    }

    public AnswersEntity(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswersEntity{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
