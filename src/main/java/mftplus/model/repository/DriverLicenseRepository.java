package mftplus.model.repository;

import mftplus.model.entity.DriverLicense;
import mftplus.model.entity.Education;
import mftplus.model.entity.SimCard;
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
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(DriverLicense driverLicense) throws Exception {
        driverLicense.setId(ConnectionProvider.getProvider().getNextId("License_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into driver_license (id, person_id, serial, license_type,city, register_date, expire_date) values (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, driverLicense.getId());
        preparedStatement.setInt(2, driverLicense.getPerson().getId());
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
                "update driver_license set person_id=?, serial=?, license_type=?,city=?, register_date=?, expire_date=? where id=?"
        );
        preparedStatement.setInt(1, driverLicense.getPerson().getId());
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
                "select * from driver_License order by id, PERSON_ID"
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


    public List<DriverLicense> findByPersonId(int personId) throws Exception {
        List<DriverLicense> driverLicenseList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM driver_License WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            DriverLicense driverLicense = driverLicensesMapper.driverLicensesMapper(resultSet);
            driverLicenseList.add(driverLicense);
        }
        return driverLicenseList;
    }


    public List<DriverLicense> findBySerial(String Serial) throws Exception {
        List<DriverLicense> driverLicenseList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from driver_License where Serial like ?");
        preparedStatement.setString(1, Serial + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            DriverLicense driverLicense = driverLicensesMapper.driverLicensesMapper(resultSet);
            driverLicenseList.add(driverLicense);
        }
        return driverLicenseList;
    }

    public int getNextId() throws Exception {
        ResultSet resultSet = connection.prepareStatement("SELECT license_seq.nextval AS NEXTID FROM DUAL").executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXTID");
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }


}
