
import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person {
    
    private final LocalDate registrationDate;
    private int schoolID;
    private static int lastGeneratedID = 0;
    private ArrayList<Grade> marks;
    private ArrayList<Course> courses;
    private Progress progress;


    private static long generateAutoID() {
        String year = String.valueOf(LocalDate.now().getYear()).substring(2); // Get last two digits of the year
        lastGeneratedID++; // Increment the last generated ID
        return Long.parseLong(year + String.format("%04d", lastGeneratedID)); // Combine year and incremented number
    }
    public Student(){
        super();
        this.registrationDate = LocalDate.now();  // Use current date if null
        this.schoolID = 0;
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
        this.progress = new Progress();
    }

    public Student(String name, String email, LocalDate dateOfBirth, int schoolID, String phone, String address, String username, String password) {
        super(generateAutoID(), name, email, dateOfBirth, phone, address, username, password);
        this.registrationDate = LocalDate.now();  // Use current date if null
        this.schoolID = schoolID;
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
    }

    // private void validateSchoolID(Integer schoolID) {
    //     if (schoolID <= 0) {
    //         throw new IllegalArgumentException("School ID must be a positive integer.");
    //     }
    // }

    // Methods related to Student actions
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

    public double getProgressPercentage() {
        return progress.getProgressPercentage();
    }

    public void enrollInCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            System.out.println(getName() + " enrolled in " + course.getCourseName());
        } else {
            System.out.println(getName() + " is already enrolled in " + course.getCourseName());
        }
    }

    // Getter and setter methods for attributes
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer schoolID) {
        this.schoolID = schoolID;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    // calculate progress
    private void calculateProgress() {
        progress.addCourse(courses);
        int coumpeletedCourses = 0;
        for(Course course: courses){
           if(course.areAllLessonsCompleted())
           {
                coumpeletedCourses++;
            }
        }
        progress.setProgressPercentage(coumpeletedCourses*100/courses.size());
    }
    
    //getAverageMarks
    public double getAverageMarks() {
        calculateProgress();
        return progress.getProgressPercentage();
    }
    
    //getCompletedCourses
    public ArrayList<Course> getCompletedCourses() {
        progress.addCourse(courses);
        return progress.getCompletedCourses();
    }
   
    //getNotCompletedCourses
    public ArrayList<Course> getNotCompletedCourses() {
        progress.addCourse(courses);
        return progress.getNotCompletedCourses();
    }
    
    //getMarksForQuiz
    public void getMarksForEachCourse() {
        for(Course course: courses){
            System.out.println(course.getCourseProgress());
        }
    }
    
    @Override
    public String toString() {
        return "Student{"
                + "registrationDate=" + registrationDate
                + ", schoolID=" + schoolID
                + ", marks=" + marks
                + ", courses=" + courses
                + "} " + super.toString();
    }
}
