package mftplus.model.tools;

import mftplus.model.entity.Job;
import mftplus.model.entity.enums.JobTitle;
import mftplus.model.service.PersonService;


import java.sql.ResultSet;

public class JobMapper {
    public Job jobMapper(ResultSet resultSet) throws Exception {
        return Job
                .builder()
                .id(resultSet.getInt("id"))
                .person(PersonService.getService().findById(resultSet.getInt("person_id")))
                .organisation(resultSet.getString("organisation"))
                .jobTitle(JobTitle.valueOf(resultSet.getString("job_title")))
                .startDate(resultSet.getDate("start_date").toLocalDate())
                .endDate(resultSet.getDate("end_date").toLocalDate())
                .description(resultSet.getString("description"))
                .build();

    }
}
