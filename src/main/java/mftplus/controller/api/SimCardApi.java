package mftplus.controller.api;

import mftplus.model.entity.SimCard;
import mftplus.model.entity.enums.SimCardOperator;
import mftplus.model.entity.enums.Title;
import mftplus.model.service.SimCardService;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;


@Log4j2
@Path("/simCard")
public class SimCardApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimCard(@QueryParam("token") String token) {
        if (token != null && token.equals("abs123")) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    public Response save(@QueryParam("id") int id,
                         @QueryParam("title") Title title,
                         @QueryParam("numbers") String numbers,
                         @QueryParam("simCardOperator") SimCardOperator simCardOperator,
                         @QueryParam("registerDate") LocalDate registerDate,
                         @QueryParam("status") boolean status
    ) {

        SimCard simCard = new SimCard();
        try {
            SimCardService.getService().save(simCard);
            log.info("save success.");
            return Response.ok(simCard).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
}
