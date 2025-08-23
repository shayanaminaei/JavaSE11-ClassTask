package mftplus.model.repository;

import mftplus.model.entity.Person;
import mftplus.model.entity.Property;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.PropertyMapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository implements Repository<Property, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PropertyMapper PropertyMapper = new PropertyMapper();


    public PropertyRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Property property) throws Exception {
        preparedStatement = connection.prepareStatement(
                "insert into properties (id, person_id, name, brand, serial, count, date_time) values (property_seq.nextval,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, property.getPersonId());
        preparedStatement.setString(2, property.getName());
        preparedStatement.setString(3, property.getBrand());
        preparedStatement.setString(4, property.getSerial());
        preparedStatement.setInt(5, property.getCount());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(property.getDateTime()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(Property property) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update properties set personal_id=?, name=?, brand=?, serial=?, count=?, date_time=? where id=?"

        );
        preparedStatement.setInt(1, property.getPersonId());
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

        preparedStatement = connection.prepareStatement("select * from properties order by id");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Property property = PropertyMapper.propertyMapper(resultSet);
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
            property = PropertyMapper.propertyMapper(resultSet);
        }
        return property;
    }

    public List<Property> findByName(String name) throws Exception {
        List<Property> propertyList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from properties where name like ?");
        preparedStatement.setString(1, name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Property property = PropertyMapper.propertyMapper(resultSet);
            propertyList.add(property);
        }
        return propertyList;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
