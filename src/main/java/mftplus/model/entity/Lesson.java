package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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
    private LocalDate startDataTime;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
