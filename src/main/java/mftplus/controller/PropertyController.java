package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Property;
import mftplus.model.service.PersonService;
import mftplus.model.service.PropertyService;


import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2

public class PropertyController implements Initializable {


    @FXML
    private TextField idText, personIdText, nameText, brandText, serialText, countText, searchNameText, dateTimeText;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableColumn<Property, Integer> idColumn;

    @FXML
    private TableColumn<Property, Integer> personIdColumn;

    @FXML
    private TableColumn<Property, String> nameColumn;

    @FXML
    private TableColumn<Property, String> brandColumn;

    @FXML
    private TableColumn<Property, String> serialColumn;

    @FXML
    private TableColumn<Property, Integer> countColumn;

    @FXML
    private TableColumn<Property, LocalDateTime> dateTimeColumn;

    @FXML
    private TableView<Property> propertyTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
        saveButton.setOnAction(event -> {
            try {
                Property property =
                        Property
                                .builder()
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .name(nameText.getText())
                                .brand(brandText.getText())
                                .serial(serialText.getText())
                                .count(Integer.parseInt(countText.getText()))
                                .dateTime(LocalDateTime.parse(dateTimeText.getText()))
                                .build();
                PropertyService.getService().save(property);
                log.info("Property saved");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Property saved\n" + property, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("save failed {}", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving data" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        editButton.setOnAction(event -> {
            try {
                Property property =
                        Property
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .name(nameText.getText())
                                .brand(brandText.getText())
                                .serial(serialText.getText())
                                .count(Integer.parseInt(countText.getText()))
                                .dateTime(LocalDateTime.parse(dateTimeText.getText()))
                                .build();
                PropertyService.getService().edit(property);
                log.info("Property edited");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Property edited\n" + property, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("edit failed {}", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error editing data" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        deleteButton.setOnAction(event -> {
            try {
                PropertyService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Property deleted");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Property deleted" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();

            } catch (Exception e) {
                log.error("delete failed {}", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting data" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        searchNameText.setOnKeyReleased((event) -> searchName());
        propertyTable.setOnMouseReleased((event) -> selectFromTable());

        propertyTable.setOnKeyReleased((event) -> selectFromTable());
    }

    private void resetForm() throws Exception {
        idText.clear();
        personIdText.clear();
        nameText.clear();
        brandText.clear();
        serialText.clear();
        countText.clear();
        dateTimeText.clear();

        showDateOnTable(PropertyService.getService().findAll());
    }

    private void showDateOnTable(List<Property> propertyList) {
        ObservableList<Property> observableList = FXCollections.observableArrayList(propertyList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        propertyTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            Property property = propertyTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(property.getId()));
            personIdText.setText(String.valueOf(property.getPerson().getId()));
            nameText.setText(property.getName());
            brandText.setText(property.getBrand());
            serialText.setText(String.valueOf(property.getSerial()));
            countText.setText(String.valueOf(property.getCount()));
            dateTimeText.setText(property.getDateTime().toString());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading data" + e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }

    public void searchName() {
        try {
            showDateOnTable(PropertyService.getService().findByName(searchNameText.getText()));
            log.info("Property findByName {}", searchNameText.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading data" + e.getMessage(), ButtonType.OK);
            alert.show();
            log.error("Property FindByName{}failed{}", searchNameText.getText(), e.getMessage());
        }
    }

}



