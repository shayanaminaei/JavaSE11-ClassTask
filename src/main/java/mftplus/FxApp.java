package mftplus;

import javafx.application.Application;
import javafx.stage.Stage;
import mftplus.model.repository.DatabaseManager;
import mftplus.model.tools.FormLoader;

public class FxApp extends Application {
    public static void main(String[] args) throws Exception{
        DatabaseManager.createDatabase();
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FormLoader.getFormLoader().showStage(primaryStage, "/view/LoginView.fxml", "Login");
    }
}
