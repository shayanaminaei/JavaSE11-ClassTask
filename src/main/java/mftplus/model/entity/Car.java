package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.CarModel;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter



public class Car {
    private int id;
    private int personId;
    private String name;
    private String model;
    private CarModel carModel;
    private LocalDate manDate;
    private String color;
    private String plate;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
