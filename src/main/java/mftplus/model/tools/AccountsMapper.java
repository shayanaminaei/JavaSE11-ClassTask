package mftplus.model.tools;

import mftplus.model.entity.Accounts;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountsMapper {
    public Accounts AccountsMapper(ResultSet ResultSet) throws SQLException {
        return Accounts
                .Builder()
                .Id(ResultSet.getInt("ID"))
                .PersonId(ResultSet.getInt("PERSON_ID"))
                .Branch(ResultSet.getString("BRANCH"))
                .AccountId(ResultSet.getString("ACCOUNT_ID"))
                .CardNumber(ResultSet.getString("CARD_NUMBER"))
                .RegisterDate(ResultSet.getDate("REGISTER_DATE").toLocalDate())
                .Build();
    }
}