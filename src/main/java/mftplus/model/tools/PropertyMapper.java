package mftplus.model.tools;

import mftplus.model.entity.Property;
import mftplus.model.service.PersonService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyMapper {
    public Property propertyMapper(ResultSet resultSet) throws Exception {
        return Property
                         .builder()
                         .id(resultSet.getInt("id"))
                         .person(PersonService.getService().findById(resultSet.getInt("person_id")))
                         .name(resultSet.getString("name"))
                         .brand(resultSet.getString("brand"))
                         .serial(resultSet.getString("serial"))
                         .count(resultSet.getInt("count"))
                         .dateTime(resultSet.getTimestamp("dateTime").toLocalDateTime())
                         .build();


    }
}
