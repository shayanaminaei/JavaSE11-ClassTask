package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Person;
import mftplus.model.entity.SimCard;
import mftplus.model.entity.enums.SimCardOperator;
import mftplus.model.entity.enums.Title;
import mftplus.model.service.SimCardService;

import java.time.LocalDate;
import java.util.List;

@Log4j
public class SimCardController {
    @Getter
    private static SimCardController controller = new SimCardController();

    private SimCardController() {
    }

    public void save(int personId, Title title, String number, SimCardOperator operator, LocalDate registerDate, boolean status) throws Exception {
        try {
            SimCard simCard =
                    SimCard
                            .builder()
                            .personId(personId)
                            .title(title)
                            .numbers(number)
                            .simCardOperator(operator)
                            .registerDate(registerDate)
                            .status(status)
                            .build();
            SimCardService.getService().save(simCard);
            log.info("SimCard saved successfully");
        } catch (Exception e) {
            log.info("SimCard save failed" + e.getMessage());
        }

    }


    public void edit(int id, int personId, Title title, String number, SimCardOperator operator, LocalDate registerDate, boolean status) throws Exception {
        try {
            SimCard simCard =
                    SimCard
                            .builder()
                            .id(id)
                            .personId(personId)
                            .title(title)
                            .numbers(number)
                            .simCardOperator(operator)
                            .registerDate(registerDate)
                            .status(status)
                            .build();
            SimCardService.getService().edit(simCard);
            log.info("SimCard edited successfully");
        } catch (Exception e) {
            log.info("SimCard edit failed" + e.getMessage());
        }

    }


    public void delete(Integer id) throws Exception {
        try {
            SimCardService.getService().delete(id);
            log.info("SimCard deleted successfully");
        } catch (Exception e) {
            log.info("SimCard delete failed" + e.getMessage());
        }

    }


    public List<SimCard> findAll() throws Exception {
        try {
            return SimCardService.getService().findAll();
        } catch (Exception e) {
            log.info("SimCard findAll failed" + e.getMessage());
            return null;
        }
    }


    public SimCard findById(Integer id) throws Exception {
        try {
            return SimCardService.getService().findById(id);
        } catch (Exception e) {
            log.info("SimCard findById failed" + e.getMessage());
            return null;
        }


    }
}
