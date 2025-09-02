package mftplus.controller.api;

import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Job;
import mftplus.model.service.JobService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Log4j
@Path("/jobs")
public class JobApi {
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
                         @QueryParam("organisation") String organisation,
                         @QueryParam("title") String title,
                         @QueryParam("startDate") LocalDate startDate,
                         @QueryParam("endDate") LocalDate endDate,
                         @QueryParam("description") String description){
        try {
            Job job = new Job();
            JobService.getService().save(job);
            log.info("Job saved successfully");
            return Response.ok(job).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    };
}
