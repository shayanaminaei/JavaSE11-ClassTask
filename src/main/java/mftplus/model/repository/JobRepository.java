package mftplus.model.repository;

import mftplus.model.entity.Job;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.JobMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRepository implements Repository<Job, Integer>, AutoCloseable{
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final JobMapper jobMapper = new JobMapper();

    public JobRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Job job) throws Exception {
        job.setId(ConnectionProvider.getProvider().getNextId("job_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into jobs (id, person_id, organisation, title, start_date, end_date, description ) " +
                        "values (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, job.getId());
        preparedStatement.setInt(2, job.getPerson().getId());
        preparedStatement.setString(3, job.getOrganisation());
        preparedStatement.setString(4, job.getJobTitle().name());
        preparedStatement.setDate(5,Date.valueOf(job.getStartDate()));
        preparedStatement.setDate(6,Date.valueOf(job.getEndDate()));
        preparedStatement.setString(7, job.getDescription());
        preparedStatement.execute();
    }

    @Override
    public void edit(Job job) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update jobs set person_id=? ,organisation=?,title=?,start_date=?,end_date=?,description=? where id=?"
        );
        preparedStatement.setInt(1, job.getPerson().getId());
        preparedStatement.setString(2, job.getOrganisation());
        preparedStatement.setString(3, job.getJobTitle().name());
        preparedStatement.setDate(4,Date.valueOf(job.getStartDate()));
        preparedStatement.setDate(5,Date.valueOf(job.getEndDate()));
        preparedStatement.setString(6, job.getDescription());
        preparedStatement.setInt(7, job.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from jobs where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Job> findAll() throws Exception {
        List<Job> jobList = new ArrayList<>();
        preparedStatement  = connection.prepareStatement(
                "select * from jobs order by organisation"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            jobList.add(jobMapper.jobMapper(resultSet));
        }
        return jobList;
    }

    @Override
    public Job findById(Integer id) throws Exception {
        Job job = null;
        preparedStatement = connection.prepareStatement(
                "select * from jobs where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            job = jobMapper.jobMapper(resultSet);
        }
        return job;
    }

    public List<Job> findByPersonId(int personId) throws Exception {
        List<Job> jobList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM JOBS WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            jobList.add(jobMapper.jobMapper(resultSet));
        }
        return jobList;
    }

    public List<Job> findByOrganisation(String organisation) throws Exception {
        List<Job> jobList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from jobs where organisation like ?"
        );
        preparedStatement.setString(1, "%" + organisation + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            jobList.add(jobMapper.jobMapper(resultSet));
        }
        return jobList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
