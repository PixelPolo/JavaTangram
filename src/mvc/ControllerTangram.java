package mvc;

import shapes.*;
import geometry.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * ControllerTangram class.
 * It's the Controller of the MVC application model.
 * 1. Controller listens events from the View.
 * 2. Controller modifies the Model according to the events.
 * 3. Model notifies the View if changes were made.
 * 4. View updates a boolean if it receives notifications.
 * 5. The thread check the boolean and repaint() if it's necessary.
 */

public class ControllerTangram {

    // ***** FIELDS *****
    private final ModelTangram model;
    private final ViewTangram view;


    // ***** CONSTRUCTOR *****

    /**
     * Construct a Controller.
     * Store the Model and the View.
     * Add ActionListener, MenuItemListener and MouseListener to the View.
     * @param model a ModelTangram object.
     * @param view a ViewTangram object.
     */
    public ControllerTangram(ModelTangram model, ViewTangram view) {
        this.model = model;
        this.view = view;

        // Buttons listener
        LocalActionListener localActionListener = new LocalActionListener();
        view.getSquareBtn().addActionListener(localActionListener);
        view.getTriangleBtn().addActionListener(localActionListener);
        view.getRectangleBtn().addActionListener(localActionListener);
        view.getParallelogramBtn().addActionListener(localActionListener);

        // MenuItem listener
        view.getSquareItem().addActionListener(localActionListener);
        view.getTriangleItem().addActionListener(localActionListener);
        view.getRectangleItem().addActionListener(localActionListener);
        view.getParallelogramItem().addActionListener(localActionListener);

        // Mouse listener
        MouseAdapter mouseAdapter = new LocalMouseListener();
        view.addMouseListener(mouseAdapter);
        view.addMouseMotionListener(mouseAdapter);

    }


    // ***** METHODS *****

    /**
     * Method to align Shape to the grid after rotation.
     * Loop inside each Shape vertices and round the position
     * relative to the View STEP constant.
     */
    private void alignShape(Shape shape) {
        ArrayList<Point> newVertices = new ArrayList<>();
        Polygon piece = (Polygon) shape;
        for (Point point : piece.getVertices()) {
            int step = ViewTangram.STEP;
            int xAligned = (int) (Math.round(point.getX() / step)) * step;
            int yAligned = (int) (Math.round(point.getY() / step)) * step;
            Point pAligned = new Point(xAligned, yAligned);
            newVertices.add(pAligned);
        }
        piece.setVertices(newVertices);
    }

    /**
     * Check if the Tangram is complete.
     * Downsize each Tangram pieces for matching with
     * Polygon containment and segment intersection algorithms :
     * Necessary because it doesn't work if two Point are equals
     * and if two Segments are parallels or collinear.
     * Check if the Shape to fill contains all Tangram pieces vertices.
     * Check if all Tangram pieces segments do not collide.
     * After checking, resize all Tangram pieces and align it to the grid.
     * Modify the View Background if it's win !
     */
    private void checkWin() {
        boolean win = true;
        Shape shapeToFill = model.getFrontShapeToFill();
        ArrayList<Group> tangramGroup = model.getTangramPieces();
        ArrayList<Polygon> tangramPieces = new ArrayList<>();
        for (Group group : tangramGroup) {
            tangramPieces.add( (Polygon) group.getShapeGroup().get(0) );
        }

        // Tangram pieces downsizing
        for (Polygon p : tangramPieces)  p.scale(0.999);

        // Checking if each piece vertex are inside the shape to fill segments
        for (Polygon piece : tangramPieces) {
            ArrayList<Point> vertices = piece.getVertices();
            for (Point vertex : vertices) {
                if (!shapeToFill.contains(vertex)) {
                    win = false;
                    break;
                }
            }
        }

        // Checking if segments of each piece intersect other pieces segments
        for (int i = 0; i < tangramPieces.size(); i++) {
            ArrayList<Segment> pieceSegments = tangramPieces.get(i).getSegments();
            for (int j = 0; j < tangramPieces.size(); j++) {
                if (i != j) {
                    ArrayList<Segment> otherPiecesSegments =  tangramPieces.get(j).getSegments();
                    for (Segment segment : pieceSegments) {
                        for (Segment other : otherPiecesSegments) {
                            if (segment.isColliding(other)) {
                                win = false;
                                break;
                            }
                        }
                    }
                }
            }
        }

        // Tangram pieces resizing and alignment to the grid
        for (Polygon p : tangramPieces) {
            p.scale(1.001);
            alignShape(p);
        }

        // Change background Color if Win or not
        if (win) view.setBackground(ViewTangram.WIN_BACKGROUND_COLOR);
        else view.setBackground(ViewTangram.BACKGROUND_COLOR);

    }


    // ***** NESTED CLASS *****

    /**
     * Local classes implements ActionListener
     * for JButton and JMenuItem Listeners from the view.
     */
    private class LocalActionListener implements ActionListener {

