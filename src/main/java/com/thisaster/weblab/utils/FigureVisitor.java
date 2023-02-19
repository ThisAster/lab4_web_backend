package com.thisaster.weblab.utils;

import com.thisaster.weblab.beans.FigureCollector;

public interface FigureVisitor {

    boolean visit(Sector sector);
    boolean visit(Rectangle rectangle);
    boolean visit(Triangle triangle);
    boolean visit(FigureCollector figureCollector);
    void setCoordinates(double x, double y, double r);
}
