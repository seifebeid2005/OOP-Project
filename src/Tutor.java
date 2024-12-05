import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends Person {

    private String subjectArea;            // Primary subject expertise
    private LocalDate dateJoined;          // Date the tutor joined the institution
    private Role roleEnum;                 // Tutor's role (enum type)
    private final int schoolID;            // School ID where the tutor is employed
    private ArrayList<Course> courses;     // Courses the tutor teaches

    public enum Role {
        LEAD_TUTOR,
        ASSISTANT_TUTOR,
        TUTOR
    }

    // Constructor
    public Tutor(Long id, String name, String email, LocalDate dateOfBirth, String phone, String address,
                 String username, String password, String subjectArea, LocalDate dateJoined, Role roleEnum, int schoolID) {
        super(id, name, email, dateOfBirth, phone, address, username, password);
        this.subjectArea = validateNotEmpty(subjectArea, "Subject area cannot be null or empty.");
        this.dateJoined = dateJoined != null ? dateJoined : LocalDate.now(); // Default to current date if null
        this.roleEnum = roleEnum != null ? roleEnum : Role.TUTOR; // Default to "TUTOR" if roleEnum is null
        this.courses = new ArrayList<>(); // Initialize courses list
        this.schoolID = schoolID;
    }

    // Getter for subject area
    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = validateNotEmpty(subjectArea, "Subject area cannot be null or empty.");
    }

    // Getter for dateJoined
    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    // Getter for roleEnum
    public Role getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(Role roleEnum) {
        this.roleEnum = roleEnum;
    }

    // Getter for schoolID
    public int getSchoolID() {
        return schoolID;
    }

    // Calculate years of experience based on dateJoined
    public int getYearsOfExperience() {
        return LocalDate.now().getYear() - dateJoined.getYear();
    }

    // Course Management Methods
    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        courses.add(course);
    }

    public void removeCourse(long courseId) {
        courses.removeIf(course -> course.getCourseId() == courseId);
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses); // Return a copy for encapsulation
    }

    public void viewAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses to display.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    public Course findCourseById(long courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null; // Return null if no course is found
    }

    // Helper method to validate non-empty fields
    private String validateNotEmpty(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

    // Override toString to include Tutor-specific details
    @Override
    public String toString() {
        return super.toString() +
                ", SubjectArea='" + subjectArea + '\'' +
                ", DateJoined=" + dateJoined +
                ", Role='" + roleEnum + '\'' +
                ", YearsOfExperience=" + getYearsOfExperience() +
                ", CoursesCount=" + courses.size();
    }
}
