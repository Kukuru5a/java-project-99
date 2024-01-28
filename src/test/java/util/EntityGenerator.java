package util;

import hexlet.code.model.User;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EntityGenerator {
    private Faker faker = new Faker();

    @Bean
    public User generateUser() {
        return Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .supply(Select.field(User::getFirstName), () -> faker.name().firstName())
                .supply(Select.field(User::getLastName), () -> faker.name().lastName())
                .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                .supply(Select.field(User::getPasswordOrigin), () -> faker.internet().password(3, 16))
                .ignore(Select.field(User::getCreatedAt))
                .ignore(Select.field(User::getUpdatedAt))
                .create();
    }
}
