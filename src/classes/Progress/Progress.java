package classes.Progress;
import classes.Student.Student;
import java.util.Date;

public class Progress {
    private final Integer Progress_ID;
    private final Student student;
    private Integer Lesson_ID;
    private String Completion_Status;
    private Integer Score;
    private Date Date_Completed;

    // Method to generate auto ID
    private Integer generateAutoID() {
        int randomNum = (int) (Math.random() * 1000000); // Generate a random number with 6 digits
        return randomNum;
    }

    // Constructor
    public Progress(Integer lesson_ID, String completion_Status, Integer score, Date date_Completed, Student student) {
        this.Progress_ID = generateAutoID();
        this.student = student;
        this.Lesson_ID = lesson_ID;
        this.Completion_Status = completion_Status;
        this.Score = score;
        this.Date_Completed = date_Completed;
    }

    // Getters and Setters
    public Integer getProgress_ID() {
        return Progress_ID;
    }

    public int getUser_ID() {
        return student.getId();
    }


    public Integer getLesson_ID() {
        return Lesson_ID;
    }


    public String getCompletion_Status() {
        return Completion_Status;
    }


    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public Date getDate_Completed() {
        return Date_Completed;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "Progress_ID=" + Progress_ID +
                ", User_ID=" + student.getId() +
                ", User_Name=" + student.getName() +
                ", User_Age=" + student.getAge() + 
                ", User_Email=" + student.getEmail() +
                ", Lesson_ID=" + Lesson_ID +
                ", Completion_Status='" + Completion_Status + '\'' +
                ", Score=" + Score +
                ", Date_Completed=" + Date_Completed +
                '}';
    }
}