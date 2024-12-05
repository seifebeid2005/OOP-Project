
public class quiz {
    private int quiz_id;
    private String quiz_title;
    private int lesson_id;
    private int number_of_questions;
    private float passing_score;
    private int quizDuration;
    private int[] studentMarks;  // Array for student marks

    public quiz(int quiz_id, String quiz_title, int lesson_id, int number_of_questions, float passing_score, int numberOfStudents) {
        this.quiz_id = quiz_id;
        this.quiz_title = quiz_title;
        this.lesson_id = lesson_id;
        this.number_of_questions = number_of_questions;
        this.passing_score = passing_score;
        this.studentMarks = new int[numberOfStudents];  // Initialize array for student marks
        
        // Initialize student marks to -1 (indicating no marks yet)
        for (int i = 0; i < numberOfStudents; i++) {
            studentMarks[i] = -1;
        }
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

    // Timer methods (without try-catch block)
    public void start_quiz_timer(int duration_minutes) {
        quizDuration = duration_minutes;
        System.out.println("Quiz timer started for " + quizDuration + " minutes.");

        // Simulate the passage of time using a simple loop (count down)
        int durationInSeconds = quizDuration * 60;
        int count = 0;
        
        // Simulate waiting for the duration of the quiz
        while (count < durationInSeconds) {
            count++;
        }

        System.out.println("Time is up! Quiz has ended.");
    }

    // Review and retake methods
    public void review_quiz(int quiz_id, int student_id) {
        System.out.println("Reviewing Quiz ID: " + quiz_id + " for Student ID: " + student_id);
        // Additional logic for displaying questions and answers can go here
    }

    public boolean retake_quiz(int quiz_id, int student_id) {
        if (studentMarks[student_id] == -1) {
            System.out.println("No record of Student ID: " + student_id + " taking quiz: " + quiz_id);
            return false;
        }

        System.out.println("Student ID: " + student_id + " retaking Quiz ID: " + quiz_id);
        studentMarks[student_id] = -1;  // Reset the student's marks (simulate retake)
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof quiz otherQuiz) {
            return this.quiz_id == otherQuiz.quiz_id;
        }
        return false;
    }

    public int calculateMark(int studentId, int correctAnswers) {
        if (number_of_questions == 0) {
            throw new IllegalArgumentException("Number of questions cannot be zero.");
        }

        // Calculate the mark as a percentage
        float mark = (correctAnswers / (float) number_of_questions) * 100;
        int roundedMark = Math.round(mark);

        studentMarks[studentId] = roundedMark;  // Store the calculated mark for the student
        return roundedMark;
    }

    public float calculate_average_score() {
        if (studentMarks.length == 0) return 0;  // Return 0 if no students

        int totalMarks = 0;
        int studentCount = 0;

        // Sum all valid student marks
        for (int mark : studentMarks) {
            if (mark != -1) {  // Only consider students who have taken the quiz
                totalMarks += mark;
                studentCount++;
            }
        }

        if (studentCount == 0) {
            return 0;
        } else {
            return (float) totalMarks / studentCount;
        }
        
    }

    public void add_question(String question) {
        number_of_questions++;
    }

    public void remove_question(String question) {
        number_of_questions--;
    }

    @Override
    public String toString() {
        return "Quiz ID=" + quiz_id + ", Quiz Title='" + quiz_title + "', Lesson ID=" + lesson_id +
                ", Number of Questions=" + number_of_questions + ", Passing Score=" + passing_score;
    }
}
