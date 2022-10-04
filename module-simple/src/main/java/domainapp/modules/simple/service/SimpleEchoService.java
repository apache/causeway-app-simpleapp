package domainapp.modules.simple.service;

import org.apache.isis.applib.services.user.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class SimpleEchoService {
    @Inject
    private UserService userService;

    public String greet(String greeting) {
        String username = userService.currentUserName().orElse("unknown user");

        return greeting + ", " + username + "!";
    }
}
