package mftplus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mftplus.model.tools.FormLoader;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FormLoader.getFormLoader().showStage(primaryStage, "/view/MainView.fxml", "Dashboard");
    }
}
