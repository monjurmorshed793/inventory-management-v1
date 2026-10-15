package bd.gov.banbeis.core.entity;

import io.quarkus.data.hibernate.ManagedRepository;
import io.quarkus.data.hibernate.WithId;
import jakarta.persistence.Entity;

@Entity
public class Designation extends WithId.AutoUUID {
    public String name;
    public String nameBn;

    public interface DesignationRepo extends ManagedRepository<Designation>{

    }
}
