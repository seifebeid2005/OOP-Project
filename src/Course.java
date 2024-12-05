
import java.util.ArrayList;

public class Course {

    private long courseId;
    private String courseName;
    private String courseDescription;
    private Integer courseRequiredProgress;
    private Boolean courseIsActive;
    private ArrayList<Lesson> lessons = new ArrayList<>();
    private ArrayList<Tutor> tutors = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    // Constructors
    public Course() {
    }

    public Course(long courseId, String courseName, String courseDescription, Integer courseRequiredProgress, Boolean courseIsActive) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseRequiredProgress = courseRequiredProgress;
        this.courseIsActive = courseIsActive;
    }

    // Tutor Management
    public void assignTutor(Tutor tutor) {
        if (tutor == null) {
            throw new IllegalArgumentException("Tutor cannot be null.");
        }
        if (tutors.contains(tutor)) {
            throw new IllegalArgumentException("Tutor is already assigned to this course.");
        }
        tutors.add(tutor);
    }

    public void removeTutor(Tutor tutor) {
        if (tutor == null) {
            throw new IllegalArgumentException("Tutor cannot be null.");
        }
        if (!tutors.remove(tutor)) {
            throw new IllegalArgumentException("Tutor is not assigned to this course.");
        }
    }

    public ArrayList<Tutor> getAssignedTutors() {
        return new ArrayList<>(tutors); // Return a copy for encapsulation
    }

    public boolean isTutorAssigned(Tutor tutor) {
        return tutors.contains(tutor);
    }

    // Lesson Management
    public void addLesson(Lesson lesson) {
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson cannot be null.");
        }
        lessons.add(lesson);
    }

    public ArrayList<Lesson> getLessons() {
        return new ArrayList<>(lessons); // Return a copy to maintain encapsulation
    }

    public Lesson findLessonById(long lessonId) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonId() == lessonId) {
                return lesson;
            }
        }
        return null; // Lesson not found
    }

    public void removeLesson(long lessonId) {
        lessons.removeIf(lesson -> lesson.getLessonId() == lessonId);
    }

    // Student Management
    public void enrollStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (students.contains(student)) {
            throw new IllegalArgumentException("Student is already enrolled in this course.");
        }
        students.add(student);
        System.out.println("Student enrolled successfully: " + student.getName());
    }

    public void removeStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (!students.remove(student)) {
            System.out.println("Student not found in this course.");
        } else {
            System.out.println("Student removed successfully: " + student.getName());
        }
    }

    public Student findStudentById(long studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null; // Student not found
    }

    public boolean isStudentEnrolled(Student student) {
        return students.contains(student);
    }

    public ArrayList<Student> getEnrolledStudents() {
        return new ArrayList<>(students); // Return a copy for encapsulation
    }

    // Course Progress and Completion
    public double calculateStudentProgress(Student student) {
        if (!students.contains(student)) {
            throw new IllegalArgumentException("Student is not enrolled in this course.");
        }
        double completedProgress = student.getProgressLevel(); // Assume student progress is tracked by a `progressLevel`
        return (completedProgress / (double) courseRequiredProgress) * 100;
    }

    public boolean isCourseComplete(Student student) {
        if (!students.contains(student)) {
            throw new IllegalArgumentException("Student is not enrolled in this course.");
        }
        return student.getProgressLevel() >= courseRequiredProgress;
    }

    // Getters and Setters for Course Fields
    public void setCourseId(long courseId) {
        if (courseId <= 0) {
            throw new IllegalArgumentException("Course ID must be a positive number.");
        }
        this.courseId = courseId;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course Name cannot be null or empty.");
        }
        if (courseName.length() > 100) {
            throw new IllegalArgumentException("Course Name cannot exceed 100 characters.");
        }
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        if (courseDescription == null || courseDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Course Description cannot be null or empty.");
        }
        if (courseDescription.length() > 500) {
            throw new IllegalArgumentException("Course Description cannot exceed 500 characters.");
        }
        this.courseDescription = courseDescription;
    }

    public Integer getCourseRequiredProgress() {
        return courseRequiredProgress;
    }

    public void setCourseRequiredProgress(Integer courseRequiredProgress) {
        if (courseRequiredProgress == null || courseRequiredProgress < 0) {
            throw new IllegalArgumentException("Required Progress must be zero or a positive number.");
        }
        this.courseRequiredProgress = courseRequiredProgress;
    }

    public Boolean getCourseIsActive() {
        return courseIsActive;
    }

    public void setCourseIsActive(Boolean courseIsActive) {
        if (courseIsActive == null) {
            throw new IllegalArgumentException("Course Active status cannot be null.");
        }
        this.courseIsActive = courseIsActive;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Course {")
                .append("courseId=").append(courseId)
                .append(", courseName='").append(courseName).append('\'')
                .append(", courseDescription='").append(courseDescription).append('\'')
                .append(", courseRequiredProgress=").append(courseRequiredProgress)
                .append(", courseIsActive=").append(courseIsActive)
                .append(", lessonsCount=").append(lessons.size())
                .append(", tutors=[");

        // Add tutor details
        for (Tutor tutor : tutors) {
            builder.append(tutor.getName()).append(", ");
        }
        if (!tutors.isEmpty()) {
            builder.setLength(builder.length() - 2); // Remove trailing comma and space
        }
        builder.append("], studentsCount=").append(students.size()).append(" }");
        return builder.toString();
    }
}
