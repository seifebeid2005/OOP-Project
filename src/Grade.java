

import java.util.ArrayList;
import java.util.List;

public class Grade {

    private int lessonId;
    private int marks;
    private int quizId;
    private static final int MAX_MARKS = 100; // Assume each quiz is out of 100 marks

    // Constructor
    public Grade(int lessonId, int marks, int quizId) {
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

    // Get the grade percentage
    public double getGradePercentage() {
        return (double) marks / MAX_MARKS * 100; // Return percentage
    }

    // Static Methods for Aggregation
    // Get all grades for a particular quiz
    public static List<Grade> getMarksForQuiz(List<Grade> grades, int quizId) {
        List<Grade> results = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getQuizId() == quizId) {
                results.add(grade);
            }
        }
        return results;
    }

    // Calculate the average marks for all quizzes
    public static double calculateAverageMarksForAllQuizzes(List<Grade> grades) {
        if (grades.isEmpty()) {
            return 0;
        }
        int totalMarks = 0;
        for (Grade grade : grades) {
            totalMarks += grade.getMarks();
        }
        return totalMarks / (double) grades.size(); // Return average marks
    }

    // Calculate average grade percentage for all grades
    public static double calculateAveragePercentageForAllGrades(List<Grade> grades) {
        if (grades.isEmpty()) {
            return 0;
        }
        double totalPercentage = 0;
        for (Grade grade : grades) {
            totalPercentage += grade.getGradePercentage(); // Add grade percentage
        }
        return totalPercentage / grades.size(); // Return average percentage
    }

    @Override
    public String toString() {
        return "Grade{"
                + "Lesson ID=" + lessonId
                + ", Marks=" + marks
                + ", Quiz ID=" + quizId
                + ", Percentage=" + getGradePercentage() + "%"
                + '}';
    }
}
