package main.java.game.core;

import java.io.IOException;
import java.util.Scanner;

public class TerminalDisplay {
    private final int width;
    private final int height;
    private final ObjectManager objectManager;
    private final TerminalDisplayRenderer displayRenderer;

    public TerminalDisplay() {
        width = getTerminalWidth();
        height = getTerminalHeight();
        objectManager = new ObjectManager();
        displayRenderer = new TerminalDisplayRenderer();
    }

    public void addGameObject(GameObject gameObject) {
        objectManager.addGameObject(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        objectManager.removeGameObject(gameObject);
    }

    public void renderGameObjects(){
        displayRenderer.renderGameObjects(objectManager.getGameObjects());
    }

    public void refresh() {
        TerminalDisplayRenderer.clearScreen();
        renderGameObjects();
        TerminalDisplayRenderer.resetCursor();
    }

    public int getTerminalWidth(){
        try {
            Process process = Runtime.getRuntime().exec("tput cols");
            Scanner scanner = new Scanner(process.getInputStream());
            return scanner.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
            return 80;
        }
    }

    public int getTerminalHeight(){
        try {
            Process process = Runtime.getRuntime().exec("tput lines");
            Scanner scanner = new Scanner(process.getInputStream());
            return scanner.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
            return 150;
        }
    }

    public int getWidth(){ return width; }

    public int getHeight(){ return height; }
}
