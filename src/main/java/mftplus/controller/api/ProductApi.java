package mftplus.controller.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/products")
public class ProductApi {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getProduct(){
        return "man kala hastam";
    }

}
