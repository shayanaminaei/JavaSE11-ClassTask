package mftplus.controller.api;


import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Salary;
import mftplus.model.service.SalaryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log4j2
@Path("/Salary")
public class SalaryApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalary(@QueryParam("token") String token) {
        if (token != null && token.equals("abc123")) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    public Response save(
            @QueryParam("personId") int personId,
            @QueryParam("weeklyHour") int weeklyHour,
            @QueryParam("payPerHour") int payPerHour,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate,
            @QueryParam("employeeType") String employeeType,
            @QueryParam("token") String token
    ) {
        try {
            Salary salary = new Salary();
            SalaryService.getService().save(salary);
            log.info("Salary saved successfully");
            return Response.ok(salary).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }


    }
}
