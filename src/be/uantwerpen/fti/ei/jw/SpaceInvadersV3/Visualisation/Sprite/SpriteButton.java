package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import javax.swing.*;
import java.awt.*;

/**
 * The <code>SpriteButton</code> class extends the JButton class and represents a customized button for the Space Invaders game.
 * It provides a button with specific visual and formatting properties.
 */
public class SpriteButton extends JButton {

    /**
     * Creates a new SpriteButton instance with the specified text and size.
     *
     * @param text The text to be displayed on the button.
     * @param size The font size of the button text.
     */
    public SpriteButton(String text, int size) {
        this.setText(text);
        this.setFont(new Font("MV Boli", Font.BOLD, size));
        this.setBackground(new Color(0, 0, 0, 0));
        this.setOpaque(false);
        this.setForeground(Color.WHITE);
        this.setBorder(null);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setFocusPainted(false);
    }
}
