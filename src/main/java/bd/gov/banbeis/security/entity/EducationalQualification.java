package bd.gov.banbeis.security.entity;

import java.util.UUID;

import bd.gov.banbeis.security.enumeration.DegreeType;
import bd.gov.banbeis.security.enumeration.ResultDivisionType;
import io.quarkus.data.hibernate.ManagedRepository;
import io.quarkus.data.hibernate.WithId.AutoUUID;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name="educational_qualification")
public class EducationalQualification extends AutoUUID{
    @ManyToOne 
    public Employee employee;
    @Enumerated(EnumType.STRING)
    public DegreeType degreeType;
    public String degreeName;
    public Integer year;
    public Boolean gpaType;
    public Double gpa;
    @Enumerated(EnumType.STRING)
    public ResultDivisionType divisionType;

    public interface EducationalQualificationRepo extends ManagedRepository.CustomId<EducationalQualification, UUID>{
    
        
    }
}
