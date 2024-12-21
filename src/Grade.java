

import java.util.ArrayList;
import java.util.List;

public class Grade {

    private int lessonId;
    private int marks;
    private int quizId;
    private static final int MAX_MARKS = 2; // Assume each quiz is out of 100 marks
    private long Studentid;

    // Constructor
    public Grade(int lessonId, int marks, int quizId , long Studentid) {
        this.lessonId = lessonId;
        this.marks = marks;
        this.quizId = quizId;
        this.Studentid = Studentid;
    }

    public long getStudentid() {
        return Studentid;
    }

    public void setStudentid(long Studentid) {
        this.Studentid = Studentid;
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
            if (grade.getMarks() != 0) {
                
                totalMarks += grade.getMarks(); // Add marks
            }   
        }
        if(totalMarks == 0){
            return 0;
        }
        return totalMarks / (double) grades.size(); // Return average marks
    }


    @Override
    public String toString() {
        return "Grade{"
                + "Lesson ID=" + lessonId
                + ", Marks=" + marks
                + ", Quiz ID=" + quizId
                + ", Percentage=" + (marks * 100 / MAX_MARKS) + "%"
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Grade) {
            Grade grade = (Grade) obj;
            return this.lessonId == grade.lessonId && this.marks == grade.marks && this.quizId == grade.quizId;
        }
        return false;
    }
    
}
