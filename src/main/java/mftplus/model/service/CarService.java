package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Car;
import mftplus.model.entity.Person;
import mftplus.model.repository.CarRepository;
import mftplus.model.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;

public class CarService implements Service<Car, Integer> {
    @Getter
    private static CarService service = new CarService();

    private CarService() {

    }
    @Override
    public void save(Car car) throws Exception {
        try (CarRepository carRepository = new CarRepository()) {
            if(car.getManDate().isBefore(LocalDate.of(2015,1,1))) {
                carRepository.save(car);
            }else{
                throw new Exception("Car's ManDate is not acceptable !");
            }
        }
    }

    @Override
    public void edit(Car car) throws Exception {
        try (CarRepository carRepository = new CarRepository()){
            carRepository.edit(car);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        try (CarRepository carRepository = new CarRepository()){
            carRepository.delete(id);
        }
    }

    @Override
    public List<Car> findAll() throws Exception {
        try (CarRepository carRepository = new CarRepository()) {
            return carRepository.findAll();
        }
    }

    @Override
    public Car findById(Integer id) throws Exception {
        try (CarRepository carRepository = new CarRepository()) {
            return carRepository.findById(id);
        }
    }



    public List<Car> findByPersonId(Integer personId) throws Exception {
        try (CarRepository carRepository = new CarRepository()) {
            return carRepository.findByPersonId(personId);
        }
    }

    public List<Car> findByBrand ( String brand) throws Exception {
        try (CarRepository carRepository = new CarRepository()) {
            return carRepository.findByBrand(brand);
        }
    }

}




