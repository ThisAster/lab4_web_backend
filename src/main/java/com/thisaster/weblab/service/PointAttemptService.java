package com.thisaster.weblab.service;

import com.thisaster.weblab.database.PointAttemptDB;
import com.thisaster.weblab.models.PointAttempt;
import com.thisaster.weblab.models.User;

import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class PointAttemptService {

    @EJB
    private PointAttemptDB pointAttemptDB;

    public void saveAttempt(PointAttempt pointAttempt) throws Exception {
        pointAttemptDB.saveAttemptDB(pointAttempt);
    }

    public void deleteAttempt(PointAttempt pointAttempt) throws Exception {
        pointAttemptDB.deleteAttemptDB(pointAttempt);
    }

    public List<PointAttempt> findByUser(User user, int skip) {
        List<PointAttempt> attemptsStorage = pointAttemptDB.findAttemptByUserDB(user, skip);
        return attemptsStorage;
    }

    public ArrayList<PointAttempt> findAllAttempts() {
        return (ArrayList<PointAttempt>) pointAttemptDB.findAllAttemptsDB();
    }

    public int resetAttempts(User user) throws Exception {
        return pointAttemptDB.clearAttemptsDB(user);
    }

}
