package wanted.preonboarding.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.preonboarding.backend.entity.RecruitPosting;

@Repository
public interface RecruitRepository extends JpaRepository<RecruitPosting, Long> {

}
