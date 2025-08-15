package mftplus.model.repository;

import mftplus.model.entity.Person;
import mftplus.model.entity.Skill;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.PersonMapper;
import mftplus.model.tools.SkillMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillRepository implements Repository<Skill, Integer>, AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private SkillMapper skillMapper = new SkillMapper();

    public SkillRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Skill skill) throws Exception {
        preparedStatement = connection.prepareStatement(
                "insert into skills (id,person_id,title,institute,duration,register_date,score) values (skill_seq.nextval,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, skill.getPersonId());
        preparedStatement.setString(2, skill.getTitle());
        preparedStatement.setString(3, skill.getInstitute());
        preparedStatement.setInt(4, skill.getDuration());
        preparedStatement.setDate(5, Date.valueOf(skill.getRegisterDate()));
        preparedStatement.setInt(6, skill.getScore());
        preparedStatement.execute();
}

    @Override
    public void edit(Skill skill) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update skills set id=?,person_id=?,title=?,institute=?,duration=?,register_date=?,score=? where id=?"
        );
        preparedStatement.setInt(1, skill.getPersonId());
        preparedStatement.setString(2, skill.getTitle());
        preparedStatement.setString(3, skill.getInstitute());
        preparedStatement.setInt(4, skill.getDuration());
        preparedStatement.setDate(5, Date.valueOf(skill.getRegisterDate()));
        preparedStatement.setInt(6, skill.getScore());
        preparedStatement.setInt(7, skill.getId());
        PreparedStatement.execute();
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
        preparedStatement = connection.prepareStatement("select * from skills order by id,person_id");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            Skill skill = skillMapper.skillMapper(resultSet);
            skillList.add(skill);
        }
        return skillList;
    }

    @Override
    public Skill findById(Integer id) throws Exception {
        Person person= null;

        preparedStatement = connection.prepareStatement("select * from skills where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            skill= skillMapper.skillMapper(resultSet);
        }
        return skill;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
