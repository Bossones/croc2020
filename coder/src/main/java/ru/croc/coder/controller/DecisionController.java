package ru.croc.coder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.coder.domain.tasks.Decision;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.TaskService;
import ru.croc.coder.service.UserContext;
import ru.croc.coder.service.exceptions.NotFoundException;


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
}
