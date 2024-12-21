import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Admin admin = new Admin("123", "123");

        String SchoolPath = "tx/School.txt";
        String StudentsPath = "tx/Student.txt";
        String TutorPath = "tx/tutor.txt";
        String CoursePath = "tx/Course.txt";
        String lessonpath = "tx/Lesson.txt";
        String quizpath = "tx/Quiz.txt";
        String QustionsPath = "tx/Questions.txt";
        String StdWithCoursePath = "tx/StudentWithCourse.txt";
        admin.createSchoolFromFile(SchoolPath);
        admin.createStudentFromFile(StudentsPath);
        admin.createTutorFromFile(TutorPath);
        admin.createCourseFromFile(CoursePath, lessonpath, quizpath, QustionsPath);
        admin.createLessonFromFile(lessonpath);
        admin.createQuizFromFile(quizpath);
        admin.createQuestionsFromFile(QustionsPath);
        admin.assignCourseToStudentFromFile(StdWithCoursePath);

        //check username and pass for security
        int trials = 0;
        while (true) {
            System.out.println("Welcome to Education Center CAF-JS");
            System.out.println("Please choose your role: ");
            System.out.println("1. Admin");
            System.out.println("2. School");
            System.out.println("3. Student");
            System.out.println("4. tutor");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    if (checkForSecurity(admin)) {
                        AdminFunctions(admin, SchoolPath, StudentsPath, TutorPath, CoursePath, lessonpath, quizpath , QustionsPath); // Call Admin function
                    } else {
                        trials++;
                        System.out.println("Invalid username or password, please try again.");
                    }
                }
                case 2 -> {
                    if (admin.getSchoolsData().isEmpty()) {
                        System.out.println("No schools available. Please contact the admin to create a school.");
                    } else {
                        int schoolId = checkForSecurityForSchool(admin);
                        if (schoolId != 0) {
                            SchoolFunctions(admin, SchoolPath, StudentsPath, TutorPath, CoursePath, lessonpath, quizpath, schoolId);
                        } else {
                            trials++;
                            System.out.println("Invalid username or password, please try again.");
                        }
                    }
                }
                    case 3 -> {
                        if (admin.getSchoolsData().isEmpty()) {
                            System.out.println("No schools available. Please contact the admin to create a school.");
                        } else {
                            Student Student = checkForSecurityForStudent(admin);
                            if (Student.getId() != 0) {
                                StudentFunctions(admin, Student);
                            } else {
                                trials++;
                                System.out.println("Invalid username or password, please try again.");
                            }
                        }
                    }
                    case 4 -> TeacherFunctions();
                case 5 -> {
                    System.out.println("See you later!");
                    return; // Exit the program
                }
                default -> System.out.println("Invalid choice, please try again");
            }
            if (trials == 3) {
                System.out.println("You have exceeded the maximum number of trials. Exiting program.");
                return;
            }
        }
    }

    // ---------------------Handle Operations---------------------
   
    // Handle Add Operations
    public static void handleAddOperations(Admin admin, String pathfile, String pathfile2, String TutorPath, String CoursePath, String lessonpath, String quizpath , String QustionsPath) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------");
        System.out.println("Add Operations:");
        System.out.println("1. Add School");
        System.out.println("2. Add Student");
        System.out.println("3. Add Teacher");
        System.out.println("4. Add Course");

        int choice = sc.nextInt();
        sc.nextLine(); // consume the remaining newline

        switch (choice) {
            case 1:
                Schoolcreation(admin, pathfile);
                break;
            case 2:
                Studentcreation(admin, pathfile2);
                break;
            case 3:
                Teachercreation(admin, TutorPath);
                break;
            case 4:
                CourseCreation(admin, CoursePath, lessonpath, quizpath , QustionsPath);

                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    // Handle Update Operations
    public static void handleUpdateOperations(Admin admin) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------");
        System.out.println("Update Operations:");
        System.out.println("1. Update School");
        System.out.println("2. Update Student");
        System.out.println("3. Update Teacher");
        System.out.println("4. Update Course");

        int choice = sc.nextInt();
        sc.nextLine(); // consume the remaining newline

        switch (choice) {
            case 1 -> admin.updateSchoolByCriteria();
            case 2 -> admin.updateStudentById();
            case 3 -> admin.updateTutorById();
            case 4 -> {

            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
        // admin.updateCourse();
            }

    // Handle Delete Operations
    public static void handleDeleteOperations(Admin admin) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------");
        System.out.println("Delete Operations:");
        System.out.println("1. Delete School");
        System.out.println("2. Delete Student");
        System.out.println("3. Delete Teacher");
        System.out.println("4. Delete Course");

        int choice = sc.nextInt();
        sc.nextLine(); // consume the remaining newline

        switch (choice) {
            case 1:
                admin.removeSchool();
                break;
            case 2:
                admin.removeStudentByCriteria();
                break;
            case 3:
                admin.removeTutorByCriteria();
                break;
            case 4:
                // admin.deleteCourse();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    // Handle View Operations
    public static void handleViewOperations(Admin admin) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------");
        System.out.println("View Operations:");
        System.out.println("1. View Schools");
        System.out.println("2. View Students");
        System.out.println("3. View Teachers");
        System.out.println("4. View Courses");

        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> admin.viewSchools();
            case 2 -> admin.viewStudents();
            case 3 -> admin.viewTutors();
            case 4 -> admin.viewCourses();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
    
    // ---------------------Each Role Functions---------------------
    // Function for Admin actions
    public static void AdminFunctions(Admin admin, String pathfile, String pathfile2, String TutorPath, String coursepath, String lessonpath, String quizpath , String QustionsPath) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------");
            System.out.println("Welcome Admin");
            System.out.println("Please choose your action:");
            System.out.println("1. Add Operations");
            System.out.println("2. Update Operations");
            System.out.println("3. Delete Operations");
            System.out.println("4. View Records");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // consume the remaining newline

            switch (choice) {
                case 1:
                    handleAddOperations(admin, pathfile, pathfile2, TutorPath, coursepath, lessonpath, quizpath , QustionsPath);
                    break;
                case 2:
                    handleUpdateOperations(admin);
                    break;
                case 3:
                    handleDeleteOperations(admin);
                    break;
                case 4:
                    handleViewOperations(admin);
                    break;
                case 5:
                    saveAllChanges(admin, pathfile, pathfile2, TutorPath, coursepath, lessonpath, quizpath, QustionsPath);
                    System.out.println("Goodbye Admin");
                    return; // Exit from Admin functions
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Function for School actions
    public static void SchoolFunctions(Admin admin , String SchoolPath ,   String StudentsPath , String TutorPath , String CoursePath , String lessonpath , String quizpath , int SchoolId) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------");
            System.out.println("Welcome School");
            System.out.println("Please choose your action:");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View Students");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // consume the remaining newline

            switch (choice) {
                case 1 -> Studentcreation(admin, StudentsPath);
                case 2 -> admin.updateStudentById(SchoolId);
                case 3 -> admin.removeStudentById(SchoolId);
                case 4 -> admin.viewStudentsBySchool(SchoolId);
                case 5 -> {
                    System.out.println("Goodbye School");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // Function for Student actions
    public static void StudentFunctions(Admin admin, Student student) {
         Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-------------------------");
            student.login();
            System.out.println("Choose an option:");
            System.out.println("0. Print my profile");
            System.out.println("1. See my courses");
            System.out.println("2. See my lessons");
            System.out.println("3. See my tutors");
            System.out.println("4. See my Quizzes to solve");
            System.out.println("5. See my Grades");
            System.out.println("6. See my completed courses");
            System.out.println("7. See my not completed courses");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                // Print my profile
                System.out.println(student.toString());
                break;
            case 1:
                // See my courses
                student.viewCourses();
                break;
            case 2:
                // See my lessons
                student.viewLessons();
                break;
            case 3:
                // See my tutors
                admin.getTutorsForStudentCourses(student.getId());
                break;
            case 4:
                // See my Quizzes to solve
                student.startQuiz();
                break;
            case 5:
                // See my Grades
                student.viewQuizResult();
                break;
            case 6:
                // See my completed courses
                ArrayList<Course> completedCourses = student.getCompletedCourses();
                if (completedCourses.isEmpty()) {
                    System.out.println("You have not completed any courses yet.");
                } else {
                    System.out.println("Completed Courses:");
                    completedCourses.forEach(course -> System.out.println(course.getCourseName()));
                }
                break;
            case 7:
                // See my not completed courses
                ArrayList<Course> notCompletedCourses = student.getNotCompletedCourses();
                if (notCompletedCourses.isEmpty()) {
                    System.out.println("You have completed all your courses.");
                } else {
                    System.out.println("Not Completed Courses:");
                    notCompletedCourses.forEach(course -> System.out.println(course.getCourseName()));
                }
                break;
          
            case 8:
                // Exit
                student.logout();
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice! Please select a valid option.");
                break;
            }
        }
    }

    public static void TeacherFunctions() {
        System.out.println("Welcome Teacher");
        // Implement any teacher-specific logic or actions here
    }

    //----------------------Create Functions----------------------  

    // Function for Teacher actions
    public static void Schoolcreation(Admin admin, String pathfile) {
        System.out.println("Welcome to School Creation");
        System.out.println("Please choose your action:");
        System.out.println("1. Create School from terminal");
        System.out.println("2. Create School from file");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine(); // consume the remaining newline

        switch (choice) {
            case 1 -> {
                System.out.println("Creating school from terminal...");
                admin.createSchool();
            }
            case 2 -> {
                System.out.println("Creating school from file...");
                admin.createSchoolFromFile(pathfile);
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }

    }
    
    // Function for Student actions
    public static void Studentcreation(Admin admin, String filePath2) {
        // Create a school instance
        System.out.println("Welcome to Student Creation");
        System.out.println("Please choose your action:");
        System.out.println("1. Create Student from terminal");
        System.out.println("2. Create Student from file ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine(); // consume the remaining newline
        switch (choice) {
            case 1 -> {
                System.out.println("Creating Student from terminal...");
                admin.createStudentAccount();
            }
            case 2 -> {
                System.out.println("Creating Student from file...");
                admin.createStudentFromFile(filePath2);
            }

            default -> {
            }
        }

    }
    
    // Function for Teacher actions
    public static void Teachercreation(Admin admin, String TutorPath) {
        System.out.println("Welcome to Teacher Creation");
        System.out.println("Please choose your action:");
        System.out.println("1. Create Teacher from terminal");
        System.out.println("2. Create Teacher from file");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine(); // consume the remaining newline

        switch (choice) {
            case 1 -> {
                System.out.println("Creating teacher from terminal...");
                admin.createTutorAccount();
            }
            case 2 -> {
                System.out.println("Creating teacher from file...");
                admin.createTutorFromFile(TutorPath);
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
    
    // Function for Course actions
    public static void CourseCreation(Admin admin, String coursepath, String lessonPath, String quizPath , String QustionsPath) {
        // Create a school instance
        System.out.println("Welcome to Course Creation");
        System.out.println("Please choose your action:");
        System.out.println("1. Create Course from terminal");
        System.out.println("2. Create Course from file");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1 -> {
                System.out.println("Creating Course from terminal...");
                admin.CreateCourse();
            }
            case 2 -> {
                System.out.println("Creating Course from file...");
                admin.createCourseFromFile(coursepath, lessonPath, quizPath, QustionsPath);
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    //----------------------More Functions----------------------

    // Saving all data
    public static void SaveAllData(Admin admin, String SchoolPath, String StudentsPath, String TutorPath, String CoursePath, String lessonpath, String quizpath, String QustionsPath) {
        // saveToFile(SchoolPath, admin.getSchoolsData());
        saveToFile(SchoolPath, admin.getSchoolsData());

    }
    
    // Save data to file
    public static void saveToFile(String filePath, ArrayList<?> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Object item : data) {
                writer.println(item);
                System.out.println(" ");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
   
    // Check for security
    public static Boolean checkForSecurity(Admin admin) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        return admin.checkLogin(username, password);
    }
    
    // Check for security for school
    public static int checkForSecurityForSchool(Admin admin) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        int x =  admin.checkSchoolLogin(username, password);
        return x;
    }

    public static Student checkForSecurityForStudent(Admin admin) {
        Student student ;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        student =  admin.checkStudentLogin(username, password);
        return student;
    }
    
    public static void saveAllChanges(Admin admin, String SchoolPath, String StudentsPath, String TutorPath, String CoursePath, String lessonpath, String quizpath, String QustionsPath) {
        admin.saveSchoolToFile(SchoolPath);
        admin.saveStudentToFile(StudentsPath);
        admin.saveTutorToFile(TutorPath);
        admin.saveCourseToFile(CoursePath, lessonpath, quizpath, QustionsPath);
    }

}

