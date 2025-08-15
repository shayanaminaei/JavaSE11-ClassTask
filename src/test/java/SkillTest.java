import mftplus.model.entity.Person;
import mftplus.model.entity.Skill;
import mftplus.model.entity.enums.Role;
import mftplus.model.service.SkillService;

import java.time.LocalDate;

public class SkillTest {
    public static void main(String[] args) throws Exception {
        Skill skill = Skill
                .builder()
                .personId(1)
                .title("JavaSE")
                .institute("mft")
                .duration(150)
                .registerDate(LocalDate.of(1404, 5, 1))
                .role(Role.admin)
                .status(true)
                .build();

        SkillService.getService().save(skill);
    }

}
