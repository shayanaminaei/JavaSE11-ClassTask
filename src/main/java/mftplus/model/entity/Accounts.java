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
public class Accounts {
    private int id;
    private int personId;
    private String branch;
    private String accountId;
    private String cardNumber;
    private LocalDate registerDate;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}