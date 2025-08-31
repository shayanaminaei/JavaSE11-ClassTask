package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.Role;
import mftplus.model.service.CarService;
import mftplus.model.service.EducationService;
import mftplus.model.service.MarriageService;
import mftplus.model.service.SalaryService;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Person {
    private int id;
    private String name;
    private String family;
    private LocalDate birthDate;
    private Role role;
    private boolean status;

    public List<Education> getEducationList() throws Exception {
        return EducationService.getService().findByPersonId(id);
    }

    public List<Car> getCarList() throws Exception {
        return CarService.getService().findByPersonId(id);
    }

    public List<Marriage> getMarriageList() throws Exception {
        return MarriageService.getService().findByPersonId(id);
    }
    public List<Salary> getSalaryList() throws Exception {
        return SalaryService.getService().findByPersonId(id);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
