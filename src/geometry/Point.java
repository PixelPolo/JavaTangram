package geometry;

import java.util.Objects;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Point class to represent 2d vectors
 * used to describe displacements in a 2d space.
 */

public class Point {

    // ***** FIELDS *****
    private double x;
    private double y;


    // ***** CONSTRUCTOR *****

    /**
     * Construct a new Point from two coordinates x and y.
     * @param x a double x coordinates in a Cartesian plan.
     * @param y a double y coordinates in a Cartesian plan.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct a new Point from another Point.
     * @param point another Point from which a new Point is created.
     */
    public Point(Point point){
        x = point.getX();
        y = point.getY();
    }

    /**
     * Create a new Point from the current one.
     * @return a new Point from the current one.
     */
    public Point copy() {
        return new Point(x, y);
    }


    // ***** GETTERS AND SETTERS *****

    /**
     * Get the double value of the Point x coordinates.
     * @return the double value of the Point x coordinates.
     */
    public double getX() { return x; }

    /**
     * Get the double value of the Point y coordinates.
     * @return the double value of the Point y coordinates.
     */
    public double getY() { return y; }


    // ***** METHODS *****

    /**
     * Return the double type distance between the current Point and another one.
     * This function calculates the absolute values dx and dy coordinates.
     * The distance is calculated by Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)).
     * @param other another Point to calculate the distance with.
     * @return a double value representing the distance between
     * the current Point and the other one.
     */
    public double distance(Point other) {
        double dx = Math.abs(other.x - x);
        double dy = Math.abs(other.y - y);
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Translate the current Point with a Vector.
     * Point x and y coordinates are updated by the addition
     * of the dx and dy fields of the Vector.
     * @param delta a Vector to translate the current Point.
     */
    public void translate(Vector delta) {
        x += delta.getDx();
        y += delta.getDy();
    }

    /**
     * Rotate the current Point by changing its coordinates
     * from a center Point and an Angle.
     * @param angle an Angle to rotate the current Point.
     * @param center a center Point from where the rotation starts.
     */
    public void rotate(Angle angle, Point center) {
        double dx = x - center.x;
        double dy = y - center.y;
        x = center.x + dx * angle.cos() - dy * angle.sin();
        y = center.y + dx * angle.sin() + dy * angle.cos();
    }

    /**
     * Scale the current Point from another one with a double type factor.
     * @param factor a double type factor.
     * @param center a center Point from which the current one is scaled.
     */
    public void scale(double factor, Point center) {
        x = center.x + factor * (x - center.x);
        y = center.y + factor * (y - center.y);
    }

    /**
     * Check if the current Point has the same coordinates of another one.
     * @param other another Point to check with.
     * @return true if both Point objects has the same coordinates.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;
        Point point = (Point) other;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    /**
     * Override of the Object class hashCode() methods.
     * @return the Integer type hashCode() of the current Point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Return a String with the Point coordinates.
     * This method toString() is override from Object class.
     * @return a String with the Point coordinates.
     */
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
