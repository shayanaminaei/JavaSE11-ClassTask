package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
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
            log.info("SimCard Deleted successfully");
        } catch (Exception e) {
            log.info("SimCard Delete failed" + e.getMessage());
        }

    }


    public List<SimCard> findAll() throws Exception {
        try {
            List<SimCard> simCardList = SimCardService.getService().findAll();
            log.info("SimCard findAll successfully");
            return simCardList;
        } catch (Exception e) {
            log.info("SimCard findAll failed" + e.getMessage());
            return null;
        }
    }


    public SimCard findById(Integer id) throws Exception {
        try {
            SimCard simCard = SimCardService.getService().findById(id);
            log.info("SimCard findById" + id + "successfully");
            return simCard;
        } catch (Exception e) {
            log.info("SimCard findById" + id + "failed" + e.getMessage());
            return null;
        }


    }
}
