package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.SimCardOperator;
import mftplus.model.entity.enums.Title;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class SimCard {
    private int id;
    private int personId;
    private Title title;
    private String numbers;
    private SimCardOperator simCardOperator;
    private LocalDate registerDate;
    private boolean status;


    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
