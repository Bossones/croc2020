package ru.croc.coder.domain.tasks;

import ru.croc.coder.domain.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Decision")
@Table(name = "DECISIONS")
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long decisionId;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User author;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Task task;

    @Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    @Embedded
    private Code code;

    @Enumerated(EnumType.STRING)
    @Column
    private ProcessStatus checkStatus;

    @Column(name = "solved")
    private Boolean solved;

    public ProcessStatus getCheckStatus() {
        return checkStatus;
    }

    public Decision setCheckStatus(ProcessStatus checkStatus) {
        this.checkStatus = checkStatus;
        return this;
    }

    public Long getDecisionId() {
        return decisionId;
    }

    public Decision setDecisionId(Long decisionId) {
        this.decisionId = decisionId;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Decision setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Task getTask() {
        return task;
    }

    public Decision setTask(Task task) {
        this.task = task;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Decision setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public Code getCode() {
        return code;
    }

    public Decision setCode(Code code) {
        this.code = code;
        return this;
    }

    public Boolean getSolved() {
        return solved;
    }

    public Decision setSolved(Boolean solved) {
        this.solved = solved;
        return this;
    }
}
