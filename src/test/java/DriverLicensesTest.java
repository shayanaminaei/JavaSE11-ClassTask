import mftplus.controller.DriverLicenseController;
import mftplus.model.entity.DriverLicense;
import mftplus.model.entity.Person;
import mftplus.model.entity.enums.DriverLicenseType;
import mftplus.model.entity.enums.Role;
import mftplus.model.repository.DriverLicenseRepository;
import mftplus.model.service.DriverLicenseService;
import mftplus.model.service.PersonService;

import java.time.LocalDate;

public class DriverLicensesTest {


    public static void main(String[] args) throws Exception {

        Person person =
                Person
                        .builder()
                        .name("zahra")
                        .family("moradiNejad")
                        .birthDate(LocalDate.of(1994, 2, 10))
                        .role(Role.admin)
                        .status(true)
                        .build();
        PersonService.getService().save(person);


        DriverLicense driverLicense = DriverLicense
                .builder()
                .id('1')
                .person(PersonService.getService().findById(1))
                .serial("12345678")
                .driverLicenseType(DriverLicenseType.Bus)
                .city("تهران")
                .registerDate(LocalDate.of(2000, 1, 1))
                .expireDate(LocalDate.of(2030, 1, 1))
                .build();
        DriverLicenseService.getService().save(driverLicense);



    }
}

