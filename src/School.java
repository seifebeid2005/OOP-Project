import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class School {

    public static Iterable<School> getSchools() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Attributes
    private int schoolID;
    private String schoolName;
    private String address;
    private String city;
    private String contactPerson;
    private String email;
    private String phoneNumber;
    private ManagementControl manage;



    // Default constructor
    public School() {
    }

    // Parameterized constructor
    public School(int schoolID, String schoolName, String address, String city, String contactPerson, String email,
            String phoneNumber) {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.address = address;
        this.city = city;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public ManagementControl getManage() {
        return manage;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public Integer getId() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void updateContactDetails(String contactPerson, String email, String phoneNumber) {
        this.contactPerson = contactPerson;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFullAddress() {
        return address + ", " + city;
    }

    public void createStudentAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Student Name: ");
        String name = input.nextLine();

        System.out.println("Enter Student Email: ");
        String studentEmail = input.nextLine();

        System.out.println("Enter Student Phone: ");
        String phone = input.nextLine();

        System.out.println("Enter Student Address: ");
        String studentAddress = input.nextLine();

        System.out.println("Enter Student Username: ");
        String username = input.nextLine();

        System.out.println("Enter Student Password: ");
        String password = input.nextLine();

        System.out.println("Enter Student School ID: ");
        int studentSchoolID = input.nextInt();
        input.nextLine(); // Consume newline after int input

        System.out.println("Enter Student Date of Birth (YYYY-MM-DD): ");
        LocalDate dateOfBirth = LocalDate.parse(input.nextLine());

        // Create the student object with the registration date handled by the constructor
        Student student = new Student(name, studentEmail, dateOfBirth, studentSchoolID, phone, studentAddress, username, password);
        addStudent(student);
        System.out.println("Student account created successfully!");
        input.close();
    }

    public void addStudent(Student student) {
        if (student == null) {
            System.out.println("Student cannot be null.");
            return;
        }
        manage.addStudent(student);
        System.out.println("Student added successfully!");
    }

    public void removeStudentById(Long id) {
        manage.removeStudent(id);
    }

    public void updateStudent(long Student_id) {
        manage.updateStudent(Student_id);
    }

    // public void viewStudesByCriteria(String criteria, Object value) {
    //     List<Student> filteredStudents = new ArrayList<>();
    //     for (Student student : students) {
    //         if (matchesCriteria(student, criteria, value)) {
    //             filteredStudents.add(student);
    //         }
    //     }

    //     if (filteredStudents.isEmpty()) {
    //         System.out.println("No students found for the given criteria.");
    //     } else {
    //         for (Student student : filteredStudents) {
    //             System.out.println(student);
    //         }
    //     }
    // }

    // Search for Students by Criteria
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
            case "registrationdate" -> student.getRegistrationDate().equals(value);
            default -> false;
        };
    }

    // View All Students
    public void viewStudents() {
        manage.listStudents();
    }

    //------------------- Tutor Method -------------------

    // Add a Tutor
    public void addTutor(Tutor tutor) {
        if (tutor == null) {
            System.out.println("Tutor cannot be null.");
            return;
        }
        manage.addTutor(tutor);
        System.out.println("Tutor added successfully.");
    }

    // Create Tutor Account
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

        System.out.println("Enter Tutor Role (1: LEAD_TUTOR, 2: ASSISTANT_TUTOR, 3: TUTOR): ");
        int roleChoice = input.nextInt();
        input.nextLine(); // Consume newline
        Tutor.Role roleEnum;
        switch (roleChoice) {
            case 1 -> roleEnum = Tutor.Role.LEAD_TUTOR;
            case 2 -> roleEnum = Tutor.Role.ASSISTANT_TUTOR;
            case 3 -> roleEnum = Tutor.Role.TUTOR;
            default -> {
                System.out.println("Invalid choice. Defaulting to TUTOR.");
                roleEnum = Tutor.Role.TUTOR;
            }
        }

        System.out.println("Enter Tutor Date Joined (YYYY-MM-DD): ");
        LocalDate dateJoined = LocalDate.parse(input.nextLine());

        System.out.println("Enter Tutor School ID: ");
        int schoolID = input.nextInt();
        input.nextLine(); // Consume newline

        // Create Tutor object and add to the list
        Tutor tutor = new Tutor(id, name, email, dateOfBirth, phone, address, username, password, subjectArea,
                dateJoined, roleEnum, schoolID);
        addTutor(tutor);
        System.out.println("Tutor account created successfully!");
        input.close();
    }

    // Remove a Tutor by ID
    public void removeTutorById(Long id) {
        manage.removeTutor(id);
    }

    // update tutor by id
    public void updateTutor(Long tutorId) {
        manage.updateTutor(tutorId);
    }

    // public void viewTutorsByCriteria(String criteria, Object value) {
    //     List<Tutor> filteredTutors = new ArrayList<>();
    //     for (Tutor tutor : tutors) {
    //         if (matchesCriteria(tutor, criteria, value)) {
    //             filteredTutors.add(tutor);
    //         }
    //     }

    //     if (filteredTutors.isEmpty()) {
    //         System.out.println("No tutors found for the given criteria.");
    //     } else {
    //         for (Tutor tutor : filteredTutors) {
    //             System.out.println(tutor);
    //         }
    //     }
    // }

    // // Search for Tutors by Criteria
    // private boolean matchesCriteria(Tutor tutor, String criteria, Object value) {
    //     return switch (criteria.toLowerCase()) {
    //         case "id" -> tutor.getId().equals(value);
    //         case "name" -> tutor.getName().equalsIgnoreCase((String) value);
    //         case "email" -> tutor.getEmail().equalsIgnoreCase((String) value);
    //         case "dateofbirth" -> tutor.getDateOfBirth().equals(value);
    //         case "age" -> tutor.getAge() == (int) value;
    //         case "phone" -> tutor.getPhone().equals(value);
    //         case "address" -> tutor.getAddress().equalsIgnoreCase((String) value);
    //         case "username" -> tutor.getUsername().equals(value);
    //         case "subjectarea" -> tutor.getSubjectArea().equalsIgnoreCase((String) value);
    //         case "datejoined" -> tutor.getDateJoined().equals(value);
    //         case "yearsofexperience" -> tutor.getYearsOfExperience() == (int) value;
    //         default -> false;
    //     };
    // }

    // // View All Tutors
    // public void viewTutors() {
    //     if (tutors.isEmpty()) {
    //         System.out.println("No tutors to display.");
    //     } else {
    //         for (Tutor tutor : tutors) {
    //             System.out.println(tutor);
    //         }
    //     }
    // }

    // Add chapter to a tutor
    // public void addChapterToTutor(Long tutorId, Course course) {
    //     for (Tutor tutor : tutors) {
    //         if (tutor.getId().equals(tutorId)) {
    //             tutor.addCourse(course);
    //             System.out.println("Chapter added successfully to Tutor ID: " + tutorId);
    //             return;
    //         }
    //     }
    //     System.out.println("Tutor not found.");
    // }

    // View Students of a Tutor
    public void viewStudentsOfTutor(Long tutorId) {
    }

    public void areAllChaptersAssignedToTutors() {
    }

    // String representation of the object
    @Override
    public String toString() {
        return "School ID: " + schoolID +
                "\nSchool Name: " + schoolName +
                "\nAddress: " + getFullAddress() +
                "\nContact Person: " + contactPerson +
                "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber;
    }

    // Validate email format
    public boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Validate phone number format (basic example)
    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}"); // Assuming 10-digit phone numbers
    }

}
