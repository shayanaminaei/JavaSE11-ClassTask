package mftplus.model.tools;

import mftplus.model.entity.Person;
import mftplus.model.entity.enums.Role;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper {
    public Person personMapper(ResultSet resultSet) throws SQLException {
        return  Person
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .family(resultSet.getString("family"))
                        .birthDate(resultSet.getDate("birth_date").toLocalDate())
                        .role(Role.valueOf(resultSet.getString("role")))
                        .status(resultSet.getBoolean("status"))
                        .build();
    }
}
