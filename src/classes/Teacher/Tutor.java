package classes.Teacher;

import classes.lesson.Chapter;
import classes.person.Person;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends Person {

    private String subjectArea;       // Primary subject expertise
    private LocalDate dateJoined;     // Date the tutor joined the institution
    private String role;              // Tutor's role (e.g., Lead Tutor, Assistant Tutor)
    private List<Chapter> chapters;   // List of chapters the tutor teaches

    // Constructor
    public Tutor(Long id, String name, String email, LocalDate dateOfBirth, String phone, String address, String username, String password,
            String subjectArea, LocalDate dateJoined, String role) {
        super(id, name, email, dateOfBirth, phone, address, username, password);
        validateSubjectArea(subjectArea);
        this.subjectArea = subjectArea;
        this.dateJoined = dateJoined != null ? dateJoined : LocalDate.now(); // Default to current date if null
        this.role = role != null ? role : "Tutor"; // Default to "Tutor" if role is null
        this.chapters = new ArrayList<>(); // Initialize chapters list
    }

    // Getters and Setters
    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        validateSubjectArea(subjectArea);
        this.subjectArea = subjectArea;
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

    public void viewAllChapters() {
        if (chapters.isEmpty()) {
            System.out.println("No chapters to display.");
        } else {
            for (Chapter chapter : chapters) {
                System.out.println(chapter);
            }
        }
    }

    public void viewChapterByTopic(String topic) {
        boolean found = false;
        for (Chapter chapter : chapters) {
            if (chapter.get_lesson_title().equalsIgnoreCase(topic)) {
                System.out.println(chapter);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No chapters found for the topic: " + topic);
        }
    }

    // Calculate years of experience based on dateJoined
    public int getYearsOfExperience() {
        return LocalDate.now().getYear() - dateJoined.getYear();
    }

    // Validation for subject area
    private void validateSubjectArea(String subjectArea) {
        if (subjectArea == null || subjectArea.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject area cannot be null or empty.");
        }
    }

    // toString override to include Tutor-specific details
    @Override
    public String toString() {
        return super.toString()
                + ", SubjectArea='" + subjectArea + '\''
                + ", DateJoined=" + dateJoined
                + ", Role='" + role + '\''
                + ", YearsOfExperience=" + getYearsOfExperience()
                + ", ChaptersCount=" + chapters.size();
    }
}
