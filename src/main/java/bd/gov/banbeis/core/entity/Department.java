package bd.gov.banbeis.core.entity;

import io.quarkus.data.hibernate.ManagedRepository;
import io.quarkus.data.hibernate.WithId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Department extends WithId.AutoUUID {
    public String name;
    public String nameBn;
    @ManyToOne
    public Office office;


    public interface DepartmentRepo extends ManagedRepository<Department>{

    }
}
