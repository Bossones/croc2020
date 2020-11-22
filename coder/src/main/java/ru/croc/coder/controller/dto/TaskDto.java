package ru.croc.coder.controller.dto;

import ru.croc.coder.domain.tasks.TaskLevel;

import java.time.LocalDateTime;

public class TaskDto {

    private String description;

    private UserDto author;

    private TaskLevel level;

    private LocalDateTime timeToDeadLine;

    private LocalDateTime timeOfStart;

    private Integer maxAttempts;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public TaskLevel getLevel() {
        return level;
    }

    public void setLevel(TaskLevel level) {
        this.level = level;
    }

    public LocalDateTime getTimeToDeadLine() {
        return timeToDeadLine;
    }

    public void setTimeToDeadLine(LocalDateTime timeToDeadLine) {
        this.timeToDeadLine = timeToDeadLine;
    }

    public LocalDateTime getTimeOfStart() {
        return timeOfStart;
    }

    public void setTimeOfStart(LocalDateTime timeOfStart) {
        this.timeOfStart = timeOfStart;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
