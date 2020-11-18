package ru.croc.coder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.AuthenticateService;
import ru.croc.coder.service.UserContext;
import ru.croc.coder.service.exceptions.NotFoundException;

@RestController
public class AuthenticateController {

    private UserRepository userRepository;

    private AuthenticateService authenticateService;

    private UserContext userContext;

    public AuthenticateController(UserRepository userRepository, AuthenticateService authenticateService,
                                  UserContext userContext) {
        this.userRepository = userRepository;
        this.authenticateService = authenticateService;
        this.userContext = userContext;
    }

    @Operation(summary = "Authenticate user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authenticated user", content = {
                    @Content(schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Authenticate failed")
    })
    @GetMapping("/auth/{userId}")
    public UserDto auth(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        userContext.setCurrentUser(user);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    @GetMapping("/auth")
    public ModelAndView authenticate(@RequestBody String jsonUserAuth) throws JsonProcessingException {
        return new ModelAndView("redirect:/auth/" + authenticateService.authenticateUser(jsonUserAuth));
    }
}
