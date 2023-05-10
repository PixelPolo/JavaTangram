package shapes;

import geometry.*;
import geometry.Point;

import java.awt.*;
import java.util.ArrayList;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Polygon class to create, transform, and display polygons.
 */

public class Polygon extends Shape {

    // ***** FIELDS *****
    private ArrayList<Point> vertices;


    // ***** CONSTRUCTOR *****

    /**
     * Construct a new Polygon from a Color and
     * a Point ArrayList representing each vertex.
     * @param color a Color from java.awt.
     * @param vertices a Point ArrayList representing each vertex.
     */
    public Polygon(Color color, ArrayList<Point> vertices) {
        super(color);
        this.vertices = new ArrayList<>(vertices);
    }


    // ***** GETTERS AND SETTERS *****

    /**
     * Return the Point center as the mean of Polygon coordinates.
     * @return the Point center as the mean of Polygon coordinates.
     */
    @Override
    public Point getCenter() {
        double sumOfX = 0;
        double sumOfY = 0;
        for (Point p : vertices) {
            sumOfX += p.getX();
            sumOfY += p.getY();
        }
        return new Point(
                sumOfX / vertices.size(),
                sumOfY / vertices.size());
    }

    /**
     * Set the Polygon center Point.
     * @param center a Point representing the new Polygon center.
     */
    @Override
    public void setCenter(Point center) {
        translate(new Vector(getCenter(), center));
    }

    /**
     * Return Polygon ArrayList vertices.
     * @return Return Polygon ArrayList vertices Point.
     */
    public ArrayList<Point> getVertices() {
        return vertices;
    }

    /**
     * Set the Polygon ArrayList vertices.
     * @param vertices a Point ArrayList.
     */
    public void setVertices(ArrayList<Point> vertices) {
        this.vertices = new ArrayList<>(vertices);
    }

    /**
     * Get an ArrayList with each Polygon Segments.
     * @return an ArrayList with each Polygon Segments.
     */
    public ArrayList<Segment> getSegments() {
        ArrayList<Segment> segments = new ArrayList<>();
        // Loop for each vertice of the polygon :
        // For easier understanding : vertices[i] = a and vertices[i+1] = b.
        // If A = last vertice -> B = first vertice;
        for (int i = 0; i < vertices.size(); i++) {
            Point a = vertices.get(i).copy();
            Point b = i == vertices.size() - 1 ? vertices.get(0).copy() : vertices.get(i + 1).copy();
            segments.add(new Segment(a, b));
        }
        return segments;
    }


    // ***** METHODS *****

    /**
     * Translation method.
     * @param vector a Vector that determines the
     * direction and the distance to move.
     */
    @Override
    public void translate(Vector vector) {
        for (Point p : vertices) p.translate(vector);
    }

    /**
     * Rotation method.
     * @param center a Point which is the center for the rotation.
     * @param angle an Angle to rotate.
     */
    @Override
    public void rotate(Point center, Angle angle) {
        for (Point p : vertices) p.rotate(angle, center);
    }

    /**
     * Scaling method.
     * @param refPoint a Point which is the reference from the scaling.
     * @param factor a double value which determines the scaling factor.
     */
    @Override
    public void scale(Point refPoint, Double factor) {
        for (Point p : vertices) p.scale(factor, refPoint);
    }

    /**
     * Determine if a Point is inside a Polygon.
     * Using the segment cross algorithm.
     * @param point a Point to determine if it is inside the Polygon.
     * @return a Boolean if the Point is inside or not.
     */
    @Override
    public boolean contains(Point point) {
        // Check segments crossing
        int crosses = 0;
        Segment horizontalLine = new Segment(point, new Point(1e6, point.getY()));
        for (Segment s : getSegments()) {
            if (s.isColliding(horizontalLine)) crosses += 1;
        }
        return crosses % 2 != 0;
    }


    // ***** EXTRA *****

    /**
     * Method for drawing the Polygon with a Graphics2D.
     * @param g2D a Graphics2D.
     */
    @Override
    public void g2DFill(Graphics2D g2D) {
        int[] xCoords = new int[vertices.size()];
        int[] yCoords = new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            xCoords[i] = (int) vertices.get(i).getX();
            yCoords[i] = (int) vertices.get(i).getY();
        }
        g2D.setColor(getColor());
        g2D.fillPolygon(xCoords, yCoords, vertices.size());
    }

}
