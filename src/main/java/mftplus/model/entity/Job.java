package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.JobTitle;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Job {
    private int id;
    private String organisation;
    private JobTitle jobTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
