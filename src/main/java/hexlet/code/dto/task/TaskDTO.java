package hexlet.code.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class TaskDTO {
    private Long id;

    private String title;

    private Integer index;

    private String content;

    private String status;

    @JsonProperty("assignee_id")
    private Integer assigneeId;

    private LocalDate createdAt;

    private Set<Long> taskLabelIds;
}
