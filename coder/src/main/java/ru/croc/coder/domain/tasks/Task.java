package ru.croc.coder.domain.tasks;

import ru.croc.coder.domain.users.Teacher;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Task")
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description", nullable = false)
    @Lob
    private String description;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Teacher author;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private TaskLevel level;

    @Embedded
    private Code template;

    @Column(nullable = false)
    private LocalDateTime timeToDeadLine;

    public LocalDateTime getTimeToDeadLine() {
        return timeToDeadLine;
    }

    public Task setTimeToDeadLine(LocalDateTime timeToDeadLine) {
        this.timeToDeadLine = timeToDeadLine;
        return this;
    }

    private Integer maxAttempts;

    public Teacher getAuthor() {
        return author;
    }

    public Task setAuthor(Teacher author) {
        this.author = author;
        return this;
    }

    public TaskLevel getLevel() {
        return level;
    }

    public Task setLevel(TaskLevel level) {
        this.level = level;
        return this;
    }

    public Code getTemplate() {
        return template;
    }

    public Task setTemplate(Code template) {
        this.template = template;
        return this;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public Task setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String nameOfTask) {
        Objects.requireNonNull(nameOfTask);
        this.description = nameOfTask;
        return this;
    }
}
