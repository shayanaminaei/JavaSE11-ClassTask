package mftplus.model.repository;

import mftplus.model.entity.Cars;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarsRepository {

    private static final CarsRepository instance = new CarsRepository();
    private final List<Cars> carsList = new ArrayList<>();
    private int lastId = 0; // برای ID اتوماتیک

    private CarsRepository() {}

    public static CarsRepository getInstance() {
        return instance;
    }

    // اضافه کردن ماشین با ID اتوماتیک
    public void addCar(Cars car) {
        lastId++;
        car.setId(lastId);
        carsList.add(car);
        System.out.println("✅ Car added: " + car);
    }

    public List<Cars> findAll() {
        return carsList;
    }

    public Optional<Cars> findById(int id) {
        return carsList.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public Optional<Cars> findByPlate(String plate) {
        return carsList.stream()
                .filter(c -> c.getPlate().equalsIgnoreCase(plate))
                .findFirst();
    }

    public boolean deleteById(int id) {
        return carsList.removeIf(c -> c.getId() == id);
    }

    public List<Cars> findByBrand(String brand) {
        return carsList.stream()
                .filter(c -> c.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }
}
