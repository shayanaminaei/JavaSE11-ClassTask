package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Medical;
import mftplus.model.entity.enums.Doctor;
import mftplus.model.service.MedicalService;


import java.time.LocalDate;
import java.util.List;


@Log4j
public class MedicalController {
    @Getter
    private final static MedicalController controller = new MedicalController();

    private MedicalController() {
    }

    public void save( String personId, String disease, String medicine, Doctor doctor, LocalDate visitDate, boolean status) throws Exception {
        try {
            Medical medical = Medical
                    .builder()
                    .personId(personId)
                    .disease(disease)
                    .medicine(medicine)
                    .visitDate(visitDate)
                    .doctor(doctor)
                    .status(status)
                    .build();
            MedicalService.getService().save(medical);
            log.info("Medical saved");
        } catch (Exception e) {
            log.error("Medical save failed" + e.getMessage());
        }
    }


    public void edit(int id, String personId, String disease, String medicine, Doctor doctor, LocalDate visitDate, boolean status) throws Exception {
        try {

            Medical medical = Medical
                    .builder()
                    .id(id)
                    .personId(personId)
                    .disease(disease)
                    .medicine(medicine)
                    .visitDate(visitDate)
                    .doctor(doctor)
                    .status(status)
                    .build();
            MedicalService.getService().edit(medical);
            log.info("Info : Medical Edited Successfully");
        } catch (Exception e) {
            log.error("Error : Medical Delete Failed " + e.getMessage());
        }
    }

    public void delete(Integer id) throws Exception {
        try {
            MedicalService.getService().delete(id);
            log.info("Info : Medical Deleted Successfully");
        } catch (Exception e) {
           log.error("Error : Medical Delete Failed " + e.getMessage());
        }
    }

    public List<Medical> findAll() throws Exception {
        try {
           List<Medical> medicals = MedicalService.getService().findAll();
            log.info("Medical FindAll");
           return medicals;
        } catch (Exception e) {
            log.error("Error : Medical FindAll Failed " + e.getMessage());
            return null;
        }
    }

    public Medical findById(Integer id) throws Exception {
        try {
            Medical medical = MedicalService.getService().findById(id);
            log.info("Medical findById" + id);
            return medical;
        } catch (Exception e) {
            log.error("Error : Medical FindId " + id + " Failed " + e.getMessage());
            return null;
        }
    }
}