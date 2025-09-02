package mftplus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import mftplus.model.tools.FormLoader;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j2
public class MainController implements Initializable {
    @FXML
    private MenuItem addPersonMnu;

//    PersonController personController = new PersonController();

    @FXML
    public void changeText(int a){

        System.out.println("Change Text");
    }

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
