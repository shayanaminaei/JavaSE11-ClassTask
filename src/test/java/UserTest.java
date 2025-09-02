import mftplus.model.service.UserService;
//import mftplus.model.repository.UsersRepository;


public class UserTest {
    public static void main(String[] args) throws Exception {

        System.out.println(UserService.getService().findUsersByUsernameAndPassword("ahmad", "ahmad123"));


//        User user = User
//
//                .builder()
//              //  .id()
//                .username("hedie")
//                .password("hedie123")
//                .nickname("hedii")
//                .locked(true)
//                .registerDate(LocalDate.of(1380,07,25))
//                .build();

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
