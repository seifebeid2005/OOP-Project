
import java.util.ArrayList;

public class Lesson {

    private int lessonId;
    private String lessonTitle;
    private String lessonDescription;
    private ArrayList<Quiz> quizzes = new ArrayList<>();

    public Lesson(int lessonId, String lessonTitle, String lessonDescription) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.lessonDescription = lessonDescription;
    }

    // Getters and Setters
    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    // Add a quiz to the lesson
    public void addQuiz(Quiz quiz) {
        if (quiz != null) {
            quizzes.add(quiz);
            System.out.println("Quiz added successfully: " + quiz.getQuiz_title());
        } else {
            System.out.println("Cannot add a null quiz.");
        }
    }

    // Remove a quiz from the lesson
    public void removeQuiz(int quizId) {
        boolean removed = quizzes.removeIf(q -> q.getQuiz_id() == quizId);
        if (removed) {
            System.out.println("Quiz with ID " + quizId + " removed successfully.");
        } else {
            System.out.println("Quiz with ID " + quizId + " not found.");
        }
    }

    // View all quizzes
    public void viewQuizzes() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes added to this lesson.");
        } else {
            System.out.println("Quizzes in Lesson: " + lessonTitle);
            for (Quiz quiz : quizzes) {
                System.out.println("- " + quiz);
            }
        }
    }

    @Override
    public String toString() {
        return "Lesson ID: " + lessonId
                + ", Lesson Title: " + lessonTitle
                + ", Description: " + lessonDescription
                + ", Quizzes: " + quizzes;
    }
}
