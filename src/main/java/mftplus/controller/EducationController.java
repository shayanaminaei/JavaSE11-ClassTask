package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Education;
import mftplus.model.entity.enums.EducationGrade;
import mftplus.model.service.EducationService;
import mftplus.model.service.PersonService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class EducationController implements Initializable {
    @FXML
    private TextField idText, personIdText, universityText, averageText, searchUniversityText, searchEducationalGradeText;

    @FXML
    private ComboBox<EducationGrade> educationalGradeCombo;

    @FXML
    private DatePicker startDate, endDate;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Education> educationTable;

    @FXML
    private TableColumn<Education, Integer> idColumn, personIdColumn;

    @FXML
    private TableColumn<Education, String> universityColumn;

    @FXML
    private TableColumn<Education, EducationGrade> educationalGradeColumn;

    @FXML
    private TableColumn<Education, Double> averageColumn;

    @FXML
    private TableColumn<Education, LocalDate> startDateColumn, endDateColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
            log.info("Form Initialized Successfully");
        } catch (Exception e) {
            log.error("Form Initialization Failed: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Form Initialization Failed: %s", e.getMessage()), ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Education education =
                        Education
                                .builder()
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .university(universityText.getText())
                                .educationGrade(educationalGradeCombo.getSelectionModel().getSelectedItem())
                                .average(Double.parseDouble(averageText.getText()))
                                .startDate(startDate.getValue())
                                .endDate(endDate.getValue())
                                .build();

                EducationService.getService().save(education);
                log.info(String.format("Education Saved Successfully: %n%s", education));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Saved education: %n%s", education), ButtonType.OK);
                alert.show();
            } catch (Exception e) {
                log.error("Education Save Failed: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Education Save Failed: " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                Education education =
                        Education
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .university(universityText.getText())
                                .educationGrade(educationalGradeCombo.getSelectionModel().getSelectedItem())
                                .average(Double.parseDouble(averageText.getText()))
                                .startDate(startDate.getValue())
                                .endDate(endDate.getValue())
                                .build();

                EducationService.getService().edit(education);
                log.info(String.format("Education Edited Successfully: %n%s", education));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Edited education: %n%s", education), ButtonType.OK);
                alert.show();
            } catch (Exception e) {
                log.error("Education Edited Failed: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Education Edite Failed: " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                EducationService.getService().delete(Integer.parseInt(idText.getText()));

                log.info(String.format("Education with id=%s Deleted Successfully.", idText.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Education with id=%s Deleted Successfully", idText.getText()), ButtonType.OK);
                alert.show();
            } catch (Exception e) {
                log.error("Education delete Failed: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Education delete Failed: " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        educationTable.setOnMouseReleased(event -> {
//            try {
//                Education education = educationTable.getSelectionModel().getSelectedItem();
//                if (education == null) {
//                    education = educationTable.getItems().get(0);
//                }
//                selectFromTable(education);
//
//                log.info("Data Loaded from Education Table Successfully");
//            } catch (Exception e) {
//                log.error("Loading Data from Education Table Failed: " + e.getMessage());
//                Alert alert = new Alert(Alert.AlertType.ERROR, "Loading Data From Table Failed: " + e.getMessage(), ButtonType.OK);
//                alert.show();
//            }
        });

        educationTable.setOnKeyReleased(event -> {
//            try {
//                if (event.getCode() == KeyCode.TAB) {
//                    Education education = educationTable.getItems().get(0);
//                    selectFromTable(education);
//
//                    log.info("Data Loaded from Education Table Successfully");
//                } else if (event.getCode() == KeyCode.UP ||
//                        event.getCode() == KeyCode.DOWN ||
//                        event.getCode() == KeyCode.LEFT ||
//                        event.getCode() == KeyCode.RIGHT) {
//                    Education education = educationTable.getSelectionModel().getSelectedItem();
//                    selectFromTable(education);
//
//                    log.info("Data Loaded from Education Table Successfully");
//                }
//            } catch (Exception e) {
//                log.error("Loading Data from Education Table Failed: " + e.getMessage());
//                Alert alert = new Alert(Alert.AlertType.ERROR, "Loading Data From Table Failed: " + e.getMessage(), ButtonType.OK);
//                alert.show();
//            }
        });

        searchUniversityText.setOnKeyReleased(event -> searchByUniversityAndEducationalGrade());

        searchEducationalGradeText.setOnKeyReleased(event ->searchByUniversityAndEducationalGrade());
    }

    private void resetForm() throws Exception {
        idText.clear();
        personIdText.clear();
        universityText.clear();

        for (EducationGrade grade : EducationGrade.values()) {
            educationalGradeCombo.getItems().add(grade);
        }
        educationalGradeCombo.getSelectionModel().select(0);

        averageText.clear();

        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now());

        showDataOnTable(EducationService.getService().findAll());
    }

    private void showDataOnTable(List<Education> educationList) {
        ObservableList<Education> educationObservableList = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        universityColumn.setCellValueFactory(new PropertyValueFactory<>("university"));
        educationalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("educationalGrade"));
        averageColumn.setCellValueFactory(new PropertyValueFactory<>("average"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        educationTable.setItems(educationObservableList);
    }

    private void selectFromTable(Education education) {
//        idText.setText(String.valueOf(education.getId()));
//        personIdText.setText(String.valueOf(education.getPersonId()));
//        universityText.setText(education.getUniversity());
//        educationalGradeCombo.getSelectionModel().select(education.getEducationGrade());
//        averageText.setText(String.valueOf(education.getAverage()));
//        startDate.setValue(education.getStartDate());
//        endDate.setValue(education.getEndDate());
    }

    private void searchByUniversityAndEducationalGrade() {
        try {
            showDataOnTable(EducationService.getService().findByUniversityAndGrade(universityText.getText(), educationalGradeCombo.getSelectionModel().getSelectedItem().name()));
        } catch (Exception e) {
            log.error("Search By University And Education Grade Failed: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data: " + e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }
    // OldEducationController
/*
    @Getter
    private static EducationController controller = new EducationController();
    private EducationController() {}

    public void save(
            int personId,
            String university,
            EducationGrade grade,
            double average,
            LocalDate startDate,
            LocalDate endDate
    ) throws Exception {
        try {
            Education education =
                    Education
                            .builder()
                            .personId(personId)
                            .university(university)
                            .educationGrade(grade)
                            .average(average)
                            .startDate(startDate)
                            .endDate(endDate)
                            .build();

            EducationService.getService().save(education);
            log.info("Education Saved Successfully");
        } catch (Exception e) {
            log.error("Education Saving Failed" + e.getMessage());
        }
    }

    public void edit(
            int id,
            int personId,
            String university,
            EducationGrade grade,
            double average,
            LocalDate startDate,
            LocalDate endDate
    ) throws Exception {
        try {
            Education education =
                    Education
                            .builder()
                            .id(id)
                            .personId(personId)
                            .university(university)
                            .educationGrade(grade)
                            .average(average)
                            .startDate(startDate)
                            .endDate(endDate)
                            .build();

            EducationService.getService().edit(education);
            log.info("Education Edited Successfully");
        } catch (Exception e) {
            log.error("Education Edit Failed" + e.getMessage());
        }
    }

    public void delete(int id) throws Exception {
        try {
            EducationService.getService().delete(id);
            log.info("Education Deleted Successfully");
        } catch (Exception e) {
            log.error("Education DeleteById " + id + " Failed" + e.getMessage());
        }
    }

    public List<Education> findall() throws Exception {
        try {
            List<Education> educationList = EducationService.getService().findAll();
            log.info("Education Findall Successfully");
            return educationList;
        } catch (Exception e) {
            log.error("Education Findall Failed" + e.getMessage());
            return null;
        }
    }

    public Education findById(int id) throws Exception {
        try {
            Education education = EducationService.getService().findById(id);
            log.info("Education FindByID " + id + " Successfully");
            return education;
        } catch (Exception e) {
            log.error("Education FindById " + id + " Failed" + e.getMessage());
            return null;
        }
    }

    public List<Education> findByUniversityAndGrade(String university, String grade) throws Exception {
        try {
            List<Education> educationList = EducationService.getService().findByUniversityAndGrade(university, grade);
            log.info("Education Find by University= " + university + " And Grade= " + grade + " Successfully");
            return educationList;
        } catch (Exception e) {
            log.error("Education Find by University= " + university + " And Grade= " + grade + " Failed" + e.getMessage());
            return null;
        }
    }
*/
}
