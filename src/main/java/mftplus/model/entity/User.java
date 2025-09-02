package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

public class User {
    private int id;
    private Person person;
    private String username;
    private String password;
    private String nickname;
    private Boolean locked;
    private LocalDate registerDate;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
