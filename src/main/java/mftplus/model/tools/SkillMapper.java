package mftplus.model.tools;

import mftplus.model.entity.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillMapper {
    public Skill skillMapper(ResultSet resultSet) throws SQLException {
        return Skill
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getInt("person_id"))
                .title(resultSet.getString("title"))
                .institute(resultSet.getString("institute"))
                .duration(resultSet.getInt("duration"))
                .registerDate(resultSet.getDate("register_date").toLocalDate())
                .score(resultSet.getInt("score"))
                .build();
    }
}
