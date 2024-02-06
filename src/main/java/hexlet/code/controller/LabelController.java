package hexlet.code.controller;

import hexlet.code.dto.label.LabelCreateDTO;
import hexlet.code.dto.label.LabelDTO;
import hexlet.code.dto.label.LabelUpdateDTO;
import hexlet.code.repositories.LabelRepository;
import hexlet.code.services.LabelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/labels")
public class LabelController {
    @Autowired
    LabelRepository labelRepository;
    @Autowired
    LabelService labelService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<LabelDTO>> getAll() {
        var res = labelService.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(res.size()))
                .body(res);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    LabelDTO show(@PathVariable Long id) {
        return labelService.show(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    LabelDTO create(@Valid @RequestBody LabelCreateDTO dto) {
        return labelService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    LabelDTO update(@RequestBody LabelUpdateDTO dto, @PathVariable Long id) {
        return labelService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        labelService.delete(id);
    }
}
