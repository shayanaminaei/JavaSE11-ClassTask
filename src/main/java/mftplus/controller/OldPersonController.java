package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Person;
import mftplus.model.entity.enums.Role;
import mftplus.model.service.PersonService;

import java.time.LocalDate;
import java.util.List;

@Log4j
public class OldPersonController {
    @Getter
    private static OldPersonController controller = new OldPersonController();


    private OldPersonController() {
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
            log.info("Person Edited Successfully");
        } catch (Exception e) {
            log.error("Person Edit Failed " + e.getMessage());
        }
    }

    public void delete(Integer id) throws Exception {
        try {
            PersonService.getService().delete(id);
            log.info("Person Deleted Successfully");
        } catch (Exception e) {
            log.error("Person Delete Failed " + e.getMessage());
        }
    }

    public List<Person> findAll() throws Exception {
        try {
            List<Person> personList = PersonService.getService().findAll();
            log.info("Persons FindAll");
            return personList;
        } catch (Exception e) {
            log.error("Person FindAll Failed " + e.getMessage());
            return null;
        }
    }

    public Person findById(Integer id) throws Exception {
        try {
            Person person = PersonService.getService().findById(id);
            log.info("Person FindById : " + id );
            return person;
        } catch (Exception e) {
            log.error("Person FindById " + id + " Failed " + e.getMessage());
            return null;
        }
    }

    public List<Person> findByNameAndFamily(String name, String family) throws Exception {
        try {
            List<Person> personList = PersonService.getService().findByNameAndFamily(name, family);
            log.info("Persons FindByNameAndFamily :" + name + " " + family);
            return personList;
        } catch (Exception e) {
            log.error("Person FindNameFamily " + name + " " + family + " Failed " + e.getMessage());
            return null;
        }
    }
}

