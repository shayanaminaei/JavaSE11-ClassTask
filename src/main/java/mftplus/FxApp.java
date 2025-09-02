package mftplus;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import mftplus.model.repository.DatabaseManager;
import mftplus.model.tools.FormLoader;

@Log4j2
public class FxApp extends Application {
    public static void main(String[] args) throws Exception{
        log.info("Starting FxApp");
        DatabaseManager.createDatabase();
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FormLoader.getFormLoader().showStage(primaryStage, "/view/LoginView.fxml", "Login");
    }
}
