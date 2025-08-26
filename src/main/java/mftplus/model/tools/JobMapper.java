package mftplus.model.tools;

import mftplus.model.entity.Job;
import mftplus.model.entity.enums.JobTitle;


import java.sql.ResultSet;
import java.sql.SQLException;

public class JobMapper {
    public Job jobMapper(ResultSet resultSet) throws SQLException {
        return Job
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getInt("person_id"))
                .organisation(resultSet.getString("organisation"))
                .title(JobTitle.valueOf(resultSet.getString("job_title")))
                .startDate(resultSet.getDate("start_date").toLocalDate())
                .endDate(resultSet.getDate("end_date").toLocalDate())
                .description(resultSet.getString("description"))
                .build();

    }
}
