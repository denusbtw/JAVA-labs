package main.java.game.core;

import main.java.game.actions.Fire;
import main.java.game.movement.MovementManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class DroidMovementKeyInputHandler implements KeyListener {
    
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_LEFT -> MovementManager.move(droid, "left");
                case KeyEvent.VK_RIGHT -> MovementManager.move(droid, "right");
                case KeyEvent.VK_UP -> MovementManager.move(droid, "up");
                case KeyEvent.VK_DOWN -> MovementManager.move(droid, "down");
                case KeyEvent.VK_ENTER -> Fire.fire();

                case KeyEvent.VK_A -> MovementManager.move(droid, "left");
                case KeyEvent.VK_D -> MovementManager.move(droid, "right");
                case KeyEvent.VK_W -> MovementManager.move(droid, "up");
                case KeyEvent.VK_S -> MovementManager.move(droid, "down");
                case KeyEvent.VK_SPACE -> Fire.fire();
            }
        }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}