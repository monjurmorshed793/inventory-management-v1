package bd.gov.banbeis.security.rest;

import java.util.List;

import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.Path;

@Path("/employee")
public class EmployeeController extends HxController{
    @CheckedTemplate 
    static class Templates{
        public static native TemplateInstance employeeIndex(boolean isHtmx, Integer page, Integer size);
    }
}
