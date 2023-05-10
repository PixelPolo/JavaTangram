package shapes;

import geometry.*;
import geometry.Point;

import java.awt.*;


/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Circle class to create, transform, and display circles.
 */

public class Circle extends Shape {

    // ***** FIELDS *****
    private double radius;
    private Point center;


    // ***** CONSTRUCTOR *****

    /**
     * Construct a new Circle from a Color, a center Point and a radius.
     * @param color a Color from java.awt.
     * @param center a Point representing the center of the Shape.
     * @param radius a double that represents the radius of the circle and its size.
     */
    public Circle(Color color, Point center, double radius) {
        super(color);
        this.center = center;
        this.radius = radius;
    }


    // ***** GETTERS AND SETTERS *****

    /**
     * Return the Circle Point center.
     * @return the Circle Point center.
     */
    @Override
    public Point getCenter() {
        return center;
    }

    /**
     * Set a Point representing the Circle center.
     * @param center a Point representing the Circle center.
     */
    @Override
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Get the double value of the Circle radius.
     * @return a double value of the Circle radius.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set the double value of the Circle radius.
     * @param radius a double value of the Circle radius.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }


    // ***** METHODS *****

    /**
     * Translation method.
     * @param vector a Vector that determines the
     * direction and the distance to move.
     */
    @Override
    public void translate(Vector vector) {
        getCenter().translate(vector);
    }

    /**
     * Rotation method.
     * @param center a Point which is the center for the rotation.
     * @param angle an Angle to rotate.
     */
    @Override
    public void rotate(Point center, Angle angle) {
        getCenter().rotate(angle, center);
    }

    /**
     * Scaling method.
     * @param refPoint a Point which is the reference from the scaling.
     * @param factor a double value which determines the scaling factor.
     */
    @Override
    public void scale(Point refPoint, Double factor) {
        getCenter().scale(factor, refPoint);
        radius *= factor;
    }

    /**
     * Determine if a Point is inside the Circle.
     * @param point A Point to determine if it is inside the Circle
     * @return a Boolean if the Point is inside or not.
     */
    @Override
    public boolean contains(Point point) {
        return getCenter().distance(point) < getRadius();
    }


    // ***** EXTRA *****

    /**
     * Method for drawing the Circle with a Graphics2D.
     * @param g2D a Graphics2D.
     */
    @Override
    public void g2DFill(Graphics2D g2D) {
        g2D.setColor(getColor());
        g2D.fillOval(
                (int) (getCenter().getX() - getRadius()),
                (int) (getCenter().getY() - getRadius()),
                (int) getRadius() * 2,  // Width = 2 * radius
                (int) getRadius() * 2); // Height = 2 * radius
    }

}