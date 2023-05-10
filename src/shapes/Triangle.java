package shapes;

import geometry.*;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import static shapes.Parallelogram.getAngleBAX;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Triangle class representing a Triangle Shape.
 * It extends the Polygon class.
 */

public class Triangle extends Polygon {

    // ***** STATIC METHODS *****

    /**
     * S Point for calculating the third Triangle corner.
     * @param a a Point representing the first Triangle corner.
     * @param b a Point representing the second Triangle corner.
     * @param shift a double value representing the Triangle shift.
     * @return S Point.
     */
    private static Point computeS(Point a, Point b, double shift) {
        Point t = new Point(a);
        t.translate(new Vector(shift, 0));
        t.rotate(getAngleBAX(a, b), a);
        return t;
    }

    /**
     * S2 Point for calculating the third Triangle corner.
     * @param a a Point representing the first Triangle corner.
     * @param b a Point representing the second Triangle corner.
     * @param shift a double value representing the Triangle shift.
     * @param height a double value representing the Triangle height.
     * @return S2 Point
     */
    private static Point computeS2(Point a, Point b, double shift, double height) {
        Point t2 = new Point(computeS(a, b, shift));
        t2.translate(new Vector(height, 0));
        return t2;
    }

    /**
     * Compute all the Triangle corners.
     *
     * @param a a Point representing the first Triangle corner.
     * @param b a Point representing the second Triangle corner.
     * @param shift  a double value representing the Triangle shift.
     * @param height a double value representing the Triangle height.
     * @return Return an Array of Point representing each Triangle corners.
     */
    private static ArrayList<Point> computePoints(Point a, Point b, double shift, double height) {

        //                     c
        //                 .   |      .
        //             .     height          .
        //         .           |                     .
        //      a -- shift --- s --height-- s2 ---------- b

        Point c = new Point(computeS2(a, b, shift, height));
        c.rotate(Angle.inDegrees(90).plus(getAngleBAX(a, b)), computeS(a, b, shift));
        return new ArrayList<>(Arrays.asList(a, b, c));
    }


    //***** CONSTRUCTOR *****

    /**
     * Construct a Triangle from a Color,
     * two Points representing the base,
     * a shift and a height.
     * @param color a Color from java.awt.
     * @param a a Point representing the first Triangle corner.
     * @param b a Point representing the second Triangle corner.
     * @param shift a double value representing the Triangle shift.
     * @param height a double value representing the Triangle height.
     */
    public Triangle(Color color, Point a, Point b, double shift, double height) {
        super(color, computePoints(a, b, shift, height));
    }

    /**
     * Static factory method : Return an Isosceles Triangle.
     * @param color a Color from java.awt.
     * @param a a Point representing the first Triangle corner.
     * @param b a Point representing the second Triangle corner.
     * @param height a double value representing the Triangle height.
     * @return Return an Isosceles Triangle.
     */
    public static Triangle isosceles(Color color, Point a, Point b, double height) {
        return new Triangle(color, a, b, a.distance(b) / 2, height);
    }

    /**
     * Static factory method : Return a Right-angled Triangle.
     * @param color a Color from java.awt.
     * @param a a Point representing the first Triangle corner.
     * @param b a Point representing the second Triangle corner.
     * @param height a double value representing the Triangle height.
     * @return Return a Right-angled Triangle.
     */
    public static Triangle rightAngled(Color color, Point a, Point b, double height) {
        return new Triangle(color, a, b, 0, height);
    }

    /**
     * Static factory method : Return a Right-angled-isosceles Triangle.
     * @param color a Color from java.awt.
     * @param a a Point representing the first Triangle corner.
     * @param b a Point representing the second Triangle corner.
     * @return Return a Right-angled-isosceles Triangle.
     */
    public static Triangle rightAngledIsosceles(Color color, Point a, Point b) {
        return new Triangle(color, a, b, a.distance(b) / 2, a.distance(b) / 2);
    }

    /**
     * Static factory method : Return an Equilateral Triangle.
     * @param color a Color from java.awt.
     * @param a a Point representing the first Triangle corner.
     * @param b a Point representing the second Triangle corner.
     * @return Return an Equilateral Triangle.
     */
    public static Triangle equilateral(Color color, Point a, Point b) {
        double base = a.distance(b);
        return new Triangle(color, a, b, base / 2, -(Math.sqrt(3) / 2) * base);
    }

}
