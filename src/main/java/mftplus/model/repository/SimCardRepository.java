package mftplus.model.repository;


import mftplus.model.entity.SimCard;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.SimCardMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimCardRepository implements Repository<SimCard, Integer>, AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private SimCardMapper simCardMapper = new SimCardMapper();

    public SimCardRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(SimCard simCard) throws Exception {
        preparedStatement = connection.prepareStatement(
                "insert into sim_Cards (id,person_id,title,numbers,operator,register_date,status) values (sim_card_seq.nextval,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, simCard.getPersonId());
        preparedStatement.setString(2, simCard.getTitle().name());
        preparedStatement.setString(3, simCard.getNumbers());
        preparedStatement.setString(4, simCard.getSimCardOperator().name());
        preparedStatement.setDate(5, Date.valueOf(simCard.getRegisterDate()));
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
        preparedStatement.setDate(5, Date.valueOf(simCard.getRegisterDate()));
        preparedStatement.setBoolean(6, simCard.isStatus());
        preparedStatement.setInt(7, simCard.getId());
        preparedStatement.execute();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from sim_cards where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<SimCard> findAll() throws Exception {
        List<SimCard> simCardList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from sim_cards");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            SimCard simCard = simCardMapper.SimCardMapper(resultSet);
            simCardList.add(simCard);
        }
        return simCardList;
    }


    @Override
    public SimCard findById(Integer id) throws Exception {
        SimCard simCard = null;
        preparedStatement = connection.prepareStatement("select * from sim_cards where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            simCard = simCardMapper.SimCardMapper(resultSet);
        }
        return simCard;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
