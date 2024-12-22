
import java.time.LocalDate;
import java.util.*;

public class AchievementManager {

    private Progress progress;
    private List<Achievement> uniqueAchievements = new ArrayList<>();

    public AchievementManager(Progress progress) {
        this.progress = progress;
    }

    // Add and Validate Achievement (Unique Check)
    public void addAchievement(Achievement achievement) {
        if (isAchievementUnique(achievement)) {
            uniqueAchievements.add(achievement);
            System.out.println("✅ Achievement added: " + achievement.getAchievementName());
        } else {
            System.out.println("⚠ Achievement already exists: " + achievement.getAchievementName());
        }
    }

    private boolean isAchievementUnique(Achievement newAchievement) {
        for (Achievement achievement : uniqueAchievements) {
            if (achievement.equals(newAchievement)) {
                return false;
            }
        }
        return true;
    }

    // Check for Quiz Master
    public void checkAndAwardQuizMaster(long studentID) {
        boolean allQuizzesPerfect = progress.getCourses().stream()
                .allMatch(course -> course.getCourseProgress() == 100);

        if (allQuizzesPerfect) {
            addAchievement(Achievement.quizMaster());
            new UserAchievement(studentID, Achievement.quizMaster());
        }
    }

    // Check for Early Bird (Deadline is Today for demo)
    public void checkAndAwardEarlyBird(long studentID) {
        LocalDate deadline = LocalDate.now();
        if (progress.getCourses().stream().allMatch(Course::isCompleted)) {
            addAchievement(Achievement.earlyBird());
            new UserAchievement(studentID, Achievement.earlyBird());
        }
    }

    // Check for Perfect Attendance
    public void checkAndAwardPerfectAttendance(long studentID) {
        boolean allLessonsCompleted = progress.getCourses().stream()
                .allMatch(course -> course.areAllLessonsCompleted());

        if (allLessonsCompleted) {
            addAchievement(Achievement.perfectAttendance());
            new UserAchievement(studentID, Achievement.perfectAttendance());
        }
    }

    // Check for Subject Master
    public void checkAndAwardSubjectMaster(long studentID, String subject) {
        Course targetCourse = progress.getCourses().stream()
                .filter(course -> course.getCourseName().equalsIgnoreCase(subject))
                .findFirst().orElse(null);

        if (targetCourse != null && targetCourse.getCourseProgress() == 100) {
            addAchievement(Achievement.subjectMaster(subject));
            new UserAchievement(studentID, Achievement.subjectMaster(subject));
        }
    }

    // Check for Top Performer
    public void checkAndAwardTopPerformer(long studentID) {
        if (progress.getProgressPercentage() > 95) {
            addAchievement(Achievement.topPerformer());
            new UserAchievement(studentID, Achievement.topPerformer());
        }
    }

    // Award all achievements
    public void awardAllAchievements(long studentID) {
        checkAndAwardQuizMaster(studentID);
        checkAndAwardEarlyBird(studentID);
        checkAndAwardPerfectAttendance(studentID);
        checkAndAwardTopPerformer(studentID);
        checkAndAwardSubjectMaster(studentID, "Mathematics"); // Example subject
    }
}
