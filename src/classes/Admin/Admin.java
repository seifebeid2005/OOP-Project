package classes.Admin;

import classes.Student.Student;
import classes.Teacher.Tutor;
import classes.person.Person;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Person {

    private final Role roleEnum;
    private final ArrayList<Student> students;
    private ArrayList<Tutor> tutors;

    public enum Role {
        ADMIN, SUPER_ADMIN, MODERATOR
    }

    public Admin(Long id, String name, String email, Role roleEnum, ArrayList<Student> students, ArrayList<Tutor> tutors,
            LocalDate dateOfBirth, String phone, String address, String username, String password) {
        super(id, name, email, dateOfBirth, phone, address, username, password);
        this.roleEnum = roleEnum;
        this.students = students != null ? students : new ArrayList<>();
        this.tutors = tutors != null ? tutors : new ArrayList<>();
    }

    // Getters and Setters
    public List<Tutor> getTutors() {
        return new ArrayList<>(tutors); // Return a copy to preserve encapsulation
    }

    public void setTutors(ArrayList<Tutor> tutors) {
        this.tutors = tutors != null ? tutors : new ArrayList<>();
    }

    // Add a Tutor
    public void addTutor(Tutor tutor) {
        if (tutor != null) {
            tutors.add(tutor);
            System.out.println("Tutor added successfully.");
        } else {
            System.out.println("Tutor cannot be null.");
        }
    }

    // Remove a Tutor
    public void removeTutor(Tutor tutor) {
        if (tutors.remove(tutor)) {
            System.out.println("Tutor removed successfully.");
        } else {
            System.out.println("Tutor not found.");
        }
    }

    // Update Tutor Information
    public void updateTutor(Long tutorId, String newName, String newEmail, String newPhone, String newAddress, String newSubjectArea) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(tutorId)) {
                if (newName != null) {
                    tutor.setName(newName);
                }
                if (newEmail != null) {
                    tutor.setEmail(newEmail);
                }
                if (newPhone != null) {
                    tutor.setPhone(newPhone);
                }
                if (newAddress != null) {
                    tutor.setAddress(newAddress);
                }
                if (newSubjectArea != null) {
                    tutor.setSubjectArea(newSubjectArea);
                }
                System.out.println("Tutor updated successfully.");
                return;
            }
        }
        System.out.println("Tutor not found.");
    }

    // Assign Role to a Tutor
    public void assignTutorRole(Long tutorId, String role) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(tutorId)) {
                tutor.setRole(role);
                System.out.println("Role updated successfully for Tutor ID: " + tutorId);
                return;
            }
        }
        System.out.println("Tutor not found.");
    }

    // Generalized Search for Tutors
    public void viewTutorsByCriteria(String criteria, Object value) {
        List<Tutor> filteredTutors = new ArrayList<>();
        for (Tutor tutor : tutors) {
            if (matchesCriteria(tutor, criteria, value)) {
                filteredTutors.add(tutor);
            }
        }

        if (filteredTutors.isEmpty()) {
            System.out.println("No tutors found for the given criteria.");
        } else {
            for (Tutor tutor : filteredTutors) {
                System.out.println(tutor);
            }
        }
    }

    private boolean matchesCriteria(Tutor tutor, String criteria, Object value) {
        return switch (criteria.toLowerCase()) {
            case "id" -> tutor.getId().equals(value);
            case "name" -> tutor.getName().equalsIgnoreCase((String) value);
            case "email" -> tutor.getEmail().equalsIgnoreCase((String) value);
            case "dateofbirth" -> tutor.getDateOfBirth().equals(value);
            case "age" -> tutor.getAge() == (int) value;
            case "phone" -> tutor.getPhone().equals(value);
            case "address" -> tutor.getAddress().equalsIgnoreCase((String) value);
            case "username" -> tutor.getUsername().equals(value);
            case "subjectarea" -> tutor.getSubjectArea().equalsIgnoreCase((String) value);
            case "datejoined" -> tutor.getDateJoined().equals(value);
            case "role" -> tutor.getRole().equalsIgnoreCase((String) value);
            case "yearsofexperience" -> tutor.getYearsOfExperience() == (int) value;
            default -> false;
        };
    }

    // View All Tutors
    public void viewTutors() {
        if (tutors.isEmpty()) {
            System.out.println("No tutors to display.");
        } else {
            for (Tutor tutor : tutors) {
                System.out.println(tutor);
            }
        }
    }

    // View Tutors by Experience
    public void viewTutorsByExperience(int minYears) {
        for (Tutor tutor : tutors) {
            if (tutor.getYearsOfExperience() >= minYears) {
                System.out.println(tutor);
            }
        }
    }

    // Assign Subjects to a Tutor
    public void assignSubjectToTutor(Long tutorId, String subject) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(tutorId)) {
                tutor.setSubjectArea(subject);
                System.out.println("Subject assigned successfully to Tutor ID: " + tutorId);
                return;
            }
        }
        System.out.println("Tutor not found.");
    }

    // View Tutors by Subject
    public void viewTutorsBySubject(String subject) {
        for (Tutor tutor : tutors) {
            if (tutor.getSubjectArea().equalsIgnoreCase(subject)) {
                System.out.println(tutor);
            }
        }
    }

    // Deactivate a Tutor
    public void deactivateTutor(Long tutorId) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(tutorId)) {
                tutors.remove(tutor);
                System.out.println("Tutor with ID " + tutorId + " has been deactivated.");
                return;
            }
        }
        System.out.println("Tutor not found.");
    }

    // Account creation for Tutor
    public void createTutorAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Tutor ID: ");
        Long id = input.nextLong();
        input.nextLine(); // Consume newline

        System.out.println("Enter Tutor Name: ");
        String name = input.nextLine();

        System.out.println("Enter Tutor Email: ");
        String email = input.nextLine();

        System.out.println("Enter Tutor Date of Birth (YYYY-MM-DD): ");
        LocalDate dateOfBirth = LocalDate.parse(input.nextLine());

        System.out.println("Enter Tutor Phone: ");
        String phone = input.nextLine();

        System.out.println("Enter Tutor Address: ");
        String address = input.nextLine();

        System.out.println("Enter Tutor Username: ");
        String username = input.nextLine();

        System.out.println("Enter Tutor Password: ");
        String password = input.nextLine();

        System.out.println("Enter Tutor Subject Area: ");
        String subjectArea = input.nextLine();

        System.out.println("Enter Tutor Role: ");
        String role = input.nextLine();

        System.out.println("Enter Tutor Date Joined (YYYY-MM-DD): ");
        LocalDate dateJoined = LocalDate.parse(input.nextLine());

        // Create Tutor object and add to the list
        Tutor tutor = new Tutor(id, name, email, dateOfBirth, phone, address, username, password, subjectArea, dateJoined, role);
        addTutor(tutor);
        System.out.println("Tutor account created successfully!");
    }

    // Account creation for Student
    public void createStudentAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Student ID: ");
        Long id = input.nextLong();
        input.nextLine(); // Consume newline

        System.out.println("Enter Student Name: ");
        String name = input.nextLine();

        System.out.println("Enter Student Email: ");
        String email = input.nextLine();

        System.out.println("Enter Student Date of Birth (YYYY-MM-DD): ");
        LocalDate dateOfBirth = LocalDate.parse(input.nextLine());

        System.out.println("Enter Student Phone: ");
        String phone = input.nextLine();

        System.out.println("Enter Student Address: ");
        String address = input.nextLine();

        System.out.println("Enter Student Username: ");
        String username = input.nextLine();

        System.out.println("Enter Student Password: ");
        String password = input.nextLine();

        System.out.println("Enter Student School ID: ");
        int schoolID = input.nextInt();
        input.nextLine(); // Consume newline

        System.out.println("Enter Student Current Level: ");
        int currentLevel = input.nextInt();
        input.nextLine(); // Consume newline

        System.out.println("Enter Student Registration Date (YYYY-MM-DD): ");
        LocalDate registrationDate = LocalDate.parse(input.nextLine());

        // Create Student object and add to the list
        Student student = new Student(name, email, phone, schoolID, currentLevel, 
        address, id.intValue(), dateOfBirth, registrationDate.atStartOfDay(), username, password, "", "");
        addStudent(student);
        System.out.println("Student account created successfully!");
    }

    // Add a Student to the list
    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student cannot be null.");
        }
    }

    // Remove a Student from the list
    public void removeStudent(Student student) {
        if (students.remove(student)) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Update Student Information
    public void updateStudent(Long studentId, String newName, String newEmail, String newPhone, String newAddress, int newSchoolID, int newCurrentLevel) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                if (newName != null) {
                    student.setName(newName);
                }
                if (newEmail != null) {
                    student.setEmail(newEmail);
                }
                if (newPhone != null) {
                    student.setPhone(newPhone);
                }
                if (newAddress != null) {
                    student.setAddress(newAddress);
                }
                if (newSchoolID != 0) {
                    student.setSchoolID(newSchoolID);
                }
                if (newCurrentLevel != 0) {
                    student.setCurrentLevel(newCurrentLevel);
                }
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Generalized Search for Students
    public void viewStudentsByCriteria(String criteria, Object value) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (matchesCriteria(student, criteria, value)) {
                filteredStudents.add(student);
            }
        }

        if (filteredStudents.isEmpty()) {
            System.out.println("No students found for the given criteria.");
        } else {
            for (Student student : filteredStudents) {
                System.out.println(student);
            }
        }
    }

    private boolean matchesCriteria(Student student, String criteria, Object value) {
        return switch (criteria.toLowerCase()) {
            case "id" -> student.getId() == (int) value;
            case "name" -> student.getName().equalsIgnoreCase((String) value);
            case "email" -> student.getEmail().equalsIgnoreCase((String) value);
            case "dateofbirth" -> student.getDateOfBirth().equals(value);
            case "age" -> student.getAge() == (int) value;
            case "phone" -> student.getPhone().equals(value);
            case "address" -> student.getAddress().equalsIgnoreCase((String) value);
            case "username" -> student.getUsername().equals(value);
            case "schoolid" -> student.getSchoolID() == (int) value;
            case "currentlevel" -> student.getCurrentLevel() == (int) value;
            case "registrationdate" -> student.getRegistrationDate().equals(value);
            default -> false;
        };
    }

    // View All Students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Deactivate a Student
    public void deactivateStudent(Long studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                students.remove(student);
                System.out.println("Student with ID " + studentId + " has been deactivated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // toString override to include Admin-specific details
    @Override
    public String toString() {
        return "Admin { "
                + "roleEnum = " + roleEnum
                + ", Email = " + getEmail()
                + ", Name = " + getName()
                + ", Age = " + getAge()
                + ", ID = " + getId()
                + ", DateAdded = " + getDateAdded()
                + " }";
    }
}
