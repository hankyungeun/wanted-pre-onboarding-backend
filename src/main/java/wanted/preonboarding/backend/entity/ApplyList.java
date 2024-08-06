package wanted.preonboarding.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ApplyList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private RecruitPosting recruitPosting;
}
