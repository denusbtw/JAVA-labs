package main.java.game.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DroidSelectKeyInputHandler implements KeyListener {
    private final GameStateManager gameStateManager;
    private final DroidSelectManager droidSelectManager;

    public DroidSelectKeyInputHandler(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        this.droidSelectManager = new DroidSelectManager();
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT -> droidSelectManager.switchDroid(false);
            case KeyEvent.VK_RIGHT -> droidSelectManager.switchDroid(true);
            case KeyEvent.VK_ENTER -> {
                if (!gameStateManager.hasUser1Selected()){
                    gameStateManager.setUser1Selected(true);
                    gameStateManager.setKeyInputHandler(new DroidSelectKeyInputHandler(gameStateManager));
                    droidSelectManager.selectDroid(1);
                } else {
                    gameStateManager.setUser2Selected(true);
                    gameStateManager.setKeyInputHandler(new DroidMovementKeyInputHandler());
                    droidSelectManager.selectDroid(2);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
