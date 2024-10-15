package Lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class ReplaySystem {
    private List<GameEvent> eventLog;
    private boolean paused = false;
    private List<Droid> topDroids;
    private List<Droid> bottomDroids;


    public ReplaySystem() {
        this.eventLog = new ArrayList<>();
        this.topDroids = new ArrayList<>();
        this.bottomDroids = new ArrayList<>();
    }

    public void logEvent(String eventType, List<String> eventData){
        long timestamp = System.currentTimeMillis();
        GameEvent event = new GameEvent(timestamp, eventType, eventData);
        eventLog.add(event);
    }

    public void replayEvents(List<GameEvent> events){
        long startTime = events.getFirst().getTimestamp();

        for (GameEvent event : events) {
            while (paused){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            long currentTime = event.getTimestamp();
            long timeDiff = currentTime - startTime;

            try {
                Thread.sleep(timeDiff);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (event.getEventType()) {
                case "fire":
                    handleFireEvent(event);
                    break;
                case "droidMove":
                    handleDroidMoveEvent(event);
                    break;
            }

            startTime = currentTime; // Update for next event
        }
    }

    private void handleFireEvent(GameEvent event) {

    }

    public void saveDroidNamesToFile(String filename, List<Droid> topDroids, List<Droid> bottomDroids) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            // Write top droid names
            for (Droid droid : topDroids) {
                writer.write(droid.getName() + " ");
            }
            writer.write("\n");

            // Write bottom droid names
            for (Droid droid : bottomDroids) {
                writer.write(droid.getName() + " ");
            }
            writer.write("\n");

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveEventLogToFile(String filename, List<GameEvent> eventLog) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Serialize and write the eventLog
            oos.writeObject(eventLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDroidNamesFromFile(String filename, List<Droid> availableDroids) {
        System.out.println("Loading droid names from " + filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read top droid names
            String topDroidsLine = reader.readLine();
            for (String droidName: topDroidsLine.split(" ")){
                topDroids.add(findDroidByName(droidName, availableDroids));
            }
            System.out.println("Top droids: " + topDroidsLine);

            // Read bottom droid names
            String bottomDroidsLine = reader.readLine();
            for (String droidName: bottomDroidsLine.split(" ")){
                bottomDroids.add(findDroidByName(droidName, availableDroids));
            }
            System.out.println("Bottom droids: " + bottomDroidsLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadEventLogFromFile(String filename) {
        System.out.println("Loading event log from " + filename);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // Deserialize the eventLog
            eventLog = (List<GameEvent>) ois.readObject();
            System.out.println("Loaded " + eventLog.size() + " events.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void handleDroidMoveEvent(GameEvent event){
        String droidName = event.getEventData().getFirst();
        int x = Integer.parseInt(event.getEventData().get(1));
        int y = Integer.parseInt(event.getEventData().get(2));
        String direction = event.getEventData().get(3);

        List<Droid> availableDroids = new ArrayList<>();

        availableDroids.addAll(topDroids);
        availableDroids.addAll(bottomDroids);

        Droid droid = findDroidByName(droidName, availableDroids);

        if (droid == null){
            System.out.println("Error: Could not find droid with name: " + droidName);
            return;
        }

        droid.setX(x);
        droid.setY(y);

        droid.move(direction, this);
    }

    public Droid findDroidByName(String droidName, List<Droid> availableDroids){
        for (Droid droid: availableDroids){
            if (droidName.equals(droid.getName())){
                return droid;
            }
        }

        return null;
    }

    public List<GameEvent> getEventLog() { return this.eventLog; }

    public void clearLog() {
        this.eventLog.clear();
    }
}
