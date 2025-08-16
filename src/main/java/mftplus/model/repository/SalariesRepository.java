package mftplus.model.repository;

import mftplus.model.entity.Salaries;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.SalariesMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalariesRepository implements Repository<Salaries, Integer>, AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private SalariesMapper salariesMapper = new SalariesMapper();

    public SalariesRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Salaries salaries) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO salaries (person_id, weekly_hour, pay_per_hour, start_date, end_date, employee_type) VALUES (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, salaries.getPersonId());
        preparedStatement.setInt(2, salaries.getWeeklyHour());
        preparedStatement.setInt(3, salaries.getPayPerHour());
        preparedStatement.setDate(4, Date.valueOf(salaries.getStartDate()));
        preparedStatement.setDate(5, Date.valueOf(salaries.getEndDate()));
        preparedStatement.setString(6, salaries.getEmployeeType().name());
        preparedStatement.execute();
    }

    @Override
    public void edit(Salaries salaries) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE salaries SET person_id=?, weekly_hour=?, pay_per_hour=?, start_date=?, end_date=?, employee_type=? WHERE id=?"
        );
        preparedStatement.setInt(1, salaries.getPersonId());
        preparedStatement.setInt(2, salaries.getWeeklyHour());
        preparedStatement.setInt(3, salaries.getPayPerHour());
        preparedStatement.setDate(4, Date.valueOf(salaries.getStartDate()));
        preparedStatement.setDate(5, Date.valueOf(salaries.getEndDate()));
        preparedStatement.setString(6, salaries.getEmployeeType().name());
        preparedStatement.setInt(7, salaries.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM salaries WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Salaries> findAll() throws Exception {
        List<Salaries> salaryList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM salaries ORDER BY id, person_id");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            salaryList.add(salariesMapper.salaryMapper(rs));
        }
        return salaryList;
    }

    @Override
    public Salaries findById(Integer id) throws Exception {
        Salaries salaries = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM salaries WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            salaries = salariesMapper.salaryMapper(rs);
        }
        return salaries;
    }


    @Override
    public void close() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        if (connection != null) connection.close();
    }
}