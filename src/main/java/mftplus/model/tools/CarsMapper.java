package mftplus.model.tools;

import mftplus.model.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarsMapper {

    public Car carsMapper(ResultSet resultSet) throws SQLException {
        return Car
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getInt("person_id"))
                .name(resultSet.getString("name"))
                .model(resultSet.getString("car_model"))
                .brand(resultSet.getString("brand"))
                .manDate(resultSet.getDate("man_date").toLocalDate())
                .color(resultSet.getString("color"))
                .plate(resultSet.getString("plate"))
                .build();
    }
}
