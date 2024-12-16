import java.util.Scanner;

public class Lesson {

    private int lessonId;
    private String lessonTitle;
    private String lessonDescription;
    private boolean isCompleted = false;  // Track completion status
    private Quiz quiz;  // Single quiz associated with the lesson
    private static int idCounter = 1;

// id genrator

    // Constructor
    public Lesson(String lessonTitle, String lessonDescription) {
        this.lessonId = idCounter++;
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
    public void createQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("now you can create a quiz for this lesson");
        System.out.println("Enter the question text: ");
        String question_text = scanner.nextLine();
        
        System.out.println("Enter option A: ");
        String A = scanner.nextLine();
        
        System.out.println("Enter option B: ");
        String B = scanner.nextLine();
        
        System.out.println("Enter option C: ");
        String C = scanner.nextLine();
        
        System.out.println("Enter option D: ");
        String D = scanner.nextLine();
        
        System.out.println("Enter the correct answer (A, B, C, D): ");
        String correct_answer_str = scanner.nextLine().toUpperCase();

        // Validating the correct answer input
        Question.CorrectAnswer correct_answer;
        try {
            correct_answer = Question.CorrectAnswer.valueOf(correct_answer_str);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid correct answer. Must be A, B, C, or D.");
            return;
        }
        Question question = new Question( question_text, A, B, C, D, correct_answer);
        quiz.addQuestionToQuiz(question);
        System.out.println("Question added successfully.");
        
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
            if (quiz.getGrade().getMarks() >= Quiz.getPassingScore()) {
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