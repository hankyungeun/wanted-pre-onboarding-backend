package wanted.preonboarding.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import wanted.preonboarding.backend.entity.RecruitPosting;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<RecruitPosting, Long>, JpaSpecificationExecutor<RecruitPosting> {
    List<RecruitPosting> findByCompanyId(Long companyId);
}
