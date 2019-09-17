package org.onosproject.MyTest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.onosproject.rest.AbstractWebResource;

@Path("ChenPoTest")
public class TestWebResource extends AbstractWebResource {
    
    /**
     * Get hello world greeting.
     *
     * @return 200 OK
     */
    @GET
    @Path("")
    public Response hello() {
        ObjectNode node = mapper().createObjectNode().put("hello", "world");
        return ok(node).build();
    }

    public Response sayHI(@FormParam("user") String user, @FormParam("pass") String pass){
        String msg = "Hello";
        return Response.ok(msg).build();
    }
}