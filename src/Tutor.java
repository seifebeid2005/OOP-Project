import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends Person {

    private String subjectArea;       // Primary subject expertise
    private LocalDate dateJoined;     // Date the tutor joined the institution
    private String role;              // Tutor's role (e.g., Lead Tutor, Assistant Tutor)
    private ArrayList<Lesson> lessons;     // List of Lessons the tutor teaches
    private final int schoolID;       // School ID where the tutor is employed

    public enum Role {
        LEAD_TUTOR,
        ASSISTANT_TUTOR,
        TUTOR
    }

    // Update role to use the enum
    private Role roleEnum;

    // Constructor
    public Tutor(Long id, String name, String email, LocalDate dateOfBirth, String phone, String address, String username, String password,
                 String subjectArea, LocalDate dateJoined, Role roleEnum, int schoolID) {
        super(id, name, email, dateOfBirth, phone, address, username, password);
        this.subjectArea = validateNotEmpty(subjectArea, "Subject area cannot be null or empty.");
        this.dateJoined = dateJoined != null ? dateJoined : LocalDate.now(); // Default to current date if null
        this.roleEnum = roleEnum != null ? roleEnum : Role.TUTOR; // Default to "TUTOR" if roleEnum is null
        this.lessons = new ArrayList<>(); // Initialize Lessons list
        this.schoolID = schoolID;
    }

    // Getter and Setter for roleEnum
    public Role getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(Role roleEnum) {
        this.roleEnum = roleEnum;
    }

    // Getters and Setters
    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = validateNotEmpty(subjectArea, "Subject area cannot be null or empty.");
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Lesson> getLessons() {
        return new ArrayList<>(lessons); // Return a copy for encapsulation
    }

    public void setLessons(List<Lesson> lessons) {
        if (lessons != null) {
            this.lessons = new ArrayList<>(lessons);
        }
    }

    public int getSchoolID() {
        return schoolID;
    }

    // Calculate years of experience based on dateJoined
    public int getYearsOfExperience() {
        return LocalDate.now().getYear() - dateJoined.getYear();
    }

    // Helper method to validate non-empty fields
    private String validateNotEmpty(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

    // Lesson Management Methods
    public void addLesson(Lesson lesson) {
        if (lesson != null) {
            lessons.add(lesson);
        }
    }

    public void removeLesson(int lessonId) {
        lessons.removeIf(lesson -> lesson.getLessonId() == lessonId);
    }

    public void removeAllLessons() {
        lessons.clear();
    }

    public void viewAllLessons() {
        if (lessons.isEmpty()) {
            System.out.println("No Lessons to display.");
        } else {
            lessons.forEach(System.out::println);
        }
    }

    public void viewLessonByTopic(String topic) {
        if (lessons.stream().noneMatch(lesson -> lesson.getLessonTitle().equalsIgnoreCase(topic))) {
            System.out.println("No Lessons found for the topic: " + topic);
        } else {
            lessons.stream()
                    .filter(lesson -> lesson.getLessonTitle().equalsIgnoreCase(topic))
                    .forEach(System.out::println);
        }
    }

    // Assign/Unassign Lesson Methods
    public void assignLesson(Lesson lesson) {
        if (lesson != null) {
            lesson.setAssigned(true);
        }
    }

    public void unassignLesson(Lesson lesson) {
        if (lesson != null) {
            lesson.setAssigned(false);
        }
    }

    public void unassignAllLessons() {
        lessons.forEach(lesson -> lesson.setAssigned(false));
    }

    public boolean isLessonAssigned(Lesson lesson) {
        return lesson != null && lesson.isAssigned();
    }

    public boolean areAllLessonsAssigned() {
        return lessons.stream().allMatch(Lesson::isAssigned);
    }


    // Student Progress and Achievement Viewing
    public void viewStudentsProgress(Lesson lesson) {
        if (lesson != null) {
            lesson.viewStudentsProgress(schoolID);
        }
    }

    public void viewStudentsProgress(String topic) {
        lessons.stream()
                .filter(lesson -> lesson.getLessonTitle().equalsIgnoreCase(topic))
                .forEach(lesson -> lesson.viewStudentsProgress(schoolID));
    }

    public void viewStudentsAchievements(Lesson lesson) {
        if (lesson != null) {
            lesson.viewStudentsAchievements(schoolID);
        }
    }

    public void viewStudentsAchievements(String topic) {
        lessons.stream()
                .filter(lesson -> lesson.getLessonTitle().equalsIgnoreCase(topic))
                .forEach(lesson -> lesson.viewStudentsAchievements(schoolID));
    }

    // toString override to include Tutor-specific details
    @Override
    public String toString() {
        return super.toString() +
                ", SubjectArea='" + subjectArea + '\'' +
                ", DateJoined=" + dateJoined +
                ", Role='" + role + '\'' +
                ", YearsOfExperience=" + getYearsOfExperience() +
                ", LessonsCount=" + lessons.size();
    }
}
