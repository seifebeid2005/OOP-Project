
import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private int quiz_id;
    private String quiz_title;
    private int lesson_id; // Lesson this quiz belongs to
    private int number_of_questions;
    private float passing_score;

    // Additional attributes to link with Lesson, Course, and Tutor
    private String course_title;
    private String tutor_name;
    private List<Question> questions = new ArrayList<>(); // List to store questions in the quiz

    public Quiz(int quiz_id, String quiz_title, int lesson_id, int number_of_questions, float passing_score) {
        this.lesson_id = lesson_id;
        this.quiz_id = quiz_id;
        this.quiz_title = quiz_title;
        this.number_of_questions = number_of_questions;
        this.passing_score = passing_score;
        this.questions = new ArrayList<>(); // Initialize the questions list
    }

    // Getters and Setters
    public int getQuiz_id() {
        return quiz_id;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public int getNumber_of_questions() {
        return number_of_questions;
    }

    public float getPassing_score() {
        return passing_score;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
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

    public void setPassing_score(float passing_score) {
        this.passing_score = passing_score;
    }

    // Add a question to the quiz
    public void addQuestion(Question question) {
        if (question != null) {
            questions.add(question);
            number_of_questions++;
            System.out.println("Question added successfully.");
        } else {
            System.out.println("Invalid question.");
        }
    }
    // 1- remove question from quiz by id
    public void removeQuestion(int question_id) {
        for (Question question : questions) {
            if (question.getQuestion_id() == question_id) {
                questions.remove(question);
                number_of_questions--;
                System.out.println("Question removed successfully.");
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

    // Link the quiz to a lesson
    public void linkToLesson(int lessonId) {
        this.lesson_id = lessonId;
        System.out.println("Quiz linked to Lesson ID: " + lessonId);
    }

    // Assign a tutor to the quiz
    public void assignTutor(String tutorName) {
        this.tutor_name = tutorName;
        System.out.println("Quiz assigned to Tutor: " + tutorName);
    }

    @Override
    public String toString() {
        return "Quiz { "
                + "Quiz ID=" + quiz_id
                + ", Quiz Title='" + quiz_title + '\''
                + ", Lesson ID=" + lesson_id
                + ", Course='" + course_title + '\''
                + ", Tutor='" + tutor_name + '\''
                + ", Number of Questions=" + number_of_questions
                + ", Passing Score=" + passing_score
                + " }";
    }
}
