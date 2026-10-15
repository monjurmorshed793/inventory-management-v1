package bd.gov.banbeis.core.entity;

import io.quarkus.data.hibernate.ManagedEntity;
import io.quarkus.data.hibernate.ManagedRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Division implements ManagedEntity.CustomId {
    @Id
    @Column(name="division_id")
    public Long id;
    @Column(name = "division_name")
    public String name;
    @Column(name="division_name_bn")
    public String nameBn;

    public interface DivisionRepo extends ManagedRepository<Division>{

    }
}
