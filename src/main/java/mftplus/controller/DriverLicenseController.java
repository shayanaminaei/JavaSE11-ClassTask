package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.DriverLicense;
import mftplus.model.entity.enums.DriverLicenseType;
import mftplus.model.service.DriverLicenseService;
import mftplus.model.service.PersonService;
import mftplus.model.service.SimCardService;
import mftplus.model.tools.FormLoader;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
@Log4j
public class DriverLicenseController  implements Initializable {

    @FXML
    private TextField idText, personText,serialText, cityText, searchSerial;

    @FXML
    private DatePicker registerDate,expireDate;

    @FXML
    private ComboBox<DriverLicenseType> driverLisenseCombo;


    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<DriverLicense> driverLicenseTable;

    @FXML
    private TableColumn<DriverLicense, Integer> idColumn,personIdColumn;

    @FXML
    private TableColumn<DriverLicense, String> serialIdColumn, cityColumn;

    @FXML
    private TableColumn<DriverLicense, LocalDate> registerDateColumn,expireDateColumn;

    @FXML
    private TableColumn<DriverLicense, DriverLicenseType> driverLicenseTColumn;


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
                DriverLicense driverLicense =
                        DriverLicense
                                .builder()
                                .person(PersonService.getService().findById(Integer.parseInt(personText.getText())))
                                .serial(serialText.getText())
                                .driverLicenseType(driverLisenseCombo.getSelectionModel().getSelectedItem())
                                .city(cityText.getText())
                                .registerDate(registerDate.getValue())
                                .expireDate(expireDate.getValue())
                                .build();
                DriverLicenseService.getService().save(driverLicense);
                log.info("driverLicense Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + driverLicense, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("driverLicense Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "driverLicense Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction((event) -> {
            try {
                DriverLicense driverLicense =
                        DriverLicense
                                .builder()
                                .person(PersonService.getService().findById(Integer.parseInt(personText.getText())))
                                .serial(serialText.getText())
                                .driverLicenseType(driverLisenseCombo.getSelectionModel().getSelectedItem())
                                .city(cityText.getText())
                                .registerDate(registerDate.getValue())
                                .expireDate(expireDate.getValue())
                                .build();
                DriverLicenseService.getService().edit(driverLicense);
                log.info("driverLicense Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + driverLicense, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("driverLicense Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "driverLicense Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction((event) -> {
            try {
                FormLoader.getFormLoader().showStage(new Stage(), "/view/driverLicenseView.fxml", "driverLicense Information");
            } catch (Exception e) {
                log.error("driverLicense Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "driverLicense Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });


        driverLicenseTable.setOnMouseReleased((event) -> selectFromTable());

        driverLicenseTable.setOnKeyReleased((event) -> selectFromTable());

    }

    private void resetForm() throws Exception {
        idText.clear();
        personText.clear();
        serialText.clear();
        cityText.clear();
        registerDate.setValue(LocalDate.now());
        expireDate.setValue(LocalDate.now());

        for (DriverLicenseType driverLicenseType : DriverLicenseType.values()) {
            driverLisenseCombo.getItems().add(driverLicenseType);
        }
        driverLisenseCombo.getSelectionModel().select(0);



        showDateOnTable(DriverLicenseService.getService().findAll());
    }

    private void showDateOnTable(List<DriverLicense> driverLicenseList) {
        ObservableList<DriverLicense> observableList = FXCollections.observableList(driverLicenseList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        serialIdColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));
        driverLicenseTColumn.setCellValueFactory(new PropertyValueFactory<>("driverLicenseType"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        registerDateColumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        expireDateColumn.setCellValueFactory(new PropertyValueFactory<>("expireDate"));

        driverLicenseTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            DriverLicense driverLicense = driverLicenseTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(driverLicense.getId()));
            personIdColumn.setText(String.valueOf(driverLicense.getPerson().getId()));
            serialText.setText(driverLicense.getSerial());
            driverLisenseCombo.getSelectionModel().select(driverLicense.getDriverLicenseType());
            cityText.setText(driverLicense.getCity());
            registerDate.setValue(driverLicense.getRegisterDate());
            expireDate.setValue(driverLicense.getExpireDate());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }
    public void findBySerial() {
        try {
            showDateOnTable(DriverLicenseService.getService().findBySerial(searchSerial.getText()));
            log.info("DriverLicense findBySerial " + searchSerial.getText() + " Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            log.info("DriverLicense findBySerial " + searchSerial.getText() + " error" + e.getMessage());
            alert.show();
        }
    }

}
