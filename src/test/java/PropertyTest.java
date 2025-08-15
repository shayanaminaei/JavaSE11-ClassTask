import mftplus.model.entity.Property;
import mftplus.model.service.PropertyService;

import java.sql.Timestamp;
import java.time.Instant;

public class PropertyTest {
    public static void main(String[] args) throws Exception {
        Property property = Property
                .builder()
                .id(1)
                .personId(1)
                .name("arian")
                .brand("nike")
                .serial("1234")
                .count(2)
                .dateTime(Timestamp.from(Instant.from(Instant.now())))
                .build();

        PropertyService.getService().save(property);
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
//        System.out.println(propertyRepository.findById(7));

//        test passed
//        System.out.println(propertyRepository.findByName("r", ""));
//        }
    }
}



