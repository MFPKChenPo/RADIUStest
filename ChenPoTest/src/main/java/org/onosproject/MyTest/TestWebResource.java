package org.onosproject.MyTest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.onosproject.rest.AbstractWebResource;
import org.tinyradius.packet.AccessRequest;

@Path("ChenPoTest")
public class TestWebResource extends AbstractWebResource {
    
    /**
     * Get hello world greeting.
     *
     * @return 200 OK
     */
    @GET
    public Response hello() {
        ObjectNode node = mapper().createObjectNode().put("hello", "world");
        return ok(node).build();
    }
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response sayHI(@FormParam("user")String user,@FormParam("pass")String pass){
        ObjectNode node = mapper().createObjectNode().put(user, pass);
        MyRADIUStest newUser = new MyRADIUStest();
        //newUser.createRadiusClient();
        AccessRequest myAr = newUser.createRequest(user, pass);
        newUser.authWithRadius(myAr);
        return Response.ok(node).build();
    }
}