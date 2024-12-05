import java.util.Date;
public class Progress {
    private final Integer Progress_ID;
    private final Student student;
    private final Integer Lesson_ID;
    private final String Completion_Status;
    private Integer Score;
    private final Date Date_Completed;


    // Constructor with validation
    public Progress(Integer lesson_ID, String completion_Status, Integer score, Date date_Completed, Student student) {
        validateLessonID(lesson_ID);
        validateCompletionStatus(completion_Status);
        validateScore(score);
        validateDateCompleted(date_Completed);
        validateStudent(student);

        this.Progress_ID = generateAutoID();
        this.student = student;
        this.Lesson_ID = lesson_ID;
        this.Completion_Status = completion_Status;
        this.Score = score;
        this.Date_Completed = date_Completed;
    }

    // Method to generate auto ID
    private Integer generateAutoID() {
        int randomNum = (int) (Math.random() * 1000000); // Generate a random number with 6 digits
        return randomNum;
    }
    // validate methods
    private void validateLessonID(Integer lesson_ID) {
        if (lesson_ID == null || lesson_ID <= 0) {
            throw new IllegalArgumentException("Lesson ID must be a positive integer.");
        }
    }

    private void validateCompletionStatus(String completion_Status) {
        if (completion_Status == null || completion_Status.isEmpty()) {
            throw new IllegalArgumentException("Completion Status cannot be null or empty.");
        }
    }

    private void validateScore(Integer score) {
        if (score == null || score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
    }

    private void validateDateCompleted(Date date_Completed) {
        if (date_Completed == null) {
            throw new IllegalArgumentException("Date Completed cannot be null.");
        }
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
    }    

    // Getters and Setters
    public Integer getProgress_ID() {
        return Progress_ID;
    }

    public Long getUser_ID() {
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

    // Calculate the progress of the student
    public double calculateProgress() {
        if (Score >= 80) {
            return 100;
        } else if (Score >= 60) {
            return 75;
        } else if (Score >= 40) {
            return 50;
        } else if (Score >= 20) {
            return 25;
        } else {
            return 0;
        }
    }

    // calculate the grade of the student
    public String calculateGrade() {
        if (Score >= 80) {
            return "A";
        } else if (Score >= 60) {
            return "B";
        } else if (Score >= 40) {
            return "C";
        } else if (Score >= 20) {
            return "D";
        } else {
            return "F";
        }
    }

    // show the progress of the student
    public void showProgress() {
        System.out.println("Student ID: " + student.getId());
        System.out.println("Student Name: " + student.getName());
        System.out.println("Lesson ID: " + Lesson_ID);
        System.out.println("Completion Status: " + Completion_Status);
        System.out.println("Score: " + Score);
        System.out.println("Date Completed: " + Date_Completed);
        System.out.println("Progress: " + calculateProgress() + "%");
        System.out.println("Grade: " + calculateGrade());
    }

    // generate a report of the student as a file
    public void generateReport() {
        String report = "Student ID: " + student.getId() + "\n" +
                "Student Name: " + student.getName() + "\n" +
                "Lesson ID: " + Lesson_ID + "\n" +
                "Completion Status: " + Completion_Status + "\n" +
                "Score: " + Score + "\n" +
                "Date Completed: " + Date_Completed + "\n" +
                "Progress: " + calculateProgress() + "%" + "\n" +
                "Grade: " + calculateGrade() + "\n";

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