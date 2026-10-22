package bd.gov.banbeis.core.service;

import java.util.List;

import bd.gov.banbeis.core.entity.Designation;
import bd.gov.banbeis.core.entity.Designation.DesignationRepo;
import io.quarkus.data.hibernate.blocking.BlockingDataQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped 
public class DesignationService {
    @Inject 
    DesignationRepo designationRepo;

    public List<Designation> getList(Integer page, Integer size){
        BlockingDataQuery<Designation> designationsQuery = designationRepo.findAll();
         List<Designation> designations = designationsQuery.pages().page(page, size).list();
         return designations;
    }
}
