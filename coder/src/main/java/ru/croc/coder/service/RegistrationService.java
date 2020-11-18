package ru.croc.coder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.exceptions.InvalidEmailException;
import ru.croc.coder.service.exceptions.PasswordsDontMatchException;
import ru.croc.coder.service.exceptions.UnknownRoleException;
import ru.croc.coder.service.exceptions.UserExistsException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class RegistrationService {

    private UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User registrationUser(String jsonUserInformation) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode jsonUserNodeRoot = objectMapper.readTree(jsonUserInformation);
        String userEmail = jsonUserNodeRoot.get("email").asText();

        checkPasswords(jsonUserNodeRoot.get("password").asText(),
                jsonUserNodeRoot.get("passwordForConfirmation").asText());

        checkValidEmail(userEmail);

        findUserByEmail(userEmail);

        JsonNode jsonUserNodeRole = jsonUserNodeRoot.get("role");
        String role = jsonUserNodeRole.asText();
        User user;
        if (role.equals("student")) {
            user = objectMapper.readValue(jsonUserInformation, Student.class);
        } else if (role.equals("teacher")) {
            user = objectMapper.readValue(jsonUserInformation, Teacher.class);
        } else {
            throw new UnknownRoleException("UnknownRoleException");
        }

        userRepository.save(user);
        return user;
    }

    private void checkPasswords(String password, String passwordForConfirmation) {
        Objects.requireNonNull(password);
        Objects.requireNonNull(passwordForConfirmation);
        if (!password.equals(passwordForConfirmation)) throw new PasswordsDontMatchException("Passwords don't match");
    }

    private void findUserByEmail(String email) {
        Objects.requireNonNull(email);
        if (userRepository.findByEmailIgnoreCase(email).isPresent())
            throw new UserExistsException("This user already exists");
    }

    private void checkValidEmail(String email) {
        Pattern patternEmail = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher matcherEmail = patternEmail.matcher(email);
        if (!matcherEmail.find()) {
            throw new InvalidEmailException("Invalid email");
        }
    }

}
