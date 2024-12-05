import java.time.LocalDateTime;
import java.util.Objects;

public class Achievement {
    private static long idCounter = 1000L; // Starting sequence for IDs
    private long achievementID;
    private String achievementName;
    private String achievementDescription;
    private LocalDateTime achievementDateAchieved;
    private long studentID;
    private String type; // New field for type

    // Constructor
    public Achievement(String achievementName, String achievementDescription, LocalDateTime achievementDateAchieved, long studentID, String type) {
        this.achievementID = generateUniqueID();
        setAchievementName(achievementName);
        setAchievementDescription(achievementDescription);
        setAchievementDateAchieved(achievementDateAchieved);
        setStudentID(studentID);
        setType(type); // Validate and set type
    }

    public static synchronized long generateUniqueID() {
        return idCounter++;
    }

    // New Method: Validate Type
    public static boolean isValidType(String type) {
        // Define a list of valid types
        return type != null && (type.equalsIgnoreCase("Academic") ||
                                type.equalsIgnoreCase("Sports") ||
                                type.equalsIgnoreCase("Community Service") ||
                                type.equalsIgnoreCase("Skill-Based") ||
                                type.equalsIgnoreCase("Other"));
    }

    // Getter and Setter for Type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!isValidType(type)) {
            throw new IllegalArgumentException("Invalid type. Allowed types are: Academic, Sports, Community Service, Skill-Based, Other.");
        }
        this.type = type;
    }

    // Existing Methods (Getters, Setters, Equals, HashCode, etc.)

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
        if (studentID <= 0) {
            throw new IllegalArgumentException("Student ID must be a positive integer.");
        }
        this.studentID = studentID;
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Achievement that = (Achievement) obj;
        return achievementName.equalsIgnoreCase(that.achievementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(achievementName.toLowerCase());
    }

    // ToString
    @Override
    public String toString() {
        return "Achievement {" +
                "achievementID=" + achievementID +
                ", achievementName='" + achievementName + '\'' +
                ", achievementDescription='" + achievementDescription + '\'' +
                ", achievementDateAchieved=" + achievementDateAchieved +
                ", studentID=" + studentID +
                ", type='" + type + '\'' +
                '}';
    }
}