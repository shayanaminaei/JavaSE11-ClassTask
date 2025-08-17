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
            System.out.println("Info : Job Edited Successfully");
        } catch (Exception e) {
            System.out.println("Error : Job Edit Failed " + e.getMessage());
        }

    }
}
