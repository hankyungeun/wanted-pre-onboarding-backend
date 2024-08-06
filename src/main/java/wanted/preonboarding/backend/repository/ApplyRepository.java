package wanted.preonboarding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preonboarding.backend.entity.ApplyList;
import wanted.preonboarding.backend.entity.RecruitPosting;
import wanted.preonboarding.backend.entity.User;

public interface ApplyRepository extends JpaRepository<ApplyList, Long> {
    boolean existsByUserAndRecruitPosting(User user, RecruitPosting recruitPosting);
}
