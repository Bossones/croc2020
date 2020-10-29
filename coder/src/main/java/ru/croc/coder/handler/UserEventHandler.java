package ru.croc.coder.handler;

import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import ru.croc.coder.domain.users.User;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {

    @HandleBeforeSave
    public void handleBeforeSave(User user) {
        user.setPassword("hash" + user.getPassword().hashCode());
        System.out.println("!!!!: " + user);
    }
}
