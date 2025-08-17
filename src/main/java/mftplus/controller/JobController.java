package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Job;
import mftplus.model.entity.enums.JobTitle;
import mftplus.model.service.JobService;

import java.time.LocalDate;
import java.util.List;

@Log4j
public class JobController {
    @Getter
    private static JobController controller = new JobController();

    private JobController(){
    }

    public void save (String organisation, LocalDate startDate, LocalDate endDate, JobTitle title, String description) throws Exception {
        try {
            Job job =
                    Job
                            .builder()
                            .organisation(organisation)
                            .startDate(startDate)
                            .endDate(endDate)
                            .title(title)
                            .description(description)
                            .build();
            JobService.getService().edit(job);
            log.info("Info : Job Edited Successfully");
        } catch (Exception e) {
            log.info("Error : Job Edit Failed " + e.getMessage());
        }

    }

    public void delete (Integer id) throws Exception {
        try {
            JobService.getService().delete(id);
            log.info("Info : Job Deleted Successfully");
        }catch (Exception e) {
            log.info("Error : Job Delete Failed " + e.getMessage());}
    }
}
