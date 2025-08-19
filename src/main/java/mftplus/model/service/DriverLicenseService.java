package mftplus.model.service;
import lombok.Getter;
import mftplus.model.entity.DriverLicense;
import mftplus.model.repository.DriverLicenseRepository;



import java.util.List;

public class DriverLicenseService implements Service<DriverLicense, Integer> {

    @Getter
    private static DriverLicenseService service = new DriverLicenseService();

    private DriverLicenseService() {
    }


   @Override
   public void save(DriverLicense driverLicense) throws Exception {
        try (DriverLicenseRepository driverLicenseRepository = new DriverLicenseRepository()) {
           driverLicenseRepository.save(driverLicense);
      }
   }

    @Override
    public void edit(DriverLicense driverLicense) throws Exception {
      try (DriverLicenseRepository driverLicenseRepository = new DriverLicenseRepository()) {
            driverLicenseRepository.edit(driverLicense);
        }
   }

   @Override
    public void delete(Integer id) throws Exception {
        try (DriverLicenseRepository driverLicenseRepository = new DriverLicenseRepository()) {
            driverLicenseRepository.delete(id);
       }
    }

    @Override
    public List<DriverLicense> findAll() throws Exception {
        try (DriverLicenseRepository driverLicenseRepository = new DriverLicenseRepository()) {
           return driverLicenseRepository.findAll();
        }
    }

    @Override
   public DriverLicense findById(Integer id) throws Exception {
       try (DriverLicenseRepository driverLicenseRepository = new DriverLicenseRepository()) {
           return driverLicenseRepository.findById(id);
       }
   }
}
