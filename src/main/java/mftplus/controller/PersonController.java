package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Person;
import mftplus.model.entity.enums.Role;
import mftplus.model.service.PersonService;
import mftplus.model.tools.FormLoader;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class PersonController implements Initializable {
//    MainController mainController = new MainController();

    @FXML
    private TextField idText, nameText, familyText, searchNameText, searchFamilyText;

    @FXML
    private DatePicker birthDate;

    @FXML
    private ComboBox<Role> roleCombo;

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
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction((event) -> {
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + person, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Person Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction((event) -> {
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + person, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Person Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction((event) -> {
            try {
                PersonService.getService().delete(Integer.parseInt(idText.getText()));
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

        searchNameText.setOnKeyReleased((event) -> searchByNameAndFamily());
        searchFamilyText.setOnKeyReleased((event) -> searchByNameAndFamily());

        personTable.setOnMouseReleased((event) -> selectFromTable());

        personTable.setOnKeyReleased((event) -> selectFromTable());

    }

    private void resetForm() throws Exception {
        idText.clear();
        nameText.clear();
        familyText.clear();

        birthDate.setValue(LocalDate.now());

        for (Role role : Role.values()) {
            roleCombo.getItems().add(role);
        }
        roleCombo.getSelectionModel().select(0);

        enableRadio.setSelected(true);

        showDateOnTable(PersonService.getService().findAll());
    }

    private void showDateOnTable(List<Person> personList) {
        ObservableList<Person> observableList = FXCollections.observableList(personList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyColumn.setCellValueFactory(new PropertyValueFactory<>("family"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        personTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            Person person = personTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(person.getId()));
            nameText.setText(person.getName());
            familyText.setText(person.getFamily());
            birthDate.setValue(person.getBirthDate());
            roleCombo.getSelectionModel().select(person.getRole());
            enableRadio.setSelected(person.isStatus());
            disableRadio.setSelected(!person.isStatus());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
//        mainController.changeText(2);
    }

    public void searchByNameAndFamily() {
        try {
            showDateOnTable(PersonService.getService().findByNameAndFamily(searchNameText.getText(), searchFamilyText.getText()));
            log.info("Persons FindByNameAndFamily :" + searchNameText.getText() + " " + searchFamilyText.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Person FindNameFamily " + searchNameText.getText() + " " + searchFamilyText.getText() + " Failed " + e.getMessage());
        }
    }
}

