package com.thisaster.weblab.beans;

import com.thisaster.weblab.models.Point;
import com.thisaster.weblab.models.PointAttempt;
import com.thisaster.weblab.models.User;
import com.thisaster.weblab.service.PointAttemptService;
import com.thisaster.weblab.service.UserService;

import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;

import java.util.ArrayList;

@Singleton
@TransactionManagement(value = TransactionManagementType.BEAN)
public class ControllerBean {

    @EJB
    private PointAttemptService pointAttemptService;
    @EJB
    private UserService userService;
    private final Checker checker = new Checker();
    private final FigureCollector figureCollector = new FigureCollector();

    public PointAttempt createAttempt(Point point, String username) {
        final long start = System.nanoTime();
        final PointAttempt pointAttempt = new PointAttempt();

        pointAttempt.setUser(userService.findByUsername(username));
        pointAttempt.setX(point.x());
        pointAttempt.setY(point.y());
        pointAttempt.setR(point.r());
        pointAttempt.setAttemptTime(System.currentTimeMillis());
        pointAttempt.setProcessTime(System.nanoTime() - start);
        pointAttempt.setSuccess(checkHit(point.x(), point.y(), point.r()));

        return pointAttempt;
    }

    public void addAttempt(PointAttempt pointAttempt) throws Exception {
        pointAttemptService.saveAttempt(pointAttempt);
    }

    public void addUser(User user) throws Exception {
        userService.saveUser(user);
    }

    public ArrayList<PointAttempt> getAttempts(String username) {
        return (ArrayList<PointAttempt>) pointAttemptService.findByUser(userService.findByUsername(username));
    }

    public ArrayList<User> getUsers() throws Exception {
        return userService.findAllUsers();
    }

    public boolean isRegistered(String username, String password) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public boolean checkHit(double x, double y, double r){
        checker.setCoordinates(x, y, r);
        return figureCollector.accept(checker);
    }

}
