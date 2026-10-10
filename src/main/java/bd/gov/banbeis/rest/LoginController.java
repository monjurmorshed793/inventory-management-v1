package bd.gov.banbeis.rest;

import io.quarkiverse.renarde.htmx.HxController;
import io.quarkiverse.renarde.security.LoginPage;
import io.quarkiverse.renarde.security.RenardeSecurity;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

public class LoginController extends HxController {
    @Inject
    RenardeSecurity security;

    @CheckedTemplate 
    static class Templates{
        public static native TemplateInstance login();
        // public static native TemplateInstance logout();
    }

    @Path("/login")
    @LoginPage
    public TemplateInstance login(){
        return Templates.login();
    }
}
