package mftplus.controller;


import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Education;
import mftplus.model.entity.enums.EducationGrade;
import mftplus.model.service.EducationService;

import java.time.LocalDate;
import java.util.List;

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

    public void edit(
            int id,
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
                            .id(id)
                            .personId(personId)
                            .university(university)
                            .educationGrade(grade)
                            .average(average)
                            .startDate(startDate)
                            .endDate(endDate)
                            .build();

            EducationService.getService().edit(education);
            log.info("Education Edited Successfully");
        } catch (Exception e) {
            log.error("Education Edit Failed" + e.getMessage());
        }
    }

    public void delete(int id) throws Exception {
        try {
            EducationService.getService().delete(id);
            log.info("Education Deleted Successfully");
        } catch (Exception e) {
            log.error("Education DeleteById " + id + " Failed" + e.getMessage());
        }
    }

    public List<Education> findall() throws Exception {
        try {
            List<Education> educationList = EducationService.getService().findAll();
            log.info("Education Findall Successfully");
            return educationList;
        } catch (Exception e) {
            log.error("Education Findall Failed" + e.getMessage());
            return null;
        }
    }

    public Education findById(int id) throws Exception {
        try {
            Education education = EducationService.getService().findById(id);
            log.info("Education FindByID " + id + " Successfully");
            return education;
        } catch (Exception e) {
            log.error("Education FindById " + id + " Failed" + e.getMessage());
            return null;
        }
    }

    public List<Education> findByUniversityAndGrade(String university, String grade) throws Exception {
        try {
            List<Education> educationList = EducationService.getService().findByUniversityAndGrade(university, grade);
            log.info("Education Findby University= " + university + " And Grade= " + grade + " Successfully");
            return educationList;
        } catch (Exception e) {
            log.error("Education Findby University= " + university + " And Grade= " + grade + " Failed" + e.getMessage());
            return null;
        }
    }
}
