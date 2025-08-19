

import mftplus.model.entity.Marriage;
import mftplus.model.repository.MarriageRepository;


import java.sql.SQLException;
import java.time.LocalDate;

import lombok.extern.log4j.Log4j;

@Log4j
public class MarriageTest {
    public static void main(String[] args) throws SQLException {

        MarriageRepository repo = new MarriageRepository();

    public MarriageTest() throws SQLException {
    }


        Marriage marriage = Marriage.builder()
                .personId(1)
                .name("Sara")
                .family("Ahmadi")
                .marriageDate(LocalDate.of(2020, 5, 15))
                .isAlive(true)
                .children(2)
                .build();


        log.info("Marriage saved: " + marriage.getMarriageId());
    }
}

