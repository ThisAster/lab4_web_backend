package com.thisaster.weblab.controllers;

import com.google.common.hash.Hashing;

import com.thisaster.weblab.beans.ServiceBean;
import com.thisaster.weblab.models.Point;
import com.thisaster.weblab.models.PointAttempt;
import com.thisaster.weblab.utils.JSONParser;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

@Path("/points")
public class PointsController {

    @EJB
    private ServiceBean serviceBean;

    @POST
    @Path("/add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addPoint(Map<String, String> data) {
        if (serviceBean.isRegistered(data.get("username"), Hashing.sha256().hashString(data.get("password"), StandardCharsets.UTF_8).toString())) {
            ArrayList<PointAttempt> result = new ArrayList<>();
            double x = Double.parseDouble(data.get("X"));
            double y = Double.parseDouble(data.get("Y"));
            double r = Double.parseDouble(data.get("R"));
            Point point = new Point(x, y, r);
            try {
                PointAttempt pointAttempt = serviceBean.createAttempt(point, data.get("username"));
                serviceBean.addAttempt(pointAttempt);
                result.add(pointAttempt);
                return Response.ok()
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .entity(JSONParser.toJSON(result))
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError()
                        .entity("Could not add a PointAttempt")
                        .build();
            }
        } else {
            return Response.status(HttpServletResponse.SC_FORBIDDEN)
                    .entity("User is not confirmed")
                    .build();
        }
    }

    @POST
    @Path("/get")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response getAllAttempts(Map<String, String> data) {
        if (serviceBean.isRegistered(data.get("username"), Hashing.sha256().hashString(data.get("password"), StandardCharsets.UTF_8).toString())) {
            int skip = data.get("skip") == null ? 0 : Integer.parseInt(data.get("skip"));
            return Response.ok()
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(JSONParser.toJSON(serviceBean.getAttempts(data.get("username"), skip)))
                    .build();
        } else {
            return Response.status(HttpServletResponse.SC_FORBIDDEN)
                    .entity("User is not confirmed")
                    .build();
        }
    }

    @POST
    @Path("/clear")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response clearAllAttempts(Map<String, String> data) throws Exception {
        if (serviceBean.isRegistered(data.get("username"), Hashing.sha256().hashString(data.get("password"), StandardCharsets.UTF_8).toString())) {
            serviceBean.reset(data.get("username"));
            return Response.ok()
                    .entity("Clear successful")
                    .build();
        } else {
            return Response.status(HttpServletResponse.SC_FORBIDDEN)
                    .entity("User is not confirmed")
                    .build();
        }
    }

}
