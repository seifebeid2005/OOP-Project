
import java.util.Date;

public class UserAchievement {

    // Attributes specific to UserAchievement
    private Long StudentID;
    private int userAchievementID;
    private int achievementID;
    private Date dateAwarded;

    // Constructor
    public UserAchievement(int userAchievementID, int achievementID, Date dateAwarded, Long StudentID) {
        this.StudentID = StudentID;
        this.userAchievementID = userAchievementID;
        this.achievementID = achievementID;
        this.dateAwarded = dateAwarded;
    }

    // Getters and Setters for UserAchievement-specific attributes
    public int getUserAchievementID() {
        return userAchievementID;
    }

    public void setUserAchievementID(int userAchievementID) {
        this.userAchievementID = userAchievementID;
    }

    public int getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(int achievementID) {
        this.achievementID = achievementID;
    }

    public Date getDateAwarded() {
        return dateAwarded;
    }

    public void setDateAwarded(Date dateAwarded) {
        this.dateAwarded = dateAwarded;
    }

    public Long getStudentID() {
        return StudentID;
    }

    public void setStudentID(Long StudentID) {
        this.StudentID = StudentID;
    }


    @Override
    public String toString() {
        return "UserAchievement { " +
                "userAchievementID=" + userAchievementID +
                ", achievementID=" + achievementID +
                ", dateAwarded=" + dateAwarded +
                ", StudentID=" + StudentID +
                " }";
    }

}
