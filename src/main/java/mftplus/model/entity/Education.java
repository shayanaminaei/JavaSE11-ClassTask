package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.EducationGrade;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter


public class Education {
    private int id;
    private int personId;
    private String university;
    private EducationGrade educationGrade;
    private double average;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
