package ru.croc.coder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.coder.domain.tasks.Task;
import ru.croc.coder.service.TaskService;

import java.time.LocalDateTime;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/course/{courseId}/task")
    public TaskDto createTask(@PathVariable Long courseId, @RequestBody String jsonTaskInformation) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonTaskRootNode = objectMapper.readTree(jsonTaskInformation);
        Task task = objectMapper.readValue(jsonTaskInformation, Task.class);
        LocalDateTime startTime = LocalDateTime.parse(jsonTaskRootNode.get("startTime").asText());
        LocalDateTime deadLineTime = LocalDateTime.parse(jsonTaskRootNode.get("deadLineTime").asText());
        task
                .setTimeToDeadLine(deadLineTime)
                .setTimeOfStart(startTime);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(taskService.createTask(courseId, task), TaskDto.class);
    }
}
