package mftplus.model.repository;

import mftplus.model.entity.Job;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.JobMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRepository implements Repository<Job, Integer>, AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private JobMapper jobMapper = new JobMapper();

    public JobRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Job job) throws Exception {
        preparedStatement = connection.prepareStatement(
                "insert into jobs (id, organisation, title, start_date, end_date, description ) values (person_seq.nextval,?,?,?,?,?)"
        );
        preparedStatement.setString(1, job.getOrganisation());
        preparedStatement.setString(2, job.getTitle().name());
        preparedStatement.setDate(3,Date.valueOf(job.getStartDate()));
        preparedStatement.setDate(4,Date.valueOf(job.getEndDate()));
        preparedStatement.setString(5, job.getDescription());
        preparedStatement.execute();
    }

    @Override
    public void edit(Job job) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update jobs set orgnisation=?,jobTitle=?,startDate=?,endDate=?,description=? where id=?"
        );
        preparedStatement.setString(1, job.getOrganisation());
        preparedStatement.setString(2, job.getTitle().name());
        preparedStatement.setDate(3,Date.valueOf(job.getStartDate()));
        preparedStatement.setDate(4,Date.valueOf(job.getEndDate()));
        preparedStatement.setString(5, job.getDescription());
        preparedStatement.setInt(6, job.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from job where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Job> findAll() throws Exception {
        List<Job> jobList = new ArrayList<>();
        preparedStatement  = connection.prepareStatement("select * from job order by organisation");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Job job = jobMapper.jobMapper(resultSet);
            jobList.add(job);
        }
        return jobList;
    }

    @Override
    public Job findById(Integer id) throws Exception {
        Job job = null;
        preparedStatement = connection.prepareStatement("select * from job where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            job = jobMapper.jobMapper(resultSet);
        }
        return job;
    }

    public List<Job> findByOrganisation(String organisation) throws Exception {
        List<Job> jobList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from job where organisation like ?");
        preparedStatement.setString(1, "%" + organisation + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Job job = jobMapper.jobMapper(resultSet);
            jobList.add(job);
        }
        return jobList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
