import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Tutor> tutors = new ArrayList<>();
    private int capacity;

    // Default constructor
    public School() {}

    // Parameterized constructor
    public School(int schoolID, String schoolName, String address, String city, String contactPerson, String email, String phoneNumber, int capacity) {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.address = address;
        this.city = city;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
    }

    // Getters and Setters
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStudentCount() {
        return students.size();
    }


    // Get all students
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    // Get all tutors
    public List<Tutor> getTutors() {
        return new ArrayList<>(tutors);
    }

    // Update contact details
    public void updateContactDetails(String contactPerson, String email, String phoneNumber) {
        this.contactPerson = contactPerson;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Get full address
    public String getFullAddress() {
        return address + ", " + city;
    }
    
    // ------------------- Student Method -------------------

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
        Student student = new Student(name, email, phone, schoolID, currentLevel, address, id.intValue(), dateOfBirth,
                registrationDate.atStartOfDay(), username, password, "", "");
        addStudent(student);
        System.out.println("Student account created successfully!");
        input.close();
    }
  
    // Add a Student to the list
    public void addStudent(Student student) {
        if (student == null) {
            System.out.println("Student cannot be null.");
            return;
        }
        if (students.size() < capacity) {
            students.add(student);
            System.out.println("Student added successfully!");
        }
        else
        System.out.println("Maximum capacity reached!");
    }

    // Remove a Student from the list
    public void removeStudentByCriteria() {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose criteria to remove Student (1: ID, 2: Name, 3: Email, 4: Username): ");
        int criteria = input.nextInt();
        input.nextLine(); // Consume newline

        switch (criteria) {
            case 1 -> {
                System.out.println("Enter Student ID: ");
                Long id = input.nextLong();
                input.nextLine(); // Consume newline
                removeStudentById(id);
            }
            case 2 -> {
                System.out.println("Enter Student Name: ");
                String name = input.nextLine();
                removeStudentByName(name);
            }
            case 3 -> {
                System.out.println("Enter Student Email: ");
                String email = input.nextLine();
                removeStudentByEmail(email);
            }
            case 4 -> {
                System.out.println("Enter Student Username: ");
                String username = input.nextLine();
                removeStudentByUsername(username);
            }
            default -> System.out.println("Invalid criteria. Please choose 1, 2, 3, or 4.");
        }
        input.close();
    }

    // Remove a Student by ID
    private void removeStudentById(Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Remove a Student by Name
    private void removeStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                students.remove(student);
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Remove a Student by Email
    private void removeStudentByEmail(String email) {
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                students.remove(student);
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Remove a Student by Username
    private void removeStudentByUsername(String username) {
        for (Student student : students) {
            if (student.getUsername().equalsIgnoreCase(username)) {
                students.remove(student);
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
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
    
    // Update Student Information by ID with user input
    public void updateStudentById() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Student ID to update: ");
        Long studentId = input.nextLong();
        input.nextLine(); // Consume newline

        System.out.println("Choose what to update (1: Name, 2: Email, 3: Phone, 4: Address, 5: School ID, 6: Current Level): ");
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

    switch (choice) {
        case 1 -> {
            System.out.println("Enter new Name: ");
            String newName = input.nextLine();
            updateStudent(studentId, newName, null, null, null, 0, 0);
        }
        case 2 -> {
            System.out.println("Enter new Email: ");
            String newEmail = input.nextLine();
            updateStudent(studentId, null, newEmail, null, null, 0, 0);
        }
        case 3 -> {
            System.out.println("Enter new Phone: ");
            String newPhone = input.nextLine();
            updateStudent(studentId, null, null, newPhone, null, 0, 0);
        }
        case 4 -> {
            System.out.println("Enter new Address: ");
            String newAddress = input.nextLine();
            updateStudent(studentId, null, null, null, newAddress, 0, 0);
        }
        case 5 -> {
            System.out.println("Enter new School ID: ");
            int newSchoolID = input.nextInt();
            input.nextLine(); // Consume newline
            updateStudent(studentId, null, null, null, null, newSchoolID, 0);
        }
        case 6 -> {
            System.out.println("Enter new Current Level: ");
            int newCurrentLevel = input.nextInt();
            input.nextLine(); // Consume newline
            updateStudent(studentId, null, null, null, null, 0, newCurrentLevel);
        }
        default -> System.out.println("Invalid choice. Please choose 1, 2, 3, 4, 5, or 6.");
    }
    input.close();
}

    // Generalized Search for Students
    public void viewStudesByCriteria(String criteria, Object value) {
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

    // remove a Student
    public void removeStudent(Long studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                students.remove(student);
                System.out.println("Student with ID " + studentId + " has been removed.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
    //------------------- Tutor Method -------------------
    
    // Add a Tutor
    public void addTutor(Tutor tutor) {
        if (tutor != null) {
            tutors.add(tutor);
            System.out.println("Tutor added successfully.");
        } else {
            System.out.println("Tutor cannot be null.");
        }
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
  
    // Remove a Tutor
    public void removeTutorByCriteria() {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose criteria to remove Tutor (1: id, 2: name, 3: email, 4: username): ");
        int criteria = input.nextInt();
        input.nextLine(); // Consume newline

        switch (criteria) {
            case 1 -> {
                System.out.println("Enter Tutor ID: ");
                Long id = input.nextLong();
                input.nextLine(); // Consume newline
                removeTutorById(id);
            }
            case 2 -> {
                System.out.println("Enter Tutor Name: ");
                String name = input.nextLine();
                removeTutorByName(name);
            }
            case 3 -> {
                System.out.println("Enter Tutor Email: ");
                String email = input.nextLine();
                removeTutorByEmail(email);
            }
            case 4 -> {
                System.out.println("Enter Tutor Username: ");
                String username = input.nextLine();
                removeTutorByUsername(username);
            }
            default -> System.out.println("Invalid criteria. Please choose 1, 2, 3, or 4.");
        }
        input.close();
    }
    
    // Remove a Tutor by ID
    private void removeTutorById(Long id) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(id)) {
                tutors.remove(tutor);
                System.out.println("Tutor removed successfully.");
                return;
            }
        }
        System.out.println("Tutor not found.");
    }
  
    // Remove a Tutor by Name
    private void removeTutorByName(String name) {
        for (Tutor tutor : tutors) {
            if (tutor.getName().equalsIgnoreCase(name)) {
                tutors.remove(tutor);
                System.out.println("Tutor removed successfully.");
                return;
            }
        }
        System.out.println("Tutor not found.");
    }
    
    // Remove a Tutor by Email
    private void removeTutorByEmail(String email) {
        for (Tutor tutor : tutors) {
            if (tutor.getEmail().equalsIgnoreCase(email)) {
                tutors.remove(tutor);
                System.out.println("Tutor removed successfully.");
                return;
            }
        }
        System.out.println("Tutor not found.");
    }
  
    // Remove a Tutor by Username
    private void removeTutorByUsername(String username) {
        for (Tutor tutor : tutors) {
            if (tutor.getUsername().equalsIgnoreCase(username)) {
                tutors.remove(tutor);
                System.out.println("Tutor removed successfully.");
                return;
            }
        }
        System.out.println("Tutor not found.");
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
  
    // Update Tutor Information by ID with user input
    public void updateTutorById() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Tutor ID to update: ");
        Long tutorId = input.nextLong();
        input.nextLine(); // Consume newline

        System.out.println("Choose what to update (1: Name, 2: Email, 3: Phone, 4: Address, 5: Subject Area): ");
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        String newValue = null;
        switch (choice) {
            case 1 -> {
                System.out.println("Enter new Name: ");
                newValue = input.nextLine();
                updateTutor(tutorId, newValue, null, null, null, null);
            }
            case 2 -> {
                System.out.println("Enter new Email: ");
                newValue = input.nextLine();
                updateTutor(tutorId, null, newValue, null, null, null);
            }
            case 3 -> {
                System.out.println("Enter new Phone: ");
                newValue = input.nextLine();
                updateTutor(tutorId, null, null, newValue, null, null);
            }
            case 4 -> {
                System.out.println("Enter new Address: ");
                newValue = input.nextLine();
                updateTutor(tutorId, null, null, null, newValue, null);
            }
            case 5 -> {
                System.out.println("Enter new Subject Area: ");
                newValue = input.nextLine();
                updateTutor(tutorId, null, null, null, null, newValue);
            }
            default -> System.out.println("Invalid choice. Please choose 1, 2, 3, 4, or 5.");
        }
        input.close();
    }
   
    // Assign Role to a Tutor using the current enum
    public void assignTutorRole(Long tutorId, Tutor.Role roleEnum) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(tutorId)) {
                tutor.setRoleEnum(roleEnum);
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
   
    // Search for Tutors by Criteria
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
   
    // remove a Tutor
    public void removeTutor(Long tutorId) {
        for (Tutor tutor : tutors) {
            if (tutor.getId().equals(tutorId)) {
                tutors.remove(tutor);
                System.out.println("Tutor with ID " + tutorId + " has been removed.");
                return;
            }
        }
        System.out.println("Tutor not found.");
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
