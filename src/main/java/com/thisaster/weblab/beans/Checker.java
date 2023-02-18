package com.thisaster.weblab.beans;

import com.thisaster.weblab.utils.FigureVisitor;
import com.thisaster.weblab.utils.Rectangle;
import com.thisaster.weblab.utils.Sector;
import com.thisaster.weblab.utils.Triangle;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;

import java.io.Serializable;

@LocalBean
@Singleton
public class Checker implements FigureVisitor, Serializable {

    private double x;
    private double y;
    private double r;
    @EJB
    private FigureCollector figureCollector;

    @Override
    public void setCoordinates(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public boolean visit(Sector sector) {
        double rr = Math.sqrt(x*x + y*y);
        return (x <= 0 && y <= 0 && rr <= r);
    }

    @Override
    public boolean visit(Rectangle rectangle) {
        return ((x>=0 && y<=r) && (y>=0 && (y <= - r/2)));
    }

    @Override
    public boolean visit(Triangle triangle) {
        return ((x >= 0 && y <= 0) && (y >= (x - r)));
    }

    @Override
    public boolean visit(FigureCollector figureCollector) {
        return true;
    }

    public boolean checkHit(double x, double y, double r){
        this.setCoordinates(x, y, r);
        return figureCollector.accept(this);
    }

}
