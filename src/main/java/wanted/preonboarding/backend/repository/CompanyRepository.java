package wanted.preonboarding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.preonboarding.backend.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
