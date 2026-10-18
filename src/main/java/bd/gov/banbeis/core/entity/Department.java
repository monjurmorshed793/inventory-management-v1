package bd.gov.banbeis.core.entity;

import io.quarkus.data.hibernate.ManagedRepository;
import io.quarkus.data.hibernate.WithId;
import jakarta.data.repository.Find;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.List;
import java.util.UUID;

@Entity
public class Department extends WithId.AutoUUID {
    public String name;
    public String nameBn;
    @ManyToOne
    public Office office;

    public Department(){

    }

    public Department(String name, String nameBn, Office office){
        this.name = name;
        this.nameBn = nameBn;
        this.office = office;
    }


    public interface DepartmentRepo extends ManagedRepository.CustomId<Department, UUID> {
        @Find
        List<Department> getByOffice(Office office);
    }
}
