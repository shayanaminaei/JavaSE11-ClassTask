package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Skill;
import mftplus.model.service.PersonService;
import mftplus.model.service.SkillService;
import mftplus.model.tools.FormLoader;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class SkillController implements Initializable {
    @FXML
    private TextField idText, personIdText, titleText, instituteText, durationText,scoreText,searchTitleText;

    @FXML
    private DatePicker registerDate;

    @FXML
    private Button saveButton,editButton,deleteButton;

    @FXML
    private TableView<Skill> skillTable;

    @FXML
    private TableColumn<Skill,Integer> idColumn,personIdColumn,durationColumn,scoreColumn;

    @FXML
    private TableColumn<Skill,String> titleColumn,instituteColumn;

    @FXML
    private TableColumn<Skill, LocalDate> registerDateColumn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
        saveButton.setOnAction((event) -> {
            try {
                Skill skill  =
                        Skill
                                .builder()
                                .personId(Integer.parseInt(personIdText.getText()))
                                . title(titleText.getText())
                                . institute(instituteText.getText())
                                .duration(Integer.parseInt(durationText.getText()))
                                .registerDate(registerDate.getValue())
                                .score(Integer.parseInt(scoreText.getText()))
                                .build();
                SkillService.getService().save(skill);
                log.info("Skill Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + skill, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Skill Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Skill Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction((event) -> {
            try {
                Skill skill =
                        Skill
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .personId(Integer.parseInt(personIdText.getText()))
                                . title(titleText.getText())
                                . institute(instituteText.getText())
                                .duration(Integer.parseInt(durationText.getText()))
                                .registerDate(registerDate.getValue())
                                .score(Integer.parseInt(scoreText.getText()))
                                .build();
                SkillService.getService().edit(skill);
                log.info("Skill Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + skill, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Skill Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Skill Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction((event) -> {
            try {
                SkillService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Skill Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Skill Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Skill Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        skillTable.setOnMouseReleased((event) -> selectFromTable());

        skillTable.setOnKeyReleased((event) -> selectFromTable());

        searchTitleText.setOnKeyReleased((event) -> searchByTitle());
        searchTitleText.setOnKeyReleased((event) -> searchByTitle());

    }
    private void resetForm() throws Exception {
        idText.clear();
        personIdText.clear();
        titleText.clear();
        instituteText.clear();
        durationText.clear();
        scoreText.clear();

        registerDate.setValue(LocalDate.now());


        showDateOnTable(SkillService.getService().findAll());
    }
    private void showDateOnTable(List<Skill> skillList) {
        ObservableList<Skill> observableList = FXCollections.observableList(skillList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        instituteColumn.setCellValueFactory(new PropertyValueFactory<>("institute"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        registerDateColumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        skillTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            Skill skill  = skillTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(skill.getId()));
            personIdText.setText(String.valueOf(skill.getPersonId()));
            titleText.setText(skill.getTitle());
            instituteText.setText(skill.getInstitute());
            durationText.setText(String.valueOf(skill.getDuration()));
            registerDate.setValue(skill.getRegisterDate());
            scoreText.setText(String.valueOf(skill.getScore()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchByTitle() {
        try {
            showDateOnTable(SkillService.getService().findSkillByTitle(searchTitleText.getText()));
            log.info("skill findSkillByTitle " + searchTitleText.getText() + " Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            log.info("skill findSkillByTitle " + searchTitleText.getText() + " error" + e.getMessage());
            alert.show();
        }
    }
}
