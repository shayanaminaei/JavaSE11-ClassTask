package mftplus.model.repository;

import mftplus.model.entity.Property;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.PropertyMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository implements Repository<Property, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PropertyMapper propertyMapper = new PropertyMapper();


    public PropertyRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Property property) throws Exception {
        preparedStatement = connection.prepareStatement(
                "insert into properties (id, person_id, name, brand, serial, count, date_time) values (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, property.getId());
        preparedStatement.setInt(2, property.getPerson().getId());
        preparedStatement.setString(3, property.getName());
        preparedStatement.setString(4, property.getBrand());
        preparedStatement.setString(5, property.getSerial());
        preparedStatement.setInt(6, property.getCount());
        preparedStatement.setTimestamp(7, Timestamp.valueOf(property.getDateTime()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(Property property) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update properties set person_id=?, name=?, brand=?, serial=?, count=?, date_time=? where id=?"

        );
        preparedStatement.setInt(1, property.getPerson().getId());
        preparedStatement.setString(2, property.getName());
        preparedStatement.setString(3, property.getBrand());
        preparedStatement.setString(4, property.getSerial());
        preparedStatement.setInt(5, property.getCount());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(property.getDateTime()));
        preparedStatement.setInt(7, property.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("delete from properties where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Property> findAll() throws Exception {
        List<Property> propertyList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from properties");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Property property = propertyMapper.propertyMapper(resultSet);
            propertyList.add(property);
        }
        return propertyList;
    }

    @Override
    public Property findById(Integer id) throws Exception {
        Property property = null;

        preparedStatement = connection.prepareStatement("select * from properties where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            property = propertyMapper.propertyMapper(resultSet);
        }
        return property;
    }

    public List<Property> findByName(String name) throws Exception {
        List<Property> propertyList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from properties where name like ?");
        preparedStatement.setString(1, "%" + name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            propertyList.add(propertyMapper.propertyMapper(resultSet));
        }
        return propertyList;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
