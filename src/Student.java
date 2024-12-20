
import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person {
    
    private final LocalDate registrationDate;
    private int schoolID;
    private static Long lastGeneratedID = (long)1;
    private ArrayList<Grade> marks;
    private ArrayList<Course> courses;
    private Progress progress;


    public Student(){
        super();
        this.registrationDate = LocalDate.now();  // Use current date if null
        this.schoolID = 0;
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
        this.progress = new Progress();
    }

    public Student(String name, String email, LocalDate dateOfBirth, int schoolID, String phone, String address, String username, String password) {
        super(lastGeneratedID++, name, email, dateOfBirth, phone, address, username, password);
        this.registrationDate = LocalDate.now();  // Use current date if null
        this.schoolID = schoolID;
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
    }
    
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

    // view courses that student is enrolled in
    public void viewCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses to display.");
        } else {
            courses.forEach(System.out::println);
        }
    }


    // view lessons in each course
    public void viewLessons() {
        for (Course course : courses) {
            System.out.println(course.getCourseName());
            // Assuming Course class has a method to get lessons
            for (Lesson lesson : course.getLessons()) {
                System.out.println(lesson);
            }
        }
    }

    //view quiz result
    public void viewQuizResult() {
        for (Course course : courses) {
            System.out.println(course.getCourseName());
            // Assuming Course class has a method to get lessons
            for (Lesson lesson : course.getLessons()) {
                System.out.println("Lesson: " + lesson.getLessonTitle());
                // Assuming Lesson class has a method to get a quiz
                Quiz quiz = lesson.getQuiz();
                if (quiz != null) {
                    System.out.println(quiz);
                } else {
                    System.out.println("No quiz available for this lesson.");
                }
            }
        }
    }

     // Start quiz
    public void startQuiz(long courseId, long lessonId) {
        Course course = courses.stream().filter(c -> c.getCourseId() == courseId).findFirst().orElse(null);
        if (course == null) {
            System.out.println("Course not found.");
            return; 
        }
        Lesson lesson = course.findLessonById(lessonId);
        if (lesson == null) {
            System.out.println("Lesson not found.");
            return;
        }
        Quiz quiz = lesson.getQuiz();
        if (quiz == null) {
            System.out.println("No quiz available for this lesson.");
            return;
        }
        System.out.println("Starting quiz: " + quiz.getQuiz_title());
        // Assuming Quiz class has a method to start the quiz
        quiz.answerQuestions();
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
