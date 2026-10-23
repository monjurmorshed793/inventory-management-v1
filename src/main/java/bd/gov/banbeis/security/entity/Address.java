package bd.gov.banbeis.security.entity;

import bd.gov.banbeis.core.entity.District;
import bd.gov.banbeis.core.entity.Division;
import bd.gov.banbeis.core.entity.Thana;
import bd.gov.banbeis.security.enumeration.AddressType;
import io.quarkus.data.hibernate.WithId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "address")
public class Address extends WithId.AutoUUID{
    @Enumerated(EnumType.STRING)
    public AddressType type;
    @ManyToOne 
    public Division division;
    @ManyToOne 
    public District district;
    @ManyToOne 
    public Thana thana;
    public String road;
    public String postOffice;
    public Integer postCode;
}
