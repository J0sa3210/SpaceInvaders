package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import javax.swing.*;
import java.awt.*;

/**
 * The <code>SpriteLabel</code> class represents a label with customized sprite-like visual appearance.
 * It extends the {@link JLabel} class and provides methods for setting the text, font, and color of the label.
 */
public class SpriteLabel extends JLabel {
    /**
     * Constructs a `SpriteLabel` object with the specified text and font size.
     *
     * @param text The text to be displayed on the label.
     * @param size The font size of the text.
     */
    public SpriteLabel(String text, int size) {
        this.setText(text);
        this.setFont(new Font("MV Boli", Font.BOLD, size));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
    }
}