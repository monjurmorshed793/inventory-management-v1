package bd.gov.banbeis.core.rest;

import java.util.ArrayList;
import java.util.List;

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
        public static native TemplateInstance pageIndex(String url, List<Integer> pages, Integer page, Integer size, Long totalPages);
    }

    @GET
    @Path("/")
    public TemplateInstance index(@QueryParam("url") String url, @QueryParam("page") Integer page, @QueryParam("size") Integer size, @QueryParam("totalItems") Long totalItems){
        List<Integer> pages = new ArrayList<>();
        Integer totalPages = Integer.parseInt(Math.ceilDiv(totalItems, size)+"");
        // previous page counter
        int previousPageCounter = 0;
        if(page>0){
            for(int i=page-1; i>=0; i--){
                pages.add(i);
                previousPageCounter+=1;
                if(previousPageCounter>2)
                    break;
            }
        }
        //next page counter
        int nextPageCounter = 0;
        if(page<totalPages){
            for(int i=page; i<=totalPages-1; i++){
                pages.add(i);
                if(nextPageCounter>2)
                    break;
            }
        }

        pages.sort(Integer::compareTo);
        return Templates.pageIndex(url, pages, page, size, totalPages.longValue()-1);
    }
}
