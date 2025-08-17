package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Property;
import mftplus.model.service.PropertyService;


import java.time.LocalDateTime;
import java.util.List;

@Log4j

public class PropertyController {
    @Getter
    private static PropertyController controller = new PropertyController();

    private PropertyController() {
    }

    public void save(int personId, String name, String brand, String serial, int count, LocalDateTime dateTime) throws Exception {
        try {
            Property property =
                    Property
                            .builder()
                            .personId(personId)
                            .name(name)
                            .brand(brand)
                            .serial(serial)
                            .count(count)
                            .dateTime(dateTime)
                            .build();
            PropertyService.getService().save(property);
            log.info("Property saved");
        } catch (Exception e) {
            log.error("Property save failed" + e.getMessage());
        }
    }

    public void edit(int id, int personId, String name, String brand, String serial, int count, LocalDateTime dateTime) throws Exception {
        try {
            Property property =
                    Property
                            .builder()
                            .id(id)
                            .personId(personId)
                            .name(name)
                            .brand(brand)
                            .serial(serial)
                            .count(count)
                            .dateTime(dateTime)
                            .build();
            PropertyService.getService().edit(property);
            log.info("Property edited");
        } catch (Exception e) {
            log.error("Property edited failed" + e.getMessage());

        }
    }

    public void delete(Integer id) throws Exception {
        try {
            PropertyService.getService().delete(id);
            log.info("Property deleted");
        } catch (Exception e) {
            log.error("Property deleted failed" + e.getMessage());
        }
    }

    public List<Property> findAll() throws Exception {
        try {
            return PropertyService.getService().findAll();
        } catch (Exception e) {
            log.error("Property findAll failed" + e.getMessage());
            return null;
        }
    }

    public Property findById(int id) throws Exception {
        try {
            return PropertyService.getService().findById(id);
        } catch (Exception e) {
            log.error("Property findById failed" + id + "failed" + e.getMessage());
            return null;
        }
    }

    public List<Property> findByName(String name) throws Exception {
        try {
            return PropertyService.getService().findByName(name);
        } catch (Exception e) {
            log.error("Property findByName failed" + name + "failed" + e.getMessage());
            return null;

        }
    }
}
