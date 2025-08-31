package mftplus.controller.api;

import mftplus.model.entity.Person;
import mftplus.model.service.PersonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/persons")
public class PersonApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@QueryParam("token") String token){
        if (token != null && token.equals("abc123")){
            return Response.ok().build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    public Response save(@QueryParam("name") String name, @QueryParam("family") String family){
        try {
            Person person = null; // new Person(1, name, family);
            PersonService.getService().save(person);
//            log.info();
            return Response.ok(person).build();
        }catch (Exception e){
//            log.error();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
