

import mftplus.model.entity.Marriage;
import mftplus.model.repository.MarriageRepository;


import java.sql.SQLException;
import java.time.LocalDate;

public class MarriageTest {
    public static void main(String[] args) throws SQLException {

        MarriageRepository repo = new MarriageRepository();


        Marriage marriage = Marriage.builder()
                .personId(1)
                .name("Sara")
                .family("Ahmadi")
                .marriageDate(LocalDate.of(2020, 5, 15))
                .isAlive(true)
                .children(2)
                .build();

//        repo.save(marriage);
        System.out.println("Marriage saved: " + marriage.getMarriageId());
    }
}

