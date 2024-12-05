
import java.util.ArrayList;
import java.util.List;

public class Grade {

    private int studentId;
    private int lessonId;
    private int marks;
    private int quizId;

    // Constructor
    public Grade(int studentId, int lessonId, int marks, int quizId) {
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.marks = marks;
        this.quizId = quizId;
    }

    // Getters and Setters
    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    // Static Methods for Aggregation
    public static List<Grade> getMarksForQuiz(List<Grade> grades, int quizId) {
        List<Grade> results = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getQuizId() == quizId) {
                results.add(grade);
            }
        }
        return results;
    }

    public static double calculateAverageMarksForAllQuizzes(List<Grade> grades) {
        if (grades.isEmpty()) {
            return 0;
        }
        int totalMarks = 0;
        for (Grade grade : grades) {
            totalMarks += grade.getMarks();
        }
        return totalMarks / (double) grades.size();
    }

    public static double calculateStudentAverageAcrossCourses(List<Grade> grades, int studentId) {
        int totalMarks = 0;
        int count = 0;

        for (Grade grade : grades) {
            if (grade.getStudentId() == studentId) {
                totalMarks += grade.getMarks();
                count++;
            }
        }

        return count > 0 ? totalMarks / (double) count : 0;
    }

    @Override
    public String toString() {
        return "Grade{"
                + "studentId=" + studentId
                + ", Lesson ID=" + lessonId
                + ", Marks=" + marks
                + ", Quiz ID=" + quizId
                + '}';
    }
}
