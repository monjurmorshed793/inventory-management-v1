package bd.gov.banbeis.core.entity;

import io.quarkus.data.hibernate.ManagedEntity;
import io.quarkus.data.hibernate.ManagedRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class District implements ManagedEntity.CustomId {
    @Id
    @Column(name="district_id")
    public Long id;
    @Column(name="district_name")
    public String name;
    @Column(name="district_name_bn")
    public String nameBn;
    @ManyToOne
    public Division division;

    public interface DistrictRepo extends ManagedRepository<District>{

    }
}
