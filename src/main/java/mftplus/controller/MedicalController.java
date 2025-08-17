package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Medical;
import mftplus.model.service.MedicalService;


import java.time.LocalDate;
import java.util.List;


@Log4j
public class MedicalController {
    @Getter
    private final static MedicalController controller = new MedicalController();

    private MedicalController() {
    }

    public void save(int id, String personId, String disease, String medicine, String doctor, LocalDate visitDate, boolean status) throws Exception {
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
            MedicalService.getService().save(medical);
            log.info("Medical saved");
        } catch (Exception e) {
            log.error("Medical save failed" + e.getMessage());
        }
    }


    public void edit(int id, String personId, String disease, String medicine, String doctor, LocalDate visitDate, boolean status) throws Exception {
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
            System.out.println("Info : Medical Edited Successfully");
        } catch (Exception e) {
            System.out.println("Error : Medical Delete Failed " + e.getMessage());
        }
    }

    public void delete(Integer id) throws Exception {
        try {
            MedicalService.getService().delete(id);
            System.out.println("Info : Medical Deleted Successfully");
        } catch (Exception e) {
            System.out.println("Error : Medical Delete Failed " + e.getMessage());
        }
    }

    public List<Medical> findAll() throws Exception {
        try {
            return MedicalService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error : Medical FindAll Failed " + e.getMessage());
            return null;
        }
    }

    public Medical findById(Integer id) throws Exception {
        try {
            return MedicalService.getService().findById(id);
        } catch (Exception e) {
            System.out.println("Error : Medical FindId " + id + " Failed " + e.getMessage());
            return null;
        }
    }
}