package mftplus.model.tools;

import mftplus.model.entity.DriverLicense;
import mftplus.model.entity.enums.DriverLicenseType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverLicensesMapper {

    public DriverLicense driverLicensesMapper(ResultSet resultSet) throws SQLException {
        return  DriverLicense
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getString("personId"))
                .serial(resultSet.getString("serial"))
                .driverLicenseType(DriverLicenseType.valueOf(resultSet.getString("licenseType")))
                .city(resultSet.getString("city"))
                .registerDate(resultSet.getDate("registerDate").toLocalDate())
                .expireDate(resultSet.getDate("expireDate").toLocalDate())
                .build();
    }
}
