package mvc;


import geometry.Angle;
import geometry.Point;
import geometry.Vector;
import shapes.*;
import shapes.Rectangle;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * ModelTangram class.
 * It's the Model of the MVC application model.
 * Extends java.util.Observable (which is deprecated since java 9).
 */

public class ModelTangram extends Observable {

    // ***** FIELDS *****
    private final ArrayList<Shape> shapesToFill;
    private final ArrayList<Group> tangramPieces;
    private final Square whiteSquare;
    private final Triangle whiteTriangle;
    private final Rectangle whiteRectangle;
    private final Parallelogram whiteParallelogram;


    /**
     * Construct a Model.
     * Initializes all the Tangram game shapes.
     * A Tangram pieces is a Group (Shape piece = index 1 & Circle center = index 1).
     * For simplification, all the positions are calculated before.
     * 1. Controller listens events from the View.
     * 2. Controller modifies the Model according to the events.
     * 3. Model notifies the View if changes were made.
     * 4. View updates a boolean if it receives notifications.
     * 5. The thread check the boolean and repaint() if it's necessary.
     */
    public ModelTangram() {

        // Shapes to fill settings
        shapesToFill = new ArrayList<>();
        Color shapeToFillColor = Color.WHITE;

        // Tangram pieces settings
        tangramPieces = new ArrayList<>();
        double centerRadius = 10;
        Color centerColor = Color.BLACK;
        Color color1 = Color.RED;
        Color color2 = Color.BLUE;
        Color color3 = Color.GREEN;
        Color color4 = Color.MAGENTA;
        Color color5 = Color.YELLOW;
        Color color6 = Color.PINK;
        Color color7 = Color.CYAN;
        int bigTriBase = 400;
        int medTriSide = 200;
        int smallTriBase = 200;
        int squBase = 140;
        int paraBase = 200;
        int paraShift = 100;
        int paraHeight = 100;

        // Square to fill
        whiteSquare = new Square(shapeToFillColor, new Point(250, 100), new Point(650, 100));
        shapesToFill.add(whiteSquare);

        // Rectangle to fill
        whiteRectangle = new Rectangle(shapeToFillColor, new Point(170, 160), new Point(730, 160), 280);
        shapesToFill.add(whiteRectangle);

        // Triangle to fill
        whiteTriangle = Triangle.rightAngledIsosceles(shapeToFillColor, new Point(50, 100), new Point(850, 100));
        shapesToFill.add(whiteTriangle);

        // Parallelogram to fill
        whiteParallelogram = new Parallelogram(shapeToFillColor, new Point(30, 440), new Point(590, 440), 280, -280);
        shapesToFill.add(whiteParallelogram);

        // Triangle 1 - Big
        Point anchor1 = new Point(220, 120);
        Triangle triangle1 = Triangle.rightAngledIsosceles(color1, new Point(20, 50), new Point(20 + bigTriBase, 50));
        Circle center1 = new Circle(centerColor, anchor1, centerRadius);
        Group group1 = new Group(color1, Arrays.asList(triangle1, center1), anchor1.copy());
        tangramPieces.add(group1);

        // Triangle 2 - Big
        Point anchor2 = new Point(220, 370);
        Triangle triangle2 = Triangle.rightAngledIsosceles(color2, new Point(20, 300), new Point(20 + bigTriBase, 300));
        Circle center2 = new Circle(centerColor, anchor2, centerRadius);
        Group group2 = new Group(color2, Arrays.asList(triangle2, center2), anchor2.copy());
        tangramPieces.add(group2);

        // Triangle 3 - Med
        Point anchor3 = new Point(520, 120);
        Triangle triangle3 = Triangle.rightAngled(color3, new Point(450, 50), new Point(450 + medTriSide, 50), medTriSide);
        Circle center3 = new Circle(centerColor, anchor3, centerRadius);
        Group group3 = new Group(color3, Arrays.asList(triangle3, center3), anchor3.copy());
        tangramPieces.add(group3);

        // Triangle 4 - Small
        Point anchor4 = new Point(550, 330);
        Triangle triangle4 = Triangle.rightAngledIsosceles(color4, new Point(450, 300), new Point(450 + smallTriBase, 300));
        Circle center4 = new Circle(centerColor, anchor4, centerRadius);
        Group group4 = new Group(color4, Arrays.asList(triangle4, center4), anchor4.copy());
        tangramPieces.add(group4);

        // Triangle 5 - Small
        Point anchor5 = new Point(550, 480);
        Triangle triangle5 = Triangle.rightAngledIsosceles(color5, new Point(450, 450), new Point(450 + smallTriBase, 450));
        Circle center5 = new Circle(centerColor, anchor5, centerRadius);
        Group group5 = new Group(color5, Arrays.asList(triangle5, center5), anchor5.copy());
        tangramPieces.add(group5);

        // Square
        Square square = new Square(color6, new Point(680, 50), new Point(680 + squBase, 50));
        Circle center6 = new Circle(centerColor, square.getCenter(), centerRadius);
        Group group6 = new Group(color6, Arrays.asList(square, center6));
        tangramPieces.add(group6);

        // Parallelogram
        Parallelogram parallelogram = new Parallelogram(color7, new Point(820, 220), new Point(820, 220 + paraBase), paraShift, paraHeight);
        Circle center7 = new Circle(centerColor, parallelogram.getCenter(), centerRadius);
        Group group7 = new Group(color7, Arrays.asList(parallelogram, center7));
        tangramPieces.add(group7);

    }


