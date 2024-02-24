package hexlet.code.dto.taskStatus;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class TaskStatusDTO {
    private Long id;

    private String name;

    private String slug;

    private Date createdAt;
}
