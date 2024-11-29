import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Person {

    private final Role roleEnum;
    private static final ArrayList<School> schools = new ArrayList<>();

    public enum Role {
        ADMIN, SUPER_ADMIN, MODERATOR
    }

    public Admin(Long id, String name, String email, Role roleEnum, LocalDate dateOfBirth, String phone, String address, String username, String password) {
        super(id, name, email, dateOfBirth, phone, address, username, password);
        this.roleEnum = roleEnum;
    }

    // Getters and Setters

    public Role getRoleEnum() {
        return roleEnum;
    }

    public static List<School> getSchools() {
        return schools;
    }

    
    //------------------- Tutor Method -------------------
    
    // Add a Tutor to a specific school
    public void addTutorToSchool(Tutor tutor, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                school.getTutors().add(tutor);
                System.out.println("Tutor added successfully to school ID " + schoolID);
                return;
            }
        }
        System.out.println("School not found.");
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
        addTutorToSchool(tutor, schoolID);
        System.out.println("Tutor account created successfully!");
        input.close();
    }
  
    // Remove a Tutor
    public void removeTutorByCriteria() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter School ID: ");
        int schoolID = input.nextInt();
        input.nextLine(); // Consume newline

        System.out.println("Choose criteria to remove Tutor (1: id, 2: name, 3: email, 4: username): ");
        int criteria = input.nextInt();
        input.nextLine(); // Consume newline

        switch (criteria) {
            case 1 -> {
                System.out.println("Enter Tutor ID: ");
                Long id = input.nextLong();
                input.nextLine(); // Consume newline
                removeTutorById(id, schoolID);
            }
            case 2 -> {
                System.out.println("Enter Tutor Name: ");
                String name = input.nextLine();
                removeTutorByName(name, schoolID);
            }
            case 3 -> {
                System.out.println("Enter Tutor Email: ");
                String email = input.nextLine();
                removeTutorByEmail(email, schoolID);
            }
            case 4 -> {
                System.out.println("Enter Tutor Username: ");
                String username = input.nextLine();
                removeTutorByUsername(username, schoolID);
            }
            default -> System.out.println("Invalid criteria. Please choose 1, 2, 3, or 4.");
        }
        input.close();
    }
    
    // Remove a Tutor by ID
    private void removeTutorById(Long id , int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
            List<Tutor> schoolTutors = school.getTutors();
            for (Tutor tutor : schoolTutors) {
                if (tutor.getId().equals(id)) {
                schoolTutors.remove(tutor);
                System.out.println("Tutor removed successfully from school ID " + schoolID + "the tutor ID is " + id + "The tutor name is " + tutor.getName());
                return;
                }
            }
            System.out.println("Tutor not found in the specified school.");
            return;
            }
        }
        System.out.println("School not found.");
    }
  
    // Remove a Tutor by Name
    private void removeTutorByName(String name, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Tutor> schoolTutors = school.getTutors();
                for (Tutor tutor : schoolTutors) {
                    if (tutor.getName().equalsIgnoreCase(name)) {
                        schoolTutors.remove(tutor);
                        System.out.println(
                                "Tutor removed successfully from school ID " + schoolID + " with name " + name);
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }
    
    // Remove a Tutor by Email
    private void removeTutorByEmail(String email, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Tutor> schoolTutors = school.getTutors();
                for (Tutor tutor : schoolTutors) {
                    if (tutor.getEmail().equalsIgnoreCase(email)) {
                        schoolTutors.remove(tutor);
                        System.out.println(
                                "Tutor removed successfully from school ID " + schoolID + " with email " + email);
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }
   
    // Remove a Tutor by Username
    private void removeTutorByUsername(String username, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Tutor> schoolTutors = school.getTutors();
                for (Tutor tutor : schoolTutors) {
                    if (tutor.getUsername().equalsIgnoreCase(username)) {
                        schoolTutors.remove(tutor);
                        System.out.println(
                                "Tutor removed successfully from school ID " + schoolID + " with username " + username);
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }
   
    // Update Tutor Information
    public void updateTutor(Long tutorId, String newName, String newEmail, String newPhone, String newAddress,
            String newSubjectArea) {
        for (School school : schools) {
            for (Tutor tutor : school.getTutors()) {
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
    public void assignTutorRole(Long tutorId, Tutor.Role roleEnum, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Tutor tutor : school.getTutors()) {
                    if (tutor.getId().equals(tutorId)) {
                        tutor.setRoleEnum(roleEnum);
                        System.out.println(
                                "Role updated successfully for Tutor ID: " + tutorId + " in School ID: " + schoolID);
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }
   
    // Generalized Search for Tutors
    public void viewTutorsByCriteria(String criteria, Object value, int schoolID) {
        List<Tutor> filteredTutors = new ArrayList<>();
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Tutor tutor : school.getTutors()) {
                    if (matchesCriteria(tutor, criteria, value)) {
                        filteredTutors.add(tutor);
                    }
                }
                break;
            }
        }

        if (filteredTutors.isEmpty()) {
            System.out.println("No tutors found for the given criteria in the specified school.");
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
        if (schools.isEmpty()) {
            System.out.println("No tutors to display.");
        } else {
            for (School school : schools) {
                for (Tutor tutor : school.getTutors()) {
                    System.out.println(tutor.toString());
                }
            }
        }
    }
    
    // remove a Tutor
    public void removeTutor(Long tutorId, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Tutor> schoolTutors = school.getTutors();
                for (Tutor tutor : schoolTutors) {
                    if (tutor.getId().equals(tutorId)) {
                        schoolTutors.remove(tutor);
                        System.out.println("Tutor with ID " + tutorId + " has been removed from school ID " + schoolID);
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
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
                registrationDate.atStartOfDay(), username, password, null, null, null);
        addStudent(student , schoolID);
        System.out.println("Student account created successfully!");
        input.close();
    }
  
    // Add a Student to a specific school
    public void addStudent(Student student, int schoolID) {
        if (student != null) {
            for (School school : schools) {
                if (school.getSchoolID() == schoolID) {
                    school.getStudents().add(student);
                    System.out.println("Student added successfully to school ID " + schoolID);
                    return;
                }
            }
            System.out.println("School not found.");
        } else {
            System.out.println("Student cannot be null.");
        }
    }

    // Remove a Student from the list
    public void removeStudentByCriteria() {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose criteria to remove Student (1: ID, 2: Name, 3: Email, 4: Username): ");
        int criteria = input.nextInt();
        System.out.println("Enter School ID: ");
        int schoolID = input.nextInt();
        input.nextLine(); // Consume newline

        switch (criteria) {
            case 1 -> {
                System.out.println("Enter Student ID: ");
                Long id = input.nextLong();
                input.nextLine(); // Consume newline
                removeStudentById(id , schoolID);
            }
            case 2 -> {
                System.out.println("Enter Student Name: ");
                String name = input.nextLine();
                removeStudentByName(name , schoolID);
            }
            case 3 -> {
                System.out.println("Enter Student Email: ");
                String email = input.nextLine();
                removeStudentByEmail(email , schoolID);
            }
            case 4 -> {
                System.out.println("Enter Student Username: ");
                String username = input.nextLine();
                removeStudentByUsername(username , schoolID);
            }
            default -> System.out.println("Invalid criteria. Please choose 1, 2, 3, or 4.");
        }
        input.close();
    }

    // Remove a Student by ID
    private void removeStudentById(Long id, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Student> schoolStudents = school.getStudents();
                for (Student student : schoolStudents) {
                    if (student.getId().equals(id)) {
                        schoolStudents.remove(student);
                        System.out.println("Student removed successfully from school ID " + schoolID + " with student ID " + id);
                        return;
                    }
                }
                System.out.println("Student not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Remove a Student by Name
    private void removeStudentByName(String name, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Student> schoolStudents = school.getStudents();
                for (Student student : schoolStudents) {
                    if (student.getName().equalsIgnoreCase(name)) {
                        schoolStudents.remove(student);
                        System.out.println("Student removed successfully from school ID " + schoolID + " with name " + name);
                        return;
                    }
                }
                System.out.println("Student not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Remove a Student by Email
    private void removeStudentByEmail(String email, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Student> schoolStudents = school.getStudents();
                for (Student student : schoolStudents) {
                    if (student.getEmail().equalsIgnoreCase(email)) {
                        schoolStudents.remove(student);
                        System.out.println("Student removed successfully from school ID " + schoolID + " with email " + email);
                        return;
                    }
                }
                System.out.println("Student not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Remove a Student by Username
    private void removeStudentByUsername(String username, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Student> schoolStudents = school.getStudents();
                for (Student student : schoolStudents) {
                    if (student.getUsername().equalsIgnoreCase(username)) {
                        schoolStudents.remove(student);
                        System.out.println("Student removed successfully from school ID " + schoolID + " with username " + username);
                        return;
                    }
                }
                System.out.println("Student not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Update Student Information
    public void updateStudent(Long studentId, String newName, String newEmail, String newPhone, String newAddress,
            int newSchoolID, int newCurrentLevel , int schoolID) {
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (school.getSchoolID() == schoolID) {
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
            }
        }
        System.out.println("Student not found.");
    }
    
    // Update Student Information by ID with user input
    public void updateStudentById() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Student ID to update: ");
        Long studentId = input.nextLong();
        
        System.out.println("Enter School ID: ");
        int SchoolID = input.nextInt();

        System.out.println("Choose what to update (1: Name, 2: Email, 3: Phone, 4: Address, 5: School ID, 6: Current Level): ");
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

    switch (choice) {
        case 1 -> {
            System.out.println("Enter new Name: ");
            String newName = input.nextLine();

            updateStudent(studentId, newName, null, null, null, 0, 0 , SchoolID);
        }
        case 2 -> {
            System.out.println("Enter new Email: ");
            String newEmail = input.nextLine();
            updateStudent(studentId, null, newEmail, null, null, 0, 0 , SchoolID);
        }
        case 3 -> {
            System.out.println("Enter new Phone: ");
            String newPhone = input.nextLine();
            updateStudent(studentId, null, null, newPhone, null, 0, 0 , SchoolID);
        }
        case 4 -> {
            System.out.println("Enter new Address: ");
            String newAddress = input.nextLine();
            updateStudent(studentId, null, null, null, newAddress, 0, 0, SchoolID);
        }
        case 5 -> {
            System.out.println("Enter new School ID: ");
            int newSchoolID = input.nextInt();
            input.nextLine(); // Consume newline
            updateStudent(studentId, null, null, null, null, newSchoolID, 0 , SchoolID);
        }
        case 6 -> {
            System.out.println("Enter new Current Level: ");
            int newCurrentLevel = input.nextInt();
            input.nextLine(); // Consume newline
            updateStudent(studentId, null, null, null, null, 0, newCurrentLevel , SchoolID);
        }
        default -> System.out.println("Invalid choice. Please choose 1, 2, 3, 4, 5, or 6.");
    }
    input.close();
}

    // Generalized Search for Students
    public void viewStudentsByCriteria(String criteria, Object value, int schoolID) {
        List<Student> filteredStudents = new ArrayList<>();
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Student student : school.getStudents()) {
                    if (matchesCriteria(student, criteria, value)) {
                        filteredStudents.add(student);
                    }
                }
                break;
            }
        }

        if (filteredStudents.isEmpty()) {
            System.out.println("No students found for the given criteria in the specified school.");
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
        boolean hasStudents = false;
        for (School school : schools) {
            if (!school.getStudents().isEmpty()) {
                hasStudents = true;
                for (Student student : school.getStudents()) {
                    System.out.println(student);
                }
            }
        }
        if (!hasStudents) {
            System.out.println("No students to display.");
        }
    }

    // Search for a specific school by ID
    public School searchSchoolById(int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                return school;
            }
        }
        System.out.println("School not found.");
        return null;
    }

    // Search for a specific student by ID within a specific school
    public Student searchStudentById(Long studentId, int schoolID) {
        School school = searchSchoolById(schoolID);
        if (school != null) {
            for (Student student : school.getStudents()) {
                if (student.getId().equals(studentId)) {
                    return student;
                }
            }
            System.out.println("Student not found in the specified school.");
        }
        return null;
    }
   
    //------------------- School Method -------------------

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
        input.close();
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

    // Update School Information
    public void updateSchool(int schoolID, String newName, String newAddress, String newCity, String newContactPerson,String newEmail, String newPhoneNumber) {
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
    
    // Update School Information by ID with user input
    public void updateSchoolByCriteria() {
    Scanner input = new Scanner(System.in);

    System.out.println("Enter School ID to update: ");
    int schoolID = input.nextInt();
    input.nextLine(); // Consume newline

    System.out.println("Choose what to update (1: Name, 2: Address, 3: City, 4: Contact Person, 5: Email, 6: Phone Number): ");
    int choice = input.nextInt();
    input.nextLine(); // Consume newline

    switch (choice) {
        case 1 -> {
            System.out.println("Enter new Name: ");
            String newName = input.nextLine();
            updateSchool(schoolID, newName, null, null, null, null, null);
        }
        case 2 -> {
            System.out.println("Enter new Address: ");
            String newAddress = input.nextLine();
            updateSchool(schoolID, null, newAddress, null, null, null, null);
        }
        case 3 -> {
            System.out.println("Enter new City: ");
            String newCity = input.nextLine();
            updateSchool(schoolID, null, null, newCity, null, null, null);
        }
        case 4 -> {
            System.out.println("Enter new Contact Person: ");
            String newContactPerson = input.nextLine();
            updateSchool(schoolID, null, null, null, newContactPerson, null, null);
        }
        case 5 -> {
            System.out.println("Enter new Email: ");
            String newEmail = input.nextLine();
            updateSchool(schoolID, null, null, null, null, newEmail, null);
        }
        case 6 -> {
            System.out.println("Enter new Phone Number: ");
            String newPhoneNumber = input.nextLine();
            updateSchool(schoolID, null, null, null, null, null, newPhoneNumber);
        }
        default -> System.out.println("Invalid choice. Please choose 1, 2, 3, 4, 5, or 6.");
    }
    input.close();
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

    // remove a School
    public void removeSchool(int schoolID) {
        for (School school : Admin.getSchools()) {
            if (school.getSchoolID() == schoolID) {
                if (Admin.getSchools().remove(school)) {
                    System.out.println("School with ID " + schoolID + " and name " + school.getSchoolName() + " has been removed.");
                } else {
                    System.out.println("School not found.");
                }
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
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Student student : school.getStudents()) {
                    filteredStudents.add(student);
                }
                break;
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
        School school = searchSchoolById(schoolID);
        if (school != null) {
            List<Tutor> tutors = school.getTutors();
            if (tutors.isEmpty()) {
                System.out.println("No tutors found for the given school ID.");
            } else {
                for (Tutor tutor : tutors) {
                    System.out.println(tutor);
                }
            }
        } else {
            System.out.println("School not found.");
        }
    }
    //------------------- Chapter Method -------------------
    
    public void addChapterToTutor(Long tutorId, int schoolID, Chapter chapter) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Tutor tutor : school.getTutors()) {
                    if (tutor.getId().equals(tutorId)) {
                        tutor.addChapter(chapter);
                        System.out.println("Chapter added successfully to tutor ID " + tutorId + " in school ID " + schoolID);
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Remove chapter from a tutor
    public void removeChapterFromTutor(Long tutorId, int schoolID, int chapterId) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Tutor tutor : school.getTutors()) {
                    if (tutor.getId().equals(tutorId)) {
                        tutor.removeChapter(chapterId);
                        System.out.println("Chapter removed successfully from tutor ID " + tutorId + " in school ID " + schoolID);
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // View all chapters of a tutor
    public void viewChaptersByTutor(Long tutorId) {
        for (School school : schools) {
            for (Tutor tutor : school.getTutors()) {
                if (tutor.getId().equals(tutorId)) {
                    tutor.viewAllChapters();
                    return;
                }
            }
        }
        System.out.println("Tutor not found.");
    }

    // View all chapters of a tutor by school
    public void viewChaptersByTutorAndSchool(Long tutorId, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Tutor tutor : school.getTutors()) {
                    if (tutor.getId().equals(tutorId)) {
                        tutor.viewAllChapters();
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    //...........Admin Class.............

    // view how many students are in a school
    public void viewStudentCountBySchool(int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                System.out.println("Number of students in school ID " + schoolID + ": " + school.getStudents().size());
                return;
            }
        }
        System.out.println("School not found.");
    }

    // view how many tutors are in a school
    public void viewTutorCountBySchool(int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                System.out.println("Number of tutors in school ID " + schoolID + ": " + school.getTutors().size());
                return;
            }
        }
        System.out.println("School not found.");
    }

    // view how many schools are in the system
    public void viewSchoolCount() {
        System.out.println("Number of schools in the system: " + schools.size());
    }

    // view how many chapters are in a tutor
    public void viewChapterCountByTutor(Long tutorId, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Tutor tutor : school.getTutors()) {
                    if (tutor.getId().equals(tutorId)) {
                        System.out.println("Number of chapters for tutor ID " + tutorId + " in school ID " + schoolID + ": " + tutor.getChapters().size());
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // view how many chapters are in the system
    public void viewChapterCount() {
        int totalChapters = 0;
        for (School school : schools) {
            for (Tutor tutor : school.getTutors()) {
                totalChapters += tutor.getChapters().size();
            }
        }
        System.out.println("Number of chapters in the system: " + totalChapters);
    }

    // view how many students are in the system
    public void viewStudentCount() {
        int totalStudents = 0;
        for (School school : schools) {
            totalStudents += school.getStudents().size();
        }
        System.out.println("Number of students in the system: " + totalStudents);
    }

    // view how many tutors are in the system
    public void viewTutorCount() {
        int totalTutors = 0;
        for (School school : schools) {
            totalTutors += school.getTutors().size();
        }
        System.out.println("Number of tutors in the system: " + totalTutors);
    }   

    // Compare the number of student accounts in a specified school with the number the admin will enter
    public void compareStudentCountBySchool(int schoolID, int studentCount) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                int actualCount = school.getStudents().size();
                if (actualCount == studentCount) {
                    System.out.println("The number of students in school ID " + schoolID + " matches the expected count.");
                } else if (actualCount > studentCount) {
                    System.out.println("The number of students in school ID " + schoolID + " is more than the expected count by " + (actualCount - studentCount) + ".");
                } else {
                    System.out.println("The number of students in school ID " + schoolID + " is less than the expected count by " + (studentCount - actualCount) + ".");
                }
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Compare the number of tutor accounts in a specified school with the number the admin will enter
    public void compareTutorCountBySchool(int schoolID, int tutorCount) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                int actualCount = school.getTutors().size();
                if (actualCount == tutorCount) {
                    System.out.println("The number of tutors in school ID " + schoolID + " matches the expected count.");
                } else if (actualCount > tutorCount) {
                    System.out.println("The number of tutors in school ID " + schoolID + " is more than the expected count by " + (actualCount - tutorCount) + ".");
                } else {
                    System.out.println("The number of tutors in school ID " + schoolID + " is less than the expected count by " + (tutorCount - actualCount) + ".");
                }
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Compare the number of schools in the system with the number the admin will enter
    public void compareSchoolCount(int schoolCount) {
        int actualCount = schools.size();
        if (actualCount == schoolCount) {
            System.out.println("The number of schools in the system matches the expected count.");
        } else if (actualCount > schoolCount) {
            System.out.println("The number of schools in the system is more than the expected count by " + (actualCount - schoolCount) + ".");
        } else {
            System.out.println("The number of schools in the system is less than the expected count by " + (schoolCount - actualCount) + ".");
        }
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