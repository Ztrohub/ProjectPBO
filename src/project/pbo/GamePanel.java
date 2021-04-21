package project.pbo;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static int width, height;

    public GamePanel(int width, int height){
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }
}
