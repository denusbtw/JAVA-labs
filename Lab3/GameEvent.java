package Lab3;

import java.io.Serializable;
import java.util.List;

public class GameEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private long timestamp;
    private String eventType;
    private List<String> eventData;

    public GameEvent(long timestamp, String eventType, List<String> eventData) {
        this.timestamp = timestamp;
        this.eventType = eventType;
        this.eventData = eventData;
    }

    public long getTimestamp() { return this.timestamp; }

    public String getEventType() { return this.eventType; }

    public List<String> getEventData() { return this.eventData; }

    @Override
    public String toString() {
        return "GameEvent{" +
                "timestamp=" + timestamp +
                ", eventType='" + eventType + '\'' +
                ", eventData=" + eventData +
                '}';
    }
}
