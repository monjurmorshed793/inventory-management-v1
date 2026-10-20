package bd.gov.banbeis.rest;

import java.util.List;

import bd.gov.banbeis.core.entity.Designation;
import bd.gov.banbeis.core.entity.Designation.DesignationRepo;
import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.data.hibernate.blocking.BlockingDataQuery;
import io.quarkus.data.hibernate.runtime.orm.PanacheBlockingQueryImpl;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/designation")
public class DesignationController extends HxController{

    @Inject
    DesignationRepo designationRepo;

    @CheckedTemplate
    static class Templates{
        public static native TemplateInstance designationIndex(boolean isHtmx);
        public static native TemplateInstance designationList(List<Designation> designations, Long totalItems, Integer page, Integer size);
    }

    @GET
    @Path("/")
    public TemplateInstance index(){
        hx(HxResponseHeader.REPLACE_URL, "/designation");
        return Templates.designationIndex(isHxRequest());
    }

    @GET
    @Path("/list")
    public TemplateInstance list(
        @QueryParam("page") @DefaultValue("0") Integer page,
        @QueryParam("size") @DefaultValue("10") Integer size){
         BlockingDataQuery<Designation> designationsQuery = designationRepo.findAll();
         List<Designation> designations = designationsQuery.pages().page(page, size).list();
        return Templates.designationList(designations, designationRepo.count(), page, size);
    }
}
