import mftplus.model.entity.Cars;
import mftplus.model.service.CarsService;

import java.time.LocalDate;
import java.util.List;

public class CarsTest {
    public static void main(String[] args) throws Exception {
        CarsService carsService = CarsService.getService();

        // 1️⃣ اضافه کردن ماشین‌ها
        Cars car1 = Cars.builder()
                .id(1)
                .personId(101)
                .carName("C-Class")
                .carModel("2020")
                .brand("Benz")
                .manDate(LocalDate.of(2020, 3, 15))
                .color("Black")
                .plate("IR123BENZ")
                .build();

        Cars car2 = Cars.builder()
                .id(2)
                .personId(102)
                .carName("X5")
                .carModel("2021")
                .brand("BMW")
                .manDate(LocalDate.of(2021, 5, 10))
                .color("White")
                .plate("IR456BMW")
                .build();

        carsService.save(car1);
        carsService.save(car2);

        // 2️⃣ نمایش همه ماشین‌ها
        System.out.println("All Cars:");
        List<Cars> allCars = carsService.findAll();
        allCars.forEach(System.out::println);

        // 3️⃣ پیدا کردن ماشین با ID
        System.out.println("\nFind by ID 1:");
        System.out.println(carsService.findById(1));

        // 4️⃣ پیدا کردن ماشین با Plate
        System.out.println("\nFind by Plate IR456BMW:");
        System.out.println(carsService.findByPlate("IR456BMW"));

        // 5️⃣ ویرایش ماشین
        car2.setColor("Blue");
        carsService.edit(car2);
        System.out.println("\nAfter editing color of BMW:");
        System.out.println(carsService.findById(2));

        // 6️⃣ حذف ماشین
        carsService.delete(1);
        System.out.println("\nAll Cars after deleting ID 1:");
        carsService.findAll().forEach(System.out::println);

        // 7️⃣ تست متد کمکی findByBrand
        System.out.println("\nCars with brand BMW:");
        carsService.findByBrand("BMW").forEach(System.out::println);
    }
}
