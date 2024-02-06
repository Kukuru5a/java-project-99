package hexlet.code.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @Size(min = 1)
    @Column(unique = true)
    @ToString.Include
    private String name;

    @ToString.Include
    private Integer index;

    @ToString.Include
    private String description;

    @ToString.Include
    @OneToMany(fetch = FetchType.EAGER)
    private TaskStatus status;

    @ToString.Include
    @OneToMany(fetch = FetchType.EAGER)
    private User assignee;

    @CreatedDate
    private LocalDate createdAt;
}
