package bd.gov.banbeis.rest;

import bd.gov.banbeis.core.entity.Department;
import bd.gov.banbeis.core.entity.Office;
import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.List;
import java.util.UUID;

@Path("/department")
public class DepartmentController extends HxController {

    @Inject
    Department.DepartmentRepo departmentRepo;
    @Inject
    Office.OfficeRepo officeRepo;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance departmentIndex(Boolean isHtmx, String route, List<Office> offices);

        public static native TemplateInstance departmentList(List<Department> departments);
    }

    @GET
    @Path("/")
    public TemplateInstance index() {
        this.hx(HxResponseHeader.PUSH_URL, "/department");
        return Templates.departmentIndex(isHxRequest(), "department", officeRepo.listAll());
    }

    @GET
    public TemplateInstance list(@QueryParam("office") UUID officeId) {
        List<Department> departments = departmentRepo.getByOffice(officeRepo.getSession().getReference(Office.class, officeId));
        return Templates.departmentList(departments);
    }
}
