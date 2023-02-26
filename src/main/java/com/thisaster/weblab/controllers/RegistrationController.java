package com.thisaster.weblab.controllers;

import com.google.common.hash.Hashing;

import com.thisaster.weblab.beans.ServiceBean;
import com.thisaster.weblab.models.PointAttempt;
import com.thisaster.weblab.models.User;
import com.thisaster.weblab.utils.JSONParser;

import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

@Path("/registration")
public class RegistrationController {

    @EJB
    private ServiceBean serviceBean;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response registration(Map<String, String> data) {
        ArrayList<PointAttempt> result = new ArrayList<>();
        User user = new User();
        user.setUsername(data.get("username"));
        user.setPassword(Hashing.sha256().hashString(data.get("password"), StandardCharsets.UTF_8).toString());
        if (serviceBean.isRegistered(user.getUsername(), user.getPassword())) {
            return Response.status(HttpServletResponse.SC_FORBIDDEN)
                    .entity("User already exists")
                    .build();
        }
        try {
            serviceBean.addUser(user);
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
