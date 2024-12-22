

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
        } else {
        
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
    public boolean checkAndAwardQuizMaster(Student student) {
        boolean allQuizzesCompleted = progress.getCourses().stream()
                .allMatch(course -> course.areAllLessonsCompleted());

        if (allQuizzesCompleted) {
            addAchievement(Achievement.quizMaster());
            return true;            
        }
        return false;
    }

    // Check for Early Bird (Deadline is Today for demo)
    public boolean checkAndAwardEarlyBird(Student student) {
        Date today = new Date();
        boolean allCoursesCompleted = progress.getCourses().stream()
                .allMatch(course -> ((Date) course.getCourseDeadline()).equals(today));

        if (allCoursesCompleted) {
            addAchievement(Achievement.earlyBird());
            return true;
        }
        return false;
    }

    // Check for Perfect Attendance
    public boolean checkAndAwardPerfectAttendance(Student student) {
        boolean allLessonsCompleted = progress.getCourses().stream()
                .allMatch(course -> course.areAllLessonsCompleted());

        if (allLessonsCompleted) {
            addAchievement(Achievement.perfectAttendance());
            return true;
        }
        return false;
    }

    // Check for Top Performer
    public boolean checkAndAwardTopPerformer(Student student) {
        if (progress.getProgressPercentage() > 95) {
            addAchievement(Achievement.topPerformer());
            new UserAchievement(student.getId(), Achievement.topPerformer());
            return true;
        }
        return false;
    }

    // Award all achievements
    public List<Achievement>  awardAllAchievements(Student student) {
        List<Achievement> awardedAchievements = new ArrayList<>();
        if (checkAndAwardQuizMaster(student)) {
            awardedAchievements.add(Achievement.quizMaster());
        }
        if (checkAndAwardEarlyBird(student)) {
            awardedAchievements.add(Achievement.earlyBird());
        }
        if (checkAndAwardPerfectAttendance(student)) {
            awardedAchievements.add(Achievement.perfectAttendance());
        }
        if (checkAndAwardTopPerformer(student)) {
            awardedAchievements.add(Achievement.topPerformer());
        }
        return awardedAchievements;
    }

    // public void viewAchievements() {
    //     System.out.println("🏆 Achievements:");
    //     uniqueAchievements.forEach(System.out::println);
    // }

    public void viewAllAchievements() {
        System.out.println("🏆 All Achievements:");
        for (Achievement achievement : getAllAchievements()) {
            System.out.println(achievement);
        }
    }

    public List<Achievement> getAllAchievements() {
        return List.of(
                Achievement.quizMaster(),
                Achievement.courseLord(),
                Achievement.consistencyChampion(),
                Achievement.earlyBird(),
                Achievement.perfectAttendance(),
                Achievement.subjectMaster("Mathematics"),
                Achievement.topPerformer()
        );
    }


    @Override
    public String toString() {
        return "AchievementManager{" +
                "progress=" + progress +
                ", uniqueAchievements=" + uniqueAchievements +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AchievementManager that = (AchievementManager) obj;
        return Objects.equals(progress, that.progress) &&
                Objects.equals(uniqueAchievements, that.uniqueAchievements);
    }

}
