package mftplus.model.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class Cars{
    private int id;
    private int personId;
    private String carName;
    private String carModel;
    private String brand;
    private LocalDate manDate;
    private String color;
    private String plate;

}
