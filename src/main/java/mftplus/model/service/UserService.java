package mftplus.model.service;

import lombok.Getter;
import mftplus.controller.exception.DuplicateUsernameException;
import mftplus.controller.exception.UserNotFoundException;
import mftplus.model.entity.User;
import mftplus.model.repository.UserRepository;

import java.util.List;


public class UserService implements Service<User, Integer> {
    @Getter
    public static UserService service = new UserService();

    private UserService() {
    }

    @Override
    public void save(User user) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            if (userRepository.findByUsername(user.getUsername()) == null) {
                userRepository.save(user);
            } else {
                throw new DuplicateUsernameException();
            }
        }
    }

    @Override
    public void edit(User user) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            if (userRepository.findByUsername(user.getUsername()) == null) {
                userRepository.edit(user);
            } else {
                throw new DuplicateUsernameException();
            }
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            userRepository.delete(id);
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findAll();
        }
    }

    @Override
    public User findById(Integer id) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findById(id);
        }
    }

    public User findByPersonId(int personId) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findByPersonId(personId);
        }
    }


    public User findUsersByUsername(String username) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            } else {
                throw new UserNotFoundException();
            }
        }
    }

    public User findUsersByUsernameAndPassword(String username, String password) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            User user = userRepository.findByUsernameAndPassword(username, password);
            if (user != null) {
                return user;
            } else {
                throw new UserNotFoundException();
            }
        }
    }
}

