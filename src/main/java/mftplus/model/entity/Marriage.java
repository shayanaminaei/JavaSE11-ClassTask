package mftplus.model.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

public class Marriage {
    private int marriageId;
    private int personId;
    private String name;  //husband or wife's name
    private String family; //husband or wife's family
    private LocalDate marriageDate;
    private boolean isAlive;
    private int children;
}
