package mftplus.model.repository;

import mftplus.model.entity.Salary;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.SalariesMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryRepository implements Repository<Salary, Integer>, AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private SalariesMapper salariesMapper = new SalariesMapper();

    public SalaryRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Salary salary) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO salaries (person_id, weekly_hour, pay_per_hour, start_date, end_date, employee_type) VALUES (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, salary.getPersonId());
        preparedStatement.setInt(2, salary.getWeeklyHour());
        preparedStatement.setInt(3, salary.getPayPerHour());
        preparedStatement.setDate(4, Date.valueOf(salary.getStartDate()));
        preparedStatement.setDate(5, Date.valueOf(salary.getEndDate()));
        preparedStatement.setString(6, salary.getEmployeeType().name());
        preparedStatement.execute();
    }

    @Override
    public void edit(Salary salary) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE salaries SET person_id=?, weekly_hour=?, pay_per_hour=?, start_date=?, end_date=?, employee_type=? WHERE id=?"
        );
        preparedStatement.setInt(1, salary.getPersonId());
        preparedStatement.setInt(2, salary.getWeeklyHour());
        preparedStatement.setInt(3, salary.getPayPerHour());
        preparedStatement.setDate(4, Date.valueOf(salary.getStartDate()));
        preparedStatement.setDate(5, Date.valueOf(salary.getEndDate()));
        preparedStatement.setString(6, salary.getEmployeeType().name());
        preparedStatement.setInt(7, salary.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM salaries WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Salary> findAll() throws Exception {
        List<Salary> salaryList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM salaries ORDER BY id, person_id");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            salaryList.add(salariesMapper.salaryMapper(rs));
        }
        return salaryList;
    }

    @Override
    public Salary findById(Integer id) throws Exception {
        Salary salary = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM salaries WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            salary = salariesMapper.salaryMapper(rs);
        }
        return salary;
    }


    @Override
    public void close() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        if (connection != null) connection.close();
    }
}