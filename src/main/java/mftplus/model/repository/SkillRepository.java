package mftplus.model.repository;


import mftplus.model.entity.Skill;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.SkillMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository implements Repository<Skill, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final SkillMapper skillMapper = new SkillMapper();

    public SkillRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Skill skill) throws Exception {
        skill.setId(ConnectionProvider.getProvider().getNextId("skill_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into skills (id,personId,title,institute,duration,registerDate,score)"+" values (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, skill.getId());
        preparedStatement.setInt(2, skill.getPerson().getId());
        preparedStatement.setString(3, skill.getTitle());
        preparedStatement.setString(4, skill.getInstitute());
        preparedStatement.setInt(5, skill.getDuration());
        preparedStatement.setDate(6, Date.valueOf(skill.getRegisterDate()));
        preparedStatement.setInt(7, skill.getScore());
        preparedStatement.execute();
}

    @Override
    public void edit(Skill skill) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update skills set title=?,institute=?,duration=?,register_date=?,score=? where id=?"
        );
        preparedStatement.setString(1, skill.getTitle());
        preparedStatement.setString(2, skill.getInstitute());
        preparedStatement.setInt(3, skill.getDuration());
        preparedStatement.setDate(4, Date.valueOf(skill.getRegisterDate()));
        preparedStatement.setInt(5, skill.getScore());
        preparedStatement.setInt(6, skill.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from skills where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Skill> findAll() throws Exception {
        List<Skill> skillList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from skills order by title,institute");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Skill skill = skillMapper.skillMapper(resultSet);
            skillList.add(skill);
        }
        return skillList;
    }

    @Override
    public Skill findById(Integer id) throws Exception {
        Skill  skill= null;

        preparedStatement = connection.prepareStatement("select * from skills where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            skill= skillMapper.skillMapper(resultSet);
        }
        return skill;
    }

    public List<Skill> findSkillByTitle(String title) throws Exception {
        List<Skill> skillList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from skills where title like ?");
        preparedStatement.setString(1, title + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Skill skill = skillMapper.skillMapper(resultSet);
            skillList.add(skill);
        }
        return skillList;
    }
    public List<Skill> findByPersonId(int personId) throws Exception {
        List<Skill> skillList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM SKILLS WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Skill skill = skillMapper.skillMapper(resultSet);
            skillList.add(skill);
        }
        return skillList;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
