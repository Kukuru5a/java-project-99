package hexlet.code.mapper;

import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.dto.user.UserDTO;
import hexlet.code.dto.user.UserUpdateDTO;
import hexlet.code.model.User;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {
    @Autowired
    private PasswordEncoder encoder;

    @Mapping(target = "passwordOrigin", source = "password")
    public abstract User map(UserCreateDTO dto);

    @Mapping(target = "passwordOrigin", ignore = true)
    public abstract void update(UserUpdateDTO dto, @MappingTarget User model);

    public abstract UserDTO map(User model);

    public abstract List<UserDTO> map(List<User> models);

    @BeforeMapping
    public void encryptCreatePassword(UserCreateDTO dto) {
        var password = dto.getPassword();
        dto.setPassword(encoder.encode(password));
    }

    @BeforeMapping
    public void encryptUpdatePassword(UserUpdateDTO dto, @MappingTarget User model) {
        var password = dto.getPassword();
        if (password != null && password.isPresent()) {
            model.setPasswordOrigin(encoder.encode(password.get()));
        }
    }
}