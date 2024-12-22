import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends Person implements Comparable<Student> {

    private final LocalDate registrationDate;
    private int schoolID;
    private static long lastGeneratedID = 1; // Change from Long to long
    private ArrayList<Grade> marks;
    private ArrayList<Course> courses;
    private int progress; // Declare the progress variable

    // Default constructor
    public Student() {
        super();
        this.registrationDate = LocalDate.now();  // Use current date if null
        this.schoolID = 0;
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
    }

    // Parameterized constructor
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

    // Get the average progress percentage
    public void getAverageMarks() {
        for(Course course : courses) {
            System.out.println("Course: " + course.getCourseName() + " - Progress: " + course.getCourseProgress());
        }
        
    }

    public ArrayList<Course> getCompletedCourses() {
        ArrayList<Course> completedCourses = new ArrayList<>();
        
        // Return early if there are no courses or marks
        if (courses.isEmpty() || marks.isEmpty()) {
            return completedCourses;
        }
        
        for (Course course : courses) {
            boolean courseCompleted = true;

            for (Lesson lesson : course.getLessons()) {
                boolean lessonCompleted = false;

                // Check if the lesson is completed by finding a passing grade
                for (Grade grade : marks) {
                    if (grade != null && grade.getLessonId() != 0 && 
                        lesson.getLessonId() != 0 && grade.getLessonId() == lesson.getLessonId() && 
                        grade.getMarks() >= 5) {
                        lessonCompleted = true;
                        break; 
                    }
                }

                if (!lessonCompleted) {
                    courseCompleted = false;
                    break; 
                }
            }

           if (courseCompleted) {
                completedCourses.add(course);
            }
        }
        return completedCourses;
    }

    public ArrayList<Course> getNotCompletedCourses() {
    ArrayList<Course> notCompletedCourses = new ArrayList<>();
    
    // Return early if there are no courses or marks
    if (courses.isEmpty() || marks.isEmpty()) {
        return notCompletedCourses;
    }

    for (Course course : courses) {
        boolean courseCompleted = true;

        for (Lesson lesson : course.getLessons()) {
            boolean lessonCompleted = false;

            // Check if the lesson is completed by finding a passing grade
            for (Grade grade : marks) {
                if (grade != null && grade.getLessonId() != 0 && 
                    lesson.getLessonId() != 0 && grade.getLessonId() == lesson.getLessonId() && 
                    grade.getMarks() >= 5) {
                    lessonCompleted = true;
                    break; 
                }
            }

            if (!lessonCompleted) {
                courseCompleted = false;
                break; 
            }
        }

        // Add course to notCompletedCourses only if not all lessons are completed
        if (!courseCompleted) {
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
        if (marks.isEmpty()) {
            System.out.println("No quiz results available.");
        } else {
            marks.forEach(System.out::println);
        }
    }

    public ArrayList<Grade> getgrade(){
        return marks;
    }
    
    public void setgrade(Grade grade){
        marks.add(grade);
    }

    // Start quiz
    public void startQuiz(AchievementManager achievementManager) {
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

        for (Grade grade : marks) {
            if (grade.getLessonId() == selectedLesson.getLessonId()
                    && grade.getQuizId() == quiz.getQuiz_id()
                    && grade.getStudentid() == getId()) {
                System.out.println("You have already attempted this quiz.");
                return;
            }
        }

        System.out.println("Do you want to start the quiz: " + quiz.getQuiz_title() + "? (yes/no)");
        scanner.nextLine();  // Consume newline
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Starting quiz: " + quiz.getQuiz_title());
            int mark = quiz.answerQuestions();
            if (mark != 0) {
                marks.add(new Grade(selectedLesson.getLessonId(), mark, quiz.getQuiz_id(), getId()));
                System.out.println("You scored " + mark + " on this quiz.");

                // Calculate and display progress
                updateCourseProgress(selectedCourse);

                // Award achievements
                achievementManager.awardAllAchievements(getId());
            }
        } else {
            System.out.println("Quiz not started.");
        }
    }

// Method to calculate and update course progress
    private void updateCourseProgress(Course selectedCourse) {
        int totalQuizzes = selectedCourse.getLessons().size(); // Each lesson has one quiz
        int completedQuizzes = 0;

        for (Lesson lesson : selectedCourse.getLessons()) {
            Quiz quiz = lesson.getQuiz();
            if (quiz != null) {
                for (Grade grade : marks) {
                    if (grade.getLessonId() == lesson.getLessonId()
                            && grade.getQuizId() == quiz.getQuiz_id()
                            && grade.getStudentid() == getId()) {
                        completedQuizzes++;
                        break;
                    }
                }
            }
        }

        double progressPercentage = (double) completedQuizzes / totalQuizzes * 100;
        System.out.printf("Your progress in the course '%s' is %.2f%% (%d/%d quizzes completed).\n",
                selectedCourse.getCourseName(), progressPercentage, completedQuizzes, totalQuizzes);
    }

    public void saveProgressInFile() {
        File file = new File("progress.txt");
        List<String> lines = new ArrayList<>();
        boolean studentFound = false;

        // Read existing progress data
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2 && Long.parseLong(parts[0]) == getId()) {
                    // Update progress for the current student
                    line = getId() + "," + calculateProgress();
                    studentFound = true;
                }
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet; it will be created
        } catch (IOException e) {
            System.err.println("Error reading progress file: " + e.getMessage());
            return;
        }

        // If student ID was not found, add a new entry
        if (!studentFound) {
            lines.add(getId() + "," + calculateProgress());
        }

        // Write updated progress data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Progress saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to progress file: " + e.getMessage());
        }
    }


    private String calculateProgress() {
        int totalQuizzes = 0;
        int completedQuizzes = 0;

        for (Course course : courses) {
            for (Lesson lesson : course.getLessons()) {
                totalQuizzes++;
                for (Grade grade : marks) {
                    if (grade.getLessonId() == lesson.getLessonId() && grade.getMarks() >= 5) {
                        completedQuizzes++;
                        break;
                    }
                }
            }
        }

        return totalQuizzes == 0 ? "0" : String.valueOf((completedQuizzes * 100) / totalQuizzes);
    }

    // get progress 
    public String getProgress() {
        return calculateProgress();
    }
    
    public void setProgress(int progress) {
        this.progress = progress;
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
    @Override
    //equal
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return getId() == student.getId();
    }
    
    @Override
    public int compareTo(Student other) {
        int nameComparison = this.getName().compareTo(other.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.getUsername().compareTo(other.getUsername());
    }


}
