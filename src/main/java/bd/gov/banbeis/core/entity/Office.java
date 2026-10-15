package bd.gov.banbeis.core.entity;

import io.quarkus.data.hibernate.ManagedRepository;
import io.quarkus.data.hibernate.WithId;
import jakarta.persistence.Entity;

@Entity
public class Office extends WithId.AutoUUID {
    public String name;
    public String nameBn;

    public Office(){

    }

    public Office(String name, String nameBn){
        this.name = name;
        this.nameBn = nameBn;
    }

    public interface OfficeRepo extends ManagedRepository<Office>{

    }
}
