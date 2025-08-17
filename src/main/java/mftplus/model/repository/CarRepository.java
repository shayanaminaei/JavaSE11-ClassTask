package mftplus.model.repository;

import mftplus.model.entity.Car;

import mftplus.model.tools.PersonMapper;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Objects;


public class CarRepository implements Repository<Car, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PersonMapper personMapper = new PersonMapper();

    public CarRepository(Connection connection) throws SQLException {
        //connection.ConnectionProvider.getProvider().getConnection();
    }



