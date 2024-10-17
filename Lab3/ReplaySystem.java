package Lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class ReplaySystem {
    private List<GameEvent> eventLog;


    public ReplaySystem() {
        this.eventLog = new ArrayList<>();
    }

    public void logEvent(String eventType, List<String> eventData){
        long timestamp = System.currentTimeMillis();
        GameEvent event = new GameEvent(timestamp, eventType, eventData);
        eventLog.add(event);
    }

    public void saveLogsToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename, true))) {

            oos.writeObject(eventLog);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadLogsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {

            eventLog = (List<GameEvent>) ois.readObject();

            System.out.println("Number of events: " + eventLog.size());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void replayEvents(List<GameEvent> events) {
        if (events.isEmpty()) {
            System.out.println("No events to replay.");
            return;
        }

        long startTime = events.getFirst().getTimestamp();

        for (GameEvent event : events) {
            long currentTime = event.getTimestamp();
            long timeDiff = currentTime - startTime;

            try {
                Thread.sleep(timeDiff);  // Simulate event timing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(event);

            startTime = currentTime;
        }
    }

    public List<GameEvent> getEventLog() { return this.eventLog; }

    public void clearLog() {
        this.eventLog.clear();
    }
}
