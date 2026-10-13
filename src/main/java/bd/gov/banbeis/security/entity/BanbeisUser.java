package bd.gov.banbeis.security.entity;

import io.quarkiverse.renarde.security.RenardeUserWithPassword;
import io.quarkus.data.hibernate.ManagedRepository;
import io.quarkus.data.hibernate.WithId;
import jakarta.data.repository.Find;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="banbeis_user")
public class BanbeisUser extends WithId.AutoUUID implements RenardeUserWithPassword {
    public String name;
    @Column(unique = true)
    public String username;
    public String email;
    public String mobile;
    public Boolean registered;
    public String password;
    @ManyToMany
    @JoinTable(
            name = "user_role_map",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    public Set<Role> roles = new HashSet<>();

    @Override
    public String password() {
        return password;
    }

    @Override
    public Set<String> roles() {
        return roles.stream().map(r->r.name).collect(Collectors.toSet());
    }

    @Override
    public String userId() {
        return username;
    }

    @Override
    public boolean registered() {
        return registered;
    }

    public interface BanbeisUserRepo extends ManagedRepository<BanbeisUser> {
        @Find
        BanbeisUser findByUsername(String username);
    }
}
