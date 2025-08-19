package mftplus.model.tools;

import mftplus.model.entity.Salary;
import mftplus.model.entity.enums.EmployeeType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalariesMapper {

    public Salary salaryMapper(ResultSet rs) throws SQLException {
        Salary.SalaryBuilder builder = Salary.builder()
                .id(rs.getInt("id"))
                .personId(rs.getInt("person_id"))
                .weeklyHour(rs.getInt("weekly_hour"))
                .payPerHour(rs.getInt("pay_per_hour"))
                .startDate(rs.getDate("start_date").toLocalDate())
                .endDate(rs.getDate("end_date").toLocalDate())
                .employeeType(EmployeeType.valueOf(rs.getString("employee_type")));


        return builder.build();
    }
}