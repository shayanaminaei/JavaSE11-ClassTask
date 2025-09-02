package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Job;
import mftplus.model.entity.enums.JobTitle;
import mftplus.model.service.JobService;
import mftplus.model.service.PersonService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class JobController implements Initializable {
    //MainController mainController = new MainController();

    @FXML
    private TextField idText, personIdText, organisationText, descriptionText, searchOrganisationText;

    @FXML
    private ComboBox<JobTitle> titleCombo;

    @FXML
    private DatePicker startDate, endDate;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private  TableView<Job> jobTable;

    @FXML
    private TableColumn<Job, Integer> idColumn,personIdColumn;

    @FXML
    private TableColumn<Job, String> organisationColumn,descriptionColumn;

    @FXML
    private TableColumn<Job, LocalDate> startDateColumn,endDateColumn;

    @FXML
    private TableColumn<Job, JobTitle> jobTitleColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
            log.info("Form initialized successfully");
        } catch (Exception e) {
            log.error("Form initialization failed" + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Job job =
                        Job
                                .builder()
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .organisation(organisationText.getText())
                                .title(titleCombo.getSelectionModel().getSelectedItem())
                                .startDate(startDate.getValue())
                                .endDate(endDate.getValue())
                                .description(descriptionText.getText())
                                .build();
                JobService.getService().save(job);
                log.info("Job Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + job, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Job Save Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Job Save Failed" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
           try {
               Job job =
                       Job
                               .builder()
                               .id(Integer.parseInt(idText.getText()))
                               .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                               .organisation(organisationText.getText())
                               .title(titleCombo.getSelectionModel().getSelectedItem())
                               .startDate(startDate.getValue())
                               .endDate(endDate.getValue())
                               .description(descriptionText.getText())
                               .build();
               JobService.getService().edit(job);
               log.info("Job Edited Successfully");
               Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + job, ButtonType.OK);
               alert.show();
               resetForm();
           } catch (Exception e) {
               log.error("Job Edit Failed" + e.getMessage());
               Alert alert = new Alert(Alert.AlertType.ERROR, "Job Edit Failed" + e.getMessage(), ButtonType.OK);
               alert.show();
           }
        });

        deleteButton.setOnAction(event -> {
            try {
                JobService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Job Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Job Delete Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Job Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        searchOrganisationText.setOnKeyReleased(event -> searchByOrganisation());

        jobTable.setOnMouseReleased(event -> selectFromTable());

        jobTable.setOnKeyReleased((event) -> selectFromTable());

    }

    private void resetForm() throws Exception {
        idText.clear();
        personIdText.clear();
        organisationText.clear();

        for (JobTitle jobTitle : JobTitle.values()) {
            titleCombo.getItems().add(jobTitle);
        }
        titleCombo.getSelectionModel().select(0);

        startDate.setValue(LocalDate.now());

        endDate.setValue(LocalDate.now());

        descriptionText.clear();

        showDateOnTable(JobService.getService().findAll());
    }

    private void showDateOnTable(List<Job> jobList) {
        ObservableList<Job> observableList = FXCollections.observableList(jobList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        organisationColumn.setCellValueFactory(new PropertyValueFactory<>("organisation"));
        jobTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        jobTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            Job job = jobTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(job.getId()));
            personIdText.setText(String.valueOf(job.getPerson().getId()));
            organisationText.setText(String.valueOf(job.getOrganisation()));
            titleCombo.getSelectionModel().select(job.getTitle());
            startDate.setValue(job.getStartDate());
            endDate.setValue(job.getEndDate());
            descriptionText.setText(job.getDescription());
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
        //mainController.changeText(1);
    }

    public void searchByOrganisation() {
        try {
            showDateOnTable(JobService.getService().findByOrganisation(searchOrganisationText.getText()));
            log.info("Job Find By Organisation : " + searchOrganisationText.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Job Find By Organisation :  " + searchOrganisationText.getText() + e.getMessage());
        }
    }
}
