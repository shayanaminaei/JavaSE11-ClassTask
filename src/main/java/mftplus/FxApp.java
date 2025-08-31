package mftplus;

import javafx.application.Application;
import javafx.stage.Stage;
import mftplus.model.repository.DatabaseManager;
import mftplus.model.tools.FormLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class FxApp extends Application {
    public static void main(String[] args) throws SQLException, IOException {
//        DatabaseManager.createDatabase();
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FormLoader.getFormLoader().showStage(primaryStage, "/view/PersonView.fxml", "Person Information");
    }
}
