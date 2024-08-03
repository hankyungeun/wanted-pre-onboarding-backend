package wanted.preonboarding.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Company {
    @Id
    private String id;
    private String name;
    private String country;
    private String region;
}
