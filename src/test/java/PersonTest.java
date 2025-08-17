import lombok.extern.log4j.Log4j;
import mftplus.controller.PersonController;
import mftplus.model.entity.Person;
import mftplus.model.entity.enums.Role;
import mftplus.model.service.PersonService;

import java.time.LocalDate;

@Log4j
public class PersonTest {
    public static void main(String[] args) throws Exception {
        log.info("Starting Person Test");
        log.error("error saving");
//        UNIT TEST
//        تست واحد
//        PersonController.getController().save("ali", "alipour", LocalDate.of(2010, 1, 1), Role.customer,true);
//        PersonController.getController().edit(7,"ahmad", "ahmadi", LocalDate.of(2010, 1, 1), Role.customer,true);
//        PersonController.getController().delete(7);
//        System.out.println(PersonController.getController().findAll());
//        System.out.println(PersonController.getController().findById(6));
//        System.out.println(PersonController.getController().findByNameAndFamily("a" ,"a"));

//        Person person = Person
//                .builder()
//                .name("reza")
//                .family("rezsdfdsfsdaii")
//                .birthDate(LocalDate.of(2000, 1, 1))
//                .role(Role.admin)
//                .status(true)
//                .build();
//
//        PersonService.getService().save(person);
//        System.out.println(person);
//        System.out.println(PersonService.getService().findByNameAndFamily("r", ""));

//        Repository Test Passed
//        try with resource
//        try (PersonRepository personRepository = new PersonRepository()) {

//        test passed
//        personRepository.save(person);

//        test passed
//        personRepository.edit(person);

//        test passed
//        personRepository.delete(1);

//        test passed
//        System.out.println(personRepository.findAll());

//        test passed
//        System.out.println(personRepository.findById(1));
//        System.out.println(personRepository.findById(7));

//        test passed
//        System.out.println(personRepository.findByNameAndFamily("r", ""));
//        }
    }
}
