
public class CourseLevel {
    private Long courseLevelId;
    private String courseLevelName;
    private String courseLevelDescription;
    private Integer courseLevelRequiredProgress;
    private Boolean courseLevelIsActive;

    public CourseLevel(Long courseLevelId, String courseLevelName, String courseLevelDescription, Integer courseLevelRequiredProgress, Boolean courseLevelIsActive) {
        setCourseLevelId(courseLevelId);
        setCourseLevelName(courseLevelName);
        setCourseLevelDescription(courseLevelDescription);
        setCourseLevelRequiredProgress(courseLevelRequiredProgress);
        setCourseLevelIsActive(courseLevelIsActive);
    }

    public Long getCourseLevelId() {
        return courseLevelId;
    }

    public void setCourseLevelId(Long courseLevelId) {
        if (courseLevelId == null || courseLevelId <= 0) {
            throw new IllegalArgumentException("Course Level ID must be a positive number.");
            //print error message
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

