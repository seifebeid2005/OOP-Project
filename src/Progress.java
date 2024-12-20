import java.util.ArrayList;

public class Progress {

    private long progressId;
    private ArrayList<Course> courses = new ArrayList<>();
    private int progressPercentage;

    // Add a course to the progress
    public void addCourse(ArrayList<Course> courses) {
        this.courses = new ArrayList<>(courses); // Create a new list to avoid shared references
    }

    // Get all courses in progress
    public ArrayList<Course> getCourses() {
        return new ArrayList<>(courses); // Return a copy to maintain encapsulation
    }

    // Get the current progress of the user (average progress of all courses)
    public int getProgressPercentage() {
        return progressPercentage;
    }

    // Set overall progress percentage
    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    // Mark course as completed if all lessons in it are completed
    public void checkAndMarkCourseCompletion(long courseId) {
        Course course = findCourseById(courseId);
        if (course != null) {
            if (course.areAllLessonsCompleted()) {
                course.markAsCompleted();
            }
        }
    }

    // Find course by ID
    private Course findCourseById(long courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    // View courses that are completed
    public ArrayList<Course> getCompletedCourses() {
        ArrayList<Course> completedCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.isCompleted()) {
                completedCourses.add(course);
            }
        }
        return completedCourses;
    }

    // View courses that are not completed
    public ArrayList<Course> getNotCompletedCourses() {
        ArrayList<Course> notCompletedCourses = new ArrayList<>();
        for (Course course : courses) {
            if (!course.isCompleted()) {
                notCompletedCourses.add(course);
            }
        }
        return notCompletedCourses;
    }

    // Mark lesson as completed for a specific course
    public void markLessonAsCompleted(long courseId, long lessonId) {
        Course course = findCourseById(courseId);
        if (course != null) {
            Lesson lesson = course.findLessonById(lessonId);
            if (lesson != null) {
                lesson.markAsCompleted();
            }
        }
    }

    // Mark lesson as incomplete for a specific course
    public void markLessonAsIncomplete(long courseId, long lessonId) {
        Course course = findCourseById(courseId);
        if (course != null) {
            Lesson lesson = course.findLessonById(lessonId);
            if (lesson != null) {
                lesson.markAsIncomplete();
            }
        }
    }

    // Return overall progress level based on all courses
    public String getOverallProgressLevel() {
        if (progressPercentage >= 90) {
            return "Excellent";
        } else if (progressPercentage >= 75) {
            return "Good";
        } else if (progressPercentage >= 50) {
            return "Average";
        } else {
            return "Poor";
        }
    }
}
