package bd.gov.banbeis.util;

import java.nio.file.DirectoryIteratorException;

import bd.gov.banbeis.core.entity.Department;
import bd.gov.banbeis.core.entity.Designation;
import bd.gov.banbeis.core.entity.Office;
import bd.gov.banbeis.core.entity.Department.DepartmentRepo;
import bd.gov.banbeis.core.entity.Designation.DesignationRepo;
import bd.gov.banbeis.core.entity.Office.OfficeRepo;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped 
public class OfficeStartUp {
    @Inject 
    OfficeRepo officeRepo;
    @Inject 
    DepartmentRepo departmentRepo;
    @Inject 
    DesignationRepo designationRepo;

    @Transactional 
    public void start(@Observes StartupEvent evt){
        if(officeRepo.count()==0){
            Office banbeis = new Office("BANBEIS", "ব্যানবেইস");
            Office uitrce = new Office("UITRCE", "ইউআইটিআরসিই");
            officeRepo.persistAndFlush(banbeis);
            officeRepo.persistAndFlush(uitrce);

            Department adminDept = new Department("Admin", "Admin", banbeis);
            Department ictDept = new Department("ICT", "ICT", banbeis);
            Department dlpDept = new Department("DLP", "DLP", banbeis);
            Department statDept = new Department("STAT", "STAT", banbeis);

            departmentRepo.persist(adminDept, ictDept, dlpDept, statDept);

            Designation ap = new Designation("AP");
            Designation so = new Designation("SO");
            Designation dd = new Designation("DD");
            Designation dg = new Designation("DG");
            Designation director = new Designation("Director");
            Designation statDirector = new Designation("Director Stat");

            designationRepo.persist(ap, so, dd, dg, director, statDirector);
        }
    }
}
