package Lab3;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main implements KeyListener {
    protected final Screen screen;
    private List<String> menuChoices;
//    private Queue menuChoicesQueue;
    private List<Droid> availableDroids;
    private int selectedMenuChoice = 0;
    private int selectedDroidIndex1 = 0;
    private int selectedDroidIndex2 = 0;
    private boolean menuSelected = false;
    private boolean user1Selected = false;
    private boolean user2Selected = false;
    private Droid topDroid;
    private Droid bottomDroid;
    private Droid AITopDroid;
    private Droid AIBottomDroid;
    private boolean AIPlays = false;
    private ColorConverter colorConverter;
    private List<Droid> topDroids;
    private List<Droid> bottomDroids;
    private SoundPlayer soundPlayer;
    private ReplaySystem replaySystem;
    private List<String> replaysFilesNames;
    private boolean replaySelecting = false;
    private int selectedReplay = 0;

    public Main(){
        screen = new Screen();
        screen.clear();

        replaySystem = new ReplaySystem();

        screen.setReplaySystem(replaySystem);

        menuChoices = initMenu();
        colorConverter = new ColorConverter();
        soundPlayer = new SoundPlayer();

        availableDroids = initializeAllDroidsList();

        topDroids = new ArrayList<>();
        bottomDroids = new ArrayList<>();

        JFrame myJFrame = new JFrame();
        myJFrame.addKeyListener(this);
        myJFrame.setVisible(true);
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Main mainInstance = new Main();

        mainInstance.printMenuChoices();

        /*TODO:
        1) queue for menu choices when user presses `esc` then go back
        2) replay system
        3) AI droid
        */
    }

    public void playerVSPlayer() {
        showSelectDroidInfo(availableDroids.getFirst(), 1, 1);
    }

    public void teamVSTeam() {
        AIPlays = true;

        showSelectDroidInfo(availableDroids.getFirst(), 1, 1);
    }

    public void showReplays() {
        replaySelecting = true;

        File replaysFolder = new File("replays");

        replaysFilesNames = List.of(replaysFolder.list());

        System.out.println("List of files and directories in the specified directory:");

        printReplaysChoices();
    }

    public void switchMenuChoice(boolean next){
        selectedMenuChoice = (selectedMenuChoice + (next ? 1 : menuChoices.size() - 1)) % menuChoices.size();

        printMenuChoices();
    }

    public void selectMenuChoice(){
        switch (selectedMenuChoice){
            case 0 -> System.exit(0);
            case 1 -> playerVSPlayer();
            case 2 -> teamVSTeam();
            case 3 -> showReplays();
            default -> {
                System.out.printf("%sINVALID CHOICE%s\n", colorConverter.getTextColor("red"), colorConverter.getTextColor("reset"));

                return;
            }
        }
        menuSelected = true;
    }

    public void printMenuChoices(){
        screen.clear();

        for (String choice: menuChoices) {
            if (menuChoices.indexOf(choice) == selectedMenuChoice) {
                System.out.print("--> ");
            }
            System.out.println(choice);
        }
    }

    public void switchReplaysChoice(boolean next) {
        selectedReplay = (selectedReplay + (next ? 1 : replaysFilesNames.size() - 1)) % replaysFilesNames.size();

        printReplaysChoices();
    }

    public void selectReplaysChoice() {
        String selectedReplayFile = replaysFilesNames.get(selectedReplay);

        replaySystem.loadLogsFromFile("replays/" + selectedReplayFile);

        List<GameEvent> events = replaySystem.getEventLog();

        if (events == null || events.isEmpty()) {
            System.out.println("Error: Event log is empty or replay file failed to load.");
            return;
        }

        replaySystem.replayEvents(events);

        replaySelecting = false;

        screen.clear();
        printMenuChoices();
    }

    public void printReplaysChoices() {
        screen.clear();

        for (String fileName: replaysFilesNames) {
            if (replaysFilesNames.indexOf(fileName) == selectedReplay) {
                System.out.print("--> ");
            }
            System.out.println(fileName);
        }
    }

    public static List<String> initMenu(){
        List <String> menuChoices = new ArrayList<>();

        menuChoices.add("0. Exit");
        menuChoices.add("1. 1x1");
        menuChoices.add("2. 2x2");
        menuChoices.add("3. Show Replays");

        return menuChoices;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (replaySelecting){
            soundPlayer.playSound("sounds/click_game_menu.wav");
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP -> switchReplaysChoice(false);
                case KeyEvent.VK_DOWN -> switchReplaysChoice(true);
                case KeyEvent.VK_ENTER -> selectReplaysChoice();
            }
        } else if (!menuSelected) {
            // Menu
            soundPlayer.playSound("sounds/click_game_menu.wav");
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> switchMenuChoice(false);
                case KeyEvent.VK_DOWN -> switchMenuChoice(true);
                case KeyEvent.VK_ENTER -> selectMenuChoice();
            }
        } else if (!user1Selected) {
            // User 1 selecting droid
            soundPlayer.playSound("sounds/click_game_menu.wav");
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> switchDroid(1, true);
                case KeyEvent.VK_LEFT -> switchDroid(1, false);
                case KeyEvent.VK_ENTER -> {
                    try {
                        selectDroid(1);
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }

                    screen.clear();
                    showSelectDroidInfo(availableDroids.getFirst(), 1, 2);
                }
            }
        } else if (!user2Selected) {
            soundPlayer.playSound("sounds/click_game_menu.wav");
            // User 2 selecting droid
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> switchDroid(2, true);
                case KeyEvent.VK_LEFT -> switchDroid(2, false);
                case KeyEvent.VK_ENTER -> {
                    try {
                        selectDroid(2);
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        } else {
            // Both users have selected droids, start game logic
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> topDroid.move("up", replaySystem);
                case KeyEvent.VK_A -> topDroid.move("left", replaySystem);
                case KeyEvent.VK_S -> topDroid.move("down", replaySystem);
                case KeyEvent.VK_D -> topDroid.move("right", replaySystem);
                case KeyEvent.VK_SPACE -> {
                    topDroid.fire("down", bottomDroids, replaySystem);
                    soundPlayer.playSound("sounds/blaster.wav");
                }

                case KeyEvent.VK_UP -> bottomDroid.move("up", replaySystem);
                case KeyEvent.VK_LEFT -> bottomDroid.move("left", replaySystem);
                case KeyEvent.VK_DOWN -> bottomDroid.move("down", replaySystem);
                case KeyEvent.VK_RIGHT -> bottomDroid.move("right", replaySystem);
                case KeyEvent.VK_ENTER -> {
                    bottomDroid.fire("up", topDroids, replaySystem);
                    soundPlayer.playSound("sounds/blaster.wav");
                }

            }
        }
    }

    private void showSelectDroidInfo(Droid droid, int index, int user){
        screen.clear();
        System.out.printf("USER %d:\n\n", user);
        System.out.printf("%s%s%s\n", colorConverter.getTextColor(droid.getColor()), droid.getString(), colorConverter.getTextColor("reset"));
        System.out.println(droid.toString());
        System.out.printf("Droid (%d/%d)\n", index, availableDroids.size());

        String firstString = "<- Previous droid";
        String middleString = "ENTER to select droid";
        String lastString = "Next droid ->";

        int padding = (screen.getMaxWidth() - firstString.length() - middleString.length() - lastString.length())/2;

        System.out.printf("%s%s%s%s%s", firstString, " ".repeat(padding), middleString, " ".repeat(padding), lastString);
    }

    private void switchDroid(int user, boolean forward) {
        Droid droid = availableDroids.getFirst();
        int selectedDroidIndex = 0;

        if (user == 1) {
            selectedDroidIndex1 = (selectedDroidIndex1 + (forward ? 1 : availableDroids.size() - 1)) % availableDroids.size();
            droid = availableDroids.get(selectedDroidIndex1);
            selectedDroidIndex = selectedDroidIndex1 + 1;
        } else if (user == 2) {
            selectedDroidIndex2 = (selectedDroidIndex2 + (forward ? 1 : availableDroids.size() - 1)) % availableDroids.size();
            droid = availableDroids.get(selectedDroidIndex2);
            selectedDroidIndex = selectedDroidIndex2 + 1;
        }

        showSelectDroidInfo(droid, selectedDroidIndex, user);
    }

    private void selectDroid(int user) throws CloneNotSupportedException {
        int startX = screen.getMaxWidth()/2-1;
        int startY = screen.getMaxHeight()-2;

        if (user == 1) {
            topDroid = (Droid) availableDroids.get(selectedDroidIndex1).clone();
            topDroid.setX(startX);
            topDroid.setY(3);

            user1Selected = true;

            replaySystem.logEvent("selectDroid", List.of("User: " + user, "Droid: " + topDroid.getName()));

            if (AIPlays){
                Random rand = new Random();

                topDroid.setX(startX-20);

                int randomDroidIndex = rand.nextInt(availableDroids.size());
                AITopDroid = (Droid) availableDroids.get(randomDroidIndex).clone();

                AITopDroid.setX(startX+20);
                AITopDroid.setY(3);

                replaySystem.logEvent("selectDroid", List.of("Computer " + user, "Droid: " + AITopDroid.getName()));
            }

        } else if (user == 2) {
            bottomDroid = (Droid) availableDroids.get(selectedDroidIndex2).clone();
            bottomDroid.setX(startX);
            bottomDroid.setY(startY);

            user2Selected = true;

            replaySystem.logEvent("selectDroid", List.of("User " + user, "Droid: " + bottomDroid.getName()));

            if (AIPlays){
                Random rand = new Random();

                bottomDroid.setX(startX-20);

                int randomDroidIndex = rand.nextInt(availableDroids.size());
                AIBottomDroid = (Droid) availableDroids.get(randomDroidIndex).clone();

                AIBottomDroid.setX(startX+20);
                AIBottomDroid.setY(startY);

                replaySystem.logEvent("selectDroid", List.of("Computer " + user, "Droid: " + AIBottomDroid.getName()));
            }

            startGame();
        }
    }

    private void startGame() {
        screen.addObject(topDroid);
        screen.addObject(bottomDroid);

        topDroids.add(topDroid);
        bottomDroids.add(bottomDroid);

        DroidHud topHud = null;
        DroidHud bottomHud = null;

        if (AIPlays) {
            screen.addObject(AITopDroid);
            screen.addObject(AIBottomDroid);

            topDroids.add(AITopDroid);
            bottomDroids.add(AIBottomDroid);

            topHud = new DroidHud(topDroid, AITopDroid,  "top", 1, 1, screen);
            bottomHud = new DroidHud(bottomDroid, AIBottomDroid,  "bottom", 1, screen.getMaxHeight()-1, screen);
        } else {
            topHud = new DroidHud(topDroid, "top", 1, 1, screen);
            bottomHud = new DroidHud(bottomDroid, "bottom", 1, screen.getMaxHeight()-1, screen);
        }

        screen.setTopDroids(topDroids);
        screen.setBottomDroids(bottomDroids);

        screen.addObject(topHud);
        screen.addObject(bottomHud);

        replaySystem.logEvent("gameStart", List.of());

        screen.clear();
        screen.drawAllObjects();
    }

    public List<Droid> initializeAllDroidsList(){
        List<Droid> droids = new ArrayList<>();

        Weapon weapon1 = new Weapon("yellow", 25, 2.4);
        Weapon weapon2 = new Weapon("purple", 18, 3.6);
        Weapon weapon3 = new Weapon("red", 30, 1.8);
        Weapon weapon4 = new Weapon("green", 20, 4.5);
        Weapon weapon5= new Weapon("yellow", 22, 3);

        Droid blazeBot = new Droid(
                screen,
                "[*]",
                "BlazeBot",
                "red",
                weapon1,
                150,
                2,
                "BlazeBot is a fierce combatant equipped with a highly powerful yellow weapon. Its attacks are swift, delivering solid damage while maintaining a good pace.",
                1,
                1,
                soundPlayer
        );

        Droid aquaDroid = new Droid(
                screen,
                "[&]",
                "AquaDroid",
                "blue",
                weapon2,
                120,
                1,
                "AquaDroid represents stability on the battlefield. With a purple weapon that balances damage and speed, it excels in tactical engagements.",
                1,
                1,
                soundPlayer
        );

        Droid smashTrooper = new Droid(
                screen,
                "[#]",
                "SmashTrooper",
                "green",
                weapon3,
                180,
                2,
                "SmashTrooper is a heavy-hitting combat machine. Its red weapon delivers crushing blows with slow, devastating attacks.",
                1,
                1,
                soundPlayer
        );

        Droid laserScout = new Droid(
                screen,
                "[+]",
                "LaserScout",
                "yellow",
                weapon4,
                110,
                3,
                "LaserScout is an agile fighter, using quick attacks to deal consistent damage while rapidly repositioning on the battlefield.",
                1,
                1,
                soundPlayer
        );

        Droid voltAssault = new Droid(
                screen,
                "[%]",
                "VoltAssault",
                "purple",
                weapon5,
                130,
                1,
                "VoltAssault is a versatile fighter with a balance between damage and speed, capable of holding its own in prolonged battles.",
                1,
                1,
                soundPlayer
        );

        droids.add(blazeBot);
        droids.add(aquaDroid);
        droids.add(smashTrooper);
        droids.add(laserScout);
        droids.add(voltAssault);

        return droids;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}