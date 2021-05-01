package project.pbo.states;

import project.pbo.Handler;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class LoginState extends State implements SIZE {

    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();

    public LoginState(Handler handler) {
        super(handler);
        username.setBounds(20, 215, 370, 30);
        username.setMargin(new Insets(0, 5, 0, 0));
        username.setFont(new Font("SansSerif", Font.BOLD, 20));
        password.setBounds(20, 295, 370, 30);
        password.setMargin(new Insets(0, 5, 0, 0));
        password.setFont(new Font("SansSerif", Font.BOLD, 20));
        handler.getGame().getWindow().getLayeredPane().add(username,0);
        handler.getGame().getWindow().getLayeredPane().add(password,0);

        Clip clip = Assets.audioLogin;
        clip.start();

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.loginBG, 0, 0, width, height, null);
        g.setColor(new Color(0,0,0,0.5f));
        g.fillRect(10, 150, 430, 300);
        Text.drawString(g, "Username: ", 20, 200, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Password: ", 20, 280, false, Color.WHITE, Assets.regulerFont);
        g.setColor(Color.red);
        g.fillRect(280,370,110,50);
        g.fillRect(20,370, 150, 50 );
        Text.drawString(g, "Register", 38, 400, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Login", 300, 400, false, Color.WHITE, Assets.regulerFont);

//        Input
    }

}
