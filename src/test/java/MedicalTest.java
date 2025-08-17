import mftplus.model.entity.Medical;
import mftplus.model.service.MedicalService;

import java.time.LocalDate;

public class MedicalTest {
    public static void main(String[] args) throws Exception {
        Medical medical = Medical
                .builder()
                .id(1)
                .personId("123")
                .disease("haert failure")
                .doctor("heart surgeon")
                .visitDate(LocalDate.now())
                .status(true)
                .build();

        MedicalService.getService().save(medical);

    }
}
