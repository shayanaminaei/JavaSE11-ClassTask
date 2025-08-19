package mftplus.model.service;
import lombok.Getter;
import mftplus.model.entity.DriverLicense;
//import mftplus.model.repository.DriverLicenseRepository;
import mftplus.model.repository.PersonRepository;

import java.util.List;

public class DriverLicenseService{ // implements Service<DriverLicense, Integer> {

    @Getter
    private static DriverLicenseService service = new DriverLicenseService();

    private DriverLicenseService() {
    }

//
//    @Override
//    public void save(DriverLicense driverLicense) throws Exception {
//        try (PersonRepository personRepository = new PersonRepository()) {
//            personRepository.save(driverLicense);
//        }
//    }
//
//    @Override
//    public void edit(DriverLicense driverLicense) throws Exception {
//        try (DriverLicenseRepository driverLicenseRepository = new DriverLicenseRepository()) {
//            driverLicenseRepository.edit(driverLicense);
//        }
//    }
//
//    @Override
//    public void delete(Integer id) throws Exception {
//        try (DriverLicenseRepository driverLicenseRepository = new DriverLicenseRepository()) {
//            driverLicenseRepository.delete(id);
//        }
//    }
//
//    @Override
//    public List<Person> findAll() throws Exception {
//        try (PersonRepository personRepository = new PersonRepository()) {
//            return personRepository.findAll();
//        }
//    }
//
//    @Override
//    public Person findById(Integer id) throws Exception {
//        try (PersonRepository personRepository = new PersonRepository()) {
//            return personRepository.findById(id);
//        }
//    }
//
//    public void findByNameAndFamily(String name, String family) throws Exception {
//        try (PersonRepository personRepository = new PersonRepository()) {
//            personRepository.findByNameAndFamily(name, family);
//        }
//    }
}
