import lombok.extern.log4j.Log4j;
import mftplus.controller.MedicalController;
import mftplus.model.entity.Medical;
import mftplus.model.entity.enums.Doctor;
import mftplus.model.service.MedicalService;

import java.time.LocalDate;


@Log4j
public class MedicalTest {
    public static void main(String[] args) throws Exception {
        // log.info("Starting Medical Test");
        //log.error("error saving");

        // MedicalController.getController().save( "123","digestion","gastric pain pills",
        //  Doctor.gastroenterologist, LocalDate.now(),true );


        //Medical medical = Medical
        //      .builder()
        //   .personId("123")
        // .disease("digestion")
        //           .medicine("gastric pain pills")
        //    .doctor(Doctor.gastroenterologist)
        //  .visitDate(LocalDate.now())
        //    .status(true)
        //  .build();
//
        //      MedicalService.getService().save(medical);
        //   System.out.println(medical);

    }
}