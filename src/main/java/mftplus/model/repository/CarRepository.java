package mftplus.model.repository;

import mftplus.model.entity.Car;

import mftplus.model.tools.PersonMapper;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class CarRepository implements Repository<Car, Integer>, AutoCloseable {
    //private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PersonMapper personMapper = new PersonMapper();

    public CarRepository(Connection connection) throws SQLException {
        //connection.ConnectionProvider.getProvider().getConnection();

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void save(Car car) throws Exception {

    }

    @Override
    public void edit(Car car) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Car> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Car findById(Integer id) throws Exception {
        return null;
    }
}



