package project.pbo.states;

import project.pbo.Handler;

import javax.swing.*;

public interface Pesan {

    default void buatPesan(String pesan, int type, Handler handler){
        JOptionPane.showMessageDialog(handler.getGame().getWindow(), pesan, pesan, type);
    }
}
