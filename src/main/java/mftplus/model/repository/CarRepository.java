package mftplus.model.repository;

import mftplus.model.entity.Car;
import mftplus.model.tools.CarMapper;
import mftplus.model.tools.ConnectionProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements Repository<Car, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final CarMapper carMapper = new CarMapper();

    public CarRepository() throws SQLException {
        connection= ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Car car) throws Exception {
        car.setId(ConnectionProvider.getProvider().getNextId("Car_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into cars(id, person_id, name, brand, man_date,color,plate) values(?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, car.getId());
        preparedStatement.setInt(2, car.getPersonId());
        preparedStatement.setString(3,car.getName());
        preparedStatement.setString(4, car.getBrand());
        preparedStatement.setDate(5, Date.valueOf(car.getManDate()));
        preparedStatement.setString(6, car.getColor());
        preparedStatement.setString(7, car.getPlate());
        preparedStatement.execute();

    }

    @Override
    public void edit(Car car) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update cars set  person_id =?, name = ?, brand=?, man_date=?,color=?,plate=? where id=?"
        );

        preparedStatement.setInt(1, car.getPersonId());
        preparedStatement.setString(2,car.getName());
        preparedStatement.setString(3, car.getBrand());
        preparedStatement.setDate(4, Date.valueOf(car.getManDate()));
        preparedStatement.setString(5, car.getColor());
        preparedStatement.setString(6, car.getPlate());
        preparedStatement.setInt(7, car.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from cars where id=?"

        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }


    @Override
    public List<Car> findAll() throws Exception {
        List<Car> carList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from CARS order by brand, name");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Car car = carMapper.carMapper(resultSet);
            carList.add(car);
        }
        return carList;
    }

    @Override
    public Car findById(Integer id) throws Exception {
        Car car = null;
        preparedStatement = connection.prepareStatement("select * from CARS where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            car = carMapper.carMapper(resultSet);
        }
        return car;
    }

    public List<Car> findByPersonId(int personId) throws Exception {
        List<Car> carList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from CARS where person_id=?");
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Car car = carMapper.carMapper(resultSet);
            carList.add(car);
        }
        return carList;
    }

    public List<Car> findByBrand(String brand) throws Exception {
        List<Car> carList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from CARS where BRAND like ? ");
        preparedStatement.setString(1, brand + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Car car = carMapper.carMapper(resultSet);
            carList.add(car);
        }
        return carList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }





}



