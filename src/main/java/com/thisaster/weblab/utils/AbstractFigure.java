package com.thisaster.weblab.utils;

@FunctionalInterface
public interface AbstractFigure {

    boolean accept(FigureVisitor visitor);
}
