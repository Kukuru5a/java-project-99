package hexlet.code.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.FetchType;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task implements BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(min = 1)
    @Column(unique = true)
    private String name;

    private Integer index;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private User assignee;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Label> labels;
}
