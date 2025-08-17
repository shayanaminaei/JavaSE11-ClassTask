package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.DriverLicenseType;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class DriverLicenses {
    private int id;
    private String personId;
    private String serial;
    private DriverLicenseType driverLicenseType;
    private String city;
    private LocalDate registerDate;
    private LocalDate expireDate;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
