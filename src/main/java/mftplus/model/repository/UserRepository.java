package mftplus.model.repository;

import mftplus.model.entity.User;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.UserMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final UserMapper userMapper = new UserMapper();

    public UserRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(User user) throws Exception {
        user.setId(ConnectionProvider.getProvider().getNextId("users_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into Users (id,person_id,username,password,nickName, locked,register_date)  values (users_seq.nextval,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, user.getPerson().getId());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getNickname());
        preparedStatement.setBoolean(6, user.getLocked());
        preparedStatement.setDate(7, Date.valueOf(user.getBirthdate()));
        preparedStatement.execute();
    }

    @Override
    public void edit(User user) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Users set person_id=?,username=?,password=?,nickName=?, locked=?,birthdate_date=?  where id=?"
        );
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, user.getPerson().getId());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getNickname());
        preparedStatement.setBoolean(6, user.getLocked());
        preparedStatement.setDate(7, Date.valueOf(user.getBirthdate()));
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Users where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> userList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from Users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = userMapper.userMapper(resultSet);
            userList.add(user);

        }
        return userList;
    }

    @Override
    public User findById(Integer id) throws Exception {
        User user = null;
        preparedStatement = connection.prepareStatement("select * from Users where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }
        return user;
    }


    public User findByPersonId(int personId) throws Exception {
        User user = null;
        preparedStatement = connection.prepareStatement("select * from Users where person_id=?");
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }
        return user;
    }

    public User findByUsername(String username) throws Exception {
        User user = null;
        preparedStatement = connection.prepareStatement("select * from Users where username=?");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }
        return user;
    }

    public User findByUsernameAndPassword(String username, String password) throws Exception {
        User user = null;
        preparedStatement = connection.prepareStatement("select * from Users where username=? and password=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }
        return user;
    }

    @Override
    public void close() throws Exception {

        preparedStatement.close();
        connection.close();

    }
}


