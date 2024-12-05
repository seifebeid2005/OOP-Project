
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ManagementControl {

    private ArrayList<Tutor> tutors;
    private ArrayList<Student> students;
    private ArrayList<Course> courses; // Added courses

    // Constructor
    public ManagementControl() {
        this.students = new ArrayList<>();
        this.tutors = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // Getters for tutors, students, and courses
    public ArrayList<Tutor> getTutors() {
        return new ArrayList<>(tutors); // Return a copy to maintain immutability
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students); // Return a copy to maintain immutability
    }

    public ArrayList<Course> getCourses() {
        return new ArrayList<>(courses); // Return a copy to maintain immutability
    }

    //----------------- Added methods for Tutors ----------------- 
    public void addTutor(Tutor tutor) {
        if (tutor != null && !tutors.contains(tutor)) {
            tutors.add(tutor);
            System.out.println("Tutor " + tutor.getName() + " added successfully.");
        } else {
            System.out.println("Invalid tutor or already added.");
        }

    }

    public void removeTutor(Long id) {
        Tutor tutor = findTutorById(id);
        if (tutors.remove(tutor)) {
            System.out.println("Tutor " + tutor.getName() + " removed successfully.");
        } else {
            System.out.println("Tutor not found.");
        }
    }

    public Tutor findTutorById(Long id) {
        for (Tutor tutor : tutors) {
            if (Objects.equals(tutor.getId(), id)) {
                return tutor;
            }
        }
        return null; // Tutor not found
    }

    public void listTutors() {
        if (tutors.isEmpty()) {
            System.out.println("No tutors available.");
        } else {
            System.out.println("Tutors:");
            for (Tutor tutor : tutors) {
                System.out.println("- " + tutor.getName());
            }
        }
    }

    public void updateTutor(long tutorId) {
        Tutor tutor = findTutorById(tutorId);
        if (tutor == null) {
            System.out.println("Tutor not found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean continueUpdating = true;

        while (continueUpdating) {
            System.out.println("\nSelect an option to update for tutor " + tutor.getName() + ":");
            System.out.println("1. Update Name");
            System.out.println("2. Update Email");
            System.out.println("3. Update Phone");
            System.out.println("4. Update Address");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    tutor.setName(newName);
                    System.out.println("Name updated successfully.");
                }

                case 2 -> {
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    tutor.setEmail(newEmail);
                    System.out.println("Email updated successfully.");
                }

                case 3 -> {
                    System.out.print("Enter new phone: ");
                    String newPhone = scanner.nextLine();
                    tutor.setPhone(newPhone);
                    System.out.println("Phone updated successfully.");
                }

                case 4 -> {
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    tutor.setAddress(newAddress);
                    System.out.println("Address updated successfully.");
                }

                case 5 -> {
                    continueUpdating = false;
                    System.out.println("Exiting update process.");
                }

                default ->
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public void addStudent(Student student) {
        if (student != null && !students.contains(student)) {
            students.add(student);
            System.out.println("Student " + student.getName() + " added successfully.");
        } else {
            System.out.println("Invalid student or already enrolled.");
        }
    }

    public void removeStudent(long id) {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student " + student.getName() + " removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
    
    public Student findStudentById(long id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null; // Student not found
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("Students:");
            for (Student student : students) {
                System.out.println("- " + student.getName());
            }
        }
    }

    public void updateStudent(long studentId) {
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean continueUpdating = true;

        while (continueUpdating) {
            System.out.println("\nSelect an option to update for student " + student.getName() + ":");
            System.out.println("1. Update Name");
            System.out.println("2. Update Email");
            System.out.println("3. Update Phone");
            System.out.println("4. Update Address");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    student.setName(newName);
                    System.out.println("Name updated successfully.");
                }

                case 2 -> {
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    student.setEmail(newEmail);
                    System.out.println("Email updated successfully.");
                }

                case 3 -> {
                    System.out.print("Enter new phone: ");
                    String newPhone = scanner.nextLine();
                    student.setPhone(newPhone);
                    System.out.println("Phone updated successfully.");
                }

                case 4 -> {
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    student.setAddress(newAddress);
                    System.out.println("Address updated successfully.");
                }

                case 5 -> {
                    continueUpdating = false;
                    System.out.println("Exiting update process.");
                }

                default ->
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    //----------------- Added methods for Courses -----------------

    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
            System.out.println("Course " + course.getCourseName() + " added successfully.");
        } else {
            System.out.println("Invalid course or already added.");
        }
    }

    public void removeCourse(Course course) {
        if (courses.remove(course)) {
            System.out.println("Course " + course.getCourseName() + " removed successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public Course findCourseById(long id) {
        for (Course course : courses) {
            if (course.getCourseId() == id) {
                return course;
            }
        }
        return null; // Course not found
    }

    public void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Courses:");
            for (Course course : courses) {
                System.out.println("- " + course.getCourseName());
            }
        }
    }

    // List all tutors, students, and courses
    public void listAll() {
        System.out.println("Listing all tutors, students, and courses:");
        listTutors();
        listStudents();
        listCourses();
    }

}
