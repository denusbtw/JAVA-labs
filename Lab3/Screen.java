package Lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Screen {
    private final int maxWidth;
    private final int maxHeight;
    private List<GameObject> objects;
    private ReplaySystem replaySystem;
    private List<Droid> topDroids;
    private List<Droid> bottomDroids;

    public Screen() {
        int[] dimensions = this.size();
        this.maxHeight = dimensions[0];
        this.maxWidth = dimensions[1];
        this.objects = new ArrayList<>();

        if (dimensions[0] * dimensions[1] == 1){
            System.out.println("Unable to determine terminal size.");
            System.exit(0);
        }
    }

    public int getMaxWidth(){
        return this.maxWidth;
    }

    public int getMaxHeight(){
        return this.maxHeight;
    }

    public List<GameObject> getObjects(){
        return this.objects;
    }

    public void addObject(GameObject object){
        this.objects.add(object);
    }

    public void removeObject(GameObject object){
        this.objects.remove(object);
    }

    public List<Droid> getTopDroids(){
        return this.topDroids;
    }

    public List<Droid> getBottomDroids(){
        return this.bottomDroids;
    }

    public void setTopDroids(List<Droid> droids){
        this.topDroids = droids;
    }

    public void setBottomDroids(List<Droid> droids){
        this.bottomDroids = droids;
    }

    public void setReplaySystem(ReplaySystem replaySystem){ this.replaySystem = replaySystem; }

    public int[] size() {
        int[] dimensions = new int[2];

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", "stty size < /dev/tty");

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.readLine();

            if (output != null && !output.isEmpty()) {
                String[] size = output.split(" ");

                int rows = Integer.parseInt(size[0]);
                int cols = Integer.parseInt(size[1]);

                dimensions[0] = rows;
                dimensions[1] = cols;
            } else {
                dimensions[0] = -1;
                dimensions[1] = -1;
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return dimensions;
    }

    public void clear(){
//        try {
//            new ProcessBuilder("clear").inheritIO().start().waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void drawAllObjects(){
        for (GameObject object : this.objects){
            object.draw();
        }
    }

    public void endGame(Droid droid){
        this.clear();

        String winnerName = (droid == null) ? "No winner" : droid.getName();
        String leftHealth = (droid == null) ? "" : String.valueOf(droid.getHealth());

        System.out.println("\n=====Game ended=====");
        System.out.println("Winner name: " + winnerName);
        System.out.println("Left health: " + leftHealth);
        System.out.println("====================");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fileName = "replays/" + dtf.format(now) + ".txt";

        replaySystem.saveLogsToFile(fileName);

        System.exit(0);
    }

    public void moveCursor(int x, int y){
        System.out.printf("\033[%d;%df", y, x);
    }
}
