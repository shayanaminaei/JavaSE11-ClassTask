package mftplus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.SimCard;
import mftplus.model.entity.enums.SimCardOperator;
import mftplus.model.entity.enums.Title;
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
    private RadioButton disableRadio,enableRadio;

    @FXML
    private TableColumn<SimCard,Integer>idColumn;

    @FXML
    private TableColumn<SimCard,Integer>personIdColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
