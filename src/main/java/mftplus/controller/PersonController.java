package mftplus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Person;
import mftplus.model.entity.enums.Role;
import mftplus.model.service.PersonService;

import java.beans.EventHandler;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class PersonController implements Initializable {
    @FXML
    private TextField idText, nameText, familyText;

    @FXML
    private DatePicker birthDate;

    @FXML
    private ComboBox<Role> roleCombo;

    @FXML
    private ToggleGroup statusToggle;

    @FXML
    private RadioButton enableRadio, disableRadio;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, Integer> idColumn;

    @FXML
    private TableColumn<Person, String> nameColumn, familyColumn;

    @FXML
    private TableColumn<Person, LocalDate> birthDateColumn;

    @FXML
    private TableColumn<Person, Role> roleColumn;

    @FXML
    private TableColumn<Person, Boolean> statusColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        saveButton.setOnAction((event) ->{
            try {
                Person person =
                        Person
                                .builder()
                                .name(nameText.getText())
                                .family(familyText.getText())
                                .birthDate(birthDate.getValue())
                                .role(roleCombo.getSelectionModel().getSelectedItem())
                                .status(enableRadio.isSelected())
                                .build();
                PersonService.getService().save(person);
                log.info("Person Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" +person, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Person Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction((event) ->{
            try {
                Person person =
                        Person
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .name(nameText.getText())
                                .family(familyText.getText())
                                .birthDate(birthDate.getValue())
                                .role(roleCombo.getSelectionModel().getSelectedItem())
                                .status(enableRadio.isSelected())
                                .build();
                PersonService.getService().edit(person);
                log.info("Person Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" +person, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Person Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction((event) ->{
            try {
                PersonService.getService().delete(Integer.valueOf(idText.getText()));
                log.info("Person Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Person Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

    }

    private void resetForm() {
        idText.clear();
        nameText.clear();
        familyText.clear();

        birthDate.setValue(LocalDate.now());

        for (Role role : Role.values()) {
            roleCombo.getItems().add(role);
        }
        roleCombo.getSelectionModel().select(0);

        enableRadio.setSelected(true);

//        showDateOnTable(PersonService.getService().findAll());
    }

    private void showDateOnTable(List<Person> personList){}
}

