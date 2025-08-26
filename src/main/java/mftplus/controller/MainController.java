package mftplus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import mftplus.model.tools.FormLoader;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
public class MainController implements Initializable {
    @FXML
    private MenuItem addPersonMnu;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPersonMnu.setOnAction(e -> {
            try {
                FormLoader.getFormLoader().showStage(new Stage(), "/view/PersonView.fxml", "Person Information");
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        });
    }
}
