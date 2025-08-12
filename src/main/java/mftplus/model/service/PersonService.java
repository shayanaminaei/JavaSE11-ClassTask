package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Person;
import mftplus.model.repository.PersonRepository;

import java.util.List;


public class PersonService implements Service<Person, Integer> {
    @Getter
    private static PersonService service = new PersonService();

    private PersonService() {
    }


    @Override
    public void save(Person person) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            personRepository.save(person);
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

    public void findByNameAndFamily(String name, String family) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            personRepository.findByNameAndFamily(name, family);
        }
    }
}
