package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Person;
import mftplus.model.entity.User;
import mftplus.model.service.PersonService;

import java.time.LocalDate;
import java.util.List;
@Log4j
public class UserController {

    @Getter
    public static UserController userController = new UserController();

    private UserController() {
    }

    public void save(Person person, String username, String password, String nickname, LocalDate birthdate, boolean locked) throws Exception {
        try {
            User user =
                    User
                            .builder()
                            .person(person)
                            .username(username)
                            .password(password)
                            .nickname(nickname)
                            .birthdate(birthdate)
                            .locked(locked)
                            .build();
            PersonService.getService().save(person);
          log.info(System.out.println(" User  saved Successfully");
        } catch (Exception e) {
          log.error(System.out.println(" User  Save Failed" + e.getMessage());
        }
    }

    public void edit(int id, Person person, String username, String password, String nickname, LocalDate birthdate, boolean locked) throws Exception {
        try {
            User user =
                    User
                            .builder()
                            .person(person)
                            .username(username)
                            .password(password)
                            .nickname(nickname)
                            .birthdate(birthdate)
                            .locked(locked)
                            .build();
            PersonService.getService().save(person);
            System.out.println("Info: User Edited Successfully");
        } catch (Exception e) {
            System.out.println("Info: User Edited Failed" + e.getMessage());
        }
    }

    public void delete(Integer id) throws Exception {
        try {
            PersonService.getService().delete(id);
            System.out.println("Info: User Deleted Successfully");
        } catch (Exception e) {
            System.out.println("Info: User Delete Failed" + e.getMessage());
        }
    }

    public List<User> findAll() throws Exception {
        try {
            return PersonService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Info: User FindAll Failed" + e.getMessage());
            return null;
        }
    }

    public User findById(Integer id) throws Exception {
        try {
            return PersonService.getService().findById(id);
        } catch (Exception e) {
            System.out.println("Info: User FindById Failed" + "Failed" + e.getMessage());
            return null;
        }
    }

    public User findByPersonId(int personId) throws Exception {
        try {
            return PersonService.getService().findByPersonId( int personId);
        } catch (Exception e) {
            System.out.println("Info: User FindByPersonId Failed" + "" + "Failed" + e.getMessage());
            return null;
        }

    }

    public User findUsersByUsername(String username) throws Exception {
        try {
            return PersonService.getService().findUsersByUsername(String username);
        } catch (Exception e) {
            System.out.println("Info: User FindByUsername Failed" + e.getMessage());
        }
        return null;
    }

    public User findUsersByUsernameAndPassword(String username, String password) throws Exception {
        try {
            return PersonService.getService().findUsersByUsernameAndPassword(String username, String password);
        } catch (Exception e) {
            System.out.println("Info: UsersByUsernameAndPassword Failed" + e.getMessage());
        }
        return null;
    }
}


