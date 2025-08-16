package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Salaries;
import mftplus.model.repository.SalariesRepository;

import java.sql.SQLException;
import java.util.List;

public class SalariesService implements Service<Salaries, Integer> {
    @Getter
    private static SalariesService service = new SalariesService();
    private SalariesRepository repository;

    private SalariesService() {
        try {
            repository = new SalariesRepository();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize SalariesRepository: " + e.getMessage());
        }
    }

    @Override
    public void save(Salaries salaries) throws Exception {
        repository.save(salaries);
    }

    @Override
    public void edit(Salaries salaries) throws Exception {
        repository.edit(salaries);
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(id);
    }

    @Override
    public List<Salaries> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Salaries findById(Integer id) throws Exception {
        return repository.findById(id);
    }
}