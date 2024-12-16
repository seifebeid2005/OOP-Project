import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin(); // Create an admin instance
        String filePath = "School.txt";

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
                    AdminFunctions(admin ,  filePath); // Call Admin function
                    break;
                case 2:
                    SchoolFunctions(); // Call School function
                    break;
                case 3:
                    StudentFunctions(); // Call Student function
                    break;
                case 4:
                    System.out.println("Goodbye");
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice, please try again");
                    break;
            }
        }
    }

    // Function for Admin actions
    public static void AdminFunctions(Admin admin   , String pathfile ) {
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
                    handleAddOperations(admin ,  pathfile);
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
    public static void handleAddOperations(Admin admin , String pathfile) {
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
                admin.createSchoolFromFile(pathfile);
                break;
            case 2:
                admin.createStudentAccount();
                break;
            case 3:
                admin.createTutorAccount();
                break;
            case 4:
                // admin.createCourse();
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
        sc.nextLine(); // consume the remaining newline

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
                // admin.viewCourses();
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


}
