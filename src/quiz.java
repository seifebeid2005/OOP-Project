
public class quiz {
    private int quiz_id;
    private String quiz_title;
    private int lesson_id;
    private int number_of_questions;
    private float passing_score;

    public quiz(int quiz_id, String quiz_title, int lesson_id, int number_of_questions, float passing_score) {
        this.lesson_id = lesson_id;
        this.quiz_id = quiz_id;
        this.quiz_title = quiz_title;
        this.number_of_questions = number_of_questions;
        this.passing_score = passing_score;
    }

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

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public void setNumber_of_questions(int number_of_questions) {
        this.number_of_questions = number_of_questions;
    }

    public void setPassing_score(float passing_score) {
        this.passing_score = passing_score;
    }

    public float calculate_avarage_score() {
        return passing_score / number_of_questions;
    }
    public void add_question(String question) {
        number_of_questions++;
    }
    public void remove_question(String question) {
        number_of_questions--;
    }
    public String toString() {
        return  "Quiz ID=" + quiz_id + ", Quiz Title='" + quiz_title + ", Lesson ID=" + lesson_id + ", Number of Questions=" + number_of_questions + ", Passing Score=" + passing_score;
    }
}

