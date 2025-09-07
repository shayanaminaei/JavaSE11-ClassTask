package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Salary;
import mftplus.model.entity.enums.EmployeeType;
import mftplus.model.repository.SalaryRepository;

import java.util.List;

public class SalaryService implements Service<Salary, Integer> {
    @Getter
    private static SalaryService service = new SalaryService();

    private SalaryService() {
    }

    @Override
    public void save(Salary salary) throws Exception {
        if (salary.getPayPerHour() <= 0) {
            throw new Exception("Pay per hour must be positive!");
        }
        if (salary.getEndDate().isBefore(salary.getStartDate())) {
            throw new Exception("End date must be after start date!");
        }
        try (SalaryRepository salaryRepository = new SalaryRepository()) {
            salaryRepository.save(salary);
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

    public List<Salary> findByPersonId(Integer personId) throws Exception {
        try (SalaryRepository salaryRepository = new SalaryRepository()) {
            return salaryRepository.findByPersonId(personId);
        }
    }

    public List<Salary> findByEmployeeType(EmployeeType employeeType) throws Exception {
        try (SalaryRepository salaryRepository = new SalaryRepository()) {
            return salaryRepository.findByEmployeeType(employeeType);
        }
    }
}