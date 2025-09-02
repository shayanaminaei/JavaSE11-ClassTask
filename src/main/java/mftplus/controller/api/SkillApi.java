package mftplus.controller.api;

import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Skill;
import mftplus.model.service.SkillService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.time.LocalDate;

@Log4j
@Path("skills")
public class SkillApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSkill(@QueryParam("token") String token) {
        if (token != null && token.equals("abc123")) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    @POST
    public Response save(@QueryParam("id")Integer id, @QueryParam("title") String title, @QueryParam("institute") String institute,@QueryParam("duration") String duration, @QueryParam("registerDate") LocalDate registerDate, @QueryParam("score") Integer score ) {
        try {
            Skill skill = new Skill();
            SkillService.getService().save(skill);
            log.info(MessageFormat.format("{0} Saved Successfully", skill.toString()));
            return Response.ok(skill).build();
        }catch (Exception e){
            log.error("Save Failed"+e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
