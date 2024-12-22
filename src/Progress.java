import java.util.ArrayList;

public class Progress {

    private long progressId;
    private ArrayList<Course> courses = new ArrayList<>();
    private int progressPercentage;

    // Add a course to the progress
    public void addCourse(Course course) {
        if (course == null) {
            System.out.println("Course cannot be null.");
            return;
        }
        if (courses.contains(course)) {
            System.out.println("Course is already added to progress.");
            return;
        }
        courses.add(course);
        System.out.println("Course " + course.getCourseName() + " added to progress.");
    }
    // add array of courses
    public void addCourses(ArrayList<Course> courses) {
        for (Course course : courses) {
            addCourse(course);
        }
    }


    // Get all courses in progress
    public ArrayList<Course> getCourses() {
        return new ArrayList<>(courses); // Return a copy to maintain encapsulation
    }

    // Get completed courses
    public ArrayList<Course> getCompletedCourses() {
        ArrayList<Course> completedCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.isCompleted()) {
                completedCourses.add(course);
            }
        }
        return completedCourses;
    }

    // Get not completed courses
    public ArrayList<Course> getNotCompletedCourses() {
        ArrayList<Course> notCompletedCourses = new ArrayList<>();
        for (Course course : courses) {
            if (!course.isCompleted()) {
                notCompletedCourses.add(course);
            }
        }
        return notCompletedCourses;
    }

    // Calculate and update progress percentage
    public void calculateProgressPercentage() {
        if (courses.isEmpty()) {
            progressPercentage = 0;
            return;
        }

        int totalLessons = 0;
        int completedLessons = 0;

        for (Course course : courses) {
            totalLessons += course.getLessons().size();
            completedLessons += course.getCompletedLessonsCount();
        }

        progressPercentage = totalLessons > 0 ? (completedLessons * 100 / totalLessons) : 0;
    }

    // Get overall progress percentage
    public int getProgressPercentage() {
        return progressPercentage;
    }

    // Mark a lesson as completed
    public void markLessonAsCompleted(long courseId, long lessonId) {
        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        Lesson lesson = course.findLessonById(lessonId);
        if (lesson == null) {
            System.out.println("Lesson not found.");
            return;
        }

        lesson.markAsCompleted();
        calculateProgressPercentage();
    }

    // Mark a lesson as incomplete
    public void markLessonAsIncomplete(long courseId, long lessonId) {
        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        Lesson lesson = course.findLessonById(lessonId);
        if (lesson == null) {
            System.out.println("Lesson not found.");
            return;
        }

        lesson.markAsIncomplete();
        calculateProgressPercentage();
    }

    // Check and mark course as completed
    public void checkAndMarkCourseCompletion(long courseId) {
        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.areAllLessonsCompleted()) {
            course.markAsCompleted();
            calculateProgressPercentage();
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

    // Get overall progress level
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
