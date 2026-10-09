package bd.gov.banbeis.security.entity;

import io.quarkus.data.hibernate.ManagedRepository;
import io.quarkus.data.hibernate.WithId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="role")
public class Role extends WithId.AutoUUID {
    public String name;

    public interface RoleRepo extends ManagedRepository<Role>{

    }
}
