package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Users;
//import mftplus.model.repository.UsersRepository;
import mftplus.model.repository.UsersRpository;

import java.util.List;


    public class UsersService implements Service<Users,Integer> {
        @Getter
        public static UsersService singleton = new UsersService();

        public UsersService() {
        }


        @Override
        public void save(Integer users) throws Exception {
            try (UsersRpository usersRpository = new UsersRpository()) {
                usersRpository.save(users);
            }

        }

        @Override
        public void edit(Users users) throws Exception {
            try (UsersRpository usersRpository = new UsersRpository()) {
                usersRpository.edit(users);
            }

        }

        @Override
        public void delete(Integer id) throws Exception {
            try (UsersRpository usersRpository = new UsersRpository()) {
                usersRpository.delete(id);
            }

        }

        @Override
        public List<Users> findAll() throws Exception {
            try (UsersRpository usersRpository = new UsersRpository()) {
                return usersRpository.findAll();
            }

        }

        @Override
        public Users findById(Integer id) throws Exception {
            try (UsersRpository usersRpository = new UsersRpository()) {
                return usersRpository.findById(id);

        }
    }

        public List<Users> findByPersonId(int personId) throws Exception {
            try (UsersRpository usersRpository = new UsersRpository()) {
               return usersRpository.findByPersonId(personId);

            }
        }


        public List<Users> findUsersByNumber(String number) throws Exception {
            try (UsersRpository usersRpository = new UsersRpository()) {
                return usersRpository.findUsersByNumber(number);
            }
        }


    }

