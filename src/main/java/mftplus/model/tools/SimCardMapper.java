package mftplus.model.tools;

import mftplus.model.entity.SimCard;
import mftplus.model.entity.enums.SimCardOperator;
import mftplus.model.entity.enums.Title;
import mftplus.model.service.PersonService;

import java.sql.ResultSet;


public class SimCardMapper {
    public SimCard simCardMapper(ResultSet resultSet) throws Exception {
        return SimCard
                .builder()
                .id(resultSet.getInt("id"))
                .person(PersonService.getService().findById(resultSet.getInt("person_id")))
                .title(Title.valueOf(resultSet.getString("title")))
                .numbers(resultSet.getString("numbers"))
                .simCardOperator(SimCardOperator.valueOf(resultSet.getString("operator")))
                .registerDate(resultSet.getDate("register_date").toLocalDate())
                .status(resultSet.getBoolean("status"))
                .build();
    }
}
