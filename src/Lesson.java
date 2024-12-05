import java.util.List;
import java.util.ArrayList;

public class Lesson {

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
    private ArrayList<Quiz> quizzes = new ArrayList<>();
    private int Grade;

    public Lesson(int lessonId, String lessonTitle, String lessonDescription, int levelId, int duration, String language, String lessonType, int tutorId) {
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

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    // Additional methods

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


    // Methods for viewing student progress/achievements (stub implementation)
    public void viewStudentsProgress(int schoolID) {
        // Implementation here
    }

    public void viewStudentsAchievements(int schoolID) {
        // Implementation here
    }

  //  Assigning a quiz to all students with the same grade and same tutorId as the lesson
    // public void assignQuiz() {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getGrade() == this.Grade && quiz.getTutorId() == this.tutorId) {
    //             quiz.assignToStudents();
    //         }
    //     }
    // }

    // // Viewing progress of all students with the same grade and same tutorId as the lesson
    // public void viewProgress() {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getGrade() == this.Grade && quiz.getTutorId() == this.tutorId) {
    //             quiz.viewProgress();
    //         }
    //     }
    // }

    // // Viewing achievements of all students with the same grade and same tutorId as the lesson
    // public void viewAchievements() {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getGrade() == this.Grade && quiz.getTutorId() == this.tutorId) {
    //             quiz.viewAchievements();
    //         }
    //     }
    // }

    // // Viewing all students with the same grade and same tutorId as the lesson
    // public void viewStudents() {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getGrade() == this.Grade && quiz.getTutorId() == this.tutorId) {
    //             quiz.viewStudents();
    //         }
    //     }
    // }

    // // Viewing a specific student with the same grade and same tutorId as the lesson
    // public void viewStudent(int studentID) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getGrade() == this.Grade && quiz.getTutorId() == this.tutorId) {
    //             quiz.viewStudent(studentID);
    //         }
    //     }
    // }

    // // list all the quizzes
    // public void listQuizzes() {
    //     for (Quiz quiz : quizzes) {
    //         System.out.println(quiz);
    //     }
    // }

    // // create a new quiz
    // public void createQuiz(int quizId, String quizTitle, String quizDescription, int grade, int tutorId) {
    //     Quiz quiz = new Quiz(quizId, quizTitle, quizDescription, grade, tutorId);
    //     quizzes.add(quiz);
    // }

    // // remove a quiz
    // public void removeQuiz(int quizId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId) {
    //             quizzes.remove(quiz);
    //             break;
    //         }
    //     }
    // }

    // // update a quiz
    // public void updateQuiz(int quizId, String quizTitle, String quizDescription, int grade, int tutorId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId) {
    //             quiz.setQuizTitle(quizTitle);
    //             quiz.setQuizDescription(quizDescription);
    //             quiz.setGrade(grade);
    //             quiz.setTutorId(tutorId);
    //             break;
    //         }
    //     }
    // }

    // // upload a quiz of the same lesson that's already created
    // public void uploadQuiz(int quizId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId) {
    //             quiz.uploadQuiz();
    //             break;
    //         }
    //     }
    // }

    // // view a quiz of the same lesson that's already created
    // public void viewQuiz(int quizId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId) {
    //             quiz.viewQuiz();
    //             break;
    //         }
    //     }
    // }

    // // genaralized search for a quiz
    // public void searchQuiz(String search) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizTitle().contains(search) || quiz.getQuizDescription().contains(search)) {
    //             System.out.println(quiz);
    //         }
    //     }
    // }

    // // search for quiz by criteria
    // public void searchQuizByCriteria(String criteria, String search) {
    //     for (Quiz quiz : quizzes) {
    //         if (criteria.equals("quizTitle") && quiz.getQuizTitle().contains(search)) {
    //             System.out.println(quiz);
    //         } else if (criteria.equals("quizDescription") && quiz.getQuizDescription().contains(search)) {
    //             System.out.println(quiz);
    //         } else if (criteria.equals("grade") && quiz.getGrade() == Integer.parseInt(search)) {
    //             System.out.println(quiz);
    //         } else if (criteria.equals("tutorId") && quiz.getTutorId() == Integer.parseInt(search)) {
    //             System.out.println(quiz);
    //         }
    //     }
    // }

    // // view all the students who have taken the quiz of the same lesson with the same grade and same tutorId
    // public void viewStudentsTakenQuiz(int quizId, int grade, int tutorId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getGrade() == grade && quiz.getTutorId() == tutorId) {
    //             quiz.viewStudents();
    //             break;
    //         }
    //     }
    // }

    // // view a specific student who has taken the quiz of the same lesson with the same grade and same tutorId
    // public void viewStudentTakenQuiz(int quizId, int grade, int tutorId, int studentID) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getGrade() == grade && quiz.getTutorId() == tutorId) {
    //             quiz.viewStudent(studentID);
    //             break;
    //         }
    //     }
    // }

    // // view the progress of all students who have taken the quiz of the same lesson with the same grade and same tutorId
    // public void viewProgressTakenQuiz(int quizId, int grade, int tutorId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getGrade() == grade && quiz.getTutorId() == tutorId) {
    //             quiz.viewProgress();
    //             break;
    //         }
    //     }
    // }

    // // view the achievements of all students who have taken the quiz of the same lesson with the same grade and same tutorId
    // public void viewAchievementsTakenQuiz(int quizId, int grade, int tutorId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getGrade() == grade && quiz.getTutorId() == tutorId) {
    //             quiz.viewAchievements();
    //             break;
    //         }
    //     }
    // }

    // // view the progress of a specific student who has taken the quiz of the same lesson with the same grade and same tutorId
    // public void viewProgressStudentTakenQuiz(int quizId, int grade, int tutorId, int studentID) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getGrade() == grade && quiz.getTutorId() == tutorId) {
    //             quiz.viewProgressStudent(studentID);
    //             break;
    //         }
    //     }
    // }

    // // view the achievements of a specific student who has taken the quiz of the same lesson with the same grade and same tutorId
    // public void viewAchievementsStudentTakenQuiz(int quizId, int grade, int tutorId, int studentID) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getGrade() == grade && quiz.getTutorId() == tutorId) {
    //             quiz.viewAchievementsStudent(studentID);
    //             break;
    //         }
    //     }
    // }

    // view the progress of all students with the same quizId and same lessonId
    // public void viewProgressAllStudentsTakenQuiz(int quizId, int lessonId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getLessonId() == lessonId) {
    //             quiz.viewProgress();
    //             break;
    //         }
    //     }
    // }

    // // view the achievements of all students with the same quizId and same lessonId
    // public void viewAchievementsAllStudentsTakenQuiz(int quizId, int lessonId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getLessonId() == lessonId) {
    //             quiz.viewAchievements();
    //             break;
    //         }
    //     }
    // }

    // // view the progress of a specific student with the same quizId and same lessonId
    // public void viewProgressStudentTakenQuiz(int quizId, int lessonId, int studentID) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getLessonId() == lessonId) {
    //             quiz.viewProgressStudent(studentID);
    //             break;
    //         }
    //     }
    // }

    // // view the achievements of a specific student with the same quizId and same lessonId
    // public void viewAchievementsStudentTakenQuiz(int quizId, int lessonId, int studentID) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getLessonId() == lessonId) {
    //             quiz.viewAchievementsStudent(studentID);
    //             break;
    //         }
    //     }
    // }

    // // view the progress of all students with the same quizId and lessonId
    // public void viewProgressAllStudentsTakenQuiz(int quizId, int lessonId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getLessonId() == lessonId) {
    //             quiz.viewProgress();
    //             break;
    //         }
    //     }
    // }

    // // view the achievements of all students with the same quizId and lessonId
    // public void viewAchievementsAllStudentsTakenQuiz(int quizId, int lessonId) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getLessonId() == lessonId) {
    //             quiz.viewAchievements();
    //             break;
    //         }
    //     }
    // }

    // // view the progress of a specific student with the same quizId and lessonId
    // public void viewProgressStudentTakenQuiz(int quizId, int lessonId, int studentID) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getLessonId() == lessonId) {
    //             quiz.viewProgressStudent(studentID);
    //             break;
    //         }
    //     }
    // }

    // // view the achievements of a specific student with the same quizId and lessonId
    // public void viewAchievementsStudentTakenQuiz(int quizId, int lessonId, int studentID) {
    //     for (Quiz quiz : quizzes) {
    //         if (quiz.getQuizId() == quizId && quiz.getLessonId() == lessonId) {
    //             quiz.viewAchievementsStudent(studentID);
    //             break;
    //         }
    //     }
    // }


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
