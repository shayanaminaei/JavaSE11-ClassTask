package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Education;
import mftplus.model.entity.Person;
import mftplus.model.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;


public class PersonService implements Service<Person, Integer> {
    @Getter
    private static PersonService service = new PersonService();

    private PersonService() {
    }

    @Override
    public void save(Person person) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            if(person.getBirthDate().isBefore(LocalDate.of(2015,1,1))) {
                personRepository.save(person);
            }else{
                throw new Exception("Age is not acceptable !");
            }
        }
    }

    @Override
    public void edit(Person person) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            personRepository.edit(person);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            personRepository.delete(id);
        }
    }

    @Override
    public List<Person> findAll() throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            return personRepository.findAll();
        }
    }

    @Override
    public Person findById(Integer id) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            return personRepository.findById(id);
        }
    }

    public List<Education> getAllEducations(int personId) throws Exception {
        return EducationService.getService().findByPersonId(personId);
    }

    public List<Person> findByNameAndFamily(String name, String family) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            return personRepository.findByNameAndFamily(name, family);
        }
    }

}
