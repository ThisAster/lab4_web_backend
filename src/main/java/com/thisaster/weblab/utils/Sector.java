package com.thisaster.weblab.utils;

public class Sector implements AbstractFigure {
    @Override
    public boolean accept(FigureVisitor visitor) {
        return visitor.visit(this);
    }

}
