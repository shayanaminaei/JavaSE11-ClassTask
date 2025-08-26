package mftplus.model.repository;

import mftplus.model.entity.Person;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.PersonMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonRepository implements Repository<Person, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PersonMapper personMapper = new PersonMapper();

    public PersonRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getH2Connection();
    }

    @Override
    public void save(Person person) throws Exception {
        person.setId(ConnectionProvider.getProvider().getNextId("person_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into persons (id, name, family, birth_date, role, status)" +
                        " values (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setDate(4, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(5, person.getRole().name());
        preparedStatement.setBoolean(6, person.isStatus());
        preparedStatement.execute();
    }

    @Override
    public void edit(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update persons set name=?, family=?, birth_date=?, role=?, status=? where id=?"
        );
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setDate(3, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(4, person.getRole().name());
        preparedStatement.setBoolean(5, person.isStatus());
        preparedStatement.setInt(6, person.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from persons where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Person> findAll() throws Exception {
        List<Person> personList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from persons order by family, name");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = personMapper.personMapper(resultSet);
            personList.add(person);
        }
        return personList;
    }

    @Override
    public Person findById(Integer id) throws Exception {
        Person person = null;

        preparedStatement = connection.prepareStatement("select * from persons where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            person = personMapper.personMapper(resultSet);
        }
        return person;
    }

    public List<Person> findByNameAndFamily(String name, String family) throws Exception {
        List<Person> personList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from persons where name like ? and family like ?");
        preparedStatement.setString(1, name + "%");
        preparedStatement.setString(2, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = personMapper.personMapper(resultSet);
            personList.add(person);
        }
        return personList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
