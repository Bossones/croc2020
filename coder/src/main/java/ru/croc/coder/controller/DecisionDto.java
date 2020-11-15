package ru.croc.coder.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.croc.coder.domain.tasks.ProcessStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionDto {

    private Long decisionId;

    private Long taskId;

    @JsonProperty("submit time")
    private LocalDateTime time;

    private ProcessStatus checkStatus;

    private Boolean solved;

    public Long getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(Long decisionId) {
        this.decisionId = decisionId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ProcessStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(ProcessStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }
}
