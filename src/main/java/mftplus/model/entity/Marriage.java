package mftplus.model.entity;
import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Marriage {
    private int marriageId;
    private int personId;
    private String name;
    private String family;
    private LocalDate marriageDate;
    private boolean isAlive;
    private int children;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
