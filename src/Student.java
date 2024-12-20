import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Person {

    private final LocalDate registrationDate;
    private int schoolID;
    private static long lastGeneratedID = 1; // Change from Long to long
    private ArrayList<Grade> marks;
    private ArrayList<Course> courses;
    private Progress progress;

    // Default constructor
    public Student() {
        super();
        this.registrationDate = LocalDate.now();  // Use current date if null
        this.schoolID = 0;
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
        this.progress = new Progress();
    }

    // Parameterized constructor
    public Student(String name, String email, LocalDate dateOfBirth, int schoolID, String phone, String address, String username, String password) {
        super(lastGeneratedID++, name, email, dateOfBirth, phone, address, username, password);
        this.registrationDate = LocalDate.now();  // Use current date if null
        this.schoolID = schoolID;
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
        this.progress = new Progress();
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
        calculateProgress();
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

    // Calculate progress (called by getAverageMarks)
    private void calculateProgress() {
        progress.addCourse(courses);
        int completedCourses = 0;
        for (Course course : courses) {
            if (course.areAllLessonsCompleted()) {
                completedCourses++;
            }
        }
        progress.setProgressPercentage((completedCourses * 100) / courses.size());
    }

    // Get the average progress percentage
    public void getAverageMarks() {
        for(Course course : courses) {
            System.out.println("Course: " + course.getCourseName() + " - Progress: " + course.getCourseProgress());
        }
        
    }

    // Get completed courses
    public ArrayList<Course> getCompletedCourses() {
        ArrayList<Course> completedCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.areAllLessonsCompleted()) {  // Uses the updated method with null check
                completedCourses.add(course);
            }
        }
        return completedCourses;
    }


    public ArrayList<Course> getNotCompletedCourses() {
        ArrayList<Course> notCompletedCourses = new ArrayList<>();
        for (Course course : courses) {
            if (!course.areAllLessonsCompleted()) {  // Uses the updated method with null check
                notCompletedCourses.add(course);
            }
        }
        return notCompletedCourses;
    }

    public void getMarksForEachCourse() {
        for (Course course : courses) {
            System.out.println("Course: " + course.getCourseName() + " - Progress: " + course.getCourseProgress());
        }
    }

    public void viewCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses to display.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    public void viewLessons() {
        for (Course course : courses) {
            System.out.println(course.getCourseName());
            course.viewLessons();
            System.out.println("...");
        }
    }

    public void viewQuizResult() {
        for (Course course : courses) {
            System.out.println(course.getCourseName());
            for (Lesson lesson : course.getLessons()) {
                System.out.println("Lesson: " + lesson.getLessonTitle());
                Quiz quiz = lesson.getQuiz();
                if (quiz != null && quiz.getGrade() != null) {
                    System.out.println("Grade: " + quiz.getGrade());
                } else {
                    System.out.println("No quiz or grade available.");
                }
            }
        }
    }


    // Start quiz
    public void startQuiz() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("Select a course:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
        }

        Scanner scanner = new Scanner(System.in);
        int courseIndex = scanner.nextInt() - 1;

        if (courseIndex < 0 || courseIndex >= courses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = courses.get(courseIndex);
        ArrayList<Lesson> lessons = selectedCourse.getLessons();

        if (lessons.isEmpty()) {
            System.out.println("No lessons available in this course.");
            return;
        }

        System.out.println("Select a lesson:");
        for (int i = 0; i < lessons.size(); i++) {
            System.out.println((i + 1) + ". " + lessons.get(i).getLessonTitle());
        }

        int lessonIndex = scanner.nextInt() - 1;

        if (lessonIndex < 0 || lessonIndex >= lessons.size()) {
            System.out.println("Invalid lesson selection.");
            return;
        }

        Lesson selectedLesson = lessons.get(lessonIndex);
        Quiz quiz = selectedLesson.getQuiz();

        if (quiz == null) {
            System.out.println("No quiz available for this lesson.");
            return;
        }

        System.out.println("Do you want to start the quiz: " + quiz.getQuiz_title() + "? (yes/no)");
        scanner.nextLine();  // Consume newline
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Starting quiz: " + quiz.getQuiz_title());
            quiz.answerQuestions();
        } else {
            System.out.println("Quiz not started.");
        }
    }

    @Override
    public String toString() {
        String courseList = courses.isEmpty() ? "No courses enrolled" : courses.toString();
        String marksList = marks.isEmpty() ? "No marks available" : marks.toString();

        return "Student {\n" +
                "  Registration Date: " + registrationDate + "\n" +
                "  School ID: " + schoolID + "\n" +
                "  Marks: " + marksList + "\n" +
                "  Courses: " + courseList + "\n" +
                "----------\n" +
                super.toString() + "\n" +
                "}";
    }
}
