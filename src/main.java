
import java.io.Console;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //    public Admin(Long id, String name, String email, Role roleEnum, LocalDate dateOfBirth, String phone, String address, String username, String password) {
        Admin admin = new Admin("123", "123"); // Create an admin instance
        String SchoolPath = "tx/School.txt";
        String StudentsPath = "tx/Student.txt";
        String TutorPath = "tx/tutor.txt";
        String CoursePath = "tx/Course.txt";
        String lessonpath = "tx/Lesson.txt";
        String quizpath = "tx/Quiz.txt";
        String QustionsPath = "tx/Questions.txt";
        admin.createSchoolFromFile(SchoolPath);
        admin.createStudentFromFile(StudentsPath);
        admin.createTutorFromFile(TutorPath);
        admin.createCourseFromFile(CoursePath, lessonpath, quizpath, QustionsPath);
        admin.createLessonFromFile(lessonpath);
        admin.createQuizFromFile(quizpath);
        admin.createQuestionsFromFile(QustionsPath);

        //check username and pass for security
        int trials = 0;
        while (true) {
            System.out.println("Welcome to Education Center CAF-JS");
            System.out.println("Please choose your role: ");
            System.out.println("1. Admin");
            System.out.println("2. School");
            System.out.println("3. Student");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (checkForSecurity(admin)) {
                        AdminFunctions(admin, SchoolPath, StudentsPath, TutorPath, CoursePath, lessonpath, quizpath); // Call Admin function
                    } else {
                        trials++;
                        System.out.println("Invalid username or password, please try again.");
                    }
                    break;

                case 2:
                    SchoolFunctions(); // Call School function
                    break;
                case 3:
                    StudentFunctions(); // Call Student function
                    break;
                case 4:
                    System.out.println("Goodbye");
                    SaveAllData(admin, SchoolPath, StudentsPath, TutorPath, CoursePath, lessonpath, quizpath, QustionsPath);
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice, please try again");
                    break;
            }
            if (trials == 3) {
                System.out.println("You have exceeded the maximum number of trials. Exiting program.");
                return;
            }
        }
    }

    public static void SaveAllData(Admin admin, String SchoolPath, String StudentsPath, String TutorPath, String CoursePath, String lessonpath, String quizpath, String QustionsPath) {
        // saveToFile(SchoolPath, admin.getSchoolsData());
        saveToFile(SchoolPath, admin.getSchoolsData());

    }

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

    public static Boolean checkForSecurity(Admin admin) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        return admin.checkLogin(username, password);
    }

    // Function for Admin actions
    public static void AdminFunctions(Admin admin, String pathfile, String pathfile2, String TutorPath, String coursepath, String lessonpath, String quizpath) {
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
                    handleAddOperations(admin, pathfile, pathfile2, TutorPath, coursepath, lessonpath, quizpath);
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
                    System.out.println("Goodbye Admin");
                    return; // Exit from Admin functions
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Handle Add Operations
    public static void handleAddOperations(Admin admin, String pathfile, String pathfile2, String TutorPath, String CoursePath, String lessonpath, String quizpath) {
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
                Coursecreation(admin, CoursePath, lessonpath, quizpath);

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
            case 1:
                admin.updateSchoolByCriteria();
                break;
            case 2:
                admin.updateStudentById();
                break;
            case 3:
                admin.updateTutorById();
                break;
            case 4:
                // admin.updateCourse();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
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
            case 1:
                admin.viewSchools();
                break;
            case 2:
                admin.viewStudents();
                break;
            case 3:
                admin.viewTutors();
                break;
            case 4:
                admin.viewCourses();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    // Function for School actions
    public static void SchoolFunctions() {
        System.out.println("Welcome School");
        // Implement any school-specific logic or actions here
    }

    // Function for Student actions
    public static void StudentFunctions() {
        System.out.println("Welcome Student");
        // Implement any student-specific logic or actions here
    }

    public static void Schoolcreation(Admin admin, String pathfile) {
        System.out.println("Welcome to School Creation");
        System.out.println("Please choose your action:");
        System.out.println("1. Create School from terminal");
        System.out.println("2. Create School from file");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine(); // consume the remaining newline

        switch (choice) {
            case 1:
                System.out.println("Creating school from terminal...");
                admin.createSchool();
                break;
            case 2:
                System.out.println("Creating school from file...");
                admin.createSchoolFromFile(pathfile);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

    }

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
            case 1:
                System.out.println("Creating Student from terminal...");
                admin.createStudentAccount();

                break;
            case 2:
                System.out.println("Creating Student from file...");
                admin.createStudentFromFile(filePath2);
                break;

            default:
                break;
        }

    }

    public static void Teachercreation(Admin admin, String TutorPath) {
        System.out.println("Welcome to Teacher Creation");
        System.out.println("Please choose your action:");
        System.out.println("1. Create Teacher from terminal");
        System.out.println("2. Create Teacher from file");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine(); // consume the remaining newline

        switch (choice) {
            case 1:
                System.out.println("Creating teacher from terminal...");
                admin.createTutorAccount();
                break;
            case 2:
                System.out.println("Creating teacher from file...");
                admin.createTutorFromFile(TutorPath);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void Coursecreation(Admin admin, String coursepath, String lessonPath, String quizPath) {
        // Create a school instance
        System.out.println("Welcome to Course Creation");
        System.out.println("Please choose your action:");
        System.out.println("1. Create Course from terminal");
        System.out.println("2. Create Course from file");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Creating Course from terminal...");
                admin.CreateCourse();
                break;
            case 2:
                System.out.println("Creating Course from file...");
                admin.createCourseFromFile(coursepath, lessonPath, quizPath, "tx/Questions.txt");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

}
