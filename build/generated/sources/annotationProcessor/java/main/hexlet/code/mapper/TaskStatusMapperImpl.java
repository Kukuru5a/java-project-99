package hexlet.code.mapper;

import hexlet.code.dto.taskStatus.TaskStatusCreateDTO;
import hexlet.code.dto.taskStatus.TaskStatusDTO;
import hexlet.code.dto.taskStatus.TaskStatusUpdateDTO;
import hexlet.code.model.TaskStatus;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T22:43:52+0600",
    comments = "version: 1.6.0.Beta1, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class TaskStatusMapperImpl extends TaskStatusMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    @Override
    public TaskStatus map(TaskStatusCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TaskStatus taskStatus = new TaskStatus();

        taskStatus.setName( dto.getName() );
        taskStatus.setSlug( dto.getSlug() );

        return taskStatus;
    }

    @Override
    public void update(TaskStatusUpdateDTO dto, TaskStatus model) {
        if ( dto == null ) {
            return;
        }

        if ( jsonNullableMapper.isPresent( dto.getName() ) ) {
            model.setName( jsonNullableMapper.unwrap( dto.getName() ) );
        }
        if ( jsonNullableMapper.isPresent( dto.getSlug() ) ) {
            model.setSlug( jsonNullableMapper.unwrap( dto.getSlug() ) );
        }
    }

    @Override
    public TaskStatusDTO map(TaskStatus model) {
        if ( model == null ) {
            return null;
        }

        TaskStatusDTO taskStatusDTO = new TaskStatusDTO();

        taskStatusDTO.setId( model.getId() );
        taskStatusDTO.setName( model.getName() );
        taskStatusDTO.setSlug( model.getSlug() );
        taskStatusDTO.setCreatedAt( model.getCreatedAt() );

        return taskStatusDTO;
    }

    @Override
    public List<TaskStatusDTO> map(List<TaskStatus> models) {
        if ( models == null ) {
            return null;
        }

        List<TaskStatusDTO> list = new ArrayList<TaskStatusDTO>( models.size() );
        for ( TaskStatus taskStatus : models ) {
            list.add( map( taskStatus ) );
        }

        return list;
    }
}
