package ru.croc.coder.domain.tasks;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   /* @ManyToOne
    private Course course;*/

    @Column(nullable = false)
    private String nameOfTask;

    /*@OneToOne
    private Decision decisionOfTask;*/

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

   /* public Course getCourse() {
        return course;
    }

    public Task setCourse(Course course) {
        this.course = course;
        return this;
    }*/

    public String getNameOfTask() {
        return nameOfTask;
    }

    public Task setNameOfTask(String nameOfTask) {
        Objects.requireNonNull(nameOfTask);
        this.nameOfTask = nameOfTask;
        return this;
    }

   /* public Decision getDecisionOfTask() {
        return decisionOfTask;
    }

    public Task setDecisionOfTask(Decision decisionOfTask) {
        Objects.requireNonNull(decisionOfTask);
        this.decisionOfTask = decisionOfTask;
        return this;
    }*/
}
