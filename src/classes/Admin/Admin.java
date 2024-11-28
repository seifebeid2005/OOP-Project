package classes.Admin;

import classes.School.School;
import classes.Student.Student;
import classes.Teacher.Tutor;
import classes.person.Person;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Person {

    private final Role roleEnum;
    private ArrayList<Student> students;
    private ArrayList<Tutor> tutors;
    private static ArrayList<School> schools = new ArrayList<>();

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

    public static List<School> getSchools() {
        return schools;
    }

    public static void setSchools(ArrayList<School> schools) {
        Admin.schools = schools != null ? schools : new ArrayList<>();
    }

    public Role getRoleEnum() {
        return roleEnum;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students); // Return a copy to preserve encapsulation
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students != null ? students : new ArrayList<>();
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
        Tutor tutor = new Tutor(id, name, email, dateOfBirth, phone, address, username, password, subjectArea, dateJoined, role, 0); // Add the missing int parameter
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
        Student student = new Student(name, email, phone, schoolID, currentLevel, address, id.intValue(), dateOfBirth, registrationDate.atStartOfDay(), username, password, "", "");
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

    //Create School
    public void createSchool() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter School ID: ");
        int schoolID = input.nextInt();
        input.nextLine(); // Consume newline

        System.out.println("Enter School Name: ");
        String schoolName = input.nextLine();

        System.out.println("Enter School Address: ");
        String address = input.nextLine();

        System.out.println("Enter School City: ");
        String city = input.nextLine();

        System.out.println("Enter Contact Person: ");
        String contactPerson = input.nextLine();

        System.out.println("Enter Contact Email: ");
        String email = input.nextLine();

        System.out.println("Enter Contact Phone: ");
        String phoneNumber = input.nextLine();

        // Create School object and add to the list
        School school = new School(schoolID, schoolName, address, city, contactPerson, email, phoneNumber);
        addSchool(school);
        System.out.println("School created successfully!");
    }

    // View All Schools
    public void viewSchools() {
        if (Admin.getSchools().isEmpty()) {
            System.out.println("No schools to display.");
        } else {
            for (School school : Admin.getSchools()) {
                System.out.println(school);
            }
        }
    }

    //Add School
    public void addSchool(School school) {
        if (school != null) {
            Admin.getSchools().add(school);
            System.out.println("School added successfully.");
        } else {
            System.out.println("School cannot be null.");
        }
    }

    // Remove a School
    public void removeSchool(School school) {
        if (Admin.getSchools().remove(school)) {
            System.out.println("School removed successfully.");
        } else {
            System.out.println("School not found.");
        }
    }

    // Update School Information
    public void updateSchool(int schoolID, String newName, String newAddress, String newCity, String newContactPerson, String newEmail, String newPhoneNumber) {
        for (School school : School.getSchools()) {
            if (school.getSchoolID() == schoolID) {
                if (newName != null) {
                    school.setSchoolName(newName);
                }
                if (newAddress != null) {
                    school.setAddress(newAddress);
                }
                if (newCity != null) {
                    school.setCity(newCity);
                }
                if (newContactPerson != null) {
                    school.setContactPerson(newContactPerson);
                }
                if (newEmail != null) {
                    school.setEmail(newEmail);
                }
                if (newPhoneNumber != null) {
                    school.setPhoneNumber(newPhoneNumber);
                }
                System.out.println("School updated successfully.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Generalized Search for Schools
    public void viewSchoolsByCriteria(String criteria, Object value) {
        List<School> filteredSchools = new ArrayList<>();
        for (School school : School.getSchools()) {
            if (matchesCriteria(school, criteria, value)) {
                filteredSchools.add(school);
            }
        }

        if (filteredSchools.isEmpty()) {
            System.out.println("No schools found for the given criteria.");
        } else {
            for (School school : filteredSchools) {
                System.out.println(school);
            }
        }
    }

    // Search for Schools by Criteria
    private boolean matchesCriteria(School school, String criteria, Object value) {
        return switch (criteria.toLowerCase()) {
            case "schoolid" -> school.getSchoolID() == (int) value;
            case "schoolname" -> school.getSchoolName().equalsIgnoreCase((String) value);
            case "address" -> school.getAddress().equalsIgnoreCase((String) value);
            case "city" -> school.getCity().equalsIgnoreCase((String) value);
            case "contactperson" -> school.getContactPerson().equalsIgnoreCase((String) value);
            case "email" -> school.getEmail().equalsIgnoreCase((String) value);
            case "phonenumber" -> school.getPhoneNumber().equals(value);
            default -> false;
        };
    }

    // Deactivate a School
    public void deactivateSchool(int schoolID) {
        for (School school : School.getSchools()) {
            if (school.getSchoolID() == schoolID) {
                ((List<School>) School.getSchools()).remove(school);
                System.out.println("School with ID " + schoolID + " has been deactivated.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    //activate a School
    public void activateSchool(int schoolID) {
        for (School school : School.getSchools()) {
            if (school.getSchoolID() == schoolID) {
                ((List<School>) School.getSchools()).add(school);
                System.out.println("School with ID " + schoolID + " has been activated.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // View All Students by School
    public void viewStudentsBySchool(int schoolID) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getSchoolID() == schoolID) {
                filteredStudents.add(student);
            }
        }

        if (filteredStudents.isEmpty()) {
            System.out.println("No students found for the given school ID.");
        } else {
            for (Student student : filteredStudents) {
                System.out.println(student);
            }
        }
    }

    // View All Tutors by School
    public void viewTutorsBySchool(int schoolID) {
        List<Tutor> filteredTutors = new ArrayList<>();
        for (Tutor tutor : tutors) {
            if (tutor.getSchoolID() == schoolID) {
                filteredTutors.add(tutor);
            }
        }

        if (filteredTutors.isEmpty()) {
            System.out.println("No tutors found for the given school ID.");
        } else {
            for (Tutor tutor : filteredTutors) {
                System.out.println(tutor);
            }
        }
    }

    // Add chapter to a tutor
    public void addChapterToTutor(Long tutorId, String chapterTitle, String chapterContent) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(tutorId)) {
                tutor.addChapter(chapterTitle, chapterContent);
                System.out.println("Chapter added successfully.");
                return;
            }
        }
        System.out.println("Tutor not found.");
    }

    // Remove chapter from a tutor
    public void removeChapterFromTutor(Long tutorId, String chapterTitle) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(tutorId)) {
                tutor.removeChapter(chapterTitle);
                System.out.println("Chapter removed successfully.");
                return;
            }
        }
        System.out.println("Tutor not found.");
    }

    // View all chapters of a tutor
    public void viewChaptersByTutor(Long tutorId) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(tutorId)) {
                tutor.viewAllChapters();
                return;
            }
        }
        System.out.println("Tutor not found.");
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
