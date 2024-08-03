package wanted.preonboarding.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class RecruitPosting {
    @Id
    private String id;
    @ManyToOne
    private Company company;
    private String position;
    private int reward;
    private String contents;
    private String skill;
}
