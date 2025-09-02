package mftplus.controller.api;



import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Marriage;
import mftplus.model.entity.enums.AliveStatus;
import mftplus.model.service.MarriageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Log4j
@Path("/marriages")
public class MarriageApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarriage(@QueryParam("token") String token) {
        if (token != null && token.equals("abc123")) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    public Response saveMarriage(@QueryParam("id") Integer id,
                                 @QueryParam("name") String name,
                                 @QueryParam("family") String family,
                                 @QueryParam("isAlive") AliveStatus status,
                                 @QueryParam("marriageDate") LocalDate marriageDate,
                                 @QueryParam("children") String children) {

        try {
            Marriage marriage = new Marriage();
            MarriageService.getService().save(marriage);
            log.info("Marriage saved successfully");
            return Response.ok(marriage).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}


