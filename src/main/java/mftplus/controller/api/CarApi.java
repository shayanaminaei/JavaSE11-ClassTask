package mftplus.controller.api;

import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Car;
import mftplus.model.service.CarService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log4j
@Path("/Car")

public class CarApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCar( @QueryParam ("token")String token ) {
        if(token != null && token.equals("abc123")) {
        return Response.ok().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    public Response save(@QueryParam ("id")Integer id,@QueryParam ("name")String name, @QueryParam ("brand")String brand,
                         @QueryParam ("manDate") String manDate, @QueryParam ("color") String color, @QueryParam ("Plate") String  plate)
    {
       try{
           Car car = new Car();
           CarService.getService().save(car);
           log.info(car.toString());
           return Response.ok(car).build();
       } catch (Exception e) {
           log.error(e.getMessage());
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
       }
    }




}
