package mftplus.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Lesson;
import mftplus.model.service.LessonService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


@Log4j
public class LessonController implements Initializable {

    @FXML
    private TextField idText, personIdText, titleText, codeText, teacherText, unitText, searchTeacherText, searchUnitText;

    @FXML
    private DatePicker startDate;

    @FXML
    private Button saveButton,editButton,deleteButton;

    @FXML
    private TableView<Lesson> lessonTable;

    @FXML
    private TableColumn<Lesson, Integer> idColumn, personIdColumn,codeColumn;

    @FXML
    private TableColumn<Lesson, String> titleColumn, teacherColumn, unitColumn ;

    @FXML
    private TableColumn<Lesson, LocalDate> startDateColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"error loading data " + e.getMessage(), ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Lesson lesson=Lesson
                        .builder()
                        .title(titleText.getText())
                        .teacher(teacherText.getText())
                        .unit(unitText.getText())
                        .startDateTime(startDate.getValue())
                        .build();
                LessonService.getService().save(lesson);
                System.out.println("Lesson saved successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"saved successfully\n"+ lesson, ButtonType.OK);
                alert.show();
                resetForm();
            }catch(Exception e) {
                System.out.println("Lesson save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR,"Lesson save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }

        });

        editButton.setOnAction(event -> {
            try {
                Lesson lesson=Lesson
                        .builder()
                        .id(Integer.parseInt(idText.getText()))
                        .title(titleText.getText())
                        .teacher(teacherText.getText())
                        .unit(unitText.getText())
                        .startDateTime(startDate.getValue())
                        .build();
                LessonService.getService().edit(lesson);
                System.out.println("Lesson edited successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"edited successfully\n"+ lesson, ButtonType.OK);
                alert.show();
                resetForm();
            }catch(Exception e) {
                System.out.println("Lesson edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR,"Lesson edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }

        });

        deleteButton.setOnAction(event -> {
            try {

                LessonService.getService().delete(Integer.valueOf(idText.getText()));
                System.out.println("Lesson deleted successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"deleted successfully\n"+ idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            }catch(Exception e) {
                System.out.println("Lesson delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR,"Lesson delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }

        });

        searchTeacherText.setOnKeyReleased(event ->{searchByTeacherAndUnit();});

        searchUnitText.setOnKeyReleased(event -> {
            searchByTeacherAndUnit();
        });

        lessonTable.setOnMouseReleased(event -> {
            selectFromTable();
        });

        lessonTable.setOnKeyReleased(event -> {
            selectFromTable();
        });
    }

    private void resetForm() throws Exception {
        idText.clear();
        personIdText.clear();
        titleText.clear();
        codeText.clear();
        teacherText.clear();
        unitText.clear();

        startDate.setValue(LocalDate.now());

        //showDateOnTable(LessonService.getService().findAll());
    }

    private void showDateOnTable(List<Lesson> lessonList) {
        ObservableList<Lesson> observableList =FXCollections.observableList(lessonList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));

        lessonTable.setItems(observableList);
    }

    public void selectFromTable(){
        try {
            Lesson lesson = lessonTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(lesson.getId()));
            personIdText.setText(String.valueOf(lesson.getPersonId()));
            titleText.setText(lesson.getTitle());
            codeText.setText(String.valueOf(lesson.getCode()));
            teacherText.setText(lesson.getTeacher());
            unitText.setText(lesson.getUnit());
            startDate.setValue(lesson.getStartDateTime());
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"error loading data " + e.getMessage(), ButtonType.OK);
            alert.show();
        }

    }

    public void searchByTeacherAndUnit(){
        try {
           showDateOnTable(LessonService.getService().findByTeacherAndUnit(searchTeacherText.getText(),searchUnitText.getText()));
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"error loading data", ButtonType.OK);
            alert.show();
        }
    }
}
