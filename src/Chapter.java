import java.util.List;

public class Chapter {

    private int lessonId;
    private String lessonTitle;
    private String lessonDescription;
    private int levelId;
    private int duration;
    private String language;
    private String lessonType;
    private int tutorId;
    private String materials;
    private boolean assigned;
    private String quiz;
    private String assignment;

    public Chapter(int lessonId, String lessonTitle, String lessonDescription, int levelId, int duration, String language, String lessonType, int tutorId) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.lessonDescription = lessonDescription;
        this.levelId = levelId;
        this.duration = duration;
        this.language = language;
        this.lessonType = lessonType;
        this.tutorId = tutorId;
        this.materials = "";
        this.assigned = false; // Default to unassigned
    }

    // Getters and Setters
    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getMaterials() {
        return materials;
    }

    public void addMaterial(String material) {
        if (!materials.isEmpty()) {
            materials += ", ";
        }
        materials += material;
    }

    public void removeMaterial(String material) {
        materials = String.join(", ",
                List.of(materials.split(", "))
                        .stream()
                        .filter(mat -> !mat.equals(material))
                        .toArray(String[]::new));
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    // Methods for viewing student progress/achievements (stub implementation)
    public void viewStudentsProgress(int schoolID) {
        // Implementation here
    }

    public void viewStudentsAchievements(int schoolID) {
        // Implementation here
    }

    @Override
    public String toString() {
        return "Lesson ID: " + lessonId +
                ", Lesson Title: " + lessonTitle +
                ", Description: " + lessonDescription +
                ", Level ID: " + levelId +
                ", Duration: " + duration +
                ", Language: " + language +
                ", Type: " + lessonType +
                ", Tutor ID: " + tutorId +
                ", Materials: " + materials +
                ", Assigned: " + assigned;
    }
}
