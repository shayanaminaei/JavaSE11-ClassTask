package mftplus.model.tools;

import mftplus.model.entity.Lesson;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonMapper {
    public Lesson lessonMapper(ResultSet resultSet) throws SQLException {
        return Lesson
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getInt("person_id"))
                .title(resultSet.getString("title"))
                .code(resultSet.getInt("code"))
                .teacher(resultSet.getString("teacher"))
                .unit(resultSet.getString("unit"))
                .startDataTime(resultSet.getDate("start_date_time").toLocalDate())
                .build();
    }
}
