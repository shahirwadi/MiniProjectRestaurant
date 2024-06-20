import java.time.LocalDateTime;

public class TimeSession {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimeSession(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean overlaps(TimeSession other) {
        return startTime.isBefore(other.endTime) && endTime.isAfter(other.startTime);
    }
}