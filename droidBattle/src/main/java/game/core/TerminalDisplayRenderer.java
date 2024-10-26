package main.java.game.core;

import java.util.List;

public class TerminalDisplayRenderer {

    public void renderGameObjects(List<GameObject> gameObjects){
        for (GameObject gameObject : gameObjects) {
            gameObject.render();
        }
    }


    public static void refresh(List<GameObject> gameObjects){
        clearScreen();
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void moveTo(int x, int y) {
        System.out.printf("\033[%d;%dH", y, x);
    }

    public static void resetCursor() {
        moveTo(0, 0);
    }
}