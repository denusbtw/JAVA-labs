package main.java.game.ui;

import main.java.game.core.GameManager;
import main.java.game.core.TerminalDisplay;

import java.util.ArrayList;
import java.util.List;


public class MenuManager {
    private final GameManager gameManager;
    private final List<String> menuOptions;
    private int selectedMenuOption;

    public MenuManager() {
        gameManager = new GameManager();
        menuOptions = initMenu();
    }

    public void switchMenuOption(boolean next){
        selectedMenuOption = (selectedMenuOption + (next ? 1 : menuOptions.size() - 1)) % menuOptions.size();

        printMenuChoices();
    }

    public void selectMenuOption(){
        switch (selectedMenuOption){
            case 0 -> System.exit(0);
            case 1 -> gameManager.playerVSPlayer();
            case 2 -> gameManager.teamVSTeam();
        }
    }

    public void printMenuChoices(){
        TerminalDisplay.clearScreen();

        for (String choice: menuOptions) {
            if (menuOptions.indexOf(choice) == selectedMenuOption) {
                System.out.print("> ");
            }
            System.out.println(choice);
        }
    }

    public List<String> initMenu(){
        List<String> options = new ArrayList<>();

        options.add("Exit");
        options.add("Player VS Player");
        options.add("Team VS Team");
        options.add("Show Replays");

        return options;
    }
}
