package mftplus.model.repository;

import mftplus.model.entity.Medical;
import mftplus.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class MedicalRepository implements Repository <Medical, Integer> ,AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public MedicalRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }


    @Override
    public void save(Medical medical) throws Exception {

    }

    @Override
    public void edit(Medical medical) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Medical> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Medical findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
