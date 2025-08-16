import mftplus.model.entity.SimCard;
import mftplus.model.entity.enums.SimCardOperator;
import mftplus.model.entity.enums.Title;
import mftplus.model.repository.SimCardsRepository;
import mftplus.model.service.Service;

import java.time.LocalDate;

public class SimCardTest {
    public static void main(String[] args) throws Exception {
        SimCard simCard =
                SimCard
                        .builder()

                        .personId(1)
                        .title(Title.Work)
                        .numbers("09029999999999")
                        .simCardOperator(SimCardOperator.Irancell)
                        .registerDate(LocalDate.of(1404, 10, 1))
                        .status(true)
                        .build();

//        Repository Test Passed

//        try (SimCardsRepository simCardsRepository = new SimCardsRepository()) {

//        test passed
//            simCardsRepository.save(simCard);

//        test passed
//            simCardsRepository.edit(simCard);

//        test passed
//            simCardsRepository.delete(5);

//        test passed
//            System.out.println(simCardsRepository.findAll());

//        test passed
//            System.out.println(simCardsRepository.findById(6));
//        }


//        Service test pass

//        test passed
//        SimCardService.getService().save(simCard);
//
//        test passed
//        SimCardService.getService().edit(simCard);
//
//        test passed
//        SimCardService.getService().delete(12);
//
//        test passe
//        System.out.println(SimCardService.getService().findAll());
//
//        test passe
//        System.out.println(SimCardService.getService().findById(2));

    }
}
