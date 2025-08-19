package mftplus.model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.EmployeeType;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter


public class Salary {
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
