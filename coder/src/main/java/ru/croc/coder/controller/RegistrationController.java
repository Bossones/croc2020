package ru.croc.coder.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.coder.controller.dto.UserDto;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.service.RegistrationService;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody String jsonUserInformation) throws JsonProcessingException {
        User user = registrationService.registrationUser(jsonUserInformation);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }
}
