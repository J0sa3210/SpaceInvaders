package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import javax.swing.*;
import java.awt.*;

public class J2DButton extends JButton {

    /**
     * Creates a new J2DButton with the specified text and size.
     *
     * @param text The text displayed on the button.
     * @param size The font size of the button text.
     */
    public J2DButton(String text, int size) {
        // Set the text displayed on the button
        this.setText(text);

        // Set the font style and size of the button text
        this.setFont(new Font("MV Boli", Font.BOLD, size));

        // Set the background color of the button as fully transparent
        this.setBackground(new Color(0, 0, 0, 0));

        // Set the button to be transparent (no visible background)
        this.setOpaque(false);

        // Set the text color of the button to white
        this.setForeground(Color.WHITE);

        // Remove the border around the button
        this.setBorder(null);

        // Center-align the text on the button
        this.setHorizontalTextPosition(SwingConstants.CENTER);

        // Disable the focus painting effect when the button is selected
        this.setFocusPainted(false);
    }
}
