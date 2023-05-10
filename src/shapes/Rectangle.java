package shapes;

import geometry.Point;

import java.awt.*;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Rectangle class representing a Rectangle Shape.
 * It extends the Parallelogram class.
 */

public class Rectangle extends Parallelogram {

    //***** CONSTRUCTOR *****

    /**
     * Construct a Rectangle from a Color,
     * two Points representing the base,
     * and a height.
     * @param color a Color from java.awt.
     * @param a a Point representing the first corner of the Rectangle.
     * @param b a Point representing the second corner of the Rectangle.
     * @param height a double value representing the Rectangle height.
     */
    public Rectangle(Color color, Point a, Point b, double height) {
        super(color, a, b, 0, height);
    }

}
