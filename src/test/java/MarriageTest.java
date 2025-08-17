

import mftplus.model.entity.Marriage;
import mftplus.model.repository.MarriageRepository;


import java.time.LocalDate;

public class MarriageTest {

    private final MarriageRepository repo = new MarriageRepository();


    public void testSaveMarriage() {
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