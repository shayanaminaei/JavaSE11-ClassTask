package mftplus.model.repository;

import mftplus.model.entity.Education;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.EducationMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationRepository implements Repository<Education, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final EducationMapper educationMapper = new EducationMapper();

    public EducationRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Education education) throws Exception {
        education.setId(getNextId());

        preparedStatement = connection.prepareStatement(
                "INSERT INTO EDUCATIONS (ID, PERSON_ID, UNIVERSITY, EDUCATION_GRADE, AVERAGE, START_DATE, END_DATE)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, education.getId());
        preparedStatement.setInt(2, education.getPerson().getId());
        preparedStatement.setString(3, education.getUniversity());
        preparedStatement.setString(4, education.getEducationGrade().name());
        preparedStatement.setDouble(5, education.getAverage());
        preparedStatement.setDate(6, Date.valueOf(education.getStartDate()));
        preparedStatement.setDate(7, Date.valueOf(education.getEndDate()));
        preparedStatement.execute();
    }

    @Override
    public void edit(Education education) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE EDUCATIONS SET UNIVERSITY=?, EDUCATION_GRADE=?, AVERAGE=?, START_DATE=?, END_DATE=? WHERE ID=?"
        );
        preparedStatement.setString(1, education.getUniversity());
        preparedStatement.setString(2, education.getEducationGrade().name());
        preparedStatement.setDouble(3, education.getAverage());
        preparedStatement.setDate(4, Date.valueOf(education.getStartDate()));
        preparedStatement.setDate(5, Date.valueOf(education.getEndDate()));
        preparedStatement.setInt(6, education.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM EDUCATIONS WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Education> findAll() throws Exception {
        List<Education> educationList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS ORDER BY UNIVERSITY, EDUCATION_GRADE"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Education education = educationMapper.educationMapper(resultSet);
            educationList.add(education);
        }
        return educationList;
    }

    @Override
    public Education findById(Integer id) throws Exception {
        Education education = null;

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            education = educationMapper.educationMapper(resultSet);
        }
        return education;
    }

    public List<Education> findByPersonId(int personId) throws Exception {
        List<Education> educationList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Education education = educationMapper.educationMapper(resultSet);
            educationList.add(education);
        }
        return educationList;
    }


    public List<Education> findByUniversityAndGrade(String university, String grade) throws Exception {
        List<Education> educationList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS WHERE UNIVERSITY LIKE ? AND EDUCATION_GRADE LIKE ?"
        );
        preparedStatement.setString(1, university + "%");
        preparedStatement.setString(2, grade + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Education education = educationMapper.educationMapper(resultSet);
            educationList.add(education);
        }
        return educationList;
    }

    public int getNextId() throws Exception {
        ResultSet resultSet = connection.prepareStatement("SELECT EDUCATION_SEQ.nextval AS NEXTID FROM DUAL").executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXTID");
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}

