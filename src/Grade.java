public class Grade {
    private int studentId;
    private int Lesson_ID;
    private int marks;
    private int quizId;

    public Grade(int studentId, int Lesson_ID, int marks, int quizId) {
        this.studentId = studentId;
        this.Lesson_ID = Lesson_ID;
        this.marks = marks;
        this.quizId = quizId;
    }

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

    public int getLesson_ID() {
        return Lesson_ID;
    }

    public void setLesson_ID(int Lesson_ID) {
        this.Lesson_ID = Lesson_ID;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "studentId=" + studentId +
                ", Lesson_ID='" + Lesson_ID + '\'' +
                ", marks=" + marks +
                '}';
    }
}