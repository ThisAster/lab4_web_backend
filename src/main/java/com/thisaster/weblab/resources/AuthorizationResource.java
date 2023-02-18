package com.thisaster.weblab.resources;

import com.google.common.hash.Hashing;
import com.thisaster.weblab.beans.ControllerBean;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Path("/authorize")
public class AuthorizationResource {

    @EJB
    private ControllerBean controller;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response authorize(Map<String, String> data) {
        if (controller.isRegistered(data.get("username"), Hashing.sha256().hashString(data.get("password"), StandardCharsets.UTF_8).toString())) {
            return Response.ok()
                    .build();
        } else {
            return Response.status(403)
                    .entity("Authorization failed")
                    .build();
        }
    }

}
