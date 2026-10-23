package bd.gov.banbeis.security.entity;

import bd.gov.banbeis.core.entity.Department;
import bd.gov.banbeis.core.entity.District;
import bd.gov.banbeis.core.entity.Division;
import bd.gov.banbeis.core.entity.Office;
import bd.gov.banbeis.core.entity.Thana;
import io.quarkus.data.hibernate.WithId.AutoUUID;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "employee_office")
public class EmployeeOffice extends AutoUUID{
    @ManyToOne 
    public Office office;
    @ManyToOne 
    public Department department;
    @ManyToOne 
    public Division division;
    @ManyToOne 
    public District district;
    @ManyToOne 
    public Thana thana;
    public Boolean mainCharge;
    public Boolean additionalCharge;
}
