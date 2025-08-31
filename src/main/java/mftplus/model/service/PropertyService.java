package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Property;
import mftplus.model.repository.PropertyRepository;

import java.util.List;

public class PropertyService implements Service<Property, Integer> {
    @Getter
    private static PropertyService service = new PropertyService();

    private PropertyService() {
    }

    @Override
    public void save(Property property) throws Exception {
        try (PropertyRepository propertyRepository = new PropertyRepository()) {
            propertyRepository.save(property);
        }
    }

    @Override
    public void edit(Property property) throws Exception {
        try (PropertyRepository propertyRepository = new PropertyRepository()) {
            propertyRepository.edit(property);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (PropertyRepository propertyRepository = new PropertyRepository()) {
            propertyRepository.delete(id);
        }
    }

    public List<Property> findAll(String text) throws Exception {
        try (PropertyRepository propertyRepository = new PropertyRepository()) {
            return propertyRepository.findAll();
        }
    }

    @Override
    public Property findById(Integer id) throws Exception {
        try (PropertyRepository propertyRepository = new PropertyRepository()) {
            return propertyRepository.findById(id);
        }
    }

    public List<Property> findByName(String name) throws Exception {
        try (PropertyRepository propertyRepository = new PropertyRepository()) {
            return propertyRepository.findByName(name);
        }

    }
}

