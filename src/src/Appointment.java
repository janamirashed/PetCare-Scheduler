import java.io.*;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    private String appType;
    private LocalDateTime dateTime;
    private String notes;

    public Appointment(String appType, LocalDateTime dateTime, String notes) {
        this.appType = appType;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public String getAppType() {
        return appType;
    }
    public void setAppType(String appType) {
        this.appType = appType;
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
                "type='" + appType + '\'' +
                ", dateTime=" + dateTime +
                ", notes='" + notes + '\'' +
                '}';
    }
}
