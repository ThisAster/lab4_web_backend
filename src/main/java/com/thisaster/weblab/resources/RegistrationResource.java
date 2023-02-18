package com.thisaster.weblab.resources;

import com.google.common.hash.Hashing;
import com.thisaster.weblab.beans.ControllerBean;
import com.thisaster.weblab.models.PointAttempt;
import com.thisaster.weblab.models.User;
import com.thisaster.weblab.utils.JSONParser;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

@Path("/registration")
@Singleton
public class RegistrationResource {

    @EJB
    private ControllerBean controller;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response registration(Map<String, String> data) {
        ArrayList<PointAttempt> result = new ArrayList<>();
        User user = new User();
        user.setUsername(data.get("username"));
        user.setPassword(Hashing.sha256().hashString(data.get("password"), StandardCharsets.UTF_8).toString());
        if (controller.isRegistered(user.getUsername(), user.getPassword())) {
            return Response.status(403)
                    .entity("User already exists")
                    .build();
        }
        try {
            controller.addUser(user);
            return Response.ok()
                    .entity(JSONParser.toJSON(result))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("Could not add a user")
                    .build();
        }
    }

}
