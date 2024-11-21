package classes.Achievement;
import java.time.LocalDateTime;

public class Achievement {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime dateAchieved;
    private Integer studentID;

    public Achievement(Integer id, String title, String description, LocalDateTime dateAchieved, Integer studentID) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateAchieved = dateAchieved;
        this.studentID = studentID;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateAchieved() {
        return dateAchieved;
    }

    public void setDateAchieved(LocalDateTime dateAchieved) {
        this.dateAchieved = dateAchieved;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "Achievement {" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", dateAchieved=" + dateAchieved +
               ", studentID=" + studentID +
               '}';
    }
}

