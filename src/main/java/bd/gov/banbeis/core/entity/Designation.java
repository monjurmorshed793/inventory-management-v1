package bd.gov.banbeis.core.entity;

import java.util.UUID;

import io.quarkus.data.hibernate.ManagedRepository;
import io.quarkus.data.hibernate.WithId;
import jakarta.persistence.Entity;

@Entity
public class Designation extends WithId.AutoUUID {
    public String name;
    public String nameBn;
    public Integer grade;

    public Designation(){

    }

    public Designation(String name){
        this.name = name;
    }

    public interface DesignationRepo extends ManagedRepository.CustomId<Designation, UUID>{

    }
}
