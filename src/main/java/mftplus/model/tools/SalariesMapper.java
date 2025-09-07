package mftplus.model.tools;

import mftplus.model.entity.Salary;
import mftplus.model.entity.enums.EmployeeType;
import mftplus.model.service.PersonService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalariesMapper {

    public Salary salaryMapper(ResultSet rs) throws Exception {
        Salary.SalaryBuilder builder = Salary.builder()
                .id(rs.getInt("id"))
                .person(PersonService.getService().findById(rs.getInt("person_id")))
                .weeklyHour(rs.getInt("weekly_hour"))
                .payPerHour(rs.getInt("pay_per_hour"))
                .startDate(rs.getDate("start_date").toLocalDate())
                .endDate(rs.getDate("end_date").toLocalDate())
                .employeeType(EmployeeType.valueOf(rs.getString("employee_type")));


        return builder.build();
    }
}