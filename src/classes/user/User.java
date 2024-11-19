package classes.user;  // This must be the first statement in the file
import java.util.Date;  // Imports come after the package declaration

public class User {
    private Integer User_ID;
    private String Name;
    private String Email;
    private Integer Age;
    private String Preferred_Language;
    private Integer Current_Level;
    private Integer Progress_Level;
    private String Achievements;
    private Date Registration_Date;
    private Integer School_ID;

    // Constructor
    public User() {
        // Initialize with default values if needed
    }

    // Implementations for the methods
    public void login() {
        // Implementation for login
    }

    public void logout() {
        // Implementation for logout
    }

    public void register() {
        // Implementation for register
    }

    public void updateProfile() {
        // Implementation for updateProfile
    }

    // Getters and Setters for each field
    public Integer getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(Integer user_ID) {
        User_ID = user_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getPreferred_Language() {
        return Preferred_Language;
    }

    public void setPreferred_Language(String preferred_Language) {
        Preferred_Language = preferred_Language;
    }

    public Integer getCurrent_Level() {
        return Current_Level;
    }

    public void setCurrent_Level(Integer current_Level) {
        Current_Level = current_Level;
    }

    public Integer getProgress_Level() {
        return Progress_Level;
    }

    public void setProgress_Level(Integer progress_Level) {
        Progress_Level = progress_Level;
    }

    public String getAchievements() {
        return Achievements;
    }

    public void setAchievements(String achievements) {
        Achievements = achievements;
    }

    public Date getRegistration_Date() {
        return Registration_Date;
    }

    public void setRegistration_Date(Date registration_Date) {
        Registration_Date = registration_Date;
    }

    public Integer getSchool_ID() {
        return School_ID;
    }

    public void setSchool_ID(Integer school_ID) {
        School_ID = school_ID;
    }
}
