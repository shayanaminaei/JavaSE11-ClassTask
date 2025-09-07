package mftplus.controller.api;


import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.Medical;
import mftplus.model.service.MedicalService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.time.LocalDate;

@Log4j2
@Path("medical")
public class MedicalApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedical(@QueryParam("token")String token){
        if (token != null && token.equals("abc123")){
            return Response.ok().build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
    @POST
    public Response saveMedical(@QueryParam("id")Integer id, @QueryParam("disease") String disease, @QueryParam("medicine")String medicine, @QueryParam("visitDate") LocalDate visitDate, @QueryParam("status")boolean status ){
        try {
            Medical medical = new Medical();
            MedicalService.getService().save(medical);
            log.info(MessageFormat.format("Medical {0} has been saved", medical.toString()));
            return Response.status(Response.Status.CREATED).build();
        }catch (Exception e){
            log.error( "save failed"+ e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
