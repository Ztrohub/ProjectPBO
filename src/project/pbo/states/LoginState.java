package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.input.MouseManager;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class LoginState extends State implements SIZE, Pesan {

    private final JTextField jUsername = new JTextField();
    private final JPasswordField jPassword = new JPasswordField();

    private final Rectangle loginBtn = new Rectangle(247,480,130,43);
    private final Rectangle registBtn = new Rectangle(80,480, 130, 43 );

    private final MouseManager mouseManager;
    private final ArrayList<User> users;
    private Clip clip;
    private Clip click;

    public LoginState(Handler handler) {
        super(handler);
        jUsername.setBounds(80, 310, 295, 35);
        jUsername.setMargin(new Insets(0, 5, 0, 0));
        jUsername.setFont(new Font("SansSerif", Font.BOLD, 20));
        jPassword.setBounds(80, 400, 295, 35);
        jPassword.setMargin(new Insets(0, 5, 0, 0));
        jPassword.setFont(new Font("SansSerif", Font.BOLD, 20));

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        };

        jUsername.addActionListener(action);
        jPassword.addActionListener(action);

        this.mouseManager = handler.getMouseManager();

        this.users = handler.getDb().getUsers();

    }

    void login(){
        String username = jUsername.getText();
        String password = String.valueOf(jPassword.getPassword());

        boolean ada = false;
        for (User user : users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                ada = true;
                clip.stop();
                clip.setFramePosition(0);
                handler.getGame().getWindow().getLayeredPane().remove(jUsername);
                handler.getGame().getWindow().getLayeredPane().remove(jPassword);

                setCurrentState(new LoadingState(handler, new MainMenu(handler, user)));
            }
        }

        if (!ada){
            buatPesan("Username/Password tidak cocok!", JOptionPane.ERROR_MESSAGE, handler);
        }
    }

    @Override
    public void tick() {

        if ((mouseManager.isLeftPressed() || mouseManager.isRightPressed()) && loginBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
            click.stop();
            click.flush();
            click.setFramePosition(0);
            click.start();
            login();
            mouseManager.setLeftPressed(false);
            mouseManager.setRightPressed(false);

        }
        if ((mouseManager.isLeftPressed() || mouseManager.isRightPressed()) && registBtn.contains(mouseManager.getMouseX(), mouseManager.getMouseY())){
            click.stop();
            click.flush();
            click.setFramePosition(0);
            click.start();
            JTextField u = new JTextField();
            JPasswordField p = new JPasswordField();
            JPasswordField c = new JPasswordField();
            JPanel jPanel = new JPanel(new GridLayout(0,1));
            jPanel.add(new JLabel("Username: "));
            jPanel.add(u);
            jPanel.add(new JLabel("Password: "));
            jPanel.add(p);
            jPanel.add(new JLabel("Confirm Password: "));
            jPanel.add(c);
            int result = JOptionPane.showConfirmDialog(handler.getGame().getWindow(), jPanel, "Register",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {

                String username = u.getText();
                String password = String.valueOf(p.getPassword());
                String confirm = String.valueOf(c.getPassword());

                boolean ada = false;
                for (User user : users){
                    if (user.getUsername().equals(username)){
                        ada = true;
                        break;
                    }
                }

                if (username.isEmpty() || password.isEmpty()) buatPesan("Field tidak boleh kosong!", JOptionPane.ERROR_MESSAGE, handler);
                else {
                    if (ada) buatPesan("Username sudah terdaftar!", JOptionPane.ERROR_MESSAGE, handler);
                    else {
                        if (password.equals(confirm)) {
                            users.add(new User(username, password));
                            buatPesan("User " + username + " berhasil didaftarkan!", JOptionPane.INFORMATION_MESSAGE, handler);
                            handler.saveFile();
                        } else
                            buatPesan("Password dan Confirm Password tidak cocok!", JOptionPane.ERROR_MESSAGE, handler);
                    }
                }

            }

            mouseManager.setLeftPressed(false);
            mouseManager.setRightPressed(false);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.loginBG, 0, 0, width, height, null);
    }

    @Override
    public void playMusic() {
        clip = Assets.audioLogin;
        click = Assets.audioClick;
        handler.setVol(click, 0.1f);
        handler.setVol(clip, 0.1f);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        handler.getGame().getWindow().getLayeredPane().add(jUsername,0);
        handler.getGame().getWindow().getLayeredPane().add(jPassword,0);
        jUsername.requestFocusInWindow();
    }
}
