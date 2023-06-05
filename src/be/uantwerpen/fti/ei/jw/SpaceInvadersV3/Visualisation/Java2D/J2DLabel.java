package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code J2DLabel} class represents a label in the Java 2D visualization of the Space Invaders game.
 * It extends the {@link JLabel} class and is responsible for visualizing text on the screen.
 */
public class J2DLabel extends JLabel {

    /**
     * Constructs a {@code J2DLabel} object with the specified text and size.
     *
     * @param text The text to be displayed on the label.
     * @param size The font size of the label.
     */
    public J2DLabel(String text, int size) {
        this.setText(text);
        this.setFont(new Font("MV Boli", Font.BOLD, size));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
    }
}
