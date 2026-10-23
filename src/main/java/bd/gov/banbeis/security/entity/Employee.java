package bd.gov.banbeis.security.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import bd.gov.banbeis.core.entity.Department;
import bd.gov.banbeis.core.entity.Designation;
import bd.gov.banbeis.core.entity.District;
import bd.gov.banbeis.core.entity.Division;
import bd.gov.banbeis.core.entity.Office;
import bd.gov.banbeis.core.entity.Thana;
import io.quarkus.data.hibernate.ManagedRepository;
import io.quarkus.data.hibernate.WithId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "employee")
public class Employee extends WithId.AutoUUID{
    public String name;
    public String nameBn;
    public String email;
    public String mobile;
    public String alternativeMobile;
    @OneToMany 
    public List<EmployeeOffice> offices;
    @ManyToOne 
    public Designation designation;
    public LocalDate joiningDate;
    public Boolean isActive;
    public Boolean hasUsername;
    public LocalDate prlDate;
    public LocalDate resignDate;
    public String resignReason;
    @OneToMany 
    public List<Role> roles;
    @OneToMany 
    public List<Address> addresses;
    @OneToMany 
    public List<EducationalQualification> educationalQualifications;

    public interface EmployeeRepo extends ManagedRepository.CustomId<Employee, UUID>{
        
    }
}
