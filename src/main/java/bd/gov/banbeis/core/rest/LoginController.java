package bd.gov.banbeis.core.rest;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.server.spi.RuntimeConfiguration.Body.MultiPart;

import bd.gov.banbeis.security.entity.BanbeisUser;
import bd.gov.banbeis.security.entity.BanbeisUser_;
import bd.gov.banbeis.security.entity.BanbeisUser.BanbeisUserRepo;
import io.quarkiverse.renarde.htmx.HxController;
import io.quarkiverse.renarde.security.LoginPage;
import io.quarkiverse.renarde.security.RenardeSecurity;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

public class LoginController extends HxController {
    @Inject
    RenardeSecurity security;
    @Inject 
    BanbeisUserRepo userRepo;
    @Inject 
    DashboardController dashboardController;

    @CheckedTemplate 
    static class Templates{
        public static native TemplateInstance login(Boolean isHtmx);
        // public static native TemplateInstance logout();
    }

    @Path("/login")
    @LoginPage
    public TemplateInstance login(){
        String title = "{\"setTitle\": \"Login Pages\"}";
        if(isHxRequest()){
            this.hx(HxResponseHeader.TRIGGER, title);
        }
        return Templates.login(isHxRequest());
    }


    @POST 
    @Path ("/manual-login")
    public Response logInInput(
        @NotBlank @RestForm String username,
        @NotBlank @RestForm String password
    ){
        if(validationFailed()){
            return Response.ok(login()).build();
        }
        BanbeisUser user = userRepo.findByUsername(username) ;
        if(user == null || !BcryptUtil.matches(password, user.password)){
            validation.addError("username", "Invalid username/password");
            validation.addError("password", "Invalid username/password");
            return Response
                    .ok(Templates.login(true))
                    .header(HxResponseHeader.HX_RETARGET.key(), "#content")
                    .header(HxResponseHeader.HX_RESWAP.key(), "outerHTML")
                    .build();
        }

        NewCookie cookie = security.makeUserCookie(user);
        
        return Response
            .ok(DashboardController.Templates.dashboard(true, true, "dashboard"))
            .header(HxResponseHeader.REPLACE_URL.key(), "/")
            .header(HxResponseHeader.HX_RETARGET.key(), "#content")
            .header(HxResponseHeader.HX_RESWAP.key(), "outerHTML")
            .cookie(cookie)
            .build();
    }
    
}
