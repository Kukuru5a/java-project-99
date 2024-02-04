package hexlet.code.componnt;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.mapper.UserMapper;
import hexlet.code.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
