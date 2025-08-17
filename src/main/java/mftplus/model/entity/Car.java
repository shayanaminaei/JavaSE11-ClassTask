package mftplus.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString


public class Car {
    private int id;
    private int personId;
    private String name;
    private String model;
    private String brand;
    private LocalDate manDate;
    private String color;
    private String plate;
}
