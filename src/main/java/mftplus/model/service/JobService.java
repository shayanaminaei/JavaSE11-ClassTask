package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Job;
import mftplus.model.repository.JobRepository;

import java.util.List;


public class JobService implements Service<Job, Integer> {
    @Getter
    private static JobService service = new JobService();

    private JobService() {
    }

    @Override
    public void save(Job job) throws Exception {
        try (JobRepository jobRepository = new JobRepository()) {
            jobRepository.save(job);
        }
    }

    @Override
    public void edit(Job job) throws Exception {
        try (JobRepository jobRepository = new JobRepository()) {
            jobRepository.edit(job);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (JobRepository jobRepository = new JobRepository()) {
            jobRepository.delete(id);
        }
    }

    @Override
    public List<Job> findAll() throws Exception {
        try (JobRepository jobRepository = new JobRepository()) {
            return jobRepository.findAll();
        }
    }

    @Override
    public Job findById(Integer id) throws Exception {
        try (JobRepository jobRepository = new JobRepository()) {
            return jobRepository.findById(id);
        }
    }

    public List<Job> findByOrganisation(String organisation) throws Exception {
        try (JobRepository jobRepository = new JobRepository()) {
            jobRepository.findByOrganisation(organisation);
        }
        return null;
    }
}
