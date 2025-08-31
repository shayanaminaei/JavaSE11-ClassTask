package mftplus.model.tools;

import mftplus.model.entity.Skill;
import mftplus.model.service.PersonService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillMapper {
    public Skill skillMapper(ResultSet resultSet) throws Exception {
        return Skill
                .builder()
                .id(resultSet.getInt("id"))
                .person(PersonService.getService().findById(resultSet.getInt("person_id")))
                .title(resultSet.getString("title"))
                .institute(resultSet.getString("institute"))
                .duration(resultSet.getInt("duration"))
                .registerDate(resultSet.getDate("register_date").toLocalDate())
                .score(resultSet.getInt("score"))
                .build();
    }
}
