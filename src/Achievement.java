import java.time.LocalDateTime;
public class Achievement {
    private static int idCounter = 1000; // Starting sequence for IDs
    private long achievementID;
    private String achievementName;
    private String achievementDescription;
    private LocalDateTime achievementDateAchieved;
    private String type; // New field for type

    // Constructor
    public Achievement(String achievementName, String achievementDescription, LocalDateTime achievementDateAchieved, long studentID, String type) {
        this.achievementID = generateUniqueID();
        setAchievementName(achievementName);
        setAchievementDescription(achievementDescription);
        setAchievementDateAchieved(achievementDateAchieved);
        setType(type);
    }

    public static synchronized int generateUniqueID() {
        return idCounter++;
    }

    // New Method: Validate Type
    public static boolean isValidType(String type) {
        // Define a list of valid types
        return type != null && 
        (type.equalsIgnoreCase("Academic") ||
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Achievement that = (Achievement) obj;
        return achievementName.equalsIgnoreCase(that.achievementName);
    }


    // ToString
    @Override
    public String toString() {
        return "Achievement {" +
                "achievementID=" + achievementID +
                ", achievementName='" + achievementName + '\'' +
                ", achievementDescription='" + achievementDescription + '\'' +
                ", achievementDateAchieved=" + achievementDateAchieved +
                ", type='" + type + '\'' +
                '}';
    }
}