package mftplus.model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.EmployeeType;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Salaries {
    private int id;
    private int personId;
    private int weeklyHour;
    private int payPerHour;
    private LocalDate startDate;
    private LocalDate endDate;
    private EmployeeType employeeType;


    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
