package hexlet.code.dto.taskStatus;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskStatusDTO {
    private Long id;

    private String name;

    private String slug;

    private LocalDateTime createdAt;
}
