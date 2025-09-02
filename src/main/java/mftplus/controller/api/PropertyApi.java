package mftplus.controller.api;

import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Property;
import mftplus.model.service.PropertyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Log4j

@Path("/properties")
public class PropertyApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProperty(@QueryParam("token") String token) {
        if (token != null && token.equals("abc123")) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    @POST
    public Response save(@QueryParam("id")Integer id,
                         @QueryParam("name") String name,
                         @QueryParam("brand") String brand,
                         @QueryParam("serial") String serial,
                         @QueryParam("count") String count ){
        try {
            Property property = new Property();
            PropertyService.getService().save(property);
            log.info("Property Saved");
            return Response.ok(property).build();
        }catch (Exception e){
            log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
