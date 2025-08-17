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


public class Medical {
    private int id;
    private String personId;
    private String disease;
    private String medicine;
    private String doctor;
    private LocalDate visitDate;
    private boolean status;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

