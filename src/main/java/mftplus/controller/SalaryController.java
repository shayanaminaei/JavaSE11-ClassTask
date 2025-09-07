package mftplus.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Salary;
import mftplus.model.entity.enums.EmployeeType;
import mftplus.model.service.PersonService;
import mftplus.model.service.SalaryService;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
@Log4j2
public class SalaryController implements Initializable {
    @FXML
    private TextField idText, personIdText, payPerHourText, searchIdText, searchTypeText;
    @FXML
    private TextField weeklyHourText;
    @FXML
    private DatePicker startDate, endDate;
    @FXML
    private ComboBox<EmployeeType> employeeTypeCombo;
    @FXML
    private Button saveButton, editButton, deleteButton;
    @FXML
    private TableView<Salary> salaryTable;
    @FXML
    private TableColumn<Salary, Integer> idColumn, personIdColumn, weeklyHourColumn, payPerHourColumn;
    @FXML
    private TableColumn<Salary, Date> startDateColumn, endDateColumn;
    @FXML
    private TableColumn<Salary, EmployeeType> employeeTypeColumn;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
            setupEventHandlers();
            log.info("Form Initialized Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "error loading data " + e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }
    private void setupEventHandlers() {
        saveButton.setOnAction(event -> {
            try {
                Salary salary =
                        Salary
                                .builder()
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .weeklyHour(Integer.parseInt(weeklyHourText.getText()))
                                .payPerHour(Integer.parseInt(payPerHourText.getText()))
                                .startDate(startDate.getValue())
                                .endDate(endDate.getValue())
                                .employeeType(employeeTypeCombo.getValue())
                                .build();
                SalaryService.getService().save(salary);
                log.info("Salary Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Salary Saved Successfully\n" + salary, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Salary Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Salary Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        editButton.setOnAction(event -> {
            try {
                Salary salary =
                        Salary
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .person(PersonService.getService().findById(Integer.parseInt(personIdText.getText())))
                                .weeklyHour((Integer.parseInt(weeklyHourText.getText())))
                                .payPerHour(Integer.parseInt(payPerHourText.getText()))
                                .startDate(startDate.getValue())
                                .endDate(endDate.getValue())
                                .employeeType(employeeTypeCombo.getValue())
                                .build();

                SalaryService.getService().edit(salary);
                log.info("Salary Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Salary Edited Successfully\n" + salary, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Salary Edit F ailed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Salary Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        deleteButton.setOnAction(event -> {
            try {
                SalaryService.getService().delete(Integer.valueOf(idText.getText()));
                log.info("Salary deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Salary deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Salary delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Salary delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        salaryTable.setOnMouseClicked(event -> {
            selectFromTable();
        });
        salaryTable.setOnKeyReleased(event -> {
            selectFromTable();
        });
    }
    private void resetForm() throws Exception {
        idText.clear();
        personIdText.clear();
        payPerHourText.clear();
        startDate.setValue(LocalDate.of(2025, 1, 1));
        endDate.setValue(LocalDate.now());

        for (EmployeeType employeeType : EmployeeType.values()) {
            employeeTypeCombo.getItems().add(employeeType);
        }
        employeeTypeCombo.getSelectionModel().select(0);

        idText.setText(null);
        personIdText.setText(null);
        showDateOnTable(SalaryService.getService().findAll());

    }

    private void showDateOnTable(List<Salary> salaryList) throws Exception {
        ObservableList<Salary> observableList = FXCollections.observableList(salaryList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Salary, Integer>("id"));
        personIdColumn.setCellValueFactory(new PropertyValueFactory<Salary, Integer>("personId"));
        weeklyHourColumn.setCellValueFactory(new PropertyValueFactory<Salary, Integer>("weeklyHour"));
        payPerHourColumn.setCellValueFactory(new PropertyValueFactory<Salary, Integer>("payPerHour"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<Salary, Date>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Salary, Date>("endDate"));
        employeeTypeColumn.setCellValueFactory(new PropertyValueFactory<Salary, EmployeeType>("employeeType"));
        salaryTable.setItems(observableList);
    }
    public void selectFromTable() {
        try {
            Salary salary = salaryTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(salary.getId()));
            personIdText.setText(String.valueOf(salary.getPerson().getId()));
            payPerHourText.setText(String.valueOf(salary.getPayPerHour()));
            weeklyHourText.setText(String.valueOf(salary.getWeeklyHour()));
            startDate.setValue(salary.getStartDate());
            endDate.setValue(salary.getEndDate());
            employeeTypeCombo.getSelectionModel().select(salary.getEmployeeType());
        } catch (Exception e) {
            log.error("Error loading data " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading data " + e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }
    public void searchById() {
        try {
            if (searchIdText.getText() == null || searchIdText.getText().isEmpty()) {
                showDateOnTable(SalaryService.getService().findAll());
            } else {
                int id = Integer.parseInt(searchIdText.getText());
                Salary salary = SalaryService.getService().findById(id);
                if (salary != null) {
                    showDateOnTable(List.of(salary));
                    log.info("Salary found by ID: " + id);
                } else {
                    showDateOnTable(List.of());
                    log.info("No Salary found with ID: " + id);
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid numeric ID", ButtonType.OK);
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error searching by ID: " + e.getMessage(), ButtonType.OK);
            alert.show();
            log.error("Salary searchById failed for ID " + searchIdText.getText() + " " + e.getMessage());
        }
    }
    public void searchByType() {
        try {
            String typeText = searchTypeText.getText();
            if (typeText == null || typeText.isEmpty()) {
                showDateOnTable(SalaryService.getService().findAll());
            } else {
                EmployeeType type = EmployeeType.valueOf(typeText.toUpperCase());
                List<Salary> salaries = SalaryService.getService().findByEmployeeType(type);
                showDateOnTable(salaries);
                log.info("Salaries found for EmployeeType: " + type);
            }
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid EmployeeType", ButtonType.OK);
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error searching by EmployeeType: " + e.getMessage(), ButtonType.OK);
            alert.show();
            log.error("Salary searchByType failed for type " + searchTypeText.getText() + " " + e.getMessage());
        }
    }
}