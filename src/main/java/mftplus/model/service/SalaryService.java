package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Salary;
import mftplus.model.repository.SalaryRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SalaryService implements Service<Salary, Integer> {
    @Getter
    private static SalaryService service = new SalaryService();


    @Override
    public void save(Salary salary) throws Exception {
        try(SalaryRepository salaryRepository = new SalaryRepository()) {
            salaryRepository.save(salary);
        }
    }

    @Override
    public void edit(Salary salary) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Salary> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Salary findById(Integer id) throws Exception {
        return null;
    }


}