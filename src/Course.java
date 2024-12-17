import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private long courseId;
    private ArrayList<Lesson> lessons = new ArrayList<>();
    private String courseName;
    private String courseDescription;
    private Integer courseRequiredProgress = lessons.size();
    private Boolean courseIsActive;

    // Constructors
    public Course() {
    }

    // Generate a random course ID
    public static long generateRandomCourseId() {
        return (long) (Math.random() * 1000000);
    }
    public Course( String courseName, String courseDescription, Boolean courseIsActive) {
        this.courseId = generateRandomCourseId();
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseIsActive = courseIsActive;
    }

    // Lesson Management
    private void addLesson(Lesson lesson) {
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson cannot be null.");
        }
        lessons.add(lesson);
        courseRequiredProgress = lessons.size(); // Update the required progress dynamically
    }
    
    public void createLesson() {
        Scanner scanner = new Scanner(System.in);
        // enter lesson name
        System.out.print("Enter lesson name: ");
        String lessonName = scanner.nextLine();
        // enter lesson description
        System.out.print("Enter lesson description: ");
        String lessonDescription = scanner.nextLine();
        // Create a new lesson
        Lesson lesson = new Lesson( lessonName, lessonDescription);
        addLesson(lesson);
        lesson.createQuiz();
    
    }

    public ArrayList<Lesson> getLessons() {
        return new ArrayList<>(lessons); // Return a copy to maintain encapsulation
    }

    public void removeLesson(long lessonId) {
        lessons.removeIf(lesson -> lesson.getLessonId() == lessonId);
        courseRequiredProgress = lessons.size(); // Update required progress after lesson removal
    }

    // Getters and Setters for Course Fields
    public void setCourseId(long courseId) {
        if (courseId <= 0) {
            throw new IllegalArgumentException("Course ID must be a positive number.");
        }
        this.courseId = courseId;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course Name cannot be null or empty.");
        }
        if (courseName.length() > 100) {
            throw new IllegalArgumentException("Course Name cannot exceed 100 characters.");
        }
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        if (courseDescription == null || courseDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Course Description cannot be null or empty.");
        }
        if (courseDescription.length() > 500) {
            throw new IllegalArgumentException("Course Description cannot exceed 500 characters.");
        }
        this.courseDescription = courseDescription;
    }

    public Integer getCourseRequiredProgress() {
        return courseRequiredProgress;
    }

    public void setCourseRequiredProgress(Integer courseRequiredProgress) {
        if (courseRequiredProgress == null || courseRequiredProgress < 0) {
            throw new IllegalArgumentException("Required Progress must be zero or a positive number.");
        }
        this.courseRequiredProgress = courseRequiredProgress;
    }

    public Boolean getCourseIsActive() {
        return courseIsActive;
    }

    public void setCourseIsActive(Boolean courseIsActive) {
        if (courseIsActive == null) {
            throw new IllegalArgumentException("Course Active status cannot be null.");
        }
        this.courseIsActive = courseIsActive;
    }

    // Check if course is completed
    public boolean isCompleted() {
        int completedLessons = 0;
        for (Lesson lesson : lessons) {
            if (lesson.isQuizPassedAndLessonCompleted()) {
                completedLessons++;
            }
        }
        return completedLessons == courseRequiredProgress;
    }

    // Mark course as completed
    public void markAsCompleted() {
        this.courseIsActive = false; // Marking the course as completed (inactive)
    }

    // Get progress of the course
    public int getCourseProgress() {
        int completedLessons = 0;
        for (Lesson lesson : lessons) {
            if (lesson.isCompleted()) {
                completedLessons++;
            }
        }
        return (completedLessons * 100) / courseRequiredProgress; // Returns the progress as a percentage
    }

    // Check if all lessons are completed
    public boolean areAllLessonsCompleted() {
        for (Lesson lesson : lessons) {
            if (!lesson.isCompleted()) {
                return false; // If any lesson is not completed, return false
            }
        }
        return true; // All lessons are completed
    }


    // Method to check and mark a lesson as completed
    public void markLessonAsCompleted(long lessonId) {
        Lesson lesson = findLessonById(lessonId);
        if (lesson != null) {
            lesson.markAsCompleted();
        }
    }

    // Method to check and mark a lesson as incomplete
    public void markLessonAsIncomplete(long lessonId) {
        Lesson lesson = findLessonById(lessonId);
        if (lesson != null) {
            lesson.markAsIncomplete();
        }
    }

    // Helper method to find a lesson by ID
    public  Lesson findLessonById(long lessonId) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonId() == lessonId) {
                return lesson;
            }
        }
        return null;
    }

    // Display course info
    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseRequiredProgress=" + courseRequiredProgress +
                ", courseIsActive=" + courseIsActive +
                '}';
    }
}
