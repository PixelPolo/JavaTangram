package mvc;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 *  ALL RIGHTS RESERVED :
 *  UNIVERSITY OF FRIBOURG - SWISS

 * ViewTangram class extends JPanel.
 * It's the View of the MVC application model.
 * Implements java.util.Observer (which is deprecated since java 9).
 * 1. Controller listens events from the View.
 * 2. Controller modifies the Model according to the events.
 * 3. Model notifies the View if changes were made.
 * 4. View updates a boolean if it receives notifications.
 * 5. The thread check the boolean and repaint() if it's necessary.
 */

public class ViewTangram extends JPanel implements Observer, Runnable {

    // ***** FIELDS *****

    // Public constants
    public static final int STEP = 10;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    public static final Color BACKGROUND_COLOR = Color.lightGray;
    public static final Color WIN_BACKGROUND_COLOR = Color.BLACK;

    // Model
    private final ModelTangram model;

    // Buttons
    private JButton squareBtn;
    private JButton triangleBtn;
    private JButton rectangleBtn;
    private JButton parallelogramBtn;

    // PopUpMenu
    private JPopupMenu jPopupMenu;
    JMenuItem squareItem;
    JMenuItem triangleItem;
    JMenuItem rectangleItem;
    JMenuItem parallelogramItem;

    // Thread for Game Loop
    private final Thread thread;
    private boolean dirty;


    // ***** CONSTRUCTOR *****

    /**
     * Construct a View.
     * Store the Model.
     * Panel settings, JButtons and JPopupMenu creations.
     * Request the focus and start a new Thread for repainting.
     * @param model an object from ModelTangram class.
     */
    public ViewTangram(ModelTangram model) {
        // Model initialization
        this.model = model;
        model.addObserver(this);
        // Panel settings
        this.setDoubleBuffered(true);   // better rendering performance
        this.setBackground(BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension( (int) WIDTH, (int) HEIGHT));
        // Button and PopupMenu
        createBtn();
        createPopupMenu();
        // Graphics adjustment
        this.setFocusable(true);
        this.requestFocusInWindow();
        thread = new Thread(this);
        thread.start();
    }


    // ***** GETTERS *****

    /**
     * Get the "Square" JButton.
     * @return the "Square" JButton.
     */
    public JButton getSquareBtn() { return squareBtn; }

    /**
     * Get the "Triangle" JButton.
     * @return the "Triangle" JButton.
     */
    public JButton getTriangleBtn() { return triangleBtn; }

    /**
     * Get the "Rectangle" JButton.
     * @return the "Rectangle" JButton.
     */
    public JButton getRectangleBtn() { return rectangleBtn; }

    /**
     * Get the "Parallelogram" JButton.
     * @return "Parallelogram" JButton.
     */
    public JButton getParallelogramBtn() { return parallelogramBtn; }

    /**
     * Get the JPopupMenu.
     * @return the JPopupMenu.
     */
    public JPopupMenu getPopupMenu() { return jPopupMenu; }

    /**
     * Get the "Square" item of the JPopupMenu.
     * @return the "Square" item of the JPopupMenu.
     */
    public JMenuItem getSquareItem() { return squareItem; }

    /**
     * Get the "Triangle" item of the JPopupMenu.
     * @return the "Square" item of the JPopupMenu.
     */
    public JMenuItem getTriangleItem() { return triangleItem; }

    /**
     * Get the "Rectangle" item of the JPopupMenu.
     * @return the "Square" item of the JPopupMenu.
     */
    public JMenuItem getRectangleItem() { return rectangleItem; }

    /**
     * Get the "Parallelogram" item of the JPopupMenu.
     * @return the "Square" item of the JPopupMenu.
     */
    public JMenuItem getParallelogramItem() { return parallelogramItem; }


    // ***** METHODS *****

    /**
     * Game Loop.
     * In progress but it actually works.
     */
    @Override
    public void run() {
        while (thread != null) {
            try {
                Thread.sleep(1000/60);  // 60 fps
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (dirty) {
                repaint();
                dirty = false;
            }
        }
    }

    /**
     * Update is called when the Model notifies the View it has changed.
     * @param o the observable object.
     * @param arg an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public void update(Observable o, Object arg) {
        dirty = true;
    }

    /**
     * Override of paintComponent(Graphics) method from JComponent.
     * Cast Graphics to Graphics2D.
     * Draw the Tangram shapes to fill and pieces from the model.
     * @param g the <code>Graphics</code> object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        model.getFrontShapeToFill().g2DFill(g2D);
        for (int i = model.getTangramPieces().size() - 1; i >= 0; i--) {
            model.getTangramPieces().get(i).g2DFill(g2D);
        }
    }

    /**
     * Initialize the JButtons of the View.
     */
    private void createBtn() {
        squareBtn = new JButton("Square");
        squareBtn.setPreferredSize(new Dimension(150, 30));
        triangleBtn = new JButton("Triangle");
        triangleBtn.setPreferredSize(new Dimension(150, 30));
        rectangleBtn = new JButton("Rectangle");
        rectangleBtn.setPreferredSize(new Dimension(150, 30));
        parallelogramBtn = new JButton("Parallelogram");
        parallelogramBtn.setPreferredSize(new Dimension(150, 30));
        this.add(squareBtn);
        this.add(triangleBtn);
        this.add(rectangleBtn);
        this.add(parallelogramBtn);
    }

    /**
     * Initializes the JPopupMenu.
     */
    private void createPopupMenu() {
        jPopupMenu = new JPopupMenu();
        squareItem = new JMenuItem("Square");
        triangleItem = new JMenuItem("Triangle");
        rectangleItem = new JMenuItem("Rectangle");
        parallelogramItem = new JMenuItem("Parallelogram");
        jPopupMenu.add(squareItem);
        jPopupMenu.add(triangleItem);
        jPopupMenu.add(rectangleItem);
        jPopupMenu.add(parallelogramItem);
        this.add(jPopupMenu);
    }

}
