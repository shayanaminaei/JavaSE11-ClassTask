//package mftplus.model.repository;
//
//import mftplus.model.entity.Education;
//import mftplus.model.entity.enums.EducationGrade;
//import mftplus.model.tools.ConnectionProvider;
////import mftplus.model.tools.EducationMapper;
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class EducationRepository implements Repository<Education, Integer>, AutoCloseable{
//    private Connection connection;
//    private PreparedStatement preparedStatement;
////    private EducationMapper educationMapper = new EducationMapper();
//
//    public EducationRepository() throws SQLException {
//        connection = ConnectionProvider.getProvider().getConnection();
//    }
//
//    @Override
//    public void save(Education education) throws Exception {
//        preparedStatement = connection.prepareStatement(
//                "insert into educations (id, person_id, university, education_grade, average, start_date, end_date) values (education_seq.nextval, ?, ?, ?, ?, ?, ?)"
//        );
//        preparedStatement.setInt(1, education.getPersonId());
//        preparedStatement.setString(2, education.getUniversity());
//        preparedStatement.setString(3, education.getEducationGrade().name());
//        preparedStatement.setDouble(4, education.getAverage());
//        preparedStatement.setDate(5, Date.valueOf(education.getStartDate()));
//        preparedStatement.setDate(6, Date.valueOf(education.getEndDate()));
//        preparedStatement.execute();
//    }
//
//    @Override
//    public void edit(Education education) throws Exception {
//        preparedStatement = connection.prepareStatement(
//                "update educations set university=?, education_grade=?, average=?, start_date=?, end_date=? where id=?"
//        );
//        preparedStatement.setString(1, education.getUniversity());
//        preparedStatement.setString(2, education.getEducationGrade().name());
//        preparedStatement.setDouble(3, education.getAverage());
//        preparedStatement.setDate(4, Date.valueOf(education.getStartDate()));
//        preparedStatement.setDate(5, Date.valueOf(education.getEndDate()));
//        preparedStatement.setInt(6, education.getId());
//        preparedStatement.execute();
//    }
//
//    @Override
//    public void delete(Integer id) throws Exception {
//        preparedStatement = connection.prepareStatement(
//                "delete from educations where id=?"
//        );
//        preparedStatement.setInt(1, id);
//        preparedStatement.execute();
//    }
//
//    @Override
//    public List<Education> findAll() throws Exception {
//        List<Education> educationList = new ArrayList<>();
//
//        preparedStatement = connection.prepareStatement(
//                "select * from educations order by university, education_grade"
//        );
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()) {
//            Education education = educationMapper.educationMapper(resultSet);
//            educationList.add(education);
//        }
//        return educationList;
//    }
//
//    @Override
//    public Education findById(Integer id) throws Exception {
//        Education education = null;
//
//        preparedStatement = connection.prepareStatement(
//                "select * from educations where id=?"
//        );
//        preparedStatement.setInt(1, id);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if (resultSet.next()) {
//            education = educationMapper.educationMapper(resultSet);
//        }
//        return education;
//    }
//
//    public List<Education> findByUniversityAndGrade(String university, String grade) throws Exception {
//        List<Education> educationList = new ArrayList<>();
//
//        preparedStatement = connection.prepareStatement(
//                "select * from educations where university=? and education_grade=?"
//        );
//        preparedStatement.setString(1, university+"%");
//        preparedStatement.setString(2, grade+"%");
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()) {
////            Education education = educationMapper.educationMapper(resultSet);
//            educationList.add(education);
//        }
//        return educationList;
//    }
//
//    @Override
//    public void close() throws Exception {
//        preparedStatement.close();
//        connection.close();
//    }
//
//}
//
