package mftplus.model.repository;

import mftplus.model.entity.Lesson;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.LessonMapper;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonRepository implements Repository<Lesson, Integer> , AutoCloseable{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final LessonMapper lessonMapper = new LessonMapper();

    public LessonRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }



    @Override
    public void save(Lesson lesson) throws Exception {
        lesson.setId(ConnectionProvider.getProvider().getNextId("lesson_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into lessons (id, person_id, title, code, teacher, unit, start_date_time) values (?, ?, ?, ?, ?, ?, ?)"
        );

        preparedStatement.setInt(1, lesson.getId());
        preparedStatement.setInt(2, lesson.getPersonId());
        preparedStatement.setString(3, lesson.getTitle());
        preparedStatement.setInt(4, lesson.getCode());
        preparedStatement.setString(5, lesson.getTeacher());
        preparedStatement.setString(6, lesson.getUnit());
        preparedStatement.setDate(7,Date.valueOf(lesson.getStartDateTime()));
        preparedStatement.execute();

    }


    @Override
    public void edit(Lesson lesson) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update lessons set id=?,person_id=?,title=?,code=?,teacher=?,unit=?,start_date_time=? where id=?"
        );

        preparedStatement.setInt(1, lesson.getPersonId());
        preparedStatement.setString(2, lesson.getTitle());
        preparedStatement.setInt(3, lesson.getCode());
        preparedStatement.setString(4, lesson.getTeacher());
        preparedStatement.setString(5, lesson.getUnit());
        preparedStatement.setDate(6, Date.valueOf(lesson.getStartDateTime()));
        preparedStatement.execute();
    }



    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from lessons where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }



    @Override
    public List<Lesson> findAll() throws Exception {
        List<Lesson> lessonList= new ArrayList<>();


        preparedStatement = connection.prepareStatement("select * from lessons order by id, person_id");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            Lesson lesson =lessonMapper.lessonMapper(resultSet);
            lessonList.add(lesson);
        }
        return lessonList;
    }

    @Override
    public Lesson findById(Integer id) throws Exception {
        Lesson lesson= null;

        preparedStatement = connection.prepareStatement("select * from lessons where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            lesson=lessonMapper.lessonMapper(resultSet);
        }
        return lesson;
    }


    public List<Lesson> findByTeacherAndUnit(String teacher, String unit) throws Exception {
        List<Lesson> lessonList= new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from lessons where teacher=? and unit=?");
        preparedStatement.setString(1, teacher);
        preparedStatement.setString(2, unit);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Lesson lesson =lessonMapper.lessonMapper(resultSet);
            lessonList.add(lesson);
        }
        return lessonList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }


}
