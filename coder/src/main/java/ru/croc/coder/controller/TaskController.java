package ru.croc.coder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.croc.coder.controller.dto.TaskDto;
import ru.croc.coder.domain.tasks.Task;
import ru.croc.coder.service.TaskService;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class TaskController {

    private TaskService taskService;

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/course/{courseId}/task")
    public TaskDto createTask(@PathVariable Long courseId, @RequestBody String jsonTaskInformation)
            throws JsonProcessingException {
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

    @PostMapping(value = "/course/{courseId}/tasks/upload_file")
    public String uploadFile(@PathVariable Long courseId, @RequestParam("file") MultipartFile file, ModelMap modelMap)
            throws IOException, ParseException {
        modelMap.addAttribute("file", file);
        taskService.uploadFileWithTasks(courseId, file);
        return "File was uploaded";
    }
}
