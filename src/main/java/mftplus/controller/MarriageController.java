package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Marriage;
import mftplus.model.service.MarriageService;
import mftplus.model.service.PersonService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class MarriageController implements Initializable {
    MainController mainController = new MainController();


    @FXML
    private TextField personIdText, marriageIdText, nameText, familyText, childrenText, searchNameText;

    @FXML
    private DatePicker marriageDate;

    @FXML
    private CheckBox statusCheckBox;

    @FXML
    private Button saveButton, editButton, removeButton;

    @FXML
    private TableView<Marriage> marriageTable;

    @FXML
    private TableColumn<Marriage, Integer> marriageIdColumn, personIdColumn;

    @FXML
    private TableColumn<Marriage, String> nameColumn, familyColumn, childrenColumn;

    @FXML
    private TableColumn<Marriage, Boolean> statusColumn;

    @FXML
    private TableColumn<Marriage, LocalDate> marriageDateColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            resetForm();
            log.info("form initialized successfully");
        } catch (Exception e) {
            log.error("form initialization failed");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data!!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {


                Marriage marriage = Marriage
                        .builder()
                        .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                        .marriageId(Integer.parseInt(marriageIdText.getText()))
                        .name(nameText.getText())
                        .family(familyText.getText())
                        .marriageDate(marriageDate.getValue())
                        .isAlive(Boolean.parseBoolean(statusCheckBox.getText()))
                        .children(Integer.parseInt(childrenText.getText()))
                        .build();

                MarriageService.getService().save(marriage);
                log.info(String.format("marriage saved successfully:  %n%s", marriage));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Saved marriage: %n%s", marriage), ButtonType.OK);
                alert.show();

            } catch (Exception e) {
                log.error("marriage save Failed: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Saving Data!!!" + e.getMessage(), ButtonType.OK);
                alert.show();


            }

        });

        editButton.setOnAction(event -> {
            try {
                Marriage marriage = Marriage
                        .builder()
                        .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                        .marriageId(Integer.parseInt(marriageIdText.getText()))
                        .name(nameText.getText())
                        .family(familyText.getText())
                        .marriageDate(marriageDate.getValue())
                        .isAlive(Boolean.parseBoolean(statusCheckBox.getText()))
                        .children(Integer.parseInt(childrenText.getText()))
                        .build();
                MarriageService.getService().edit(marriage);
                log.info(String.format("marriage updated successfully:  %n%s", marriage));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Updated marriage: %n%s", marriage), ButtonType.OK);
                alert.show();
            }catch (Exception e){
                log.error("marriage edit Failed: " + e.getMessage());
                Alert alert =  new Alert(Alert.AlertType.ERROR, "Error Saving Data!!!", ButtonType.OK);
                alert.show();

            }
        });


        removeButton.setOnAction(event -> {
            try {
                MarriageService.getService().delete(Integer.parseInt(marriageIdText.getText()));

                log.info(String.format("Marriage with id=%s Deleted Successfully.", marriageIdText.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Education with id=%s Deleted Successfully", marriageIdText.getText()), ButtonType.OK);
                alert.show();
            }catch (Exception e){
                log.error("marriage delete Failed: " + e.getMessage());
                Alert alert = new  Alert(Alert.AlertType.ERROR, "Error Delete Data!!!", ButtonType.OK);
                alert.show();


            }

        });

        searchNameText.setOnAction(event -> searchByNameAndFamily());
        searchFamilyText.setOnAction(event -> searchByNameAndFamily());

        marriageTable.setOnMouseClicked(event -> selectFromTable());

        marriageTable.setOnKeyReleased(event -> selectFromTable());


    }

    private void resetForm() throws Exception {
        marriageIdText.clear();
        personIdText.clear();
        nameText.clear();
        familyText.clear();

        marriageDate.setValue(LocalDate.now());

        showDateOnTable(MarriageService.getService().findAll());
    }

    private void showDateOnTable(List<Marriage> marriageList) {
        ObservableList<Marriage> observableList = FXCollections.observableList(marriageList);

        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        marriageIdColumn.setCellValueFactory(new PropertyValueFactory<>("marriageId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyColumn.setCellValueFactory(new PropertyValueFactory<>("family"));
        childrenColumn.setCellValueFactory(new PropertyValueFactory<>("children"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        marriageDateColumn.setCellValueFactory(new PropertyValueFactory<>("marriageDate"));


        marriageTable.setItems(observableList);

    }

    public void selectFromTable() {
        try {
            Marriage marriage = marriageTable.getSelectionModel().getSelectedItem();
            marriageIdText.setText(String.valueOf(marriage.getMarriageId()));
            personIdText.setText(String.valueOf(marriage.getPerson().getId()));
            nameText.setText(marriage.getName());
            familyText.setText(marriage.getFamily());
            marriageDate.setValue(marriage.getMarriageDate());
            childrenText.setText(String.valueOf(marriage.getChildren()));
            statusCheckBox.setText(String.valueOf(marriage.isAlive()));

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data!!!", ButtonType.OK);
            alert.show();

        }
        mainController.changeText(2);

    }

    public void searchByNameAndFamily() {
        try {
            showDateOnTable(MarriageService.getService().findByNameAndFamily(searchNameText.getText(), searchFamilyText.getText()));
            log.info("Marriages FindByNameAndFamily :" + searchNameText.getText() + " " + searchFamilyText.getText() + " " + searchFamilyText.getText());


        } catch (Exception e) {
            Alert alert = new  Alert(Alert.AlertType.ERROR, "Error Searching Data!!!", ButtonType.OK);
            alert.show();
            log.error("Marriages SearchByNameAndFamily :" + searchNameText.getText() + " " + searchFamilyText.getText() + " Failed " + e.getMessage());

        }
    }


}
