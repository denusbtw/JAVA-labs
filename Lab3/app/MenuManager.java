package Lab3.app;

import Lab3.display.DisplayRenderer;
import Lab3.utils.ColorConverter;

import java.util.ArrayList;
import java.util.List;


public class MenuManager {
    private final List<String> menuChoices;
    private int selectedMenuChoiceIndex;
    private final GameManager gameManager;
    private final Game game;

    public MenuManager(GameManager gameManager, Game game) {
        menuChoices = initMenu();
        selectedMenuChoiceIndex = 0;
        this.gameManager = gameManager;
        this.game = game;
    }

    public void selectMenuChoice(){
        switch (selectedMenuChoiceIndex){
            case 0 -> System.exit(0);
            case 1 -> {
//                gameManager.playerVSPlayer();
                game.isMenuSelected = true;
                game.updateKeyHandler();
            }
            case 2 -> {
                gameManager.teamVSTeam();
                game.isAIPlaying = true;
                game.isMenuSelected = true;
                game.updateKeyHandler();
            }
            default -> {
                System.out.printf("%sINVALID CHOICE%s\n", ColorConverter.getTextColor("red"), ColorConverter.getReset());
                return;
            }
        }
    }

    public void switchMenuChoice(boolean next){
        selectedMenuChoiceIndex = (selectedMenuChoiceIndex + (next ? 1 : menuChoices.size() - 1)) % menuChoices.size();

        printMenuChoices();
    }

    public void printMenuChoices(){
        DisplayRenderer.clear();

        for (String choice: menuChoices) {
            if (menuChoices.indexOf(choice) == selectedMenuChoiceIndex) {
                System.out.print("--> ");
            }
            System.out.println(choice);
        }
    }

    public static List<String> initMenu(){
        List<String> menuChoices = new ArrayList<>();

        menuChoices.add("0. Exit");
        menuChoices.add("1. 1x1");
        menuChoices.add("2. 2x2");
        menuChoices.add("3. Show Replays");

        return menuChoices;
    }
}
