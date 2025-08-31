package mftplus.model.tools;

import mftplus.model.entity.DriverLicense;
import mftplus.model.entity.enums.DriverLicenseType;
import mftplus.model.service.PersonService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverLicensesMapper {

    public DriverLicense driverLicensesMapper(ResultSet resultSet) throws Exception {
        return  DriverLicense
                .builder()
                .id(resultSet.getInt("id"))
                .person(PersonService.getService().findById(resultSet.getInt("person_id")))
                .serial(resultSet.getString("serial"))
                .driverLicenseType(DriverLicenseType.valueOf(resultSet.getString("licenseType")))
                .city(resultSet.getString("city"))
                .registerDate(resultSet.getDate("registerDate").toLocalDate())
                .expireDate(resultSet.getDate("expireDate").toLocalDate())
                .build();
    }
}
