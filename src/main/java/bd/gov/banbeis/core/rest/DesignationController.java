package bd.gov.banbeis.core.rest;

import java.util.List;
import java.util.UUID;

import org.jboss.resteasy.reactive.RestForm;

import bd.gov.banbeis.core.entity.Designation;
import bd.gov.banbeis.core.entity.Designation.DesignationRepo;
import bd.gov.banbeis.core.service.DesignationService;
import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.data.hibernate.blocking.BlockingDataQuery;
import io.quarkus.data.hibernate.runtime.orm.PanacheBlockingQueryImpl;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.constraint.NotNull;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/designation")
public class DesignationController extends HxController{

    @Inject
    DesignationRepo designationRepo;
    @Inject 
    DesignationService designationService;

    @CheckedTemplate
    static class Templates{
        public static native TemplateInstance designationIndex(boolean isHtmx, Integer page, Integer size);
        public static native TemplateInstance designationList(Boolean initiate, List<Designation> designations, Long totalItems, Integer page, Integer size);
        public static native TemplateInstance designationEdit(Designation designation, Integer page, Integer size);
    }

    @GET
    @Path("/")
    public TemplateInstance index(@QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("size") @DefaultValue("10") Integer size
        ){
        hx(HxResponseHeader.REPLACE_URL, "/designation");
        return Templates.designationIndex(isHxRequest(), page , size);
    }

    @GET
    @Path("/list")
    public TemplateInstance list(
        @QueryParam("page") @DefaultValue("0") Integer page,
        @QueryParam("size") @DefaultValue("10") Integer size){
         List<Designation> designations = designationService.getList(page, size);
         hx(HxResponseHeader.REPLACE_URL, "/designation?page="+page+"&size="+size);
        return Templates.designationList(false, designations, designationRepo.count(), page, size);
    }

    @GET
    public TemplateInstance edit(@QueryParam("id") UUID id, @QueryParam ("page") Integer page, @QueryParam ("size") Integer size){
        Designation designation = new Designation();
        if(id!=null)
            designation = designationRepo.findById(id);
        return Templates.designationEdit(designation, page, size);
    }

    @POST
    @Path("/")
    @Transactional 
    public TemplateInstance create(
        @QueryParam("page") Integer page,
        @QueryParam("size") Integer size,
        @RestForm String name,
        @RestForm String nameBn,
        @RestForm Integer grade
    ){
        Designation designation = new Designation();
        designation.name = name;
        designation.nameBn = nameBn;
        designation.grade = grade;
        if(name == null || name.isBlank())
            validation.addError("name", "Name is required.");
        if(grade == null)
            validation.addError("grade", "Grade is required.");
        if(validation.hasErrors()){
            hx(HxResponseHeader.HX_RETARGET, "#modal-content");
            hx(HxResponseHeader.HX_RESWAP, "outerHTML");
            return Templates.designationEdit(designation, page, size);
        }

        designationRepo.persist(designation);

        hx(HxResponseHeader.HX_RETARGET, "#designation-list");
        hx(HxResponseHeader.HX_RESWAP, "outerHTML");
        List<Designation> designations = designationService.getList(page, size);
        return Templates.designationList(false, designations, designationRepo.count(), page, size);
    }
}
