package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import javax.swing.*;
import java.awt.*;

public class SpriteGameLabel extends JLabel {
    public SpriteGameLabel(String text, int size) {
        this.setText(text);
        this.setFont(new Font("MV Boli", Font.BOLD, size));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
    }
}
