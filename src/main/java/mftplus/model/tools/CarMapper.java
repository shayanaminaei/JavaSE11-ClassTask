package mftplus.model.tools;

import mftplus.model.entity.Car;
import mftplus.model.service.PersonService;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CarMapper {
    public Car carMapper(ResultSet resultSet) throws Exception {
        return Car
                .builder()
                .id(resultSet.getInt("id"))
                .person(PersonService.getService().findById(resultSet.getInt("person_id")))
                .name(resultSet.getString("name"))
                .brand(resultSet.getString("brand"))
                .manDate(resultSet.getDate("man_date").toLocalDate())
                .color(resultSet.getString("color"))
                .plate(resultSet.getString("plate"))
                .build();
    }
}
