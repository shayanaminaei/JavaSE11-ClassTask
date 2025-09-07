package mftplus.model.repository;

import mftplus.model.entity.Accounts;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.AccountsMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AccountsRepository implements Repository<Accounts, Integer>, AutoCloseable {
    private final Connection Connection;
    private PreparedStatement PreparedStatement;
    private final AccountsMapper AccountsMapper = new AccountsMapper();

    public AccountsRepository() throws SQLException {
        Connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void Save(Accounts Accounts) throws Exception {
        Accounts.setId(ConnectionProvider.getProvider().getNextId("ACCOUNTS_SEQ"));

        PreparedStatement = Connection.prepareStatement(
                "INSERT INTO ACCOUNTS (ID, PERSON_ID, BRANCH, ACCOUNT_ID, CARD_NUMBER, REGISTER_DATE)" +
                        " VALUES (?, ?, ?, ?, ?, ?)"
        );
        PreparedStatement.setInt(1, Accounts.getId());
        PreparedStatement.setInt(2, Accounts.getPersonId());
        PreparedStatement.setString(3, Accounts.getBranch());
        PreparedStatement.setString(4, Accounts.getAccountId());
        PreparedStatement.setString(5, Accounts.getCardNumber());
        PreparedStatement.setDate(6, Date.valueOf(Accounts.getRegisterDate()));
        PreparedStatement.execute();
    }

    @Override
    public void Edit(Accounts Accounts) throws Exception {
        PreparedStatement = Connection.prepareStatement(
                "UPDATE ACCOUNTS SET PERSON_ID=?, BRANCH=?, ACCOUNT_ID=?, CARD_NUMBER=?, REGISTER_DATE=? WHERE ID=?"
        );
        PreparedStatement.setInt(1, Accounts.getPersonId());
        PreparedStatement.setString(2, Accounts.getBranch());
        PreparedStatement.setString(3, Accounts.getAccountId());
        PreparedStatement.setString(4, Accounts.getCardNumber());
        PreparedStatement.setDate(5, Date.valueOf(Accounts.getRegisterDate()));
        PreparedStatement.setInt(6, Accounts.getId());
        PreparedStatement.execute();
    }

    @Override
    public void Delete(Integer Id) throws Exception {
        PreparedStatement = Connection.prepareStatement(
                "DELETE FROM ACCOUNTS WHERE ID=?"
        );
        PreparedStatement.setInt(1, Id);
        PreparedStatement.execute();
    }

    @Override
    public List<Accounts> FindAll() throws Exception {
        List<Accounts> AccountsList = new ArrayList<>();

        PreparedStatement = Connection.prepareStatement("SELECT * FROM ACCOUNTS ORDER BY PERSON_ID");
        ResultSet ResultSet = PreparedStatement.executeQuery();

        while (ResultSet.next()) {
            Accounts Accounts = AccountsMapper.accountsMapper(ResultSet);
            AccountsList.add(Accounts);
        }
        return AccountsList;
    }

    @Override
    public Accounts FindById(Integer Id) throws Exception {
        Accounts Accounts = null;

        PreparedStatement = Connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE ID=?");
        PreparedStatement.setInt(1, Id);
        ResultSet ResultSet = PreparedStatement.executeQuery();

        if (ResultSet.next()) {
            Accounts = AccountsMapper.accountsMapper(ResultSet);
        }
        return Accounts;
    }

    public List<Accounts> FindByPersonId(Integer PersonId) throws Exception {
        List<Accounts> AccountsList = new ArrayList<>();

        PreparedStatement = Connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE PERSON_ID=?");
        PreparedStatement.setInt(1, PersonId);
        ResultSet ResultSet = PreparedStatement.executeQuery();

        while (ResultSet.next()) {
            Accounts Accounts = AccountsMapper.AccountsMapper(ResultSet);
            AccountsList.add(Accounts);
        }
        return AccountsList;
    }

    @Override
    public void Close() throws Exception {
        PreparedStatement.close();
        Connection.close();
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void save(Accounts accounts) throws Exception {

    }

    @Override
    public void edit(Accounts accounts) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Accounts> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Accounts findById(Integer id) throws Exception {
        return null;
    }
}