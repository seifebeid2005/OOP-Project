package classes.Student; // Ensure this matches the directory structure
import classes.person.Person; // Import the parent class
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Student extends Person {
    private String preferredLanguage;
    private Integer currentLevel;
    private Integer progressLevel;
    private String achievements;
    private LocalDateTime registrationDate;
    private Integer schoolID;
    private String username;
    private String password;
    private static int lastGeneratedID = 0;

    // Static method to generate a unique ID
    private static long generateAutoID() {
        String year = String.valueOf(LocalDateTime.now().getYear()).substring(2); // Get last two digits of the year
        lastGeneratedID++; // Increment the last generated ID
        return Long.parseLong(year + String.format("%04d", lastGeneratedID)); // Combine year and incremented number
    }   

    // Constructor
    public Student(String name, String email, String preferredLanguage, Integer currentLevel, Integer progressLevel,String achievements, Integer schoolID, LocalDate dateOfBirth, LocalDateTime registrationDate, String phone,String address, String username, String password) {
        super(generateAutoID(), name, email, dateOfBirth, phone, address); // Pass generated ID to the superclass
        this.preferredLanguage = preferredLanguage;
        this.currentLevel = currentLevel;
        this.progressLevel = progressLevel;
        this.achievements = achievements;
        this.registrationDate = LocalDateTime.now(); // Set the registration date to the current date and time
        this.schoolID = schoolID;
        this.username = username;
        this.password = password;
    }
    

    // Implementations for the methods
    public void login() {
        System.out.println(getName() + " logged in.");
    }

    public void logout() {
        System.out.println(getName() + " logged out.");
    }

    public void register() {
        System.out.println(getName() + " registered.");
    }

    public void updateProfile() {
        System.out.println(getName() + " updated their profile.");
    }

    // Getters and Setters
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Integer getProgressLevel() {
        return progressLevel;
    }

    public void setProgressLevel(Integer progressLevel) {
        this.progressLevel = progressLevel;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer schoolID) {
        this.schoolID = schoolID;
    }

    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s', email='%s', preferredLanguage='%s', currentLevel=%d, " +
                        "progressLevel=%d, achievements='%s', registrationDate='%s', schoolID=%d}",
                getId(), getName(), getEmail(), preferredLanguage, currentLevel, progressLevel, achievements,
                registrationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), schoolID);
    }
}
