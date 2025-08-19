import mftplus.controller.DriverLicenseController;
import mftplus.model.entity.DriverLicense;
import mftplus.model.entity.enums.DriverLicenseType;
import mftplus.model.service.DriverLicenseService;

import java.time.LocalDate;

public class DriverLicensesTest {


    public static void main(String[] args) throws Exception {

      DriverLicenseController.getController().save(1,2, "12345678",DriverLicenseType.Car,"تهران",LocalDate.of(2010, 1, 1),LocalDate.of(2020, 1, 1));
       DriverLicenseController.getController().edit(1,2, "12345678",DriverLicenseType.Truck,"رشت",LocalDate.of(2011, 1, 1),LocalDate.of(2020, 1, 1));
       DriverLicenseController.getController().delete(1);
       System.out.println(DriverLicenseController.getController().findAll());
       System.out.println(DriverLicenseController.getController().findById(6));


        DriverLicense driverLicense = DriverLicense
                .builder()
                .id('1')
                .personId('2')
                .serial("12345678")
                .driverLicenseType(DriverLicenseType.Bus)
                .city("تهران")
                .registerDate(LocalDate.of(2000, 1, 1))
                .expireDate(LocalDate.of(2030, 1, 1))
                .build();
        DriverLicenseService.getService().save(driverLicense);

    }
}
