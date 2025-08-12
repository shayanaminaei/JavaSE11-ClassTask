package mftplus.model.tools;

import mftplus.model.entity.Property;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyMapper {
    public static Property propertyMapper(ResultSet resultSet) throws SQLException {
        return Property
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getInt("person_id"))
                .name(resultSet.getString("name"))
                .brand(resultSet.getString("brand"))
                .serial(resultSet.getString("serial"))
                .count(resultSet.getInt("count"))
                .dateTime(resultSet.getTimestamp("date_time"))
                .build();
    }
}
