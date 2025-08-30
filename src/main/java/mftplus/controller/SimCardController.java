package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.SimCard;
import mftplus.model.entity.enums.SimCardOperator;
import mftplus.model.entity.enums.Title;
import mftplus.model.service.PersonService;
import mftplus.model.service.SimCardService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class SimCardController implements Initializable {
    @FXML
    private TextField idText, personIdText, numberText, searchNumberText;

    @FXML
    private DatePicker registerDate;

    @FXML
    private ComboBox<SimCardOperator> simCardOperatorCombo;

    @FXML
    private ComboBox<Title> titleCombo;

    @FXML
    private RadioButton disableRadio, enableRadio;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableColumn<SimCard, Integer> idColumn;

    @FXML
    private TableColumn<SimCard, Integer> personIdColumn;

    @FXML
    private TableColumn<SimCard, Title> titleColumn;

    @FXML
    private TableColumn<SimCard, Integer> numberColumn;

    @FXML
    private TableColumn<SimCard, SimCardOperator> simCardOperatorColumn;

    @FXML
    private TableColumn<SimCard, LocalDate> registerDateColumn;

    @FXML
    private TableColumn<SimCard, Boolean> statusColumn;

    @FXML
    private TableView<SimCard> simCardTable;


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
                SimCard simCard =
                        SimCard
                                .builder()
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .title(titleCombo.getSelectionModel().getSelectedItem())
                                .numbers(numberText.getText())
                                .simCardOperator(simCardOperatorCombo.getSelectionModel().getSelectedItem())
                                .registerDate(registerDate.getValue())
                                .status(enableRadio.isSelected())
                                .build();
                SimCardService.getService().save(simCard);
                log.info("simCard Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + simCard, ButtonType.OK);
                alert.show();
                resetForm();

            } catch (Exception e) {
                log.error("SimCard Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                SimCard simCard =
                        SimCard
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .title(titleCombo.getSelectionModel().getSelectedItem())
                                .numbers(numberText.getText())
                                .simCardOperator(simCardOperatorCombo.getSelectionModel().getSelectedItem())
                                .registerDate(registerDate.getValue())
                                .status(enableRadio.isSelected())
                                .build();
                SimCardService.getService().edit(simCard);
                log.info("simCard edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "edited Successfully\n" + simCard, ButtonType.OK);
                alert.show();
                resetForm();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "SimCard Edit Data !!!", ButtonType.OK);
                alert.show();
            }


        });

        deleteButton.setOnAction(event -> {
            try {
                SimCardService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("simCard delete Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "delete Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();

            } catch (Exception e) {
                log.error("SimCard Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "SimCard Delete Failed!!!", ButtonType.OK);
                alert.show();
            }
        });
        simCardTable.setOnKeyReleased((event) -> selectFromTable());
        simCardTable.setOnMouseReleased((event) -> selectFromTable());

        searchNumberText.setOnKeyReleased((event) -> searchByNumber());

    }

    private void resetForm() throws Exception {
        idText.clear();
        personIdText.clear();
        numberText.clear();
        searchNumberText.clear();

        registerDate.setValue(LocalDate.now());

        for (SimCardOperator simCardOperator : SimCardOperator.values()) {
            simCardOperatorCombo.getItems().add(simCardOperator);
        }
        for (Title title : Title.values()) {
            titleCombo.getItems().add(title);
        }
        simCardOperatorCombo.getSelectionModel().select(0);
        titleCombo.getSelectionModel().select(0);


        enableRadio.setSelected(true);

        showDateOnTable(SimCardService.getService().findAll());
    }

    private void showDateOnTable(List<SimCard> simCardList) {
        ObservableList<SimCard> observableList = FXCollections.observableList(simCardList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numbers"));
        simCardOperatorColumn.setCellValueFactory(new PropertyValueFactory<>("simCardOperator"));
        registerDateColumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        simCardTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            SimCard simCard = simCardTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(simCard.getId()));
            personIdText.setText(String.valueOf(simCard.getPerson().getId()));
            titleCombo.getSelectionModel().select(simCard.getTitle());
            numberText.setText(simCard.getNumbers());
            simCardOperatorCombo.getSelectionModel().select(simCard.getSimCardOperator());
            registerDate.setValue(simCard.getRegisterDate());
            enableRadio.setSelected(simCard.isStatus());
            disableRadio.setSelected(!simCard.isStatus());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchByNumber() {
        try {
            showDateOnTable(SimCardService.getService().findSimCardByNumber(searchNumberText.getText()));
            log.info("simCard findSimCardByNumber " + searchNumberText.getText() + " Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            log.info("simCard findSimCardByNumber " + searchNumberText.getText() + " error" + e.getMessage());
            alert.show();
        }
    }
}
