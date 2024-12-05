
import java.util.Date;

public class Progress {

    private final Integer progressID;
    private final Student student;
    private final Integer lessonID;
    private final String completionStatus;
    private Integer score;
    private final Date dateCompleted;

    // Constructor with validation
    public Progress(Integer lessonID, String completionStatus, Integer score, Date dateCompleted, Student student) {
        validateLessonID(lessonID);
        validateCompletionStatus(completionStatus);
        validateScore(score);
        validateDateCompleted(dateCompleted);
        validateStudent(student);

        this.progressID = generateAutoID();
        this.student = student;
        this.lessonID = lessonID;
        this.completionStatus = completionStatus;
        this.score = score;
        this.dateCompleted = dateCompleted;
    }

    private Integer generateAutoID() {
        int randomNum = (int) (Math.random() * 1000000); // Generate a random number with 6 digits
        return randomNum;
    }

    private void validateLessonID(Integer lessonID) {
        if (lessonID == null || lessonID <= 0) {
            throw new IllegalArgumentException("Lesson ID must be a positive integer.");
        }
    }

    private void validateCompletionStatus(String completionStatus) {
        if (completionStatus == null || completionStatus.isEmpty()) {
            throw new IllegalArgumentException("Completion Status cannot be null or empty.");
        }
    }

    private void validateScore(Integer score) {
        if (score == null || score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
    }

    private void validateDateCompleted(Date dateCompleted) {
        if (dateCompleted == null) {
            throw new IllegalArgumentException("Date Completed cannot be null.");
        }
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
    }

    // Methods to interact with Progress
    public Integer getProgressID() {
        return progressID;
    }

    public Integer getLessonID() {
        return lessonID;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public double calculateProgress() {
        if (score >= 80) {
            return 100;
        } else if (score >= 60) {
            return 75;
        } else if (score >= 40) {
            return 50;
        } else if (score >= 20) {
            return 25;
        } else {
            return 0;
        }
    }

    public String calculateGrade() {
        if (score >= 80) {
            return "A";
        } else if (score >= 60) {
            return "B";
        } else if (score >= 40) {
            return "C";
        } else if (score >= 20) {
            return "D";
        } else {
            return "F";
        }
    }

    public void showProgress() {
        System.out.println("Student ID: " + student.getId());
        System.out.println("Student Name: " + student.getName());
        System.out.println("Lesson ID: " + lessonID);
        System.out.println("Completion Status: " + completionStatus);
        System.out.println("Score: " + score);
        System.out.println("Date Completed: " + dateCompleted);
        System.out.println("Progress: " + calculateProgress() + "%");
        System.out.println("Grade: " + calculateGrade());
    }

    @Override
    public String toString() {
        return "Progress{"
                + "Progress_ID=" + progressID
                + ", User_ID=" + student.getId()
                + ", User_Name=" + student.getName()
                + ", User_Age=" + student.getAge()
                + ", User_Email=" + student.getEmail()
                + ", Lesson_ID=" + lessonID
                + ", Completion_Status='" + completionStatus + '\''
                + ", Score=" + score
                + ", Date_Completed=" + dateCompleted
                + '}';
    }
}
