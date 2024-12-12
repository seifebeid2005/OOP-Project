public class Lesson {

    private int lessonId;
    private String lessonTitle;
    private String lessonDescription;
    private boolean isCompleted = false;  // Track completion status
    private Quiz quiz;  // Single quiz associated with the lesson

    // Constructor
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

    public boolean isCompleted() {
        isQuizPassedAndLessonCompleted();
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    // Set a quiz for the lesson
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        System.out.println("Quiz set for lesson: " + quiz.getQuiz_title());
    }

    // Get the quiz associated with the lesson
    public Quiz getQuiz() {
        return quiz;
    }

    // View the quiz
    public void viewQuiz() {
        if (quiz != null) {
            System.out.println("Quiz in Lesson: " + lessonTitle);
            System.out.println("- " + quiz);
        } else {
            System.out.println("No quiz set for this lesson.");
        }
    }

    // get is the quiz is completed with the passing score
    public boolean isQuizPassedAndLessonCompleted() {
        if (quiz != null) {
            if (quiz.getGrade() >= quiz.getPassingScore()) {
                markAsCompleted();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Lesson ID: " + lessonId
                + ", Lesson Title: " + lessonTitle
                + ", Description: " + lessonDescription
                + ", Completed: " + isCompleted
                + ", Quiz: " + (quiz != null ? quiz.getQuiz_title() : "No quiz set");
    }
}