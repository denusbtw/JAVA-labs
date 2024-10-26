package main.java.game.core;

import main.java.game.ui.MenuManager;
import main.java.game.utils.SoundPlayer;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class menuKeyInputHandler implements KeyListener {
    private final GameStateManager gameStateManager;
    private final MenuManager menuManager;

    public menuKeyInputHandler(GameStateManager gameStateManager, MenuManager menuManager) {
        this.gameStateManager = gameStateManager;
        this.menuManager = menuManager;
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        SoundPlayer.playSound("sounds/click_game_menu.wav");
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_UP -> menuManager.switchMenuOption(false);
            case KeyEvent.VK_DOWN -> menuManager.switchMenuOption(true);
            case KeyEvent.VK_ENTER -> {
                gameStateManager.setMenuSelected(true);
                gameStateManager.setKeyInputHandler(new DroidSelectKeyInputHandler(gameStateManager));
                menuManager.selectMenuOption();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
