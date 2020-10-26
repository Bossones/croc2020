package ru.croc.coder.domain.tasks;

import ru.croc.coder.domain.users.Student;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long decisionId;

    @Column(nullable = false)
    private String content;

/*    @OneToOne
    private Student student;*/

    @Column(nullable = false)
    private boolean solved;

    public Long getDecisionId() {
        return decisionId;
    }

    public Decision setDecisionId(Long decisionId) {
        this.decisionId = decisionId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Decision setContent(String content) {
        Objects.requireNonNull(content);
        this.content = content;
        return this;
    }

    /*public Student getStudent() {
        return student;
    }

    public Decision setStudent(Student student) {
        Objects.requireNonNull(student);
        this.student = student;
        return this;
    }*/

    public boolean isSolved() {
        return solved;
    }

    public Decision setSolved(boolean solved) {
        this.solved = solved;
        return this;
    }
}
