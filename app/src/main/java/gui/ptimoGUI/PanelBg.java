package gui.ptimoGUI;

import javax.swing.*;
import java.awt.*;

public class PanelBg extends JPanel {

    private final PtimoGUI gui;

    private Image bg;

    public PanelBg(PtimoGUI ptimoGui) {
        this.gui = ptimoGui;

        setBackground(Color.black);

    }

    public void setBg(String bg) {
        String backgroundsPath = "/ptimoImgs/bg/";
        this.bg = switch (bg) {
            case "forest" -> gui.getImageIcon(backgroundsPath +"forest.png").getImage();
            case "house" -> gui.getImageIcon(backgroundsPath +"house.png").getImage();
            default -> gui.getImageIcon(backgroundsPath +"MapArena.png").getImage();
        };
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg, 0,0, getWidth(), getHeight(), this);
    }
}

