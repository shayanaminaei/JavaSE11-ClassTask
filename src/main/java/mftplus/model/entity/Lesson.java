package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Lesson {
    private int id;
    private int personId;
    private String title;
    private  int code;
    private  String teacher;
    private  String unit;
    private LocalDate startDateTime;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
