package mftplus.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Person;
import mftplus.model.entity.User;
import mftplus.model.entity.enums.UserName;
import mftplus.model.service.PersonService;
import mftplus.model.service.UserService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class UserController implements Initializable {
    @FXML
    private TextField idTextField,personTextField,passwordTextField,nicknameTextField,regesterdateTextField,lockedTextField;
    @FXML
    private DatePicker registerDatePicker;
    @FXML
    private ComboBox<UserName> usernameComboBox;
    @FXML
    private ComboBox<UserName>userNameComboBox;
    @FXML
    private RadioButton enableRadio,disableRadio;
    @FXML
    private Button saveButton,editButton,deleteButton;
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, Integer> personColumn;
    @FXML
    private  TableColumn<User, String> usernameColumn;
    @FXML
    private  TableColumn<User, String> passwordColumn;
    @FXML
    private  TableColumn<User, LocalDate> registerDateColumn;
    @FXML
    private  TableColumn<User, Boolean> lockedColumn;
    @FXML
    private TableColumn<User, String> nicknameColumn;
    @FXML
    private  TableColumn<User, LocalDate> birthdateColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (UserName userName : UserName.values()) {

            userNameComboBox.getItems().add(userName);
        }
        userNameComboBox.getSelectionModel().select(0);

        enableRadio.setSelected(true);


    }




    public void save(Person person, UserName username, String password, String nickname, LocalDate birthdate, boolean locked) throws Exception {
        try {
            User user =
                    User
                            .builder()
                            //.id(id)
                            .person(person)
                            .username(username)
                            .password(password)
                            .nickname(nickname)
                            .registerDate(birthdate)
                            .locked(locked)
                            .build();
            UserService.getService().save(user);
            System.out.println("Info: UserName Save Successfully");
        } catch (Exception e) {
            System.out.println("Info: UserName Save Failed" + e.getMessage());
        }
    }

    public void edit(int id, Person person, UserName username, String password, String nickname, LocalDate birthdate, boolean locked) throws Exception {
        try {
            User user =
                    User
                            .builder()
                            .id(id)
                            .person(person)
                            .username(username)
                            .password(password)
                            .nickname(nickname)
                            .registerDate(birthdate)
                            .locked(locked)
                            .build();
            UserService.getService().save(user);
            System.out.println("Info: UserName Edited Successfully");
        } catch (Exception e) {
            System.out.println("Info: UserName Edited Failed" + e.getMessage());
        }
    }

    public void delete(Integer id) throws Exception {
        try {
            PersonService.getService().delete(id);
            System.out.println("Info: UserName Deleted Successfully");
        } catch (Exception e) {
            System.out.println("Info: UserName Delete Failed" + e.getMessage());
        }
    }

    public List<User> findAll() throws Exception {
        try {
            return UserService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Info: UserName FindAll Failed" + e.getMessage());
            return null;
        }
    }

    public User findById(Integer id) throws Exception {
        try {
            return UserService.getService().findById(id);
        } catch (Exception e) {
            System.out.println("Info: UserName FindById Failed" + "Failed" + e.getMessage());
            return null;
        }
    }

    public User findByPersonId(int personId) throws Exception {
        try {
            return UserService.getService().findByPersonId( personId);
        } catch (Exception e) {
            System.out.println("Info: UserName FindByPersonId Failed" + e.getMessage());
            return null;
        }

    }




    public User findUsersByUsername(String username) throws Exception {
        try {
            return UserService.getService().findUsersByUsername(username);
        } catch (Exception e) {
            System.out.println("Info: UserName FindByUsername Failed" + e.getMessage());
        }
        return null;
    }

    public User findUsersByUsernameAndPassword(String username, String password) throws Exception {
        try {
            return UserService.getService().findUsersByUsernameAndPassword(username, password);
        } catch (Exception e) {
            System.out.println("Info: UsersByUsernameAndPassword Failed" + e.getMessage());
        }
        return null;

    }


}


