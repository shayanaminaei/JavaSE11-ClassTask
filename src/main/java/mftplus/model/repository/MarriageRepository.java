package mftplus.model.repository;


import mftplus.model.entity.Marriage;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.MarriageMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarriageRepository implements Repository<Marriage, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private MarriageMapper marriageMapper;


    public MarriageRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();

    }


    @Override
    public void save(Marriage marriage) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO marriages(id, person_id, name, family, marriage_date, is_alive, children) VALUES (?, ?, ?, ?, ?, ?, ?) "
        );
        preparedStatement.setInt(1, marriage.getPersonId());
        preparedStatement.setInt(2, marriage.getMarriageId());
        preparedStatement.setString(3, marriage.getName());
        preparedStatement.setString(4, marriage.getFamily());
        preparedStatement.setDate(5, Date.valueOf(marriage.getMarriageDate()));
        preparedStatement.setBoolean(6, marriage.isAlive());
        preparedStatement.setInt(7, marriage.getChildren());
        preparedStatement.execute();

    }

    @Override
    public void edit(Marriage marriage) throws Exception {

        preparedStatement = connection.prepareStatement(
                "UPDATE marriages SET person_id=?, name=?, family=?, is_alive=?, children=?, marriage_date=? WHERE id=?"
        );
        preparedStatement.setInt(1, marriage.getPersonId());
        preparedStatement.setString(2, marriage.getName());
        preparedStatement.setString(3, marriage.getFamily());
        preparedStatement.setDate(4, Date.valueOf(marriage.getMarriageDate()));
        preparedStatement.setBoolean(5, marriage.isAlive());
        preparedStatement.setInt(6, marriage.getChildren());
        preparedStatement.execute();


    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM marriages WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<Marriage> findAll() throws Exception {
        List<Marriage> marriageList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM marriages ORDER BY id "
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Marriage marriage = MarriageMapper.marriageMapper(resultSet);
            marriageList.add(marriage);
        }
        return marriageList;

    }

    @Override
    public Marriage findById(Integer id) throws Exception {
        Marriage marriage = null;

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM marriages WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            marriage = MarriageMapper.marriageMapper(resultSet);

        }
        return marriage;
    }


    public List<Marriage> findByName(String name) throws Exception {
        List<Marriage> marriageList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM marriages WHERE name=?"
        );
        preparedStatement.setString(1, name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Marriage marriage = MarriageMapper.marriageMapper(resultSet);
            marriageList.add(marriage);
        }
        return marriageList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}