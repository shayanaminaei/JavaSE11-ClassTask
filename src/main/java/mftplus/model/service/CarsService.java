package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Cars;
import mftplus.model.repository.CarsRepository;

import java.util.List;

public class CarsService {

    @Getter
    private static CarsService service = new CarsService();
    private final CarsRepository carsRepository = CarsRepository.getInstance();

    private CarsService() {}

    // ذخیره ماشین
    public void save(Cars car) throws Exception {
        carsRepository.addCar(car);
    }

    // ویرایش ماشین
    public void edit(Cars car) throws Exception {
        Cars found = carsRepository.findById(car.getId()).orElse(null);
        if (found != null) {
            // بروزرسانی فیلدها
            found.setPersonId(car.getPersonId());
            found.setCarName(car.getCarName());
            found.setCarModel(car.getCarModel());
            found.setBrand(car.getBrand());
            found.setManDate(car.getManDate());
            found.setColor(car.getColor());
            found.setPlate(car.getPlate());
        }
    }

    // حذف ماشین
    public void delete(int id) throws Exception {
        carsRepository.deleteById(id);
    }

    // همه ماشین‌ها
    public List<Cars> findAll() throws Exception {
        return carsRepository.findAll();
    }

    // پیدا کردن ماشین با ID
    public Cars findById(int id) throws Exception {
        return carsRepository.findById(id).orElse(null);
    }

    // پیدا کردن ماشین با Plate
    public Cars findByPlate(String plate) throws Exception {
        return carsRepository.findByPlate(plate).orElse(null);
    }

    // متد کمکی پیدا کردن بر اساس برند
    public List<Cars> findByBrand(String brand) throws Exception {
        return carsRepository.findByBrand(brand);
    }
}
