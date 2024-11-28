import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Achievement {
    private static long idCounter = 1000L; // Starting sequence for IDs
    private static final Set<String> achievementNames = new HashSet<>(); // Unique names

    private long achievementID;
    private String achievementName;
    private String achievementDescription;
    private LocalDateTime achievementDateAchieved;
    private long studentID;

    public Achievement(String achievementName, String achievementDescription, LocalDateTime achievementDateAchieved, long studentID) {
        this.achievementID = generateUniqueID();
        setAchievementName(achievementName);
        setAchievementDescription(achievementDescription);
        setAchievementDateAchieved(achievementDateAchieved);
        setStudentID(studentID);
    }

    public static synchronized long generateUniqueID() {
        return idCounter++;
    }

    public long getAchievementID() {
        return achievementID;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        if (achievementName == null || achievementName.trim().isEmpty()) {
            throw new IllegalArgumentException("Achievement name cannot be null or empty.");
        }
        if (achievementName.length() > 100) {
            throw new IllegalArgumentException("Achievement name cannot exceed 100 characters.");
        }
        if (achievementNames.contains(achievementName)) {
            throw new IllegalArgumentException("Achievement name must be unique. '" + achievementName + "' is already in use.");
        }
        
        if (this.achievementName != null) {
            achievementNames.remove(this.achievementName);
        }
        achievementNames.add(achievementName);
        this.achievementName = achievementName;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public void setAchievementDescription(String achievementDescription) {
        if (achievementDescription == null || achievementDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        if (achievementDescription.length() > 500) {
            throw new IllegalArgumentException("Description cannot exceed 500 characters.");
        }
        this.achievementDescription = achievementDescription;
    }

    public LocalDateTime getAchievementDateAchieved() {
        return achievementDateAchieved;
    }

    public void setAchievementDateAchieved(LocalDateTime achievementDateAchieved) {
        if (achievementDateAchieved == null) {
            throw new IllegalArgumentException("Date achieved cannot be null.");
        }
        if (achievementDateAchieved.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date achieved cannot be in the future.");
        }
        this.achievementDateAchieved = achievementDateAchieved;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        if ( studentID <= 0) {
            throw new IllegalArgumentException("Student ID must be a positive integer.");
        }
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "Achievement {" +
               "achievementID=" + achievementID +
               ", achievementName='" + achievementName + '\'' +
               ", achievementDescription='" + achievementDescription + '\'' +
               ", achievementDateAchieved=" + achievementDateAchieved +
               ", studentID=" + studentID +
               '}';
    }
}


