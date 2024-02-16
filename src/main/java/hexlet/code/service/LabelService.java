package hexlet.code.service;

import hexlet.code.dto.label.LabelCreateDTO;
import hexlet.code.dto.label.LabelDTO;
import hexlet.code.dto.label.LabelUpdateDTO;
import hexlet.code.mapper.LabelMapper;
import hexlet.code.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    LabelRepository repository;
    @Autowired
    LabelMapper mapper;

    public List<LabelDTO> getAll() {
        return mapper.map(repository.findAll());
    }

    public LabelDTO show(Long id) {
        var label = repository.findById(id).orElseThrow();
        return mapper.map(label);
    }

    public LabelDTO create(LabelCreateDTO dto) {
        var label = mapper.map(dto);
        repository.save(label);
        return mapper.map(label);
    }

    public LabelDTO update(LabelUpdateDTO dto, Long id) {
        var label = repository.findById(id).orElseThrow();
        mapper.update(dto, label);
        repository.save(label);
        return mapper.map(label);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
