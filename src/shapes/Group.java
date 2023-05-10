package shapes;

import geometry.*;
import geometry.Point;

import java.awt.*;
import java.util.List;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Group class representing a group of Shapes.
 * Group class extends Shape.
 * (And so, transformable and displayable interfaces)
 */

public class Group extends Shape {

    // ***** STATIC METHODS *****

    /**
     * Return a Point representing the Group Center
     * as the mean of each Shape center.
     * @param shapeList a List of Shape or Shape children.
     * @return a Point representing the Group Center
     */
    private static Point computeCenter(List<? extends Shape> shapeList) {
        double sumOfXCoords = 0;
        double sumOfYCoords = 0;
        for (Shape s : shapeList) {
            sumOfXCoords += s.getCenter().getX();
            sumOfYCoords += s.getCenter().getY();
        }
        return new Point(
                sumOfXCoords / shapeList.size(),
                sumOfYCoords / shapeList.size()
        );
    }


    // ***** FIELDS *****
    private final List<? extends Shape> shapeGroup;
    private final Point groupCenter;


    // ***** CONSTRUCTOR *****

    /**
     * Construct a Group (Center is the mean of each Shape center).
     * @param color a Color from java.awt.
     * @param shapeGroup a List of Shape or Shape children.
     */
    public Group(Color color, List<? extends Shape> shapeGroup) {
        this(color, shapeGroup, computeCenter(shapeGroup));
    }

    /**
     * Construct a Group of Shapes from a List.
     * @param color a Color from java.awt.
     * @param shapeGroup a List of Shape or Shape children.
     * @param anchor a Point which represent the Group center/anchor.
     */
    public Group(Color color, List<? extends Shape> shapeGroup, Point anchor) {
        super(color);
        this.shapeGroup = shapeGroup;
        this.groupCenter = anchor;
    }


    // ***** GETTERS AND SETTERS *****

    /**
     * Get the Group Point center.
     * Method = Mean of each Shape center.
     * @return the Group Point center.
     */
    @Override
    public Point getCenter() {
        return groupCenter;
    }

    /**
     * Set the Group Point center
     * @param center a Point representing the Group center.
     */
    @Override
    public void setCenter(Point center) {
        translate(new Vector(getCenter(),center));
    }

    /**
     * Get the List of Shape inside the Group.
     * @return the List of Shape inside the Group.
     */
    public List<? extends Shape> getShapeGroup() {
        return shapeGroup;
    }


    // ***** METHODS *****

    /**
     * Determine if a Point is inside the Group.
     * Using the segment cross algorithm.
     * @param point a Point to determine if it is inside the Group.
     * @return a Boolean if the Point is inside or not.
     */
    @Override
    public boolean contains(Point point) {
        boolean isInside = false;
        for (Shape s : shapeGroup) {
            if (s.contains(point)) isInside = true;
        }
        return isInside;
    }

    /**
     * Translation method.
     * @param vector a Vector that determines the
     * direction and the distance to move.
     */
    @Override
    public void translate(Vector vector) {
        for (Shape s : shapeGroup) s.translate(vector);
        groupCenter.translate(vector);
    }

    /**
     * Rotation method.
     * @param center a Point which is the center for the rotation.
     * @param angle an Angle to rotate.
     */
    @Override
    public void rotate(Point center, Angle angle) {
        for (Shape s : shapeGroup) s.rotate(center, angle);
        groupCenter.rotate(angle, center);
    }

    /**
     * Scaling method.
     * @param refPoint a Point which is the reference from the scaling.
     * @param factor a double value which determines the scaling factor.
     */
    @Override
    public void scale(Point refPoint, Double factor) {
        for (Shape s : shapeGroup) s.scale(refPoint, factor);
        groupCenter.scale(factor, refPoint);
    }


    // ***** EXTRA *****

    /**
     * Method for drawing the Group with a Graphics2D.
     * Each shape will be drawn with their own color.
     * @param g2D a Graphics2D.
     */
    @Override
    public void g2DFill(Graphics2D g2D) {
        for (Shape s : shapeGroup) s.g2DFill(g2D);
    }

}
