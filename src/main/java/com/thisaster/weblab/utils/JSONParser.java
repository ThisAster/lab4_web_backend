package com.thisaster.weblab.utils;

import com.thisaster.weblab.models.PointAttempt;

import java.util.ArrayList;

public class JSONParser {

    public static String toJSON(ArrayList<PointAttempt> collection) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        if (collection.size() != 0) {
            for (int i = 0; i < collection.size(); i++) {
                if (i != collection.size() - 1) {
                    json.append(toJSON(collection.get(i))).append(",\n");
                } else {
                    json.append(toJSON(collection.get(i))).append("\n");
                }
            }
        }
        json.append("]");
        return json.toString();
    }

    public static String toJSON(PointAttempt lane) {
        return "    {\n" +
                "       \"X\": \"" + lane.getX() + "\",\n" +
                "       \"Y\": \"" + lane.getY() + "\",\n" +
                "       \"R\": \"" + lane.getR() + "\",\n" +
                "       \"attemptTime\": \"" + lane.getAttemptTime() + "\",\n" +
                "       \"processTime\": \"" + lane.getProcessTime() + "\",\n" +
                "       \"result\": \"" + lane.isSuccess() + "\"\n" +
                "   }";
    }

}
