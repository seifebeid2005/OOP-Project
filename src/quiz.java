import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class Quiz {

    private int quiz_id;
    private static int lastGeneratedID = 1;
    private String quiz_title;
    private int lesson_id;
    private static final int MAX_QUESTIONS = 10;  // Change this to the number of questions you want
    private List<Question> questions = new ArrayList<>();
    private static final int PASSINGSCORE = MAX_QUESTIONS / 2;

    // Constructor
    public Quiz(String quiz_title, int lesson_id) {
        this.quiz_id = lastGeneratedID++;
        this.quiz_title = quiz_title;
        this.lesson_id = lesson_id;
    }

    // TIMER FOR QUIZ
    public void startQuizTimer() {
        QuizTimer.start();
        Timer timer = new Timer();
        timer.schedule(new QuizTimer(), 0, 1000);  // Schedule the timer to run every second
    }

    // check if the quiz time is over
    public boolean isQuizTimeOver() {
        return QuizTimer.isTimeOver();
    }

    // getters and setters
    public static int getPASSINGSCORE() {
        return PASSINGSCORE;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    // Add question to the quiz
    public void addQuestionToQuiz(Question question) {
        if (questions.size() >= MAX_QUESTIONS) {
            System.out.println("Quiz already has the maximum number of questions.");
            return;
        }
        questions.add(question);
        System.out.println("Question added to the quiz.");
    }

    // Remove question from the quiz by ID
    public void removeQuestionById(long questionId) {
        for (Question question : questions) {
            if (question.getQuestion_id() == questionId) {
                questions.remove(question);
                System.out.println("Question removed successfully.");
                return;
            }
        }
        System.out.println("Question not found.");
    }

    // View all questions in the quiz
    public void viewQuizQuestions() {
        if (questions.isEmpty()) {
            System.out.println("No questions added to this quiz.");
            return;
        }
        System.out.println("Questions in Quiz: " + quiz_title);
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i));
        }
    }

    // Validate the quiz for answering
    public boolean isQuizReady() {
        if (questions.size() == MAX_QUESTIONS) {
            return true;
        }
        System.out.println("Quiz must have exactly " + MAX_QUESTIONS + " questions to start. Currently, it has " + questions.size() + ".");
        return false;
    }

    // Answer quiz questions
    public int answerQuestions() {
        if (!isQuizReady()) {
            return 0;
        }

        startQuizTimer();
        Scanner scanner = new Scanner(System.in);

        int score = 0;
        for (Question question : questions) {
            if (isQuizTimeOver()) {
                System.out.println("Time is over! Quiz is submitted.");
                break;
            }

            System.out.println(question.getQuestion_text());
            System.out.println("A. " + question.getOption_a());
            System.out.println("B. " + question.getOption_b());
            System.out.println("C. " + question.getOption_c());
            System.out.println("D. " + question.getOption_d());
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().toUpperCase();

            if (userAnswer.equals(question.getCorrect_answer().name())) {
                score++;
            }
        }

        System.out.println("You scored " + score + " out of " + MAX_QUESTIONS);
        if (score >= PASSINGSCORE) {
            System.out.println("Congratulations! You passed the quiz.");
        } else {
            System.out.println("Sorry, you did not pass the quiz.");
        }
        return score;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions); // Return a copy for encapsulation
    }

    @Override
    public String toString() {
        return String.format(
            "Quiz { \n" +
            "  Quiz ID: %d\n" +
            "  Quiz Title: '%s'\n" +
            "  Lesson ID: %d\n" +
            "  Number of Questions: %d\n" +
            "  Max Questions: %d\n" +
            "  Passing Score: %d\n" +
            "  Questions: %s\n" +
            "}",
            quiz_id, quiz_title, lesson_id, questions.size(), MAX_QUESTIONS, PASSINGSCORE, questions
        );
    }
}
