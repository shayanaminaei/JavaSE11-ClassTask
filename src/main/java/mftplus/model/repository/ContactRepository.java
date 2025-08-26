package mftplus.model.repository;

import mftplus.model.entity.Contact;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.ContactMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository implements Repository<Contact, Integer>, AutoCloseable{
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final ContactMapper contactMapper = new ContactMapper();

    public ContactRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Contact contact) throws Exception {
        contact.setId(ConnectionProvider.getProvider().getNextId("contact_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into contacts (id, person_id, contact_title, contact_id, contact_type, description, status)" +
                        "values (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, contact.getId());
        preparedStatement.setInt(2, contact.getPersonId());
        preparedStatement.setString(3, contact.getTitle().name());
        preparedStatement.setString(4, contact.getContactId());
        preparedStatement.setString(5, contact.getContactType());
        preparedStatement.setString(6, contact.getDescription());
        preparedStatement.setBoolean(7, contact.isStatus());
    }

    @Override
    public void edit(Contact contact) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update contacts set person_id=?, contact_title=?, contact_id=?, contact_type=?, description=?, status=? where id=?"
        );
        preparedStatement.setInt(1, contact.getPersonId());
        preparedStatement.setString(2, contact.getTitle().name());
        preparedStatement.setString(3, contact.getContactId());
        preparedStatement.setString(4, contact.getContactType());
        preparedStatement.setString(5, contact.getDescription());
        preparedStatement.setBoolean(6, contact.isStatus());
        preparedStatement.setInt(7, contact.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from contacts where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Contact> findAll() throws Exception {
        List<Contact> contactList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from contacts order by contact_id, contact_type"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Contact contact = contactMapper.contactMapper(resultSet);
            contactList.add(contact);
        }
        return contactList;
    }

    @Override
    public Contact findById(Integer id) throws Exception {
        Contact contact = null;

        preparedStatement = connection.prepareStatement(
                "select * from contacts where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            contact = contactMapper.contactMapper(resultSet);
        }
        return contact;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
