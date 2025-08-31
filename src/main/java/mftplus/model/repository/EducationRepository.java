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
        connection = ConnectionProvider.getProvider().getH2Connection();
    }

    @Override
    public void save(Education education) throws Exception {
        education.setId(ConnectionProvider.getProvider().getNextId("education_seq"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO EDUCATIONS (ID, PERSON_ID, UNIVERSITY, EDUCATION_GRADE, AVERAGE, START_DATE, END_DATE)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?)"
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
                "UPDATE EDUCATIONS SET PERSON_ID=?, UNIVERSITY=?, EDUCATION_GRADE=?, AVERAGE=?, START_DATE=?, END_DATE=? WHERE ID=?"
        );
        preparedStatement.setInt(1, education.getPerson().getId());
        preparedStatement.setString(2, education.getUniversity());
        preparedStatement.setString(3, education.getEducationGrade().name());
        preparedStatement.setDouble(4, education.getAverage());
        preparedStatement.setDate(5, Date.valueOf(education.getStartDate()));
        preparedStatement.setDate(6, Date.valueOf(education.getEndDate()));
        preparedStatement.setInt(7, education.getId());
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
            educationList.add(educationMapper.educationMapper(resultSet));
        }
        return educationList;
    }

    @Override
    public Education findById(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return educationMapper.educationMapper(resultSet);
        }
        return null;
    }

    public List<Education> findByPersonId(Integer personId) throws Exception {
        List<Education> educationList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            educationList.add(educationMapper.educationMapper(resultSet));
        }
        return educationList;
    }

    public List<Education> findByUniversityAndEducationGrade(String university, String grade) throws Exception {
        List<Education> educationList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS WHERE UNIVERSITY LIKE ? AND EDUCATION_GRADE LIKE ?"
        );
        preparedStatement.setString(1, university + "%");
        preparedStatement.setString(2, grade + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            educationList.add(educationMapper.educationMapper(resultSet));
        }
        return educationList;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
