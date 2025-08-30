package mftplus.model.tools;

import mftplus.model.entity.Education;
import mftplus.model.entity.enums.EducationGrade;
import mftplus.model.service.PersonService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationMapper {
    public Education educationMapper(ResultSet resultSet) throws Exception {
        return Education
                .builder()
                .id(resultSet.getInt("id"))
                .person(PersonService.getService().findById(resultSet.getInt("person_id")))
                .university(resultSet.getString("university"))
                .educationGrade(EducationGrade.valueOf(resultSet.getString("education_grade")))
                .startDate(resultSet.getDate("start_date").toLocalDate())
                .endDate(resultSet.getDate("end_date").toLocalDate())
                .build();
    }
}
