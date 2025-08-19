package mftplus.model.repository;

import mftplus.model.entity.DriverLicense;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.DriverLicensesMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverLicenseRepository implements Repository<DriverLicense, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final DriverLicensesMapper driverLicensesMapper = new DriverLicensesMapper();

    public DriverLicenseRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(DriverLicense driverLicense) throws Exception {
        driverLicense.setId(ConnectionProvider.getProvider().getNextId("driverLicense_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into driver_license (id, personid, serial, licenseType,city, register_Date, expire_Date) values (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, driverLicense.getId());
        preparedStatement.setInt(2, driverLicense.getPersonId());
        preparedStatement.setString(3, driverLicense.getSerial());
        preparedStatement.setString(4, driverLicense.getDriverLicenseType().name());
        preparedStatement.setString(5, driverLicense.getCity());
        preparedStatement.setDate(6, Date.valueOf(driverLicense.getRegisterDate()));
        preparedStatement.setDate(7, Date.valueOf(driverLicense.getExpireDate()));
        preparedStatement.execute();
    }

    @Override
    public void edit(DriverLicense driverLicense) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update driver_license set personid=?, serial=?, licenseType=?,city=?, register_Date=?, expire_Date=? where id=?"
        );
        preparedStatement.setInt(1, driverLicense.getPersonId());
        preparedStatement.setString(2, driverLicense.getSerial());
        preparedStatement.setString(3, driverLicense.getDriverLicenseType().name());
        preparedStatement.setString(4, driverLicense.getCity());
        preparedStatement.setDate(5, Date.valueOf(driverLicense.getRegisterDate()));
        preparedStatement.setDate(6, Date.valueOf(driverLicense.getExpireDate()));
        preparedStatement.setInt(7, driverLicense.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from driver_License where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<DriverLicense> findAll() throws Exception {
        List<DriverLicense> driverLicenseList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from driver_License order by id, personId"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            DriverLicense driverLicense = driverLicensesMapper.driverLicensesMapper(resultSet);
            driverLicenseList.add(driverLicense);
        }
        return driverLicenseList;
    }

    @Override
    public DriverLicense findById(Integer id) throws Exception {
        DriverLicense driverLicense = null;

        preparedStatement = connection.prepareStatement(
                "select * from driver_License where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            driverLicense = driverLicensesMapper.driverLicensesMapper(resultSet);
        }
        return driverLicense;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
