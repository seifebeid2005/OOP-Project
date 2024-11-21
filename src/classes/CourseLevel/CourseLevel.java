package classes.CourseLevel;

public class CourseLevel {
    private Integer id;
    private String name;
    private String description;
    private Integer requiredProgress;
    private Boolean isActive;

    public CourseLevel(Integer id, String name, String description, Integer requiredProgress, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requiredProgress = requiredProgress;
        this.isActive = isActive;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRequiredProgress() {
        return requiredProgress;
    }

    public void setRequiredProgress(Integer requiredProgress) {
        this.requiredProgress = requiredProgress;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "CourseLevel {" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", requiredProgress=" + requiredProgress +
               ", isActive=" + isActive +
               '}';
    }
}

