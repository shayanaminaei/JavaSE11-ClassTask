import mftplus.model.entity.Salaries;
import mftplus.model.entity.enums.Employee_Type;
import mftplus.model.service.SalariesService;

import java.time.LocalDate;
import java.util.List;

public class SalariesTest {
    public static void main(String[] args) throws Exception {
        Salaries salaries = Salaries.builder()
                .id(1)
                .personId(1)
                .weeklyHour(40)
                .payPerHour(200)
                .startDate(LocalDate.of(2025, 8, 1))
                .endDate(LocalDate.of(2025, 8, 2))
                .employeeType(Employee_Type.FullTime)
                .build();

        SalariesService.getService().save(salaries);
        System.out.println("Salary saved successfully!");

//        salary.setPayPerHour(250);
//        SalariesService.getService().edit(salaries);
//        System.out.println("Salary edited successfully!");


//        List<Salaries> allSalaries = SalariesService.getService().findAll();
//        System.out.println("All Salaries:");
//        allSalaries.forEach(System.out::println);


//        Salaries foundById = SalariesService.getService().findById(1);
//        System.out.println("Found by ID: " + foundById);


//        SalariesService.getService().delete(1);
//        System.out.println("Salary deleted successfully!");
    }
}