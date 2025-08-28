import lombok.extern.log4j.Log4j;
import mftplus.controller.PropertyController;
import mftplus.model.entity.Person;
import mftplus.model.entity.Property;
import mftplus.model.entity.enums.Role;
import mftplus.model.service.PropertyService;

import java.time.LocalDateTime;


@Log4j
class PropertyTest {
    public static void main(String[] args) throws Exception {

        Person person1=
                Person.builder()
                        .id(1)
                        .name("ali")
                        .family("alizade")
                        .role(Role.customer)
                        .status(true)
                        .build();

        Person person2=
                Person.builder()
                        .id(1)
                        .name("ali")
                        .family("alizade")
                        .role(Role.admin)
                        .status(false)
                        .build();

        Property property1=
                Property.builder()
                        .id(1)
                        .person(person1)
                        .name("ahmad")
                        .brand("Nike")
                        .serial("123")
                        .count(2)
                        .build();

        Property property2=
                Property.builder()
                        .id(2)
                        .person(person2)
                        .name("ali")
                        .brand("Adidas")
                        .serial("123")
                        .count(3)
                        .build();


        //       log.info("Starting Property Test");
//        log.error("error saving");
//        UNIT TEST
//        تست واحد
//        PropertyController.getController().save(1,"arian","nike","1234",2,null);
//        PropertyController.getController().edit(1,1,"arian","nike","1234",2,null);
//        PropertyController.getController().delete(3);
//        System.out.println(PropertyController.getController().findAll());
//        System.out.println(PropertyController.getController().findById(6));
//        System.out.println(PropertyController.getController().findByName("a"));


//        Property property = Property
//                .builder()
//                .id(1)
//                .personId(1)
//                .name("arian")
//                .brand("nike")
//                .serial("1234")
//                .count(2)
//                .dateTime(null)
//                .build();
//
//        PropertyService.getService().save(property);
        //       Repository Test Passed
        //      try with resource
//       try (PropertyRepository propertyRepository = new propertyRepository()) {

//        test passed
//        PropertyRepository.save(property);

//        test passed
//        PropertyRepository.edit(property);

//        test passed
//        propertyRepository.delete(1);

//        test passed
//        System.out.println(propertyRepository.findAll());

//        test passed
//        System.out.println(propertyRepository.findById(1));
//        System.out.println(propertyRepository.findById(5));

//        test passed
//        System.out.println(propertyRepository.findByName("r", ""));
//        }
    }
}



