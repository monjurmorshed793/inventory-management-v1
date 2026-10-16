package bd.gov.banbeis.rest;

import bd.gov.banbeis.core.entity.Office;
import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Authenticated
@Path("/office")
public class OfficeController extends HxController {
    @Inject
    Office.OfficeRepo officeRepo;

    @CheckedTemplate
    static class Templates{
        public static native TemplateInstance officeIndex(Boolean isHtmx, String route);
        public static native TemplateInstance officeList(List<Office> offices);
    }

    @GET
    @Path("/")
    public TemplateInstance index(){
        this.hx(HxResponseHeader.PUSH_URL, "/office");
        return Templates.officeIndex(isHxRequest(), "office");
    }

    @GET
    @Path("/list")
    public TemplateInstance list(){
        onlyHxRequest();
        return Templates.officeList(officeRepo.listAll());
    }
}
