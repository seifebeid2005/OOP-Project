
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Lesson {

    private int lessonId;
    private static int idCounter = 1;
    private int courseId;
    private String lessonTitle;
    private String lessonDescription;
    private boolean isCompleted = false; 
    private Quiz quiz;

    // Constructor
    public Lesson(String lessonTitle, String lessonDescription, int courseId) {
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

        System.out.println("Enter the quiz id: ");
        int quiz_id = scanner.nextInt();

        // Validating the correct answer input
        Question.CorrectAnswer correct_answer;
        try {
            correct_answer = Question.CorrectAnswer.valueOf(correct_answer_str);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid correct answer. Must be A, B, C, or D.");
            return;
        }
        Question question = new Question(question_text, A, B, C, D, correct_answer ,quiz_id );
        quiz.addQuestionToQuiz(question);
        System.out.println("Question added successfully.");
        

    }

    // Create a lesson from a file
    public void createqustionsFromFile(String filePath ) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String questionText = null, optionA = null, optionB = null, optionC = null, optionD = null;
            String correctAnswerStr = null;
            int Quiz_id = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    // Create a question when a blank line is encountered
                        if (questionText != null && optionA != null && optionB != null && optionC != null && optionD != null && correctAnswerStr != null) {
                            try {
                                Question.CorrectAnswer correctAnswer = Question.CorrectAnswer.valueOf(correctAnswerStr.toUpperCase());
                                Question question = new Question(questionText, optionA, optionB, optionC, optionD, correctAnswer, Quiz_id);
                                quiz.addQuestionToQuiz(question);
                                System.out.println("Question added successfully: " + questionText);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid correct answer for question: " + questionText);
                            }
                        
                    }
                    // Reset variables for the next question
                    questionText = null;
                    optionA = null;
                    optionB = null;
                    optionC = null;
                    optionD = null;
                    correctAnswerStr = null;
                    Quiz_id = 0;

                } else if (line.startsWith("question : ")) {
                    questionText = line.substring(11).trim();
                } else if (line.startsWith("optionA : ")) {
                    optionA = line.substring(10).trim();
                } else if (line.startsWith("optionB : ")) {
                    optionB = line.substring(10).trim();
                } else if (line.startsWith("optionC : ")) {
                    optionC = line.substring(10).trim();
                } else if (line.startsWith("optionD : ")) {
                    optionD = line.substring(10).trim();
                } else if (line.startsWith("correct : ")) {
                    correctAnswerStr = line.substring(9).trim();
                } else if (line.startsWith("Quiz_id : ")) {
                    Quiz_id = Integer.parseInt(line.substring(10).trim());
                }
            }

            // Handle the last question in the file if no trailing blank line
            if (questionText != null && optionA != null && optionB != null && optionC != null && optionD != null && correctAnswerStr != null) {
                try {
                    Question.CorrectAnswer correctAnswer = Question.CorrectAnswer.valueOf(correctAnswerStr.toUpperCase());
                    Question question = new Question(questionText, optionA, optionB, optionC, optionD, correctAnswer, Quiz_id);
                    quiz.addQuestionToQuiz(question);
                    System.out.println("Question added successfully: " + questionText);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid correct answer for question: " + questionText);
                }
            }
            else{
                System.out.println("Error reading file:1 ");
            }

        } catch (Exception e) {
            System.out.println("Error reading file:2 " + e.getMessage());
        }
    }

    // Set a quiz for the lesson
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        System.out.println("Quiz set for lesson: " + lessonTitle);
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
        Quiz quiz = this.getQuiz();
        if (quiz != null && quiz.getGrade() != null) {  // Null check for quiz and grade
            Grade grade = quiz.getGrade();
            System.out.println("Grade: " + grade.getMarks());
            return grade.getMarks() >= Quiz.getPASSINGSCORE();
        }
        return false;  // Return false if quiz or grade is null
    }


    @Override
    public String toString() {
        return String.format(
            "Lesson ID: %d\n" +
            "Lesson Title: %s\n" +
            "Description: %s\n" +
            "Completed: %b\n" +
            "Quiz: %s",
            lessonId, lessonTitle, lessonDescription, isCompleted, quiz
        );
    }

}