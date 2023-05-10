import mvc.*;

import javax.swing.*;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Main class for Tangram Game !
 */
public class Main {

    /**
     * PSVM as usual.
     * SwingUtilities.invokeLater(Main::setWindow)
     * to start the Swing Event Dispatcher thread.
     * @param args no used.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::setWindow);
    }

    /**
     * Initialize the frame, the Model, the View and the Controller.
     * Set the Frame visible.
     */
    private static void setWindow() {
        // Frame settings
        JFrame frame = new JFrame("Tangram Game !");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        // MVC Initialization
        ModelTangram model = new ModelTangram();
        ViewTangram view = new ViewTangram(model);
        new ControllerTangram(model, view);
        // Frame showing
        frame.setContentPane(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
