package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Car;
import mftplus.model.service.CarService;

import java.time.LocalDate;
import java.util.List;

@Log4j
public class CarController {
    @Getter
    private static CarController controller = new CarController();
    private CarController() {

    }
    public void save( int person_id, String name, String brand, LocalDate manDate,String color, String plate)throws Exception {
        try{
            Car car = Car
                    .builder()
                    .personId(person_id)
                    .name(name)
                    .brand(brand)
                    .manDate(manDate)
                    .color(color)
                    .plate(plate)
                    .build();
            CarService.getService().save(car);
            log.info("Car saved Successfully");
        } catch (Exception e){
            log.error("Car Save Failed "+ e.getMessage());
        }
    }

    public void edit( int person_id, String name, String brand, LocalDate manDate,String color, String plate)throws Exception {
        try{
            Car car = Car
                    .builder()
                    .personId(person_id)
                    .name(name)
                    .brand(brand)
                    .manDate(manDate)
                    .color(color)
                    .plate(plate)
                    .build();
            CarService.getService().edit(car);
            log.info("Car Edited Successfully");
        } catch (Exception e){
            log.error("Car Edit Failed " + e.getMessage());
        }
    }

    public void delete( Integer id )throws Exception {
        try{
            CarService.getService().delete(id);
            log.info("Car Deleted Successfully");
        } catch (Exception e){
            log.error("Car Delete Failed " + e.getMessage());

        }
    }

    public List<Car> findAll()throws Exception{
        try {
            List<Car> carList = CarService.getService().findAll();
            log.info("Cars findAll");
            return carList;
        } catch (Exception e) {
            log.error("Cars findAll Failed " + e.getMessage());
            return null;
        }
    }

    public Car findById( Integer id )throws Exception {
        try {
            Car car = CarService.getService().findById(id);
            log.info("Car findById: "+ id);
            return car;
        } catch (Exception e) {
            log.error("Car FindById "+id+" Failed "+e.getMessage());
            return null;
        }
    }

    public List<Car> findByBrand(String brand)throws Exception {
        try {
            List<Car> carList = CarService.getService().findByBrand(brand);
            log.info("Cars findByBrand: " + brand);
            return carList;
        } catch (Exception e) {
            log.error("Cars findByBrand "+ brand +" Failed "+e.getMessage());
            return null;
        }
    }


}
