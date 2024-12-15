import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    // Variables
    // remove tutor name
    private int quiz_id;
    private String quiz_title;
    private int lesson_id; // Lesson this quiz belongs to
    private static final int NUMBER_OF_QUESTIONS = 10;
    private static final int PASSING_SCORE = NUMBER_OF_QUESTIONS / 2;
    private String tutor_name;
    private List<Question> questions = new ArrayList<>(); // List to store questions in the quiz
    private Grade grade;

    // Constructor
    public Quiz(int quiz_id, String quiz_title, int lesson_id, int numberOfQuestions, float passingScore) {
        this.lesson_id = lesson_id;
        this.quiz_id = quiz_id;
        this.quiz_title = quiz_title;
    }

    // Getters and Setters
    public int getQuiz_id() {
        return quiz_id;
    }
    // Getters and Setters
    public String getQuiz_title() {
        return quiz_title;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public int getNumberOfQuestions() {
        return NUMBER_OF_QUESTIONS;
    }

    public float getPassingScore() {
        return PASSING_SCORE;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    // Add a question to the quiz
    public void addQuestion(Scanner scanner) {
        if (questions.size() >= NUMBER_OF_QUESTIONS) {
            System.out.println("Quiz is full. Cannot add more questions.");
            return;
        }
    
        // Prompt user for question and options
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
        Question question = new Question( quiz_id, question_text, A, B, C, D, correct_answer);
        questions.add(question);
        System.out.println("Question added successfully.");
    }

    // Remove a question from the quiz by ID
    public void removeQuestion(int question_id) {
        for (Question question : questions) {
            if (question.getQuestion_id() == question_id) {
                questions.remove(question);
                System.out.println("Question removed successfully.");
                return;
            }
        }
        System.out.println("Question not found.");
    }

    // Edit a question in the quiz by ID
    public void editQuestion(int question_id, Scanner scanner) {
        for (Question question : questions) {
            if (question.getQuestion_id() == question_id) {
                System.out.println("Editing question: " + question.getQuestion_text());

                // Ask user to update the question and options
                System.out.println("Enter new question text (leave blank to keep current): ");
                String newQuestionText = scanner.nextLine();
                if (!newQuestionText.isEmpty()) {
                    question.setQuestion_text(newQuestionText);
                }

                System.out.println("Enter new option A (leave blank to keep current): ");
                String newA = scanner.nextLine();
                if (!newA.isEmpty()) {
                    question.setOption_a(newA);
                }

                System.out.println("Enter new option B (leave blank to keep current): ");
                String newB = scanner.nextLine();
                if (!newB.isEmpty()) {
                    question.setOption_b(newB);
                }

                System.out.println("Enter new option C (leave blank to keep current): ");
                String newC = scanner.nextLine();
                if (!newC.isEmpty()) {
                    question.setOption_c(newC);
                }

                System.out.println("Enter new option D (leave blank to keep current): ");
                String newD = scanner.nextLine();
                if (!newD.isEmpty()) {
                    question.setOption_d(newD);
                }

                System.out.println("Enter new correct answer (A, B, C, D) (leave blank to keep current): ");
                String newCorrectAnswer = scanner.nextLine().toUpperCase();
                if (!newCorrectAnswer.isEmpty()) {
                    try {
                        Question.CorrectAnswer correctAnswerEnum = Question.CorrectAnswer.valueOf(newCorrectAnswer);
                        question.setCorrect_answer(correctAnswerEnum);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid correct answer.");
                    }
                }

                System.out.println("Question updated successfully.");
                return;
            }
        }
        System.out.println("Question not found.");
    }

    // View all questions
    public void viewQuestions() {
        if (questions.isEmpty()) {
            System.out.println("No questions available in this quiz.");
        } else {
            System.out.println("Questions in Quiz " + quiz_title + ":");
            for (Question question : questions) {
                System.out.println("- " + question);
            }
        }
    }
 
    // answer quiz questions
    public void answerQuestions(Scanner scanner) {
        int numberofTries = 0;
        if (questions.isEmpty()) {
            System.out.println("No questions available to answer.");
            return;
        }

        int score = 0;
        for (Question question : questions) {
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

        System.out.println("You scored " + score + " out of " + NUMBER_OF_QUESTIONS);
        if (score >= PASSING_SCORE) {
            System.out.println("Congratulations! You passed the quiz.");
            grade = new Grade(quiz_id, score, quiz_id);
        } else {
            System.out.println("Sorry, you did not pass the quiz.");
        }

        System.out.println("Would you like to try again? (y/n)");
        String tryAgain = scanner.nextLine().toLowerCase();
        if (numberofTries >= 3) {
            System.out.println("You have reached the maximum number of tries.");
        } else if (tryAgain.equals("y")) {
            numberofTries++;
            answerQuestions(scanner);
        } else {
            System.out.println("Thank you for taking the quiz.");
        }
    }
    
    // get the grade of the quiz
    public int getGrade() {
        return grade.getMarks();
    } 

    @Override
    public String toString() {
        return "Quiz { "
                + "Quiz ID=" + quiz_id
                + ", Quiz Title='" + quiz_title + '\''
                + ", Lesson ID=" + lesson_id
                + ", Tutor='" + tutor_name + '\''
                + ", Number of Questions=" + NUMBER_OF_QUESTIONS
                + ", Passing Score=" + PASSING_SCORE
                + " }";
    }
}
