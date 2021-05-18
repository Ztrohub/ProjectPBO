package project.pbo.window;

import project.pbo.gfx.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements SIZE{

    private final Canvas canvas;
    private final JLayeredPane layeredPane;

    public Window() {

        this.setTitle("Card Adventure");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        ImageIcon icon = new ImageIcon(ImageLoader.loadImage("/images/logo.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(Color.BLACK);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor c = toolkit.createCustomCursor(ImageLoader.loadImage("/images/cursor.png"), new Point(this.getContentPane().getX(), this.getContentPane().getY()), "img");
        this.getContentPane().setCursor(c);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width,height));
        layeredPane.setLayout(null);

        canvas = new Canvas();
        canvas.setSize(new Dimension(width,height));
        canvas.setFocusable(false);

        layeredPane.add(canvas);

        this.add(layeredPane);

        this.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public JFrame getFrame(){
        return this;
    }
}
