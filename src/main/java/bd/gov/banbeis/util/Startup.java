package bd.gov.banbeis.util;

import java.util.HashSet;
import java.util.Set;

import bd.gov.banbeis.security.entity.BanbeisUser;
import bd.gov.banbeis.security.entity.Role;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import io.quarkus.runtime.StartupEvent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class Startup {
    /**
     * This method is executed at the start of your application
     */
    @Inject
    Role.RoleRepo roleRepo;
    @Inject
    BanbeisUser.BanbeisUserRepo userRepo;

    @Transactional
    public void start(@Observes StartupEvent evt) {

        if(roleRepo.count()==0){
            Role adminRole = new Role();
            adminRole.name = "admin";
            roleRepo.persistAndFlush(adminRole);

            BanbeisUser adminUser = new BanbeisUser();
            adminUser.name="Admin";
            adminUser.username = "admin";
            adminUser.email = "admin@banbeis.gov.bd";
            adminUser.password = BcryptUtil.bcryptHash("ig100");
            adminUser.registered = true;
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            adminUser.roles = roles;
            userRepo.persist(adminUser);
        }

    }
}
