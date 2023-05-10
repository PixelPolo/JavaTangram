package geometry;

import shapes.Transformable;

import java.util.Objects;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Segment class.
 * Implements Transformable and Displayable interfaces.
 */

public class Segment implements Transformable {

    // ***** FIELDS *****
    private Point a;
    private Point b;


    // ***** CONSTRUCTOR *****

    /**
     * Construct a Segment from two Points
     * @param a the first Point.
     * @param b the second Point.
     */
    public Segment(Point a, Point b) {
        this.a = a.copy();
        this.b = b.copy();
    }


    // ***** GETTERS AND SETTERS *****

    /**
     * Get the Segment Point center
     * @return the Segment Point center
     */
    public Point getCenter() {
        return new Point(
                (a.getX() + b.getX() ) / 2,
                (a.getY() + b.getY() ) / 2);
    }

    /**
     * Get the Segment first Point.
     * @return the Segment first Point.
     */
    public Point getA() {
        return a;
    }

    /**
     * Get the Segment second Point.
     * @return the Segment second Point.
     */
    public Point getB() {
        return b;
    }

    /**
     * Set the Segment first Point.
     * @param a the new Segment first Point.
     */
    public void setA(Point a) {
        this.a = a;
    }

    /**
     * Set the Segment second Point.
     * @param b the new Segment second Point.
     */
    public void setB(Point b) {
        this.b = b;
    }


    // ***** METHODS *****

    /**
     * Translation method.
     * @param vector a Vector that determines the
     * direction and the distance to move.
     */
    @Override
    public void translate(Vector vector) {
        a.translate(vector);
        b.translate(vector);
    }

    /**
     * Rotation method.
     * @param center a Point which is the center for the rotation.
     * @param angle an Angle to rotate.
     */
    @Override
    public void rotate(Point center, Angle angle) {
        a.rotate(angle, center);
        b.rotate(angle, center);
    }

    /**
     * Rotation method.
     * @param angle an Angle to rotate.
     */
    public void rotate(Angle angle) {
        rotate(getCenter(), angle);
    }

    /**
     * Scaling method.
     * @param refPoint a Point which is the reference from the scaling.
     * @param factor a double value which determines the scaling factor.
     */
    @Override
    public void scale(Point refPoint, Double factor) {
        a.scale(factor, refPoint);
        b.scale(factor, refPoint);
    }

    /**
     * Scaling method.
     * @param factor a double value which determines the scaling factor.
     */
    public void scale(Double factor) {
        a.scale(factor, getCenter());
        b.scale(factor, getCenter());
    }

    /**
     * Get the copy of a Segment.
     * @param other the other Segment to copy.
     * @return a new Segment from the other one.
     */
    public Segment copy(Segment other) {
        return new Segment(other.getA(), other.getB());
    }

    /**
     * Compute the orientation between 3 points.
     * Details are available into the doc.
     * @param p1 the first Point.
     * @param p2 the second Point.
     * @param p3 the third Point
     * @return 0 if no orientation, -1 if clockwise, 1 otherwise.
     */
    private int orientation(Point p1, Point p2, Point p3) {
        double val = ( p2.getY() - p1.getY()) * (p3.getX() - p2.getX() )
                - ( p3.getY() - p2.getY()) * (p2.getX() - p1.getX() ) ;
        if (val == 0) return 0;
        return val < 0 ? 1 : -1 ;
    }

    /**
     * Check if the current Segment crosses another one.
     * @param other the other Segment to check with.
     * @return true if the current Segment crosses the other one.
     */
    public boolean isColliding(Segment other) {
        Point a = getA().copy();
        Point b = getB().copy();
        Point c = other.getA().copy();
        Point d = other.getB().copy();
        return  orientation(a, b, c) != orientation(a, b, d) &&
                orientation(c, d, a) != orientation(c, d, b);
    }

    /**
     * Check if the current Segment is equals to another one.
     * Override of the Object equals() method.
     * @param o the other Segment to check with.
     * @return true if both Segment are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(a, segment.a) && Objects.equals(b, segment.b);
    }

    /**
     * Override of the Object class hashCode() methods.
     * @return the Integer type hashCode() of the current Point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    /**
     * Return a String with the Point coordinates.
     * This method toString() is override from Object class.
     * @return a String with the Point coordinates.
     */
    @Override
    public String toString() {
        return "Segment{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
