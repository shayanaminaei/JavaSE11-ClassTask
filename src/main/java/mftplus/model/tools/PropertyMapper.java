package mftplus.model.tools;

import mftplus.model.entity.Property;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyMapper {
    public Property propertyMapper(ResultSet resultSet) throws SQLException {
        return Property
                         .builder()
                         .id(resultSet.getInt("id"))
                         .personId(resultSet.getInt("personId"))
                         .name(resultSet.getString("name"))
                         .brand(resultSet.getString("brand"))
                         .serial(resultSet.getString("serial"))
                         .count(resultSet.getInt("count"))
                         .dateTime(resultSet.getTimestamp("dateTime").toLocalDateTime())
                         .build();


    }
}
