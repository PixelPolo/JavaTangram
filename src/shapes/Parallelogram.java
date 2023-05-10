package shapes;

import geometry.*;
import geometry.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Parallelogram class representing a parallelogram Shape.
 * It extends the Polygon class.
 */

public class Parallelogram extends Polygon {

    // ***** STATIC METHODS *****

    /**
     * Return the Angle value between the Parallelogram base and the horizontal x-axe.
     * @param a a Point representing the first corner of the Parallelogram.
     * @param b a Point representing the second corner of the Parallelogram.
     * @return an Angle value between the Parallelogram base and the horizontal x-axe.
     */
    protected static Angle getAngleBAX(Point a, Point b) {
        return new Angle(b.getX()-a.getX(), b.getY() - a.getY());
    }

    /**
     * T Point for calculating the other Parallelogram corners.
     * @param a a Point representing the first corner of the Parallelogram.
     * @param b a Point representing the second corner of the Parallelogram.
     * @param shift a double value representing the Parallelogram shift.
     * @return T Point.
     */
    private static Point computeT(Point a, Point b, double shift) {
        Point t = new Point(b);
        t.translate(new Vector(shift, 0));
        t.rotate(getAngleBAX(a, b), b);
        return t;
    }

    /**
     * T2 Point for calculating the other Parallelogram corners.
     * @param a a Point representing the first corner of the Parallelogram.
     * @param b a Point representing the second corner of the Parallelogram.
     * @param shift a double value representing the Parallelogram shift.
     * @param height a double value representing the Parallelogram height.
     * @return T2 Point.
     */
    private static Point computeT2(Point a, Point b, double shift, double height) {
        Point t2 = new Point(computeT(a, b, shift));
        t2.translate(new Vector(height, 0));
        return t2;
    }

    /**
     * Compute all the Parallelogram Point corners.
     *
     * @param a a Point representing the first corner of the Parallelogram.
     * @param b a Point representing the second corner of the Parallelogram.
     * @param shift a double value representing the Parallelogram shift.
     * @param height a double value representing the Parallelogram height.
     * @return Return an Array of Point representing each Parallelogram corners.
     */
    private static ArrayList<Point> computePoints(Point a, Point b, double shift, double height) {

        //                    d ------- c
        //                 .         .  |
        //             .         .      height
        //         .         .          |
        //      a ------- b -- shift -- t -- height-- t2

        Point c = computeT2(a, b, shift, height);
        c.rotate(Angle.inDegrees(90).plus(getAngleBAX(a, b)), computeT(a, b, shift));
        Point d = new Point(c);
        d.translate(new Vector(a, b).times(-1));
        return new ArrayList<>(Arrays.asList(a, b, c, d));
    }


    //***** CONSTRUCTOR *****

    /**
     * Construct a Parallelogram from a Color,
     * two Points representing the base,
     * a shift and a height.
     * @param color a Color from java.awt.
     * @param a a Point representing the first corner of the Parallelogram.
     * @param b a Point representing the second corner of the Parallelogram.
     * @param shift a double value representing the Parallelogram shift.
     * @param height a double value representing the Parallelogram height.
     */
    public Parallelogram(Color color, Point a, Point b, double shift, double height) {
        super(color, computePoints(a, b, shift, height));
    }

}
