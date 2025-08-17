package mftplus.controller;


import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Education;
import mftplus.model.entity.enums.EducationGrade;
import mftplus.model.service.EducationService;

import java.time.LocalDate;

@Log4j
public class EducationController {
    @Getter
    private static EducationController controller = new EducationController();
    private EducationController() {}

    public void save(
            int personId,
            String university,
            EducationGrade grade,
            double average,
            LocalDate startDate,
            LocalDate endDate
    ) throws Exception {
        try {
            Education education =
                    Education
                            .builder()
                            .personId(personId)
                            .university(university)
                            .educationGrade(grade)
                            .average(average)
                            .startDate(startDate)
                            .endDate(endDate)
                            .build();

            EducationService.getService().save(education);
            log.info("Education Saved Successfully");
        } catch (Exception e) {
            log.error("Education Saving Failed" + e.getMessage());
        }
    }
}
