package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Person;
import mftplus.model.entity.enums.Role;
import mftplus.model.service.PersonService;

import java.time.LocalDate;
import java.util.List;

@Log4j
public class PersonController {
    @Getter
    private static PersonController controller = new PersonController();


    private PersonController() {
    }

    public void save(String name, String family, LocalDate birthDate, Role role, boolean status) throws Exception {
        try {
            Person person =
                    Person
                            .builder()
                            .name(name)
                            .family(family)
                            .birthDate(birthDate)
                            .role(role)
                            .status(status)
                            .build();
            PersonService.getService().save(person);
            log.info("Person Saved Successfully");
        } catch (Exception e) {
            log.error("Person Save Failed " + e.getMessage());
        }
    }

    public void edit(int id, String name, String family, LocalDate birthDate, Role role, boolean status) throws Exception {
        try {
            Person person =
                    Person
                            .builder()
                            .id(id)
                            .name(name)
                            .family(family)
                            .birthDate(birthDate)
                            .role(role)
                            .status(status)
                            .build();
            PersonService.getService().edit(person);
            System.out.println("Info : Person Edited Successfully");
        } catch (Exception e) {
            System.out.println("Error : Person Edit Failed " + e.getMessage());
        }
    }

    public void delete(Integer id) throws Exception {
        try {
            PersonService.getService().delete(id);
            System.out.println("Info : Person Deleted Successfully");
        } catch (Exception e) {
            System.out.println("Error : Person Delete Failed " + e.getMessage());
        }
    }

    public List<Person> findAll() throws Exception {
        try {
            return PersonService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error : Person FindAll Failed " + e.getMessage());
            return null;
        }
    }

    public Person findById(Integer id) throws Exception {
        try {
            return PersonService.getService().findById(id);
        } catch (Exception e) {
            System.out.println("Error : Person FindId " + id + " Failed " + e.getMessage());
            return null;
        }
    }

    public List<Person> findByNameAndFamily(String name, String family) throws Exception {
        try {
            return PersonService.getService().findByNameAndFamily(name, family);
        } catch (Exception e) {
            System.out.println("Error : Person FindNameFamily " + name + " " + family + " Failed " + e.getMessage());
            return null;
        }
    }
}

