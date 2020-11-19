package ru.croc.coder.domain.tasks;

import org.hibernate.annotations.Proxy;
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

    @Column(name = "description", nullable = false)
    @Lob
    private String description;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Teacher author;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private TaskLevel level;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Course course;

    @Embedded
    private Code template;

    @Column(name = "timeToDeadLine", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime timeToDeadLine;

    @Column(name = "timeOfBeginning", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime timeOfStart;

    private Integer maxAttempts;

    public Task setCourse(Course course) {
        this.course = course;
        return this;
    }

    public LocalDateTime getTimeOfStart() {
        return timeOfStart;
    }

    public Task setTimeOfStart(LocalDateTime timeOfBeginning) {
        this.timeOfStart = timeOfBeginning;
        return this;
    }

    public LocalDateTime getTimeToDeadLine() {
        return timeToDeadLine;
    }

    public Task setTimeToDeadLine(LocalDateTime timeToDeadLine) {
        this.timeToDeadLine = timeToDeadLine;
        return this;
    }

    public Course getCourse() {
        return course;
    }

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
