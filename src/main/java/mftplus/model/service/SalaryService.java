package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Salary;
import mftplus.model.repository.SalaryRepository;

import java.util.List;

public class SalaryService implements Service<Salary, Integer> {
    @Getter
    private static SalaryService service = new SalaryService();

    private SalaryService() {
    }

    @Override
    public void save(Salary salary) throws Exception {
        try (SalaryRepository salaryRepository = new SalaryRepository()) {
            if (salary.getPayPerHour() > 0) {
                salaryRepository.save(salary);
            } else {
                throw new Exception("Pay per hour must be positive!");
            }
        }
    }

    @Override
    public void edit(Salary salary) throws Exception {
        try (SalaryRepository salaryRepository = new SalaryRepository()) {
            salaryRepository.edit(salary);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (SalaryRepository salaryRepository = new SalaryRepository()) {
            salaryRepository.delete(id);
        }
    }

    @Override
    public List<Salary> findAll() throws Exception {
        try (SalaryRepository salaryRepository = new SalaryRepository()) {
            return salaryRepository.findAll();
        }
    }

    @Override
    public Salary findById(Integer id) throws Exception {
        try (SalaryRepository salaryRepository = new SalaryRepository()) {
            return salaryRepository.findById(id);
        }
    }


}