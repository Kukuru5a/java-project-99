package hexlet.code.controller;

import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.dto.user.UserDTO;
import hexlet.code.dto.user.UserUpdateDTO;
import hexlet.code.services.UserService;
import hexlet.code.utils.UserUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserUtils userUtils;
    @Autowired
    private UserService service;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<UserDTO>> getAll() {
        var res = service.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(res.size()))
                .body(res);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO show(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO create(@Valid @RequestBody UserCreateDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO update(@RequestBody UserUpdateDTO userData, @PathVariable Long id) {
        var currentUser = userUtils.getCurrentUser();
        if (currentUser.getId() == id) {
            return service.update(userData, id);
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        var currentUser = userUtils.getCurrentUser();
        if (currentUser.getId() == id) {
            service.delete(id);
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }
}
