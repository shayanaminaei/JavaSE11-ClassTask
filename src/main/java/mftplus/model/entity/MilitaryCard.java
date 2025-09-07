package mftplus.model.entity;



import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.LicenseType;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class MilitaryCard {
    private int id;
    private String personId;
    private String cardSerial;
    private LicenseType licenseType;
    private String city;
    private String organisation;
    private boolean duration;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
