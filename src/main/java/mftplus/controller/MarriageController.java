package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Marriage;
import mftplus.model.service.MarriageService;

import java.time.LocalDate;
import java.util.List;

@Log4j
public class MarriageController {
    @Getter
    private static MarriageController controller = new MarriageController();


    private MarriageController() {

    }

    public void save(int personId, int marriageId, String name, String family, LocalDate marriageDate, boolean aliveStatus, int children) throws Exception {
        try {
            Marriage marriage =
                    Marriage
                            .builder()
                            .personId(personId)
                            .marriageId(marriageId)
                            .name(name)
                            .family(family)
                            .marriageDate(marriageDate)
                            .isAlive(aliveStatus)
                            .children(children)
                            .build();
            MarriageService.getMarriageService().save(marriage);
            log.info("Marriage saved successfully");

        } catch (Exception e) {
            log.error("Marriage saving has failed" + e.getMessage());
        }
    }

    public void edit(int personId, int marriageId, String name, String family, LocalDate marriageDate, boolean aliveStatus, int children) throws Exception {
        try {
            Marriage marriage =
                    Marriage
                            .builder()
                            .personId(personId)
                            .marriageId(marriageId)
                            .name(name)
                            .family(family)
                            .marriageDate(marriageDate)
                            .isAlive(aliveStatus)
                            .children(children)
                            .build();
            MarriageService.getMarriageService().edit(marriage);
            log.info("Marriage Edited successfully");

        } catch (Exception e) {
            log.error("Marriage Edit has failed" + e.getMessage());
        }
    }

    public void delete(int marriageId) throws Exception {
        try {
            MarriageService.getMarriageService().delete(marriageId);
            log.info("Marriage deleted successfully");
        } catch (Exception e) {
            log.error("Marriage delete has failed" + e.getMessage());

        }
    }

    public List<Marriage> findAll() throws Exception {
        try {
            List<Marriage> marriageList = MarriageService.getMarriageService().findAll();
            log.info("Marriage founded successfully");
            return marriageList;

        } catch (Exception e) {
            log.error("Marriage found has failed" + e.getMessage());
            return null;
        }

    }

    public Marriage findById(int marriageId) throws Exception {
        try {
            Marriage marriage = MarriageService.getMarriageService().findById(marriageId);
            log.info("Marriage founded successfully");
            return marriage;
        } catch (Exception e) {
            log.error("Marriage found has failed with " + marriageId + "id" + e.getMessage());
            return null;
        }
    }


}
