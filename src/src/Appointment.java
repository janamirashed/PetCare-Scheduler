import java.time.LocalDateTime;

public class Appointment {
    private String type;
    private LocalDateTime dateTime;
    private String notes;

    public Appointment(String appType, LocalDateTime dateTime, String notes) {
        this.type = appType;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public String getAppType() {
        return type;
    }
    public void setAppType(String appType) {
        this.type = appType;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String toString() {
        return "Appointment{" +
                "type='" + type + '\'' +
                ", dateTime=" + dateTime +
                ", notes='" + notes + '\'' +
                '}';
    }
}
