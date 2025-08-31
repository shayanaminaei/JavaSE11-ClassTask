import mftplus.model.entity.Users;
import mftplus.model.repository.UsersRpository;
import mftplus.model.service.PersonService;
import mftplus.model.service.UsersService;
//import mftplus.model.repository.UsersRepository;

import java.time.LocalDate;

public class UsersTest {
    public static void main(String[] args) throws Exception {

        Users users = Users

                .builder()
              //  .id()
                .name("Hedie")
                .family("khdaei")
                .username("hedie")
                .password("hedie123")
                .nickname("hedii")
                .locked(true)
                .registerDate(LocalDate.of(1380,07,25))
                .build();

        // PersonService.getService().save(users);



      //  try (UsersRpository usersRpository = new UsersRpository()) {
        //    usersRpository.save(users);



              // Users users1 =
                  //     Users

                   //     .builder()
                    //    .id(2)
                    //    .person(PersonService.getService().findById(1))
                  //      .username("hedie")
                  //      .password("123")
                  //      .nickname("hedii")
                  //   .locked(true)
                   //     .registerDate(LocalDate.of(1404, 10, 1))

                   //     .build();











    }
}
