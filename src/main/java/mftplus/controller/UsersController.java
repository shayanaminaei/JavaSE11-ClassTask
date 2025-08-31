package mftplus.controller;

import lombok.Getter;
import mftplus.model.entity.Person;
import mftplus.model.entity.Users;
import mftplus.model.service.PersonService;

import java.time.LocalDate;
import java.util.List;

public static class UsersController {
        @Getter
        public static UsersController controller = new UsersController();
        private UsersController() {
        }

        public void save(int id, String usename, String password, String nickname, boolean locked , LocalDate birthdate) throws Exception {
            try {
                Users users =
                        Users
                                .builder()
                                .person(PersonService.getService().findById(id))
                                .username(usename)
                                .password(password)
                                .nickname(nickname)
                                .locked(locked)
                                .registerDate(birthdate)
                                .build();
                PersonService.getService().save(users);
                System.out.println("save ok");
            } catch (Exception e) {
                System.out.println("save error");
        }
           }



        public void edit(int id, String usename, String password, String nickname, boolean locked , LocalDate birthdate) throws Exception {
            try {
                Users users =
                        Users
                                .builder()
                                .person(PersonService.getService().findById(id))
                                .username(usename)
                                .password(password)
                                .nickname(nickname)
                                .locked(locked)
                                .registerDate(birthdate)
                                .build();
                PersonService.getService().save(users);
                System.out.println("  edit    save ok");
            } catch (Exception e) {
                System.out.println("   edit    save error");
            }

        }


        }


        public void delete(Integer id) throws Exception {
            try {

                PersonService.getService().save(id);
                System.out.println("  DELITED    save ok");
            } catch (Exception e) {
                System.out.println("   DELITED    save error");
            }
        }


        public List<Person> findAll() throws Exception {
            try {

               return PersonService.getService().findAll();
            } catch (Exception e) {
                System.out.println("Error : Person  FindAll  Failed"+ e.getMessage());
                return null;
            }


        }


        public Person findById(Integer id) throws Exception {
            try {

                return PersonService.getService().findById(id);
            } catch (Exception e) {
                System.out.println(" Error:   Person FindId Failed" + e.getMessage());
                return null;

            }

            public List<Users> findByPersonId ;

                try {

                    return PersonService.getService().findById(id);
                } catch (Exception e) {
                    System.out.println(" Error:   Person FindId Failed" + e.getMessage());
                    return null;

                }



            }
        }

void main() {
}






