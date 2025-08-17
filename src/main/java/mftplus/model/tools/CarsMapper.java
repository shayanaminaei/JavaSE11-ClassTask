package mftplus.model.tools;

import mftplus.model.entity.Cars;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CarsMapper {

    public Cars carsMapper(ResultSet resultSet) throws SQLException {
        return Cars
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getInt("person_id"))
                .carName(resultSet.getString("name"))
                .carModel(resultSet.getString("car_model"))
                .brand(resultSet.getString("brand"))
                .manDate(resultSet.getDate("man_date").toLocalDate())
                .color(resultSet.getString("color"))
                .plate(resultSet.getString("plate"))
                .build();
    }
}
