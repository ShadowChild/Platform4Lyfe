package co.uk.shadowchild.platformer.console;

import javax.swing.*;


/**
 * @author ShadowChild
 */
public class Console {

    public JTextArea textArea = new JTextArea();
    JFrame frame;

    public Console() {

        frame = new JFrame("Console");
        frame.setSize(1000, 640);
        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }
}