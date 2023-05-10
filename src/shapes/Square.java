package shapes;

import geometry.Point;
import java.awt.*;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Square class representing a Square Shape.
 * It extends the Rectangle class.
 */

public class Square extends Rectangle {

    //***** CONSTRUCTOR *****

    /**
     * Construct a Square from a Color,
     * and two Points representing the base.
     * @param color a Color from java.awt.
     * @param a a Point representing the first corner of the Square.
     * @param b a Point representing the second corner of the Square.
     */
    public Square(Color color, Point a, Point b) {
        super(color, a, b, (int) a.distance(b));
    }

}


