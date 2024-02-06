package hexlet.code.mapper;

import hexlet.code.dto.task.TaskCreateDTO;
import hexlet.code.dto.task.TaskDTO;
import hexlet.code.dto.task.TaskUpdateDTO;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T22:43:53+0600",
    comments = "version: 1.6.0.Beta1, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl extends TaskMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;
    @Autowired
    private ReferenceMapper referenceMapper;

    @Override
    public Task map(TaskCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Task task = new Task();

        task.setName( dto.getTitle() );
        task.setDescription( dto.getContent() );
        task.setStatus( slugToTaskStatus( dto.getStatus() ) );
        task.setAssignee( referenceMapper.toEntity( dto.getAssigneeId(), User.class ) );
        task.setLabels( taskLabelIdsToLabels( dto.getTaskLabelIds() ) );
        task.setIndex( dto.getIndex() );

        return task;
    }

    @Override
    public void update(TaskUpdateDTO dto, Task model) {
        if ( dto == null ) {
            return;
        }

        if ( jsonNullableMapper.isPresent( dto.getTitle() ) ) {
            model.setName( jsonNullableMapper.unwrap( dto.getTitle() ) );
        }
        if ( jsonNullableMapper.isPresent( dto.getContent() ) ) {
            model.setDescription( jsonNullableMapper.unwrap( dto.getContent() ) );
        }
        if ( jsonNullableMapper.isPresent( dto.getStatus() ) ) {
            model.setStatus( slugToTaskStatus( jsonNullableMapper.unwrap( dto.getStatus() ) ) );
        }
        if ( jsonNullableMapper.isPresent( dto.getAssigneeId() ) ) {
            model.setAssignee( referenceMapper.toEntity( jsonNullableMapper.unwrap( dto.getAssigneeId() ), User.class ) );
        }
        if ( model.getLabels() != null ) {
            if ( jsonNullableMapper.isPresent( dto.getTaskLabelIds() ) ) {
                model.getLabels().clear();
                model.getLabels().addAll( taskLabelIdsToLabels( jsonNullableMapper.unwrap( dto.getTaskLabelIds() ) ) );
            }
        }
        else {
            if ( jsonNullableMapper.isPresent( dto.getTaskLabelIds() ) ) {
                model.setLabels( taskLabelIdsToLabels( jsonNullableMapper.unwrap( dto.getTaskLabelIds() ) ) );
            }
        }
        if ( jsonNullableMapper.isPresent( dto.getIndex() ) ) {
            model.setIndex( jsonNullableMapper.unwrap( dto.getIndex() ) );
        }
    }

    @Override
    public TaskDTO map(Task model) {
        if ( model == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setTitle( model.getName() );
        taskDTO.setContent( model.getDescription() );
        taskDTO.setStatus( modelStatusSlug( model ) );
        Long id = modelAssigneeId( model );
        if ( id != null ) {
            taskDTO.setAssigneeId( id.intValue() );
        }
        taskDTO.setTaskLabelIds( labelsToLabelIds( model.getLabels() ) );
        taskDTO.setId( model.getId() );
        taskDTO.setIndex( model.getIndex() );
        taskDTO.setCreatedAt( model.getCreatedAt() );

        return taskDTO;
    }

    @Override
    public List<TaskDTO> map(List<Task> models) {
        if ( models == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( models.size() );
        for ( Task task : models ) {
            list.add( map( task ) );
        }

        return list;
    }

    private String modelStatusSlug(Task task) {
        TaskStatus status = task.getStatus();
        if ( status == null ) {
            return null;
        }
        return status.getSlug();
    }

    private Long modelAssigneeId(Task task) {
        User assignee = task.getAssignee();
        if ( assignee == null ) {
            return null;
        }
        return assignee.getId();
    }
}
