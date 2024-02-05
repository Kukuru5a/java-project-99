package hexlet.code.mapper;

import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.dto.user.UserDTO;
import hexlet.code.dto.user.UserUpdateDTO;
import hexlet.code.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-04T11:07:50+0600",
    comments = "version: 1.6.0.Beta1, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    @Override
    public User map(UserCreateDTO dto) {
        encryptCreatePassword( dto );

        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setPasswordOrigin( dto.getPassword() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );

        return user;
    }

    @Override
    public void update(UserUpdateDTO dto, User model) {
        if ( dto == null ) {
            return;
        }

        encryptUpdatePassword( dto, model );

        if ( jsonNullableMapper.isPresent( dto.getEmail() ) ) {
            model.setEmail( jsonNullableMapper.unwrap( dto.getEmail() ) );
        }
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
    public List<UserDTO> map(List<User> models) {
        if ( models == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( models.size() );
        for ( User user : models ) {
            list.add( map( user ) );
        }

        return list;
    }
}
