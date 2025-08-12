package mftplus.model.repository;


import mftplus.model.entity.SimCard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimCardsRepository implements Repository <SimCard,Integer> , AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public void save(SimCard simCard) throws Exception {
        preparedStatement = connection.prepareStatement(
                "insert into sim_Cards (id,person_id,title,numbers,operator,register_date,status) values (sim_card_seq.nextval,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, simCard.getPersonId());
        preparedStatement.setString(2, simCard.getTitle().name());
        preparedStatement.setString(3, simCard.getNumbers());
        preparedStatement.setString(4, simCard.getSimCardOperator().name());
        preparedStatement.setDate(5,Date.valueOf(simCard.getRegisterDate()));
        preparedStatement.setBoolean(6, simCard.isStatus());
        preparedStatement.execute();
    }

    @Override
    public void edit(SimCard simCard) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update sim_Cards set person_id=?,title=?,numbers=?,operator=?,register_date=?,status=? where id=?"
        );
        preparedStatement.setInt(1, simCard.getPersonId());
        preparedStatement.setString(2, simCard.getTitle().name());
        preparedStatement.setString(3, simCard.getNumbers());
        preparedStatement.setString(4, simCard.getSimCardOperator().name());
        preparedStatement.setDate(5,Date.valueOf(simCard.getRegisterDate()));
        preparedStatement.setBoolean(6, simCard.isStatus());
        preparedStatement.setInt(7, simCard.getId());
        preparedStatement.execute();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from sim_Cards where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<SimCard> findAll() throws Exception {
        return Collections.emptyList();
    }


    @Override
    public SimCard findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
