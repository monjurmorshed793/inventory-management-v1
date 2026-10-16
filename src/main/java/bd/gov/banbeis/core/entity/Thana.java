package bd.gov.banbeis.core.entity;

import io.quarkus.data.hibernate.ManagedEntity;
import io.quarkus.data.hibernate.ManagedRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Thana implements ManagedEntity.CustomId {
    @Id
    @Column(name="thana_id")
    public Long id;
    @Column(name="thana_name")
    public String name;
    @Column(name="thana_name_bn")
    public String nameBn;
    @ManyToOne
    public District district;

    public interface ThanaRepo extends ManagedRepository<Thana>{

    }
}
