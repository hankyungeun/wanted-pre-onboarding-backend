package wanted.preonboarding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.preonboarding.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
