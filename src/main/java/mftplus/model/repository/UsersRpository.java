package mftplus.model.repository;

import mftplus.model.entity.Users;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.UsersMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRpository implements Repository<Users, Integer> ,AutoCloseable {
    private  final Connection connection;
    private PreparedStatement preparedStatement;
    private final UsersMapper usersMapper = new UsersMapper();

    public UsersRpository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Users users) throws Exception {
        users.setId(ConnectionProvider.getProvider().getNextId("users_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into Users (id,person_id,username,password,nickName, locked,register_date)  values (users_seq.nextval,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, users.getId());
        preparedStatement.setInt(2, users.getPerson().getId());
        preparedStatement.setString(3, users.getUsername());
        preparedStatement.setString(4, users.getPassword());
        preparedStatement.setString(5, users.getNickname());
        preparedStatement.setBoolean(6, users.getLocked());
        preparedStatement.setDate(7, Date.valueOf(users.getRegisterDate()));
        preparedStatement.execute();


    }

    @Override
    public void edit(Users users) throws Exception {
        preparedStatement = connection.prepareStatement(
         "update Users set person_id=?,username=?,password=?,nickName=?, locked=?,register_date=?  where id=?"
        );
        preparedStatement.setInt(1, users.getId());
        preparedStatement.setInt(2, users.getPerson().getId());
        preparedStatement.setString(3, users.getUsername());
        preparedStatement.setString(4, users.getPassword());
        preparedStatement.setString(5, users.getNickname());
        preparedStatement.setBoolean(6, users.getLocked());
        preparedStatement.setDate(7, Date.valueOf(users.getRegisterDate()));
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
    public List<Users> findAll() throws Exception {
        List<Users> usersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from Users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Users users =  usersMapper.usersMapper(resultSet);
            usersList.add(users);

        }
        return usersList;
    }

    @Override
    public Users findById(Integer id) throws Exception {
        Users users = null;
        preparedStatement = connection.prepareStatement("select * from Users where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            users = usersMapper.usersMapper(resultSet);
        }
        return users;
    }






    public List<Users> findByPersonId(int personId)  throws Exception{
        List<Users> usersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from Users where person_id=?");
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Users users =  usersMapper.usersMapper(resultSet);
            usersList.add(users);
        }
        return null;
    }
    public List<Users> findUsersByNumber(String umber)  throws Exception {
        List<Users> usersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from Users where umbers like =?");
        preparedStatement.setString(1, umber + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Users users =  usersMapper.usersMapper(resultSet);
            usersList.add(users);
        }
         return usersList;
    }
    @Override
    public void close() throws Exception {

        preparedStatement.close();
        connection.close();

    }
}


