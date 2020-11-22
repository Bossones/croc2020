package ru.croc.coder.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.croc.coder.controller.dto.DecisionDto;
import ru.croc.coder.domain.tasks.Decision;
import ru.croc.coder.service.TaskService;

import java.util.List;


@RestController
public class DecisionController {


    private TaskService taskService;

    public DecisionController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("tasks/{taskId}/decisions")
    public DecisionDto submit(@PathVariable Long taskId, @RequestBody String code) {
        Decision decision = taskService.submit(taskId, code);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(decision, DecisionDto.class);
    }

    @GetMapping("/decisions_by_author")
    public List<DecisionDto> getDecisionsByAuthor() {
        return taskService.getDecisionsStat();
    }
}
