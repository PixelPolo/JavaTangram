package geometry;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Vector class to define points in a 2d space and manipulate them.
 * This class is immutable.
 */

public class Vector {

    // ***** FIELDS *****
    private final double dx;
    private final double dy;


    // ***** CONSTRUCTOR *****

    /**
     * Construct a new Vector from two distances dx and dy in a Cartesian plan.
     * @param dx a distance from the x-axe of a Cartesian plan.
     * @param dy a distance from the y-axe of a Cartesian plan.
     */
    public Vector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Construct a new Vector from the coordinates of a Point.
     * The distances dx and dy of the Vector are from (0, 0)
     * to the Point (x, y) coordinates in a Cartesian plan.
     * @param point a Point object.
     */
    public Vector(Point point) {
        dx = point.getX();
        dy = point.getY();
    }

    /**
     * Construct a new Vector from two Point objects.
     * The distances dx and dy of the Vector represent the subtraction
     * of the pointTo (x, y) and the pointFrom (x, y) coordinates.
     * @param pointFrom a Point from.
     * @param pointTo a Point to.
     */
    public Vector(Point pointFrom, Point pointTo) {
        dx = pointTo.getX() - pointFrom.getX();
        dy = pointTo.getY() - pointFrom.getY();
    }


    // ***** GETTERS AND SETTERS *****

    /**
     * Get the double value of the current Vector dx distance.
     * @return the double value of the current Vector dx distance.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Get the double value of the current Vector dy distance.
     * @return the double value of the current Vector dy distance.
     */
    public double getDy() {
        return dy;
    }


    // ***** METHODS *****

    /**
     * Addition the current Vector with the value of another one.
     * @param vector another Vector to add.
     * @return a new Vector which dx and dy distances represent the
     * addition of the current one and another one dx and dy distances.
     */
    public Vector plus(Vector vector) {
        return new Vector(dx + vector.getDx(), dy + vector.getDy());
    }

    /**
     * Multiply the current Vector dx and dy distances by a double factor.
     * @param value a double representing factor.
     * @return a new Vector which dx and dy distances represent the
     * multiplication of the current one with the factor.
     */
    public Vector times(double value) {
        return new Vector(dx * value, dy * value);
    }

    /**
     * Return a String with the Vector dx and dy distances.
     * This method toString() is override from Object class.
     * @return a String with the Vector dx and dy distances.
     */
    @Override
    public String toString() {
        return "Vector{" +
                "dx=" + dx +
                ", dy=" + dy +
                '}';
    }

}
