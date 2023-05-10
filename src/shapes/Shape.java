package shapes;

import geometry.*;
import geometry.Point;

import java.awt.*;


/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Shape abstract class that will be used as a parent class
 * of all concrete shapes such as triangles, rectangles, squares, etc.
 */

public abstract class Shape implements Transformable {

    // ***** FIELDS *****
    private Color color;


    // ***** CONSTRUCTOR *****

    /**
     * Abstract class Shape constructor initializes Color.
     * @param color a Color from java.awt.Color.
     */
    protected Shape(Color color) {
        this.color = color;
    }


    // ***** GETTERS AND SETTERS *****

    /**
     * Get the Shape Color (java.awt).
     * @return the Shape Color (java.awt).
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the Shape Color (java.awt).
     * @param color a Color (java.awt).
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Get the Shape center Point.
     * @return the Shape center Point.
     */
    public abstract Point getCenter();

    /**
     * Set the Shape center Point.
     * @param center a Point representing the Shape center.
     */
    public abstract void setCenter(Point center);


    // ***** ABSTRACT METHODS *****

    /**
     * Return true if a Point is inside the current Shape.
     * @return true if a Point is inside the current Shape.
     */
    public abstract boolean contains(Point point);


    // ***** METHODS *****

    /**
     * An abstract rotation method for shapes.
     * @param angle an Angle to rotate.
     */
    public void rotate(Angle angle) {
        rotate(getCenter(), angle);
    }

    /**
     * An abstract scale method for shapes.
     * @param factor a double value factor to scale.
     */
    public void scale(Double factor) {
        scale(getCenter(), factor);
    }


    // ***** EXTRA *****

    /**
     * Abstract method for drawing shapes with a Graphics2D.
     * @param g2D a Graphics2D.
     */
    public abstract void g2DFill(Graphics2D g2D);

}
