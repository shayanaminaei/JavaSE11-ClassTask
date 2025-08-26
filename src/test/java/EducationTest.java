import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Education;
import mftplus.model.entity.Person;
import mftplus.model.entity.enums.EducationGrade;
import mftplus.model.entity.enums.Role;
import java.time.LocalDate;

@Log4j
public class EducationTest {
    public static void main(String[] args) throws Exception {
        Person person1 =
                Person
                        .builder()
                        .name("ali")
                        .family("alipour")
                        .birthDate(LocalDate.of(1996, 1, 10))
                        .role(Role.admin)
                        .status(true)
                        .build();

        Person person2 =
                Person
                        .builder()
                        .name("reza")
                        .family("rezaii")
                        .birthDate(LocalDate.of(1980, 5, 23))
                        .role(Role.customer)
                        .status(false)
                        .build();

        Education education1 =
                Education
                        .builder()
                        .personId(1)
                        .university("amirkabir")
                        .educationGrade(EducationGrade.master)
                        .average(17.95)
                        .startDate(LocalDate.of(2020, 9, 26))
                        .endDate(LocalDate.of(2023, 1, 15))
                        .build();

        Education education2 =
                Education
                        .builder()
                        .personId(2)
                        .university("sharif")
                        .educationGrade(EducationGrade.doctorate)
                        .average(19.50)
                        .startDate(LocalDate.of(2020, 9, 26))
                        .endDate(LocalDate.of(2025, 3, 15))
                        .build();

        Education education3 =
                Education
                        .builder()
                        .personId(2)
                        .university("sharif")
                        .educationGrade(EducationGrade.master)
                        .average(18.79)
                        .startDate(LocalDate.of(2018, 6, 26))
                        .endDate(LocalDate.of(2020, 4, 15))
                        .build();

//        log.info("Starting Education Test");
//        log.error("error saving");

        // EducationController test:

        // save() test:
//        EducationController.getController().save(
//                1,
//                "amirkabir",
//                EducationGrade.master,
//                17.95,
//                LocalDate.of(2020, 9, 26),
//                LocalDate.of(2023, 1, 15)
//        );
//
//        EducationController.getController().save(
//                2,
//                "sharif",
//                EducationGrade.doctorate,
//                19.50,
//                LocalDate.of(2020, 9, 26),
//                LocalDate.of(2025, 3, 15)
//        );
//
//        EducationController.getController().save(
//                2,
//                "sharif",
//                EducationGrade.master,
//                18.79,
//                LocalDate.of(2018, 6, 26),
//                LocalDate.of(2020, 4, 15)
//        );

        // edit() test:
//        EducationController.getController().edit(
//                2,
//                2,
//                "tabriz",
//                EducationGrade.doctorate,
//                19.30,
//                LocalDate.of(2020, 9, 26),
//                LocalDate.of(2025, 3, 15)
//        );

        // delete() test:
//        EducationController.getController().delete(2);

        // findAll() test:
//        System.out.println(EducationController.getController().findall());

        // findById() test:
//        System.out.println(EducationController.getController().findById(10));
//        System.out.println(EducationController.getController().findById(3));

        // findByUniversityAndGrade() test:
//        System.out.println(EducationController.getController().findByUniversityAndGrade("sharif", ""));


        // EducationService test:

        // save() test:
//        PersonService.getService().save(person1);
//        PersonService.getService().save(person2);

//        EducationService.getService().save(education1);
//        EducationService.getService().save(education2);
//        EducationService.getService().save(education3);

        // edit() test:
//        education1.setUniversity("tabriz");
//        education1.setAverage(16.59);
//        EducationService.getService().edit(education1);
//
//        education2.setStartDate(LocalDate.of(2018, 7, 29));
//        education2.setEndDate(LocalDate.of(2021, 6, 30));
//        EducationService.getService().edit(education2);

        // delete() test:
//        EducationService.getService().delete(2);

        // findAll() test:
//        System.out.println(EducationService.getService().findAll());

        // findById() test:
//        System.out.println(EducationService.getService().findById(1));

        // findByUniversityAndGrade() test:
//        System.out.println(EducationService.getService().findByUniversityAndGrade("amir", "m"));
    }
}
