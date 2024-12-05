import java.util.ArrayList;


public class CourseLevel {
    private long courseLevelId;
    private String courseLevelName;
    private String courseLevelDescription;
    private Integer courseLevelRequiredProgress;
    private Boolean courseLevelIsActive;
    private ArrayList<Chapter> chapters = new ArrayList<>();

    public CourseLevel() {
    }

    public CourseLevel(long courseLevelId, String courseLevelName, String courseLevelDescription, Integer courseLevelRequiredProgress, Boolean courseLevelIsActive) {
        setCourseLevelId(courseLevelId);
        setCourseLevelName(courseLevelName);
        setCourseLevelDescription(courseLevelDescription);
        setCourseLevelRequiredProgress(courseLevelRequiredProgress);
        setCourseLevelIsActive(courseLevelIsActive);
    }

    public long getCourseLevelId() {
        return courseLevelId;
    }

    public void setCourseLevelId(long courseLevelId) {
        if (courseLevelId <= 0) {
            throw new IllegalArgumentException("Course Level ID must be a positive number.");
        }
        this.courseLevelId = courseLevelId;
    }
    

    public String getCourseLevelName() {
        return courseLevelName;
    }

    public void setCourseLevelName(String courseLevelName) {
        if (courseLevelName == null || courseLevelName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course Level Name cannot be null or empty.");
        }
        if (courseLevelName.length() > 100) {
            throw new IllegalArgumentException("Course Level Name cannot exceed 100 characters.");
        }
        this.courseLevelName = courseLevelName;
    }

    public String getCourseLevelDescription() {
        return courseLevelDescription;
    }

    public void setCourseLevelDescription(String courseLevelDescription) {
        if (courseLevelDescription == null || courseLevelDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Course Level Description cannot be null or empty.");
        }
        if (courseLevelDescription.length() > 500) {
            throw new IllegalArgumentException("Course Level Description cannot exceed 500 characters.");
        }
        this.courseLevelDescription = courseLevelDescription;
    }

    public Integer getCourseLevelRequiredProgress() {
        return courseLevelRequiredProgress;
    }

    public void setCourseLevelRequiredProgress(Integer courseLevelRequiredProgress) {
        if (courseLevelRequiredProgress == null || courseLevelRequiredProgress < 0) {
            throw new IllegalArgumentException("Required Progress must be zero or a positive number.");
        }
        this.courseLevelRequiredProgress = courseLevelRequiredProgress;
    }

    public Boolean getCourseLevelIsActive() {
        return courseLevelIsActive;
    }

    public void setCourseLevelIsActive(Boolean courseLevelIsActive) {
        if (courseLevelIsActive == null) {
            throw new IllegalArgumentException("Course Level Active status cannot be null.");
        }
        this.courseLevelIsActive = courseLevelIsActive;
    }

    public void addChapter(Chapter chapter) {
        if (chapter == null) {
            throw new IllegalArgumentException("Chapter cannot be null.");
        }
        chapters.add(chapter);
    }

    public ArrayList<Chapter> getChapters() {
        return new ArrayList<>(chapters); // Return a copy to maintain encapsulation
    }

    public Chapter findChapterById(long chapterId) {
        for (Chapter chapter : chapters) {
            if (chapter.getLessonID() == chapterId) {
                return chapter;
            }            
        }
        return null; // Or throw an exception if preferred
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CourseLevel that = (CourseLevel) o;
        return courseLevelId == that.courseLevelId;
    }


    @Override
    public String toString() {
        return "CourseLevel {" +
               "courseLevelId=" + courseLevelId +
               ", courseLevelName='" + courseLevelName + '\'' +
               ", courseLevelDescription='" + courseLevelDescription + '\'' +
               ", courseLevelRequiredProgress=" + courseLevelRequiredProgress +
               ", courseLevelIsActive=" + courseLevelIsActive +
               '}';
    }
}