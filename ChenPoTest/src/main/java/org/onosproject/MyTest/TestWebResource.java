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
        MyRADIUStest newUser = new MyRADIUStest();
        //newUser.createRadiusClient();
        AccessRequest myAr = newUser.createRequest(user, pass);
        String result = newUser.authWithRadius(myAr);
        ObjectNode node = mapper().createObjectNode().put("Authentication result", result);

        return Response.ok(node).build();
    }
}