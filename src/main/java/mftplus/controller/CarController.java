package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Car;
import mftplus.model.service.CarService;
import mftplus.model.service.PersonService;


import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class CarController implements Initializable {
    @FXML
    private TextField idText, personIdText, nameText, brandText, colorText, plateText, searchNameText, searchIdText;

    @FXML
    private DatePicker manDate;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<Car, Integer> idColumn, personIdColumn;

    @FXML
    private TableColumn<Car, String> nameColumn, brandColumn, colorColumn, plateColumn;

    @FXML
    private TableColumn<Car, LocalDate> manDateColumn;

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
                Car car = Car

                        .builder()
                        .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                        .name(nameText.getText())
                        .brand(brandText.getText())
                        .manDate(manDate.getValue())
                        .color(colorText.getText())
                        .plate(plateText.getText())
                        .build();
                CarService.getService().save(car);
                log.info("Car Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + car, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Car Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Car Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction((event) -> {
            try {
                Car car = Car

                        .builder()
                        .id(Integer.parseInt(idText.getText()))
                        .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                        .name(nameText.getText())
                        .brand(brandText.getText())
                        .manDate(manDate.getValue())
                        .color(colorText.getText())
                        .plate(plateText.getText())
                        .build();
                CarService.getService().edit(car);
                log.info("Car Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + car, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Car Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Car Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction((event) -> {
            try {
                CarService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Car Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Car Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Car Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        searchNameText.setOnKeyReleased((event) -> searchById());
        searchIdText.setOnKeyReleased((event) -> searchByAll());

        carTable.setOnMouseReleased((event) -> selectFromTable());
        carTable.setOnKeyReleased((event) -> selectFromTable());
    }

    private void resetForm() throws Exception {
        idText.clear();
        personIdText.clear();
        nameText.clear();
        brandText.clear();
        colorText.clear();
        plateText.clear();
        manDate.setValue(LocalDate.now());
        showDateOnTable(CarService.getService().findAll());
    }

    private void showDateOnTable(List<Car> carList) {
        ObservableList<Car> observableList = FXCollections.observableList(carList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        manDateColumn.setCellValueFactory(new PropertyValueFactory<>("manDate"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));

        carTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            Car car = carTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(car.getId()));
            personIdText.setText(String.valueOf(car.getPerson().getId()));
            nameText.setText(car.getName());
            brandText.setText(car.getBrand());
            manDate.setValue(car.getManDate());
            colorText.setText(car.getColor());
            plateText.setText(car.getPlate());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchById() {
        try {
            Car car = CarService.getService().findById(Integer.parseInt(searchIdText.getText()));
            showDateOnTable(FXCollections.observableArrayList(car));
            log.info("Car FindById: " + searchIdText.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Car FindById " + searchIdText.getText() + " Failed " + e.getMessage());
        }
    }

    public void searchByAll() {
        try {
            showDateOnTable(CarService.getService().findAll());
            log.info("Cars FindAll Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Car FindAll Failed " + e.getMessage());
        }
    }




}

