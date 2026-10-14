package bd.gov.banbeis.rest;

import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

public class DashboardController extends HxController{
    @CheckedTemplate 
    static class Templates{
        public static native TemplateInstance dashboard(boolean isHtmx);
    }

    @GET
    @Path("/")
    @Authenticated     public TemplateInstance dashboard(){
        return Templates.dashboard(isHxRequest());
    }
}
