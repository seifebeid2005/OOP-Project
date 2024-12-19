import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {

    private long courseId;
    private final ArrayList<Lesson> lessons = new ArrayList<>();
    private String courseName;
    private String courseDescription;
    private Integer courseRequiredProgress = lessons.size();
    private Boolean courseIsActive;
    private static long nextCourseId = 1; // Start from 1 or any other number you prefer

    // Constructors
    public Course(String courseName, String courseDescription, Boolean courseIsActive) {
        this.courseId = nextCourseId++;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseIsActive = courseIsActive;
    }

    // Lesson Management
    public void addLesson(Lesson lesson) {
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
        // enter course id
        System.out.print("Enter course id: ");
        int inputCourseId = scanner.nextInt();
        // Create a new lesson
        Lesson lesson = new Lesson(lessonName, lessonDescription, inputCourseId);
        addLesson(lesson);
        lesson.createQuiz();
    }

    public void AddLessonsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String lessonName = null;
            String lessonDescription = null;
            int courseId = -1;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    // Create the Lesson object if both name and description are available
                    if (courseId == getCourseId()) {
                        if (lessonName != null && lessonDescription != null) {
                            lessons.add(new Lesson(lessonName, lessonDescription, courseId));
                        }
                    }
                    // Reset variables for the next lesson
                    lessonName = null;
                    lessonDescription = null;
                    courseId = -1;
                } else if (line.startsWith("name : ")) {
                    lessonName = line.substring(7).trim();
                } else if (line.startsWith("description : ")) {
                    lessonDescription = line.substring(13).trim();
                } else if (line.startsWith("courseId : ")) {
                    courseId = Integer.parseInt(line.substring(11).trim());
                }
            }

            // Handle the last lesson in the file if no trailing blank line
            if (lessonName != null && lessonDescription != null && courseId != 0) {
                Lesson lesson = new Lesson(lessonName, lessonDescription, courseId);
                addLesson(lesson);
            }

        } catch (Exception e) {
            System.out.println("Error reading file:1 " + e.getMessage());
        }
    }

    public void createQuizFromFile(String filePath, String QuestionPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            long lessonId = -1;
            ArrayList<String> questions = new ArrayList<>();
            ArrayList<String> answers = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Check if line contains lesson ID and extract it
                if (line.startsWith("lessonId : ")) {
                    try {
                        lessonId = Long.parseLong(line.substring(11).trim()); // Extract lesson ID
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid lessonId format: " + line);
                    }
                } // Collect questions and answers
                else if (line.startsWith("question : ")) {
                    questions.add(line.substring(11).trim());
                } else if (line.startsWith("answer : ")) {
                    answers.add(line.substring(9).trim());
                }

                // When both questions and answers are collected, process them
                if (!questions.isEmpty() && !answers.isEmpty() && lessonId != -1) {
                    // Assuming `findLessonById` finds the lesson object by ID
                    Lesson lesson = findLessonById(lessonId);
                    if (lesson != null) {
                        lesson.createqustionsFromFile(QuestionPath);
                        // Optionally, print out the quiz for debugging
                        System.out.println("Lesson ID: " + lessonId);
                        System.out.println("Questions: " + questions);
                        System.out.println("Answers: " + answers);
                    } else {
                        System.out.println("Lesson with ID " + lessonId + " not found.");
                    }

                    // Reset for the next set of questions and answers
                    questions.clear();
                    answers.clear();
                    lessonId = -1;
                }
            }

            // Handle any remaining questions/answers after the last read
            if (!questions.isEmpty() && !answers.isEmpty() && lessonId != -1) {
                Lesson lesson = findLessonById(lessonId);
                if (lesson != null) {
                    lesson.createqustionsFromFile(QuestionPath);
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
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
    public Lesson findLessonById(long lessonId) {
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
        return "Course{"
                + "courseId=" + courseId
                + ", courseName='" + courseName + '\''
                + ", courseDescription='" + courseDescription + '\''
                + ", courseRequiredProgress=" + courseRequiredProgress
                + ", courseIsActive=" + courseIsActive
                + '}';
    }
    void viewStudents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
