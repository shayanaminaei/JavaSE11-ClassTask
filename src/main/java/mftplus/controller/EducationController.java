package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Education;
import mftplus.model.entity.enums.EducationGrade;
import mftplus.model.service.EducationService;
import mftplus.model.service.PersonService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class EducationController implements Initializable {

    @FXML
    private TextField idText, personIdText, universityText, averageText, searchUniversityText, searchEducationGradeText;

    @FXML
    private ComboBox<EducationGrade> educationGradeCombo;

    @FXML
    private DatePicker startDate, endDate;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Education> educationTable;

    @FXML
    private TableColumn<Education, Integer> idColumn, personIdColumn;

    @FXML
    private TableColumn<Education, String> universityColumn, averageColumn;

    @FXML
    private TableColumn<Education, EducationGrade> educationGradeColumn;

    @FXML
    private TableColumn<Education, LocalDate> startDateColumn, endDateColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        saveButton.setOnAction(event -> save());
        editButton.setOnAction(event -> edit());
        deleteButton.setOnAction(event -> delete());

        searchUniversityText.setOnKeyReleased(event -> findByUniversityAndEducationGrade());
        searchEducationGradeText.setOnKeyReleased(event -> findByUniversityAndEducationGrade());

        educationTable.setOnMouseReleased(event -> selectFromTable());
        educationTable.setOnKeyReleased(event -> selectFromTable());
    }

    private void resetForm() {
        try {
            idText.clear();
            personIdText.clear();
            universityText.clear();

            educationGradeCombo.getItems().clear();
            for (EducationGrade grade : EducationGrade.values()) {
                educationGradeCombo.getItems().add(grade);
            }
            educationGradeCombo.getSelectionModel().select(0);

            averageText.clear();

            startDate.setValue(LocalDate.now());
            endDate.setValue(LocalDate.now());

            searchUniversityText.clear();
            searchEducationGradeText.clear();

            showDataOnTable(EducationService.getService().findAll());
            log.info("Education Form Loaded Successfully");
        } catch (Exception e) {
            log.error("Loading Education Form Failed: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Error in Loading Education Form:%n%s", e.getMessage()), ButtonType.OK);
            alert.show();
        }
    }

    private void showDataOnTable(List<Education> educationList) {
        ObservableList<Education> educationObservableList = FXCollections.observableList(educationList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        universityColumn.setCellValueFactory(new PropertyValueFactory<>("university"));
        educationGradeColumn.setCellValueFactory(new PropertyValueFactory<>("educationGrade"));
        averageColumn.setCellValueFactory(new PropertyValueFactory<>("average"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        educationTable.setItems(educationObservableList);
    }

    private void selectFromTable() {
        try {
            Education education = educationTable.getSelectionModel().getSelectedItem();

            idText.setText(String.valueOf(education.getId()));
            personIdText.setText(String.valueOf(education.getPerson().getId()));
            universityText.setText(education.getUniversity());
            educationGradeCombo.getSelectionModel().select(education.getEducationGrade());
            averageText.setText(String.valueOf(education.getAverage()));
            startDate.setValue(education.getStartDate());
            endDate.setValue(education.getEndDate());
            log.info("Education Selected From Table Successfully: " + education);
        } catch (Exception e) {
            log.error("Select Education From Table Failed: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Error in Select Education From Table:%n%s", e.getMessage()), ButtonType.OK);
            alert.show();
        }
    }

    private void save() {
        try {
            Education education =
                    Education
                            .builder()
                            .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                            .university(universityText.getText())
                            .educationGrade(educationGradeCombo.getSelectionModel().getSelectedItem())
                            .average(Double.parseDouble(averageText.getText()))
                            .startDate(startDate.getValue())
                            .endDate(endDate.getValue())
                            .build();

            EducationService.getService().save(education);
            log.info("Education Saved Successfully: " + education);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Education Saved Successfully:%n%s", education), ButtonType.OK);
            alert.show();
            resetForm();
        } catch (Exception e) {
            log.error("Education Save Failed: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Education Save Failed:%n%s", e.getMessage()), ButtonType.OK);
            alert.show();
        }
    }

    private void edit() {
        try {
            Education education =
                    Education
                            .builder()
                            .id(Integer.parseInt(idText.getText()))
                            .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                            .university(universityText.getText())
                            .educationGrade(educationGradeCombo.getSelectionModel().getSelectedItem())
                            .average(Double.parseDouble(averageText.getText()))
                            .startDate(startDate.getValue())
                            .endDate(endDate.getValue())
                            .build();

            EducationService.getService().edit(education);
            log.info("Education Edited Successfully: " + education);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Education Edited Successfully:%n%s", education), ButtonType.OK);
            alert.show();
            resetForm();
        } catch (Exception e) {
            log.error("Education Edit Failed: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Education Edit Failed:%n%s", e.getMessage()), ButtonType.OK);
            alert.show();
        }
    }

    private void delete() {
        try {
            EducationService.getService().delete(Integer.parseInt(idText.getText()));
            log.info(String.format("Education Deleted Successfully: id=%s", idText.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Education Deleted Successfully:%nid=%s", idText.getText()), ButtonType.OK);
            alert.show();
            resetForm();
        } catch (Exception e) {
            log.error("Education Delete Failed: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Education Delete Failed:%n%s", e.getMessage()), ButtonType.OK);
            alert.show();
        }
    }

    private void findByUniversityAndEducationGrade() {
        try {
            showDataOnTable(EducationService.getService().findByUniversityAndEducationGrade(searchUniversityText.getText(), searchEducationGradeText.getText()));
            log.info(String.format("Education Find By University=%s And Grade=%s Successfully", searchUniversityText.getText(), searchEducationGradeText.getText()));
        } catch (Exception e) {
            log.error(String.format("Education Find By University=%s And Grade=%s Failed: ", searchUniversityText.getText(), searchEducationGradeText.getText()) + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Education Find Failed:%n%s", e.getMessage()), ButtonType.OK);
            alert.show();
        }
    }
}
