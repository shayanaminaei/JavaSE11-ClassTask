package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Skill;
import mftplus.model.service.SkillService;

import java.time.LocalDate;

@Log4j
public class SkillController {
    @Getter
    private static SkillController controller=new SkillController();


    private SkillController(){
    }

    public void save( int personId, String title, String institute, int duration, LocalDate registerDate,int score ) throws Exception {
        try {
            Skill skill =
                    Skill
                            .builder()
                            .personId(personId)
                            .title(title)
                            .institute(institute)
                            .duration(duration)
                            .registerDate(registerDate)
                            .score(score)
                            .build();
            SkillService.getService().save(skill);
            log.info = ("Skill Saved Successfully");
        } catch (Exception e) {
            log.error("Skill Saved Failed" + e.getMessage());
        }
    }

    public void edit( int personId, String title, String institute, int duration, LocalDate registerDate,int score ) throws Exception {
        try {
            Skill skill =
                    Skill
                            .builder()
                            .personId(personId)
                            .title(title)
                            .institute(institute)
                            .duration(duration)
                            .registerDate(registerDate)
                            .score(score)
                            .build();
            SkillService.getService().save(skill);
            log.info = ("Skill Saved Successfully");
        } catch (Exception e) {
            log.error("Skill Saved Failed" + e.getMessage());
        }
    }
}
