package project.pbo.window;

import project.pbo.gfx.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements SIZE{

    private final Canvas canvas;

    public Window() {

        this.setTitle("Card Adventure");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        ImageIcon icon = new ImageIcon(ImageLoader.loadImage("/logo.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(Color.BLACK);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor c = toolkit.createCustomCursor(ImageLoader.loadImage("/cursor.png"), new Point(this.getContentPane().getX(), this.getContentPane().getY()), "img");
        this.getContentPane().setCursor(c);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));

        this.add(canvas);
        this.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
