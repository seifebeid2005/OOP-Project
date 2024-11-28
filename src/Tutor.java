import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends Person {

    private String subjectArea;       // Primary subject expertise
    private LocalDate dateJoined;     // Date the tutor joined the institution
    private String role;              // Tutor's role (e.g., Lead Tutor, Assistant Tutor)
    private List<Chapter> chapters;   // List of chapters the tutor teaches
    private final int schoolID;       // School ID where the tutor is employed
    // Enum for Role
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
        this.chapters = new ArrayList<>(); // Initialize chapters list
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

    public List<Chapter> getChapters() {
        return new ArrayList<>(chapters); // Return a copy for encapsulation
    }

    public void setChapters(List<Chapter> chapters) {
        if (chapters != null) {
            this.chapters = new ArrayList<>(chapters);
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

    // Chapter Management Methods
    public void addChapter(Chapter chapter) {
        if (chapter != null) {
            chapters.add(chapter);
        }
    }

    public void removeChapter(int chapter_ID) {
        chapters.removeIf(chapter -> chapter.getLessonId() == chapter_ID);
    }

    public void removeAllChapters() {
        chapters.clear();
    }

    public void viewAllChapters() {
        if (chapters.isEmpty()) {
            System.out.println("No chapters to display.");
        } else {
            chapters.forEach(System.out::println);
        }
    }

    public void viewChapterByTopic(String topic) {
        if (chapters.stream().noneMatch(chapter -> chapter.getLessonTitle().equalsIgnoreCase(topic))) {
            System.out.println("No chapters found for the topic: " + topic);
        } else {
            chapters.stream()
                    .filter(chapter -> chapter.getLessonTitle().equalsIgnoreCase(topic))
                    .forEach(System.out::println);
        }
    }

    // Assign/Unassign Chapter Methods
    public void assignChapter(Chapter chapter) {
        if (chapter != null) {
            chapter.setAssigned(true);
        }
    }

    public void unassignChapter(Chapter chapter) {
        if (chapter != null) {
            chapter.setAssigned(false);
        }
    }

    public void unassignAllChapters() {
        chapters.forEach(chapter -> chapter.setAssigned(false));
    }

    public boolean isChapterAssigned(Chapter chapter) {
        return chapter != null && chapter.isAssigned();
    }

    public boolean areAllChaptersAssigned() {
        return chapters.stream().allMatch(Chapter::isAssigned);
    }

    // Quiz and Assignment Methods
    public void uploadQuiz(Chapter chapter, String quiz) {
        if (chapter != null && quiz != null && !quiz.trim().isEmpty()) {
            chapter.setQuiz(quiz);
        }
    }

    public void uploadAssignment(Chapter chapter, String assignment) {
        if (chapter != null && assignment != null && !assignment.trim().isEmpty()) {
            chapter.setAssignment(assignment);
        }
    }

    // Student Progress and Achievement Viewing
    public void viewStudentsProgress(Chapter chapter) {
        if (chapter != null) {
            chapter.viewStudentsProgress(schoolID);
        }
    }

    public void viewStudentsProgress(String topic) {
        chapters.stream()
                .filter(chapter -> chapter.getLessonTitle().equalsIgnoreCase(topic))
                .forEach(chapter -> chapter.viewStudentsProgress(schoolID));
    }

    public void viewStudentsAchievements(Chapter chapter) {
        if (chapter != null) {
            chapter.viewStudentsAchievements(schoolID);
        }
    }

    public void viewStudentsAchievements(String topic) {
        chapters.stream()
                .filter(chapter -> chapter.getLessonTitle().equalsIgnoreCase(topic))
                .forEach(chapter -> chapter.viewStudentsAchievements(schoolID));
    }

    // toString override to include Tutor-specific details
    @Override
    public String toString() {
        return super.toString() +
                ", SubjectArea='" + subjectArea + '\'' +
                ", DateJoined=" + dateJoined +
                ", Role='" + role + '\'' +
                ", YearsOfExperience=" + getYearsOfExperience() +
                ", ChaptersCount=" + chapters.size();
    }
}
