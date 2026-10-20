package bd.gov.banbeis.rest;

import io.quarkiverse.renarde.htmx.HxController;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/page")
public class PaginationController extends HxController{
    @CheckedTemplate
    static class Templates{
        public static native TemplateInstance pageIndex(String url, Integer page, Integer size, Long totalPages);
    }

    @GET
    @Path("/")
    public TemplateInstance index(@QueryParam("url") String url, @QueryParam("page") Integer page, @QueryParam("size") Integer size, @QueryParam("totalPages") Long totalPages){
        return Templates.pageIndex(url, page, size, totalPages);
    }
}
