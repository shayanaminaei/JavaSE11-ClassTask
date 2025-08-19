import mftplus.model.entity.DriverLicense;
import mftplus.model.entity.enums.DriverLicenseType;
import mftplus.model.service.DriverLicenseService;

import java.time.LocalDate;

public class DriverLicensesTest {


    public static void main(String[] args) throws Exception {
        DriverLicense driverLicense = DriverLicense
                .builder()
                .id('1')
                .personId(1)
                .serial("12345678")
                .driverLicenseType(DriverLicenseType.Bus)
                .city("تهران")
                .registerDate(LocalDate.of(2000, 1, 1))
                .expireDate(LocalDate.of(2030, 1, 1))
                .build();
//        DriverLicenseService.getService().save(driverLicense);

    }
}