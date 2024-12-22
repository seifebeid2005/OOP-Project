

public class Achievement {

    private static long idCounter = 1000; // Starting ID
    private long achievementID;
    private String achievementName;
    private String achievementDescription;

    // Constructor
    public Achievement(String achievementName, String achievementDescription) {
        this.achievementID = idCounter++;
        setAchievementName(achievementName);
        setAchievementDescription(achievementDescription);
    }

    public long getAchievementID() {
        return achievementID;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        if (achievementName == null || achievementName.trim().isEmpty()) {
            throw new IllegalArgumentException("Achievement name cannot be empty.");
        }
        this.achievementName = achievementName;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public void setAchievementDescription(String achievementDescription) {
        if (achievementDescription == null || achievementDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.achievementDescription = achievementDescription;
    }

    @Override
    public String toString() {
        return "Achievement: " + achievementName + " - " + achievementDescription;
    }

    // Ensure Unique Achievement Names
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Achievement that = (Achievement) obj;
        return achievementName.equalsIgnoreCase(that.achievementName);
    }

    // Predefined Achievements
    public static Achievement quizMaster() {
        return new Achievement("Quiz Master", "Awarded for scoring 100% on all quizzes.");
    }

    public static Achievement courseLord() {
        return new Achievement("Course Lord", "Awarded for completing all courses with 100% progress.");
    }

    public static Achievement consistencyChampion() {
        return new Achievement("Consistency Champion", "Awarded for maintaining progress above 90%.");
    }

    public static Achievement earlyBird() {
        return new Achievement("Early Bird", "Awarded for completing all courses before a deadline.");
    }

    public static Achievement perfectAttendance() {
        return new Achievement("Perfect Attendance", "Awarded for completing all lessons without skipping.");
    }

    public static Achievement subjectMaster(String subject) {
        return new Achievement("Subject Master - " + subject, "Awarded for achieving 100% progress in " + subject + ".");
    }

    public static Achievement topPerformer() {
        return new Achievement("Top Performer", "Awarded for consistently achieving an average progress above 95%.");
    

    }
}
