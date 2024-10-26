package main.java.game.core;


import javax.swing.*;
import java.awt.event.KeyListener;

public class GameStateManager {
    private final JFrame frame;
    private boolean menuSelected = false;
    private boolean user1Selected = false;
    private boolean user2Selected = false;
    private boolean gameOver = false;
    private KeyListener keyInputHandler;

    public GameStateManager(JFrame frame) {
        this.frame = frame;
    }

    public void setMenuSelected(boolean b) {
        menuSelected = b;
    }

    public void setKeyInputHandler(KeyListener newKeyInputHandler) {
        if (keyInputHandler != null) {
            frame.removeKeyListener(keyInputHandler);
        }

        keyInputHandler = newKeyInputHandler;

        frame.addKeyListener(keyInputHandler);
    }


    public void setUser1Selected(boolean b) {
        user1Selected = b;
    }

    public void setUser2Selected(boolean b) {
        user2Selected = b;
    }

    public void setGameOver(boolean b) {
        gameOver = b;
    }

    public boolean hasUser1Selected() {
        return user1Selected;
    }

    public boolean hasUser2Selected() {
        return user2Selected;
    }

    public boolean isMenuSelected() {
        return menuSelected;
    }

    public boolean isGameOver(){
        return gameOver;
    }
}
