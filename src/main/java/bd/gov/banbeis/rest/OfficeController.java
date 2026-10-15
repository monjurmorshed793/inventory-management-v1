package bd.gov.banbeis.rest;

import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/office")
public class OfficeController extends HxController {

    @CheckedTemplate
    static class Templates{
        public static native TemplateInstance officeIndex(Boolean isHtmx);
    }

    @GET
    @Path("/")
    public TemplateInstance index(){
        return Templates.officeIndex(isHxRequest());
    }
}
