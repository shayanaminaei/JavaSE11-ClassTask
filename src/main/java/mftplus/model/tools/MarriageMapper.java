package mftplus.model.tools;

import mftplus.model.entity.Marriage;
import mftplus.model.service.PersonService;

import java.sql.ResultSet;
import java.time.LocalDate;

public class MarriageMapper {

    public static Marriage marriageMapper(ResultSet resultSet) throws Exception {
        return Marriage
                .builder()
                .marriageId(resultSet.getInt("id"))
                .person(PersonService.getService().findById(resultSet.getInt("person_id")))
                .name(resultSet.getString("name"))
                .family(resultSet.getString("family"))
                .marriageDate(LocalDate.from(resultSet.getTimestamp("marriage_date").toLocalDateTime()))
                .isAlive(resultSet.getBoolean("is_alive"))
                .children(resultSet.getInt("children"))
                .build();
    }
}