package hexlet.code.mapper;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.dto.UserDTO;
import hexlet.code.dto.UserUpdateDTO;
import hexlet.code.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-28T13:19:31+0600",
    comments = "version: 1.6.0.Beta1, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User map(UserCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );

        return user;
    }

    @Override
    public UserDTO map(User model) {
        if ( model == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName( model.getFirstName() );
        userDTO.setLastName( model.getLastName() );
        userDTO.setEmail( model.getEmail() );
        userDTO.setCreatedAt( model.getCreatedAt() );
        userDTO.setUpdatedAt( model.getUpdatedAt() );

        return userDTO;
    }

    @Override
    public void update(UserUpdateDTO dto, User usr) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getEmail() != null ) {
            usr.setEmail( dto.getEmail() );
        }
        if ( dto.getPassword() != null ) {
            usr.setPassword( dto.getPassword() );
        }
    }
}
