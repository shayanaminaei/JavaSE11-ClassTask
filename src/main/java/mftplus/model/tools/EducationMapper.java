package mftplus.model.tools;

import mftplus.model.entity.Education;
import mftplus.model.entity.enums.EducationGrade;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationMapper {
    public Education educationMapper(ResultSet resultSet) throws SQLException {
        return Education
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getInt("person_id"))
                .university(resultSet.getString("university"))
                .educationGrade(EducationGrade.valueOf(resultSet.getString("education_grade")))
                .startDate(resultSet.getDate("start_date").toLocalDate())
                .endDate(resultSet.getDate("end_date").toLocalDate())
                .build();
    }
}
