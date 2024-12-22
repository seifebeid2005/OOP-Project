import java.util.*;

public class UserAchievement {
    private static final List<UserAchievement> achievementHistory = new ArrayList<>();

    private long studentID;
    private Achievement achievement;
    private Date dateAwarded;

    // Constructor
    public UserAchievement(long studentID, Achievement achievement) {
        this.studentID = studentID;
        this.achievement = achievement;
        this.dateAwarded = new Date();
        achievementHistory.add(this);
    }

    public long getStudentID() {
        return studentID;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public Date getDateAwarded() {
        return dateAwarded;
    }

    @Override
    public String toString() {
        return "User ID: " + studentID + " | " + achievement.getAchievementName() +
                " | Awarded On: " + dateAwarded;
    }

    // Utility Methods
    public static List<UserAchievement> getAchievementsByStudent(long studentID) {
        List<UserAchievement> userAchievements = new ArrayList<>();
        for (UserAchievement ua : achievementHistory) {
            if (ua.getStudentID() == studentID) {
                userAchievements.add(ua);
            }
        }
        return userAchievements;
    }

    public static void displayAllAchievements() {
        achievementHistory.forEach(System.out::println);
    }
}