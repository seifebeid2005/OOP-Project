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
    private Tutor tutor;
    
    private static int lastGeneratedID = 0;

    // Static method to generate a unique ID
    private static long generateAutoID() {
        String year = String.valueOf(LocalDateTime.now().getYear()).substring(2); // Get last two digits of the year
        lastGeneratedID++; // Increment the last generated ID
        return Long.parseLong(year + String.format("%04d", lastGeneratedID)); // Combine year and incremented number
    }

    // Constructor
    public Student(String name, String email, String preferredLanguage, Integer currentLevel, Integer progressLevel,String achievements, Integer schoolID, LocalDate dateOfBirth, LocalDateTime registrationDate, String phone, String address, String username, String password, Tutor tutor) {

        super(generateAutoID(), name, email, dateOfBirth, phone, address, username, password); // Pass generated ID to the superclass
        validatePreferredLanguage(preferredLanguage);
        validateCurrentLevel(currentLevel);
        validateProgressLevel(progressLevel);
        validateSchoolID(schoolID);

        this.preferredLanguage = preferredLanguage;
        this.currentLevel = currentLevel;
        this.progressLevel = progressLevel;
        this.achievements = achievements != null ? achievements : ""; // Allow achievements to be null
        this.registrationDate = (registrationDate != null) ? registrationDate : LocalDateTime.now(); // Use passed registrationDate or default to now
        this.schoolID = schoolID;
        this.tutor = tutor;
    }

    // Validation Methods

    private void validatePreferredLanguage(String preferredLanguage) {
        if (preferredLanguage == null || preferredLanguage.isEmpty()) {
            throw new IllegalArgumentException("Preferred language cannot be null or empty.");
        }
    }

    private void validateCurrentLevel(Integer currentLevel) {
        if (currentLevel == null || currentLevel <= 0) {
            throw new IllegalArgumentException("Current level must be a positive integer.");
        }
    }

    private void validateProgressLevel(Integer progressLevel) {
        if (progressLevel < 0 || progressLevel > 100) {
            throw new IllegalArgumentException("Progress level must be between 0 and 100.");
        }
    }

    private void validateSchoolID(Integer schoolID) {
        if (schoolID == null || schoolID <= 0) {
            throw new IllegalArgumentException("School ID must be a positive integer.");
        }
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

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
    

    @Override
    public String toString() {
        return super.toString() + String.format(", preferredLanguage='%s', currentLevel=%d, progressLevel=%d, achievements='%s', " +
                        "registrationDate='%s', schoolID=%d}",
                preferredLanguage, currentLevel, progressLevel, achievements,
                registrationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), schoolID);
    }
}
