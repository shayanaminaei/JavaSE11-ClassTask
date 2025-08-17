import mftplus.model.entity.Job;
import mftplus.model.entity.enums.JobTitle;
import mftplus.model.service.JobService;

import java.time.LocalDate;

public class JobTest {
    public static void main(String[] args) throws Exception {
        Job job = Job
                .builder()
                .id(1)
                .organisation("mft")
                .jobTitle(JobTitle.valueOf("employee"))
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .description("New employee")
                .build();

        JobService.getService().save(job);
    }


}
