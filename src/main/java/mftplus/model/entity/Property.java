package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Property {
    private int id;
    private int personId;
    private String name;
    private String brand;
    private String serial;
    private int count;
    private Timestamp dateTime;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
