package classes.UserAchievement;

import classes.person.Person;
import java.time.LocalDate;
import java.util.Date;

public class UserAchievement extends Person {

    // Attributes specific to UserAchievement
    private int userAchievementID;
    private int achievementID;
    private Date dateAwarded;

    // Constructor
    public UserAchievement(int id, String name, String email, LocalDate dateOfBirth, String phone, String address, 
                           int userAchievementID, int achievementID, Date dateAwarded) {
        super(id, name, email, dateOfBirth, phone, address); // Call to superclass constructor (Person)
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

    // Utility Methods
    public boolean isValidAchievement() {
        // Placeholder for validation logic (e.g., check database for user and achievement IDs)
        return getId() > 0 && achievementID > 0; // Reusing getId() from Person class
    }

    @Override
    public String toString() {
        return "UserAchievement { " +
                "userAchievementID=" + userAchievementID +
                ", achievementID=" + achievementID +
                ", dateAwarded=" + dateAwarded +
                ", name=" + getName() + // Reusing methods from Person class
                ", email=" + getEmail() +
                ", phone=" + getPhone() +
                ", address=" + getAddress() +
                ", dateOfBirth=" + getDateOfBirth() +
                ", age=" + getAge() + 
                " }";
    }
}
