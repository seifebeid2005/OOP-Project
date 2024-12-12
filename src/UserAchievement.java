import java.util.*;

public class UserAchievement {
    private Long studentID;
    private int userAchievementID;
    private int achievementID;
    private Date dateAwarded;

    // Thread-safe static list using manual synchronization
    private static final List<UserAchievement> achievementHistory = new ArrayList<>();

    // Constructor
    public UserAchievement(int userAchievementID, int achievementID, Date dateAwarded, Long studentID) {
        setUserAchievementID(userAchievementID);
        setAchievementID(achievementID);
        setDateAwarded(dateAwarded);
        setStudentID(studentID);
        synchronized (achievementHistory) {
            achievementHistory.add(this); // Add to history in a synchronized block
        }
        logAchievementAction("Added", this);
    }

    // New Methods

    public boolean hasUserEarnedAchievement(int userId, int achievementId) {
        return Objects.equals(this.studentID, (long) userId) && this.achievementID == achievementId;
    }

    public static double getAchievementProgressPercentage(int userId, int achievementId, int totalSteps) {
        synchronized (achievementHistory) {
            long count = achievementHistory.stream()
                .filter(a -> Objects.equals(a.studentID, (long) userId) && a.achievementID == achievementId)
                .count();
            return (count / (double) totalSteps) * 100;
        }
    }

    public static List<UserAchievement> generateAchievementCompletionTimeline(int userId) {
        synchronized (achievementHistory) {
            List<UserAchievement> timeline = new ArrayList<>();
            for (UserAchievement ua : achievementHistory) {
                if (Objects.equals(ua.studentID, (long) userId)) {
                    timeline.add(ua);
                }
            }
            timeline.sort(Comparator.comparing(UserAchievement::getDateAwarded, Comparator.nullsLast(Comparator.naturalOrder())));
            return timeline;
        }
    }

    public static void sendAchievementMilestoneNotification(int userId, int milestone) {
        System.out.println("Congratulations! User " + userId + " has achieved " + milestone + " milestones!");
    }

    public static List<UserAchievement> getAchievementHistory(int userId) {
        synchronized (achievementHistory) {
            List<UserAchievement> userHistory = new ArrayList<>();
            for (UserAchievement ua : achievementHistory) {
                if (Objects.equals(ua.studentID, (long) userId)) {
                    userHistory.add(ua);
                }
            }
            return userHistory;
        }
    }

    public static boolean removeUserAchievement(int userAchievementID) {
        synchronized (achievementHistory) {
            return achievementHistory.removeIf(ua -> ua.getUserAchievementID() == userAchievementID);
        }
    }

    public static List<UserAchievement> getAchievementsByDateRange(int userId, Date startDate, Date endDate) {
        synchronized (achievementHistory) {
            List<UserAchievement> filteredAchievements = new ArrayList<>();
            for (UserAchievement ua : achievementHistory) {
                if (Objects.equals(ua.studentID, (long) userId) &&
                    !ua.dateAwarded.before(startDate) && 
                    !ua.dateAwarded.after(endDate)) {
                    filteredAchievements.add(ua);
                }
            }
            return filteredAchievements;
        }
    }

    public static int getTotalAchievementsEarned(int userId) {
        synchronized (achievementHistory) {
            return (int) achievementHistory.stream()
                .filter(ua -> Objects.equals(ua.studentID, (long) userId))
                .count();
        }
    }

    public static boolean isHistoryEmpty(int userId) {
        synchronized (achievementHistory) {
            return achievementHistory.stream()
                .noneMatch(ua -> Objects.equals(ua.studentID, (long) userId));
        }
    }

    public static Map<Integer, List<UserAchievement>> groupAchievementsByType(int userId) {
        synchronized (achievementHistory) {
            if (achievementHistory.isEmpty()) {
                return Collections.emptyMap(); // Return empty map if no data
            }
    
            Map<Integer, List<UserAchievement>> groupedAchievements = new HashMap<>();
            for (UserAchievement ua : achievementHistory) {
                if (ua.studentID != null && Objects.equals(ua.studentID, (long) userId)) {
                    groupedAchievements
                        .computeIfAbsent(ua.getAchievementID(), k -> new ArrayList<>())
                        .add(ua);
                }
            }
            return groupedAchievements;
        }
    }

    public static void logAchievementAction(String action, UserAchievement achievement) {
        System.out.println(action + ": " + achievement);
    }

    // Standard Getters and Setters

    public int getUserAchievementID() {
        return userAchievementID;
    }

    public void setUserAchievementID(int userAchievementID) {
        if (userAchievementID <= 0) {
            throw new IllegalArgumentException("User Achievement ID must be a positive number.");
        }
        this.userAchievementID = userAchievementID;
    }

    public int getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(int achievementID) {
        if (achievementID <= 0) {
            throw new IllegalArgumentException("Achievement ID must be a positive number.");
        }
        this.achievementID = achievementID;
    }

    public Date getDateAwarded() {
        return dateAwarded;
    }

    public void setDateAwarded(Date dateAwarded) {
        if (dateAwarded == null) {
            throw new IllegalArgumentException("Date awarded cannot be null.");
        }
        if (dateAwarded.after(new Date())) {
            throw new IllegalArgumentException("Date awarded cannot be in the future.");
        }
        this.dateAwarded = dateAwarded;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        if (studentID == null || studentID <= 0) {
            throw new IllegalArgumentException("Student ID must be a positive number and cannot be null.");
        }
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "UserAchievement { " +
                "userAchievementID=" + userAchievementID +
                ", achievementID=" + achievementID +
                ", dateAwarded=" + dateAwarded +
                ", StudentID=" + studentID +
                " }";
    }
}