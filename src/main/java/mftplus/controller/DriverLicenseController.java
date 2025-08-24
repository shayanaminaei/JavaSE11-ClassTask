package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.DriverLicense;
import mftplus.model.entity.enums.DriverLicenseType;
import mftplus.model.service.DriverLicenseService;

import java.time.LocalDate;
import java.util.List;

@Log4j
public class DriverLicenseController {

    @Getter
    private static DriverLicenseController controller = new DriverLicenseController();
    private DriverLicenseController() {}

    public void save(
            int Id,
            int personId,
            String serial,
            DriverLicenseType driverLicenseType,
            String city,
            LocalDate registerDate,
            LocalDate expireDate)
     throws Exception {
        try {
            DriverLicense driverLicense =
                    DriverLicense
                            .builder()
                            .id(Id)
                            .personId(personId)
                            .serial(serial)
                            .driverLicenseType(driverLicenseType)
                            .city(city)
                            .registerDate(registerDate)
                            .expireDate(expireDate)
                            .build();

//            DriverLicenseService.getService().save(driverLicense);
            log.info("DriverLicense Saved Successfully");
        } catch (Exception e) {
            log.error("DriverLicense Saving Failed" + e.getMessage());
        }
    }

    public void edit(
            int Id,
            int personId,
            String serial,
            DriverLicenseType driverLicenseType,
            String city,
            LocalDate registerDate,
            LocalDate expireDate)
     throws Exception {
        try {
            DriverLicense driverLicense =
                    DriverLicense
                            .builder()
                            .id(Id)
                            .personId(personId)
                            .serial(serial)
                            .driverLicenseType(driverLicenseType)
                            .city(city)
                            .registerDate(registerDate)
                            .expireDate(expireDate)
                            .build();

//            DriverLicenseService.getService().edit(driverLicense);
            log.info("DriverLicense Edited Successfully");
        } catch (Exception e) {
            log.error("DriverLicense Edit Failed" + e.getMessage());
        }
    }

    public void delete(int id) throws Exception {
        try {
//            DriverLicenseService.getService().delete(id);
            log.info("DriverLicense Deleted Successfully");
        } catch (Exception e) {
            log.error("DriverLicense DeleteById " + id + " Failed" + e.getMessage());
        }
    }

    public List<DriverLicense> findAll() throws Exception {
        try {
//            List<DriverLicense> driverLicenseList = DriverLicenseService.getService().findAll();
            log.info("DriverLicense FindAll Successfully");
            return null; //driverLicenseList;
        } catch (Exception e) {
            log.error("DriverLicense FindAll Failed" + e.getMessage());
            return null;
        }
    }

    public DriverLicense findById(int id) throws Exception {
        try {
//            DriverLicense driverLicense = DriverLicenseService.getService().findById(id);
            log.info("DriverLicense FindByID " + id + " Successfully");
//            return driverLicense;
        } catch (Exception e) {
            log.error("DriverLicense FindById " + id + " Failed" + e.getMessage());
            return null;
        }
        return null;

    }
}