    // ***** GETTERS *****

    /**
     * Get the ArrayList of Group storing Tangram pieces.
     * @return the ArrayList of Group storing Tangram pieces.
     */
    public ArrayList<Group> getTangramPieces() { return tangramPieces; }

    /**
     * Get the Group representing the front piece from the ArrayList of Group storing Tangram pieces.
     * @return the Group representing  front piece from the ArrayList of Group storing Tangram pieces.
     */
    public Group getFrontPiece() { return tangramPieces.get(0); }

    /**
     * Get the front Shape to fill (with Tangram pieces) from the ArrayList of Shape.
     * @return the front Shape to fill (with Tangram pieces) from the ArrayList of Shape.
     */
    public Shape getFrontShapeToFill() { return shapesToFill.get(0); }

    /**
     * Get the white Square to fill (with Tangram pieces) from the ArrayList of Shape.
     * @return the white Square to fill (with Tangram pieces) from the ArrayList of Shape.
     */
    public Square getWhiteSquare() { return whiteSquare; }

    /**
     * Get the white Triangle to fill (with Tangram pieces)
     * from the ArrayList of Shape.
     * @return the white Triangle to fill (with Tangram pieces)
     * from the ArrayList of Shape.
     */
    public Triangle getWhiteTriangle() { return whiteTriangle; }

    /**
     * Get the white Rectangle to fill (with Tangram pieces)
     * from the ArrayList of Shape.
     * @return the white Rectangle to fill (with Tangram pieces)
     * from the ArrayList of Shape.
     */
    public Rectangle getWhiteRectangle() { return whiteRectangle; }

    /**
     * Get the white Parallelogram to fill (with Tangram pieces)
     * from the ArrayList of Shape.
     * @return the white Parallelogram to fill (with Tangram pieces)
     * from the ArrayList of Shape.
     */
    public Parallelogram getWhiteParallelogram() { return whiteParallelogram; }


    // ***** METHODS *****

    /**
     * Put the Group representing a Tangram piece at the front of
     * the ArrayList of Group storing Tangram pieces.
     * @param piece the Group to put at the front.
     */
    public void putTangramPieceAtFront(Group piece) {
        tangramPieces.remove(piece);
        tangramPieces.add(0, piece);
        setChanged();
        notifyObservers();
    }

    /**
     * Put the Shape representing the shape to fill (with Tangram pieces)
     * at the front of the ArrayList of Shape.
     * @param shape the Shape to put at the front.
     */
    public void putShapeToFillAtFront(Shape shape) {
        shapesToFill.remove(shape);
        shapesToFill.add(0, shape);
        setChanged();
        notifyObservers();
    }

    /**
     * Translate the front Group representing a Tangram piece
     * from the ArrayList of Group storing Tangram pieces.
     * @param vector a Vector that determines the direction
     * and the distance to move.
     */
    public void translateFrontPiece(Vector vector) {
        getFrontPiece().translate(vector);
        setChanged();
        notifyObservers();
    }

    /**
     * Rotate the front Group representing a Tangram piece
     * from the ArrayList of Group storing Tangram pieces.
     * @param angle an Angle to rotate.
     */
    public void rotateFrontShape(Angle angle) {
        getFrontPiece().rotate(angle);
        setChanged();
        notifyObservers();
    }

}
