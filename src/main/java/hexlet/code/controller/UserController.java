package hexlet.code.controller;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.dto.UserDTO;
import hexlet.code.dto.UserUpdateDTO;
import hexlet.code.handler.exception.EntityNotFoundException;
import hexlet.code.mapper.UserMapper;
import hexlet.code.model.User;
import hexlet.code.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> userList() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u->userMapper.map(u)).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO userShow(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        var userDTO = userMapper.map(user);
        return userDTO;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@Valid @RequestBody UserCreateDTO userData) {
        var user = userMapper.map(userData);
        userRepository.save(user);
        var userDTO = userMapper.map(user);
        return userDTO;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO userUpd(@PathVariable Long id, @RequestBody UserUpdateDTO userData ) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        userMapper.update(userData, user);
        userRepository.save(user);
        var userDTO = userMapper.map(user);
        return userDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
