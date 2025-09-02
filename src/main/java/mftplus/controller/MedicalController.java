package mftplus.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Medical;
import mftplus.model.entity.enums.Doctor;
import mftplus.model.service.MedicalService;
import mftplus.model.service.PersonService;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


@Log4j2
public class MedicalController implements Initializable {
    @FXML
    private TextField idText, personIdText, diseaseText, medicineText, searchIdText;

    @FXML
    private ComboBox<Doctor> doctorCombo;

    @FXML
    private DatePicker visitDate;

    @FXML
    private RadioButton disableRadio, enableRadio;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Medical> medicalTable;

    @FXML
    private TableColumn<Medical, Integer> idColumn;

    @FXML
    private TableColumn<Medical, String> personIdColumn, diseaseColumn,  medicineColumn;

    @FXML
    private TableColumn<Medical, Doctor>  doctorColumn ;

    @FXML
    private TableColumn<Medical, LocalDate> visitDateColumn;
    
    @FXML
    private TableColumn<Medical, Boolean> statusColumn;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
            log.info("Form initialized successfully");
        } catch (Exception e) {
            log.error("Form initialization failed" + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR,"Error Loading Data" ,ButtonType.OK);
            alert.show();
        }
        
        saveButton.setOnAction(event -> {
            try {
                Medical medical = 
                        Medical
                                .builder()
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .disease(diseaseText.getText())
                                .medicine(medicineText.getText())
                                .doctor(doctorCombo.getSelectionModel().getSelectedItem())
                                .visitDate(visitDate.getValue())
                                .status(enableRadio.isSelected())
                                .build();
                MedicalService.getService().save(medical);
                log.info("Medical Saved Successfully" );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + medical, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("medical Save Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        
        editButton.setOnAction(event -> {
            try {
                Medical medical =
                        Medical
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .disease(diseaseText.getText())
                                .medicine(medicineText.getText())
                                .doctor(doctorCombo.getSelectionModel().getSelectedItem())
                                .visitDate(visitDate.getValue())
                                .status(enableRadio.isSelected())
                                .build();
                MedicalService.getService().edit(medical);
                log.info("Medical Edited Successfully" );
                Alert  alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + medical, ButtonType.OK);
                alert.show();
                resetForm();
            }catch (Exception e){
                log.error("medical Edit Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Medical Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                MedicalService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Medical Deleted Successfully" );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e){
                log.error("Medical Delete Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Medical Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        searchIdText.setOnKeyReleased((event) -> searchById());

       medicalTable.setOnMouseReleased((event) -> selectFromTable());
       medicalTable.setOnKeyReleased((event) -> selectFromTable());

    }


    private void resetForm() throws Exception {
        idText.clear();


        visitDate.setValue(LocalDate.now());

        for (Doctor doctor : Doctor.values()) {
            doctorCombo.getItems().add(doctor);
        }
       doctorCombo.getSelectionModel().select(0);

        enableRadio.setSelected(true);

        showDateOnTable(MedicalService.getService().findAll());
    }

    private void showDateOnTable(List<Medical> medicalList) {
        ObservableList<Medical> observableList = FXCollections.observableList(medicalList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        diseaseColumn.setCellValueFactory(new PropertyValueFactory<>("disease"));
        medicineColumn.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        visitDateColumn.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        medicalTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            Medical medical = medicalTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(medical.getId()));
            personIdText.setText(String.valueOf( medical.getPerson().getId()));
            diseaseText.setText(medical.getDisease());
            medicineText.setText(medical.getMedicine());
            doctorCombo.getSelectionModel().select(medical.getDoctor());
            visitDate.setValue(medical.getVisitDate());
            enableRadio.setSelected(medical.isStatus());
            disableRadio.setSelected(medical.isStatus());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchById() {
        try {
            showDateOnTable(Collections.singletonList(MedicalService.getService().findById(Integer.valueOf(searchIdText.getText()))));
            log.info("Medical SearchFindId" + searchIdText.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Medical FindIdFailed" + searchIdText.getText());
        }
    }

}