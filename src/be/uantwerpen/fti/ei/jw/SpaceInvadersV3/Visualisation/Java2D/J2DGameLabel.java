package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import javax.swing.*;
import java.awt.*;

public class J2DGameLabel extends JLabel {
    public J2DGameLabel(String text, int size) {
        this.setText(text);
        this.setFont(new Font("MV Boli", Font.BOLD, size));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
    }
}
