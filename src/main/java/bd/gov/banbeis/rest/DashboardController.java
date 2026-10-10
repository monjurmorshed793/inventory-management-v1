package bd.gov.banbeis.rest;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class DashboardController {
    @CheckedTemplate 
    static class Templates{
        public static native TemplateInstance dashboard();
    }

    @GET 
    @Authenticated 
    public TemplateInstance dashboard(){
        return Templates.dashboard();
    }
}
