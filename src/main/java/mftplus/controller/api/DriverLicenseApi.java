package mftplus.controller.api;

import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.DriverLicense;
import mftplus.model.service.DriverLicenseService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;


@Log4j2
@Path("/DriverLicenses")
public class DriverLicenseApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@QueryParam("token") String token){
        if (token != null && token.equals("abc123")){
            return Response.ok().build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    public Response save(@QueryParam("id")Integer id,
                         @QueryParam("serial") String serial,
                         @QueryParam("driverLicenseType") String driverLicenseType,
                         @QueryParam("city") LocalDate city,
                         @QueryParam("registerDate") LocalDate registerDate,
                         @QueryParam("expireDate") String expireDate){
        try {
            DriverLicense driverLicense = new DriverLicense();
            DriverLicenseService.getService().save(driverLicense);
            log.info("Saved Successfully");
            return Response.ok(driverLicense).build();
        }catch (Exception e){
       log.error("Save Failed"+e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
