package hexlet.code.utils;

import hexlet.code.model.User;
import hexlet.code.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        var authintication = SecurityContextHolder.getContext().getAuthentication();
        if (authintication == null || !authintication.isAuthenticated()) {
            return null;
        }
        var email = authintication.getName();
        return userRepository.findByEmail(email).get();
    }
}
