package ru.croc.coder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.WrongPasswordException;

@Service
public class AuthenticateService {

    private UserRepository userRepository;

    public AuthenticateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Long authenticateUser(String jsonUserAuth) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonUserNodeRoot = objectMapper.readTree(jsonUserAuth);
        String email = jsonUserNodeRoot.get("email").asText();
        String password = jsonUserNodeRoot.get("password").asText();
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(NotFoundException::new);
        if (!user.getPassword().equals(password)) {
            throw new WrongPasswordException("Wrong password!");
        }
        return user.getId();
    }
}
