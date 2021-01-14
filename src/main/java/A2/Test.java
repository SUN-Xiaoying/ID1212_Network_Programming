package A2;

import javax.swing.*;
import java.awt.*;

public class Test extends JPanel {
    JTextField nameField;
    JPasswordField passwordField;
    String postURL;

    GridBagConstraints constraints = new GridBagConstraints();

    void addGB(Component component, int x, int y){
        constraints.gridx = x; constraints.gridy = y;
        add(component, constraints);
    }

    public static void main(String[] args) {
        if (args.length != 1){
            System.err.println("Must specify URL on command line");
            System.exit(-1);
        }

        JFrame frame = new JFrame("Simple Post");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
