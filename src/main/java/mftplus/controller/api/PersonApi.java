package mftplus.controller.api;

import mftplus.model.entity.Person;

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

//    @POST
//    public String save(@QueryParam("name") String name, @QueryParam("family") String family){
//        Person person = new Person(1, name, family);
//        return person.toString();
//    }
}
