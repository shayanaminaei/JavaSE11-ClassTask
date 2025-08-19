package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Skill;
import mftplus.model.service.SkillService;

import java.time.LocalDate;
import java.util.List;

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
            log.info ("Skill Saved Successfully");
        } catch (Exception e) {
            log.error("Skill Saved Failed" + e.getMessage());
        }
    }

    public void edit( int id,int personId, String title, String institute, int duration, LocalDate registerDate,int score ) throws Exception {
        try {
            Skill skill =
                    Skill
                            .builder()
                            .id(id)
                            .personId(personId)
                            .title(title)
                            .institute(institute)
                            .duration(duration)
                            .registerDate(registerDate)
                            .score(score)
                            .build();
            SkillService.getService().save(skill);
            log.info ("Skill Saved Successfully");
        } catch (Exception e) {
            log.error("Skill Saved Failed" + e.getMessage());
        }
    }

    public void delete(Integer id) throws Exception {
        try {
            SkillService.getService().delete(id);
            log.info("Skill Deleted Successfully");
        } catch (Exception e) {
            log.error("Skill Deleted Failed" + e.getMessage());
        }
    }
    public List<Skill> findAll() throws Exception {
        try {
            List<Skill> skillList = SkillService.getService().findAll();
            log.info("Skills FindAll");
            return skillList;
        } catch (Exception e) {
            log.error("Skill FindAll Failed " + e.getMessage());
            return null;
        }
    }

    public Skill findById(Integer id) throws Exception {
        try {
            Skill skill = SkillService.getService().findById(id);
            log.info("Skill FindById : " + id );
            return skill;
        } catch (Exception e) {
            log.error("Skill FindById " + id + " Failed " + e.getMessage());
            return null;
        }
    }





}
