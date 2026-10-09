package bd.gov.banbeis.security.config;

import bd.gov.banbeis.security.entity.BanbeisUser;
import io.quarkiverse.renarde.security.RenardeSecurity;
import io.quarkiverse.renarde.security.RenardeUser;
import io.quarkiverse.renarde.security.RenardeUserProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SecuritySetUp implements RenardeUserProvider {
    @Inject
    RenardeSecurity security;
    @Inject
    BanbeisUser.BanbeisUserRepo userRepo;

    @Override
    public RenardeUser findUser(String tenantId, String authId) {
        return userRepo.findByUsername(authId);
    }
}
