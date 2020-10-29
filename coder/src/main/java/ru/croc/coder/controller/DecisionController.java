package ru.croc.coder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.coder.domain.tasks.Decision;
import ru.croc.coder.service.TaskService;

@RestController
public class DecisionController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/users/{userId}/tasks/{taskId}/decision")
    public Decision submit(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody String code) {
        return taskService.submit(userId, taskId, code);
    }
}