        /**
         * Check if a JButton was clicked and change the shape to fill.
         * @param e the event to be processed.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.getSquareBtn() || e.getSource() == view.getSquareItem()) {
                model.putShapeToFillAtFront(model.getWhiteSquare());
                checkWin();
            } else if (e.getSource() == view.getTriangleBtn() || e.getSource() == view.getTriangleItem()) {
                model.putShapeToFillAtFront(model.getWhiteTriangle());
                checkWin();
            } else if (e.getSource() == view.getRectangleBtn() || e.getSource() == view.getRectangleItem()) {
                model.putShapeToFillAtFront(model.getWhiteRectangle());
                checkWin();
            } else if (e.getSource() == view.getParallelogramBtn() || e.getSource() == view.getParallelogramItem()){
                model.putShapeToFillAtFront(model.getWhiteParallelogram());
                checkWin();
            }
        }
    }

    /**
     * Local classes extends MouseAdapter for the view.
     * Handle JPopupMenu showing if e.isPopupTrigger().
     * Handle Tangram pieces dragging and rotating.
     * After each Point rotation of 45 degrees,
     * a Point will not be aligned to the grid.
     * So, alignShape() method from the Outer class
     * is called after each mouse released.
     */
    private class LocalMouseListener extends MouseAdapter {

        private Group selectedPiece;
        private Point mousePosOnClick;

        /**
         * Save the mouse position inside a class field.
         * Handle JPopupTrigger.
         * Save the Tangram piece inside a class field if
         * the mouse is pressed on it.
         * Call putTangramPieceAtFront(Group piece) with
         * this selected Tangram piece as argument.
         * @param e the event to be processed
         */
        @Override
        public void mousePressed(MouseEvent e) {

            // Saving mousePos on click
            mousePosOnClick = new Point(e.getX(), e.getY());

            // JPopUpMenu
            if (e.isPopupTrigger()) {
                JPopupMenu popupMenu = view.getPopupMenu();
                popupMenu.show(view, (int) mousePosOnClick.getX(), (int) mousePosOnClick.getY());
            }

            // Tangram Pieces selection
            for (Group piece : model.getTangramPieces()) {
                // index 0 = the Shape
                if (piece.getShapeGroup().get(0).contains(mousePosOnClick)) {
                    selectedPiece = piece;
                    model.putTangramPieceAtFront(selectedPiece);
                    break;
                } else {
                    selectedPiece = null;
                }
            }
        }

        /**
         * Handle Tangram pieces dragging and rotating.
         * @param e the event to be processed
         */
        @Override
        public void mouseDragged(MouseEvent e) {

            if (selectedPiece != null) {

                // Checking if mousePos is inside Tangram piece center Circle
                boolean translating = selectedPiece.getShapeGroup().get(1).contains(mousePosOnClick);

                // Translating the piece
                if (translating) {
                    int dx = 0;
                    int dy = 0;
                    int step = ViewTangram.STEP;
                    dx += (int) (e.getX() - mousePosOnClick.getX());
                    dy += (int) (e.getY() - mousePosOnClick.getY());
                    if (Math.abs(dx) > step || Math.abs(dy) > step) {
                        int dxAligned = (dx / step) * step;
                        int dyAligned = (dy / step) * step;
                        Vector vector = new Vector(dxAligned, dyAligned);
                        model.translateFrontPiece(vector);
                        mousePosOnClick = new Point(
                                (int) (mousePosOnClick.getX() + dxAligned),
                                (int) (mousePosOnClick.getY() + dyAligned)
                        );
                    }

                // Rotating the piece
                } else {
                    double angleStep = 45;
                    double deltaX = mousePosOnClick.getX() - selectedPiece.getShapeGroup().get(0).getCenter().getX();
                    double deltaY = mousePosOnClick.getY() - selectedPiece.getShapeGroup().get(0).getCenter().getY();
                    Angle angleMouseShapeCenterAxeX = new Angle(deltaX, deltaY);
                    double deltaX2 = e.getX() - selectedPiece.getShapeGroup().get(0).getCenter().getX();
                    double deltaY2 = e.getY() - selectedPiece.getShapeGroup().get(0).getCenter().getY();
                    Angle angleMouseDraggedShapeCenterAxeX = new Angle(deltaX2, deltaY2);
                    Angle angleToRotate = angleMouseDraggedShapeCenterAxeX.minus(angleMouseShapeCenterAxeX);
                    if (Math.abs(angleToRotate.getDegrees()) >= angleStep) {
                        if (angleToRotate.getDegrees() < 0) model.rotateFrontShape(Angle.inDegrees(-angleStep));
                        else model.rotateFrontShape(Angle.inDegrees(+angleStep));
                        mousePosOnClick = new Point(e.getX(), e.getY());
                    }
                }
            }
        }

        /**
         * Call checkWin() and alignShape() when mouse is released.
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            alignShape(model.getFrontPiece().getShapeGroup().get(0));
            checkWin();
        }

    }

}
