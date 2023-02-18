package com.thisaster.weblab.beans;

import com.thisaster.weblab.utils.AbstractFigure;
import com.thisaster.weblab.utils.FigureVisitor;
import com.thisaster.weblab.utils.Rectangle;
import com.thisaster.weblab.utils.Sector;
import com.thisaster.weblab.utils.Triangle;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;

import java.io.Serializable;
import java.util.stream.Stream;

@Singleton
@LocalBean
public class FigureCollector implements AbstractFigure, Serializable {

    AbstractFigure[] figures;

    public FigureCollector() {
        this.figures = new AbstractFigure[]{
                new Sector(),
                new Triangle(),
                new Rectangle()
        };
    }
    @Override
    public boolean accept(FigureVisitor visitor) {
        return Stream.of(figures).anyMatch(figure -> figure.accept(visitor));
    }

}
