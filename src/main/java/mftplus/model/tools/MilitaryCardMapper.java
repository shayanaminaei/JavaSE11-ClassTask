package mftplus.model.tools;

import mftplus.model.entity.MilitaryCard;
import mftplus.model.entity.enums.LicenseType;

import java.sql.ResultSet;
import java.sql.SQLException;
public class MilitaryCardMapper {
    public MilitaryCard map(ResultSet resultSet) throws SQLException {
        return  MilitaryCard
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getString("personId"))
                .cardSerial(resultSet.getString("cardSerial"))
                .licenseType(LicenseType.valueOf(resultSet.getString("LicenseType")))
                .city(resultSet.getString("city"))
                .organisation(resultSet.getString("organisation"))
                .duration(resultSet.getBoolean("duration"))
                .build();
    }

}
