package org.onosproject.MyTest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.onosproject.rest.AbstractWebResource;

@Path("ChenPoTest")
public class TestWebResource extends AbstractWebResource {

    @POST
    @Path("/hello")
    public Response sayHI(@FormParam("user") String user, @FormParam("pass") String pass){
        String msg = "Hello";
        return Response.ok(msg).build();
    }
}