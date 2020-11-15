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
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.TaskService;
import ru.croc.coder.service.UserContext;
import ru.croc.coder.service.exceptions.NotFoundException;


@RestController
public class DecisionController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserContext userContext;

    @Autowired
    private UserRepository userRepository;

/*    @Autowired
    private ModelMapper modelMapper;*/

    @Operation(summary = "Authenticate user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authenticated user", content = {
                    @Content(schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Authenticate failed")
    })
    @PostMapping("/auth/{userId}")
    public UserDto auth(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        userContext.setCurrentUser(user);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    @PostMapping("/tasks/{taskId}/decisions")
    public DecisionDto submit(@PathVariable Long taskId, @RequestBody String code) throws InterruptedException {
        Decision decision = taskService.submit(taskId, code);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(decision, DecisionDto.class);
    }
}
