package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Salary;
import mftplus.model.entity.enums.EmployeeType;
import mftplus.model.service.SalaryService;

import java.time.LocalDate;
import java.util.List;

@Log4j
public class SalaryController {
    @Getter
    private static SalaryController controller = new SalaryController();

    private SalaryController() {
    }

    public void save(int personId, int weeklyHour, int payPerHour, LocalDate startDate, LocalDate endDate, EmployeeType employeeType) throws Exception {
        try {
            Salary salary = Salary.builder()
                    .personId(personId)
                    .weeklyHour(weeklyHour)
                    .payPerHour(payPerHour)
                    .startDate(startDate)
                    .endDate(endDate)
                    .employeeType(employeeType)
                    .build();
            SalaryService.getService().save(salary);
            log.info("Salary Saved Successfully");
        } catch (Exception e) {
            log.error("Salary Save Failed " + e.getMessage());
            throw e;
        }
    }

    public void edit(int id, int personId, int weeklyHour, int payPerHour, LocalDate startDate, LocalDate endDate, EmployeeType employeeType) throws Exception {
        try {
            Salary salary = Salary.builder()
                    .id(id)
                    .personId(personId)
                    .weeklyHour(weeklyHour)
                    .payPerHour(payPerHour)
                    .startDate(startDate)
                    .endDate(endDate)
                    .employeeType(employeeType)
                    .build();
            SalaryService.getService().edit(salary);
            System.out.println("Info: Salary Edited Successfully");
        } catch (Exception e) {
            System.out.println("Error: Salary Edit Failed " + e.getMessage());
            throw e;
        }
    }

    public void delete(int id) throws Exception {
        try {
            SalaryService.getService().delete(id);
            System.out.println("Info: Salary Deleted Successfully");
        } catch (Exception e) {
            System.out.println("Error: Salary Delete Failed " + e.getMessage());
            throw e;
        }
    }

    public List<Salary> findAll() throws Exception {
        try {
            return SalaryService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error: Salary FindAll Failed " + e.getMessage());
            return null;
        }
    }

    public Salary findById(int id) throws Exception {
        try {
            return SalaryService.getService().findById(id);
        } catch (Exception e) {
            System.out.println("Error: Salary FindId " + id + " Failed " + e.getMessage());
            return null;
        }
    }

}