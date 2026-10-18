package bd.gov.banbeis.rest;

import bd.gov.banbeis.core.entity.Department;
import bd.gov.banbeis.core.entity.Office;
import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.constraint.NotNull;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

import org.hibernate.sql.Template;
import org.jboss.resteasy.reactive.RestForm;

@Path("/department")
public class DepartmentController extends HxController {

    @Inject
    Department.DepartmentRepo departmentRepo;
    @Inject
    Office.OfficeRepo officeRepo;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance departmentIndex(Boolean isHtmx, String route, UUID officeId, List<Office> offices);

        public static native TemplateInstance departmentList(List<Department> departments, boolean initiate);

        public static native TemplateInstance departmentEdit(Department department, List<Office> offices);
    }

    @GET
    @Path("/")
    public TemplateInstance index(@QueryParam("office") UUID officeId) {
        this.hx(HxResponseHeader.PUSH_URL, "/department");
        return Templates.departmentIndex(isHxRequest(), "department", officeId, officeRepo.listAll());
    }

    @GET
    public TemplateInstance list(@QueryParam("office") UUID officeId) {
        this.hx(HxResponseHeader.PUSH_URL, "/department?office="+officeId.toString());
        List<Department> departments = departmentRepo.getByOffice(officeRepo.getSession().getReference(Office.class, officeId));
        return Templates.departmentList(departments, false);
    }

    @GET
    @Path("/edit/{id}")
    public TemplateInstance edit(@PathParam("id") UUID id){
        Department department = departmentRepo.findById(id);
        List<Office> offices = officeRepo.listAll();
        return Templates.departmentEdit(department, offices);
    }

    @PUT
    @Transactional 
    public TemplateInstance update(@RestForm @NotNull   UUID id, @RestForm @NotBlank  String name, @RestForm String nameBn, @NotNull  @RestForm UUID office){
        Department department = new Department();

        if(validationFailed()){
            if(name==null)
                validation.addError(name, "Name is required");
            if(office==null)
                validation.addError("office", "Office is required");
            department.name = name;
            department.nameBn = nameBn;
            department.office = office==null? null: officeRepo.findById(office);
            hx(HxResponseHeader.HX_RETARGET, "#modal-content");
            hx(HxResponseHeader.HX_RESWAP, "outerHTML");
            return Templates.departmentEdit(department, officeRepo.listAll());
        }
        Office officeObj = officeRepo.getSession().getReference(Office.class, office);
        department = departmentRepo.findById(id);
        department.name = name;
        department.nameBn = nameBn;
        department.office = officeObj;
        departmentRepo.persistAndFlush(department);

        List<Department> departments = departmentRepo.getByOffice(officeObj);
        
        return Templates.departmentList(departments, true);
    }
}
