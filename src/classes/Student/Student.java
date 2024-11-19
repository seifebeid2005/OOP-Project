package classes.Student;  // This must be the first statement in the file
import classes.person.Person;  // Imports come after the package declaration
import java.util.Date;  // Importing the Person class from the person package

public class Student extends Person {  // Class declaration
    private Integer Student_ID;
    private String Preferred_Language;
    private Integer Current_Level;
    private Integer Progress_Level;
    private String Achievements;
    private Date Registration_Date;
    private Integer School_ID;

    // Constructor
    public Student(int Student_ID, String name, int age, String email, String preferred_Language, Integer current_Level, Integer progress_Level, String achievements, Integer school_ID) {
        super(Student_ID, name, age, email);
        this.Preferred_Language = preferred_Language;
        this.Current_Level = current_Level;
        this.Progress_Level = progress_Level;
        this.Achievements = achievements;
        this.School_ID = school_ID;
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
    public Integer getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(Integer Student_ID) {
       this.Student_ID = Student_ID;
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
