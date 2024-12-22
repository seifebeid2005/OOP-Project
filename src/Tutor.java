import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends Person {
    //  remove subjectArea
    private static long lastGeneratedID = 1;               
    private LocalDate dateJoined;         
    private Role roleEnum;                 
    private final int schoolID;           
    private ArrayList<Course> courses;  

    public enum Role {
        LEAD_TUTOR,
        ASSISTANT_TUTOR,
        TUTOR
    }
    
    public Tutor() {
        super();
        this.dateJoined = LocalDate.now(); // Use current date if null
        this.roleEnum = Role.TUTOR; // Default to "TUTOR" if roleEnum is null
        this.courses = new ArrayList<>(); // Initialize courses list
        this.schoolID = 0;
    }
    // Constructor
    public Tutor( String name, String email, LocalDate dateOfBirth, String phone, String address,
                 String username, String password, Role roleEnum, int schoolID) {
        super(lastGeneratedID++, name, email, dateOfBirth, phone, address, username, password);
        this.dateJoined = LocalDate.now(); // Default to current date if null
        this.roleEnum = roleEnum != null ? roleEnum : Role.TUTOR; // Default to "TUTOR" if roleEnum is null
        this.courses = new ArrayList<>(); // Initialize courses list
        this.schoolID = schoolID;
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

    // View tutor's student details with courseName from ManagementControl
    public void viewStudentDetails(String courseName) {
        Course course = findCourseByName(courseName);
        if (course == null) {
            System.out.println("Course not found.");
        } else {
            course.viewStudents();
        }
    }


    // Find course by name
    public Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null; // Return null if no course is found
    }

    //Find tutor by CourseId
    public Tutor findTutorByCourseId(long courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return this;
            }
        }
        return null; // Return null if no tutor is found
    }

    // Override toString to include Tutor-specific details
    @Override
    public String toString() {
        return super.toString() +
                ", DateJoined=" + dateJoined +
                ", Role='" + roleEnum + '\'' +
                ", YearsOfExperience=" + getYearsOfExperience() +
                ", CoursesCount=" + courses.size();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tutor)) return false;
        Tutor tutor = (Tutor) obj;
        return super.equals(obj) &&
                dateJoined.equals(tutor.dateJoined) &&
                roleEnum == tutor.roleEnum &&
                schoolID == tutor.schoolID;
    }
}
