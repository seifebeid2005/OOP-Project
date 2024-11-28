package classes.Lesson;

public class Chapter {

    private int lesson_id;
    private String lesson_title;
    private String lesson_description;
    private int level_id;
    private int duration;
    private String language;
    private String lesson_type;
    private int tutor_id;
    private String materials;

    public Chapter(int lesson_id, String lesson_title, String lesson_description, int level_id, int duration, String language, String lesson_type, int tutor_id) {
        this.lesson_id = lesson_id;
        this.lesson_title = lesson_title;
        this.lesson_description = lesson_description;
        this.level_id = level_id;
        this.duration = duration;
        this.language = language;
        this.lesson_type = lesson_type;
        this.tutor_id = tutor_id;
        this.materials = "";
    }

    public int get_lesson_id() {
        return lesson_id;
    }

    public void set_lesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String get_lesson_title() {
        return lesson_title;
    }

    public void set_lesson_title(String lesson_title) {
        this.lesson_title = lesson_title;
    }

    public String get_lesson_description() {
        return lesson_description;
    }

    public void set_lesson_description(String lesson_description) {
        this.lesson_description = lesson_description;
    }

    public int get_level_id() {
        return level_id;
    }

    public void set_level_id(int level_id) {
        this.level_id = level_id;
    }

    public int get_duration() {
        return duration;
    }

    public void set_duration(int duration) {
        this.duration = duration;
    }

    public String get_language() {
        return language;
    }

    public void set_language(String language) {
        this.language = language;
    }

    public String get_lesson_type() {
        return lesson_type;
    }

    public void set_lesson_type(String lesson_type) {
        this.lesson_type = lesson_type;
    }

    public int get_tutor_id() {
        return tutor_id;
    }

    public void set_tutor_id(int tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String get_materials() {
        return materials;
    }

    // Method to add material
    public void add_material(String material) {
        materials += material;
    }

    // Method to remove material
    public void remove_material(String material) {
        String[] material_array = materials.split(", ");
        String temp = "";

        for (String mat : material_array) {
            if (!mat.equals(material)) {
                if (!temp.isEmpty()) {
                    temp += ", ";
                }
                temp += mat;
            }
        }

        materials = temp;
    }



    @Override
    public String toString() {
        return "lesson ID:" + lesson_id + ", lesson Title:" + lesson_title + ", lesson Description:" + lesson_description + ", level ID:" + level_id + ", duration:" + duration + ", language:" + language + ", lesson Type:" + lesson_type + ", tutor ID:" + tutor_id + ", materials:" + materials;
    }
}
