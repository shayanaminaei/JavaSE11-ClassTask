import mftplus.model.entity.Education;
import mftplus.model.entity.Person;
import mftplus.model.entity.enums.EducationGrade;
import mftplus.model.entity.enums.Role;

import java.time.LocalDate;

public class EducationTest {
    public static void main(String[] args) throws Exception {
        Person person1 =
                Person
                        .builder()
                        .id(1)
                        .name("ali")
                        .family("alipour")
                        .birthDate(LocalDate.of(1996, 12, 1))
                        .role(Role.manager)
                        .status(true)
                        .build();

        Person person2 =
                Person
                        .builder()
                        .id(2)
                        .name("reza")
                        .family("rezaii")
                        .birthDate(LocalDate.of(2010, 3, 14))
                        .role(Role.admin)
                        .status(false)
                        .build();

        Person person3 =
                Person
                        .builder()
                        .id(3)
                        .name("ahmad")
                        .family("ahmadi")
                        .birthDate(LocalDate.of(2001, 8, 26))
                        .role(Role.customer)
                        .status(true)
                        .build();

        Person person4 =
                Person
                        .builder()
                        .id(4)
                        .name("mina")
                        .family("zamani")
                        .birthDate(LocalDate.of(2008, 9, 3))
                        .role(Role.customer)
                        .status(false)
                        .build();

        Person person5 =
                Person
                        .builder()
                        .id(5)
                        .name("arash")
                        .family("dehnavi")
                        .birthDate(LocalDate.of(1999, 6, 16))
                        .role(Role.admin)
                        .status(true)
                        .build();

        Education education1 =
                Education
                        .builder()
                        .id(1)
                        .person(person1)
                        .university("zanjan")
                        .educationGrade(EducationGrade.master)
                        .average(17.90)
                        .startDate(LocalDate.of(1996, 12, 1))
                        .endDate(LocalDate.of(1998, 12, 1))
                        .build();

        Education education2 =
                Education
                        .builder()
                        .id(2)
                        .person(person2)
                        .university("sharif")
                        .educationGrade(EducationGrade.doctorate)
                        .average(19.13)
                        .startDate(LocalDate.of(1999, 12, 1))
                        .endDate(LocalDate.of(2003, 12, 1))
                        .build();

        Education education3 =
                Education
                        .builder()
                        .id(3)
                        .person(person4)
                        .university("tehran")
                        .educationGrade(EducationGrade.bachelor)
                        .average(18.57)
                        .startDate(LocalDate.of(1998, 12, 1))
                        .endDate(LocalDate.of(2003, 12, 1))
                        .build();

        Education education4 =
                Education
                        .builder()
                        .id(4)
                        .person(person5)
                        .university("tabriz")
                        .educationGrade(EducationGrade.doctorate)
                        .average(19.33)
                        .startDate(LocalDate.of(2005, 12, 1))
                        .endDate(LocalDate.of(2009, 12, 1))
                        .build();

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // EducationService test passed:

        // save() test passed:
/*
        PersonService.getService().save(person1);
        PersonService.getService().save(person2);
        PersonService.getService().save(person3);
        PersonService.getService().save(person4);
        PersonService.getService().save(person5);

        EducationService.getService().save(education1);
        EducationService.getService().save(education2);
        EducationService.getService().save(education3);
        EducationService.getService().save(education4);
*/

        // edit() test passed:
/*
        Education education = EducationService.getService().findById(41);
        education.setUniversity("sharif");
        education.setEducationGrade(EducationGrade.bachelor);
        EducationService.getService().edit(education);
*/

        // delete() test passed:
/*
        EducationService.getService().delete(44);
*/

        // findAll() test passed:
/*
//        for (Education education : EducationService.getService().findAll()) {
//            System.out.println(education);
//        }

//        System.out.println(EducationService.getService().findAll());
*/

        // findById() test passed:
/*
        System.out.println(EducationService.getService().findById(41));
        System.out.println(EducationService.getService().findById(6));
*/

        // findByPersonId() test passed;
/*
//        for (Education education : EducationService.getService().findByPersonId(47)) {
//            System.out.println(education);
//        }

//        System.out.println(EducationService.getService().findByPersonId(41));
*/

        // findByUniversityAndEducationGrade() test passed:
/*
//        System.out.println(EducationService.getService().findByUniversityAndEducationGrade("tehran", "bachelor"));

//        for (Education education : EducationService.getService().findByUniversityAndEducationGrade("sharif", "")) {
//            System.out.println(education);
//        }
*/


        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // EducationRepository test passed:

//        try (PersonRepository personRepository = new PersonRepository();
//             EducationRepository educationRepository = new EducationRepository();) {

        // save() test passed:
/*
            personRepository.save(person1);
            personRepository.save(person2);
            personRepository.save(person3);
            personRepository.save(person4);
            personRepository.save(person5);

            educationRepository.save(education1);
            educationRepository.save(education2);
            educationRepository.save(education3);
            educationRepository.save(education4);
*/

        // edit() test passed passed:
/*
            education4.setUniversity("amirkabir");
            education4.setAverage(19.64);
            educationRepository.edit(education4);

            education2.setEducationGrade(EducationGrade.master);
            education2.setStartDate(LocalDate.of(1996, 12, 1));
            education2.setEndDate(LocalDate.of(1998, 12, 1));
            educationRepository.edit(education2);

            education3.setPerson(person5);
            education3.setUniversity("sharif");
            educationRepository.edit(education3);
*/

        // delete() test passed:
/*
            educationRepository.delete(2);
*/

        // findAll() test passed:
/*
            for (Education education : educationRepository.findAll()) {
                System.out.println(education);
            }
*/

        // findById() test passed:
/*
            System.out.println(educationRepository.findById(3));
            System.out.println(educationRepository.findById(5));
*/
        // findByPersonId() test:
/*
            List<Education> education = educationRepository.findByPersonId(47);
            System.out.println(education);
*/

        // findByUniversityAndEducationGrade() test passed:
/*
//            for (Education education : educationRepository.findByUniversityAndEducationGrade("", "doctorate")) {
//                System.out.println(education);
//            }

//            System.out.println(educationRepository.findByUniversityAndEducationGrade("r", ""));

//            System.out.println(educationRepository.findByUniversityAndEducationGrade("sharif", "bachelor"));
*/

//        }

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // Education entity test passed:

/*
        List<Education> educationList = new ArrayList<>();
        educationList.add(education1);
        educationList.add(education2);
        educationList.add(education3);
        educationList.add(education4);

        for (Education education : educationList) {
            System.out.println(education);
        }
*/

    }
}
