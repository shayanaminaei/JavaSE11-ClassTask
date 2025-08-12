import mftplus.model.entity.Person;
import mftplus.model.entity.enums.Role;
import mftplus.model.service.PersonService;

import java.time.LocalDate;

public class PersonTest {
    public static void main(String[] args) throws Exception {
        Person person = Person
                .builder()
                .name("reza")
                .family("rezsdfdsfsdaii")
                .birthDate(LocalDate.of(2000, 1, 1))
                .role(Role.admin)
                .status(true)
                .build();

        PersonService.getService().save(person);


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
