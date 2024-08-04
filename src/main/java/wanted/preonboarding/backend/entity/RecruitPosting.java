package wanted.preonboarding.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecruitPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;
    private String position;
    private int reward;
    private String contents;
    private String skill;
}
