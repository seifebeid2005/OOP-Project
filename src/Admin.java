import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Admin extends Person implements Comparable<Admin> {

    private static Long idCounter = (long) 1.0;

    private final Role roleEnum;
    private static final ArrayList<School> schools = new ArrayList<>();

    public enum Role {
        ADMIN, SUPER_ADMIN, MODERATOR
    }
    

    public Admin(String username, String password) {
        super(username, password);
        this.roleEnum = Role.ADMIN;
    }

    public ArrayList<String> getSchoolsData() {
        ArrayList<String> data = new ArrayList<>();
        for (School school : schools) {
            data.add(school.toString());  // Assuming School has a proper toString() method
            
        }
        return data;
    }

    public Admin (String name, String email, Role roleEnum, LocalDate dateOfBirth, String phone, String address, String username, String password) {
        super(idCounter++ ,name, email, dateOfBirth, phone, address, username, password);
        this.roleEnum = roleEnum;
    }

    // Getters and Setters
    public Role getRoleEnum() {
        return roleEnum;
    }

    public static List<School> getSchools() {
        return schools;
    }

    //checkLogin
    public boolean checkLogin(String username, String password) {
        return this.getUsername().equals(username) && this.getPassword().equals(password);
    }
    
    // Check login for a school and return the school ID
    public int checkSchoolLogin(String username, String password) {
        for (School school : schools) {
            if (school.getUserName().equals(username) && school.getPassword().equals(password)) {
                return school.getSchoolID();
            }
        }
        return 0;
    } 
    // checkStudentLogin
    public Student checkStudentLogin(String username, String password) {
        for (School school : schools) {
            for (Student student : school.getManage().getStudents()) {
                if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
                    return student;
                }
            }
        }
        return null;
    }
   
    // checkTutorLogin
    public Tutor checkTutorLogin(String username, String password) {
        for (School school : schools) {
            for (Tutor tutor : school.getManage().getTutors()) {
                if (tutor.getUsername().equals(username) && tutor.getPassword().equals(password)) {
                    return tutor;
                }
            }
        }
        return null;
    }
    //------------------- Tutor Method -------------------
    
    // Add a Tutor to a specific school
    public void addTutorToSchool(Tutor tutor, int schoolID) {
        for (School school : schools) {
            if (school.getId() == schoolID) {
                school.getManage().addTutor(tutor);
                System.out.println("Tutor added successfully to school ID " + schoolID);
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Create Tutor Account
    public void createTutorAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Tutor Name: ");
        String name;
        while ((name = input.nextLine().trim()).isEmpty()) {
            System.out.println("Name cannot be empty. Please enter a valid name:");
        }

        System.out.println("Enter Tutor Email: ");
        String email;
        while (!isValidEmail(email = input.nextLine().trim())) {
            System.out.println("Invalid email format. Please enter a valid email:");
        }

        System.out.println("Enter Tutor Date of Birth (YYYY-MM-DD): ");
        LocalDate dateOfBirth;
        while (true) {
            try {
                dateOfBirth = LocalDate.parse(input.nextLine().trim());
                if (dateOfBirth.isAfter(LocalDate.now())) {
                    System.out.println("Date of Birth cannot be in the future. Please try again:");
                    continue;
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a valid date (YYYY-MM-DD):");
            }
        }

        System.out.println("Enter Tutor Phone: ");
        String phone;
        while (!isValidPhone(phone = input.nextLine().trim())) {
            System.out.println("Invalid phone number. Please enter a valid phone number:");
        }

        System.out.println("Enter Tutor Address: ");
        String address;
        while ((address = input.nextLine().trim()).isEmpty()) {
            System.out.println("Address cannot be empty. Please enter a valid address:");
        }

        System.out.println("Enter Tutor Username: ");
        String username;
        while ((username = input.nextLine().trim()).isEmpty()) {
            System.out.println("Username cannot be empty. Please enter a valid username:");
        }

        System.out.println("Enter Tutor Password: ");
        String password;
        while ((password = input.nextLine().trim()).length() < 6) {
            System.out.println("Password must be at least 6 characters long. Please enter a valid password:");
        }

        System.out.println("Enter Tutor Role (1: LEAD_TUTOR, 2: ASSISTANT_TUTOR, 3: TUTOR): ");
        int roleChoice;
        Tutor.Role roleEnum;
        while (true) {
            try {
                roleChoice = Integer.parseInt(input.nextLine().trim());
                switch (roleChoice) {
                    case 1 -> roleEnum = Tutor.Role.LEAD_TUTOR;
                    case 2 -> roleEnum = Tutor.Role.ASSISTANT_TUTOR;
                    case 3 -> roleEnum = Tutor.Role.TUTOR;
                    default -> throw new IllegalArgumentException();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter 1, 2, or 3:");
            }
        }

        System.out.println("Enter Tutor School ID: ");
        int schoolID;
        while (true) {
            try {
                schoolID = Integer.parseInt(input.nextLine().trim());
                if (schoolID <= 0) {
                    System.out.println("School ID must be a positive number. Please try again:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid School ID:");
            }
        }

        // Create Tutor object and add to the list
        Tutor tutor = new Tutor(name, email, dateOfBirth, phone, address, username, password, roleEnum, schoolID);
        addTutorToSchool(tutor, schoolID);
        System.out.println("Tutor account created successfully!");
    }


    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{11}|\\+\\d{1,3}\\d{9}");
    }

    // Create Tutor from file
    public void createTutorFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Long id = null;
            String name = "", email = "", phone = "", address = "", username = "", password = "";
            LocalDate dateOfBirth = null, dateJoined = null;
            Tutor.Role roleEnum = Tutor.Role.TUTOR;
            int schoolID = -1;

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    // Create a Tutor object and add to the list when a blank line is encountered
                    Tutor tutor = new Tutor(name, email, dateOfBirth, phone, address, username, password, roleEnum, schoolID);
                    addTutorToSchool(tutor, schoolID); // Assuming you have a method addTutorToSchool to add to your list

                    // Reset variables for the next tutor
                    name = "";
                    email = "";
                    phone = "";
                    address = "";
                    username = "";
                    password = "";
                    dateOfBirth = null;
                    dateJoined = null;
                    roleEnum = Tutor.Role.TUTOR;
                    schoolID = -1;
                } else if (line.startsWith("name : ")) {
                    name = line.substring(7).trim();
                } else if (line.startsWith("email : ")) {
                    email = line.substring(8).trim();
                } else if (line.startsWith("phone : ")) {
                    phone = line.substring(8).trim();
                } else if (line.startsWith("address : ")) {
                    address = line.substring(10).trim();
                } else if (line.startsWith("username : ")) {
                    username = line.substring(11).trim();
                } else if (line.startsWith("password : ")) {
                    password = line.substring(11).trim();
                } else if (line.startsWith("dateOfBirth : ")) {
                    dateOfBirth = LocalDate.parse(line.substring(14).trim());
                } else if (line.startsWith("dateJoined : ")) {
                    dateJoined = LocalDate.parse(line.substring(13).trim());
                } else if (line.startsWith("role : ")) {
                    roleEnum = Tutor.Role.valueOf(line.substring(7).trim().toUpperCase());
                } else if (line.startsWith("schoolID : ")) {
                    schoolID = Integer.parseInt(line.substring(11).trim());
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
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
            default ->
                System.out.println("Invalid criteria. Please choose 1, 2, 3, or 4.");
        }

    }

    // Remove a Tutor by ID
    private void removeTutorById(Long id, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Tutor> schoolTutors = school.getManage().getTutors();
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
                List<Tutor> schoolTutors = school.getManage().getTutors();
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
                List<Tutor> schoolTutors = school.getManage().getTutors();
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
                List<Tutor> schoolTutors = school.getManage().getTutors();
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
    public void updateTutor(Long tutorId, String newName, String newEmail, String newPhone, String newAddress) {
        for (School school : schools) {
            for (Tutor tutor : school.getManage().getTutors()) {
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

        System.out.println("Choose what to update (1: Name, 2: Email, 3: Phone, 4: Address,): ");
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        String newValue;
        switch (choice) {
            case 1 -> {
                System.out.println("Enter new Name: ");
                newValue = input.nextLine();
                updateTutor(tutorId, newValue, null, null, null);
            }
            case 2 -> {
                System.out.println("Enter new Email: ");
                newValue = input.nextLine();
                updateTutor(tutorId, null, newValue, null, null);
            }
            case 3 -> {
                System.out.println("Enter new Phone: ");
                newValue = input.nextLine();
                updateTutor(tutorId, null, null, newValue , null);
            }
            case 4 -> {
                System.out.println("Enter new Address: ");
                newValue = input.nextLine();
                updateTutor(tutorId, null, null, null, newValue );
            }
            default ->
                System.out.println("Invalid choice. Please choose 1, 2, 3, 4, or 5.");
        }

    }

    // Assign Role to a Tutor using the current enum
    public void assignTutorRole(Long tutorId, Tutor.Role roleEnum, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Tutor tutor : school.getManage().getTutors()) {
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
                for (Tutor tutor : school.getManage().getTutors()) {
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
            case "id" ->
                tutor.getId().equals(value);
            case "name" ->
                tutor.getName().equalsIgnoreCase((String) value);
            case "email" ->
                tutor.getEmail().equalsIgnoreCase((String) value);
            case "dateofbirth" ->
                tutor.getDateOfBirth().equals(value);
            case "age" ->
                tutor.getAge() == (int) value;
            case "phone" ->
                tutor.getPhone().equals(value);
            case "address" ->
                tutor.getAddress().equalsIgnoreCase((String) value);
            case "username" ->
                tutor.getUsername().equals(value);
            case "datejoined" ->
                tutor.getDateJoined().equals(value);
            case "role" ->
                tutor.getRoleEnum().equals(Tutor.Role.valueOf((String) value));
            case "yearsofexperience" ->
                tutor.getYearsOfExperience() == (int) value;
            default ->
                false;
        };
    }

    // View All Tutors
    public void viewTutors() {
        System.out.println("List of tutors in the school:");
        if (schools == null || schools.isEmpty()) {
            System.out.println("No tutors to display.");
            return;
        }

        for (School school : schools) {
            List<Tutor> tutors = school.getManage().getTutors();
            if (tutors == null || tutors.isEmpty()) {
                System.out.println("No tutors found for school: " + school.getId());
            } else {
                System.out.println("Tutors for school: " + school.getId());
                for (Tutor tutor : tutors) {
                    System.out.println(" - " + tutor); // Ensure Tutor.toString() is well-defined
                }
            }
        }
    }

    // remove a Tutor
    public void removeTutor(Long tutorId, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Tutor> schoolTutors = school.getManage().getTutors();
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

    // view tutor courses
    public void viewTutorCourses(Long tutorId, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Tutor tutor : school.getManage().getTutors()) {
                    if (tutor.getId().equals(tutorId)) {
                        List<Course> courses = tutor.getCourses();
                        if (courses.isEmpty()) {
                            System.out.println("No courses found for the tutor ID " + tutorId + " in school ID " + schoolID);
                        } else {
                            System.out.println("Courses for tutor ID " + tutorId + " in school ID " + schoolID + ":");
                            for (Course course : courses) {
                                System.out.println(course);
                            }
                        }
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

        String name = "";
        String studentEmail = "";
        String phone = "";
        String studentAddress = "";
        String username = "";
        String password = "";
        int studentSchoolID = -1;
        LocalDate dateOfBirth = null;

        // Validate Student Name
        while (true) {
            System.out.println("Enter Student Name: ");
            name = input.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            } else {
                break; // Name is valid
            }
        }

        // Validate Student Email
        while (true) {
            System.out.println("Enter Student Email: ");
            studentEmail = input.nextLine();
            if (!isValidEmail(studentEmail)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            } else {
                break; // Email is valid
            }
        }

        // Validate Student Phone
        while (true) {
            System.out.println("Enter Student Phone: ");
            phone = input.nextLine();
            if (!isValidPhoneNumber(phone)) {
                System.out.println("Invalid phone number. Please enter a valid phone number.");
            } else {
                break; // Phone is valid
            }
        }

        // Validate Student Address
        while (true) {
            System.out.println("Enter Student Address: ");
            studentAddress = input.nextLine();
            if (studentAddress.isEmpty()) {
                System.out.println("Address cannot be empty. Please enter a valid address.");
            } else {
                break; // Address is valid
            }
        }

        // Validate Student Username
        while (true) {
            System.out.println("Enter Student Username: ");
            username = input.nextLine();
            if (username.isEmpty()) {
                System.out.println("Username cannot be empty. Please enter a valid username.");
            } else {
                break; // Username is valid
            }
        }

        // Validate Student Password
        while (true) {
            System.out.println("Enter Student Password: ");
            password = input.nextLine();
            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please enter a valid password.");
            } else {
                break; // Password is valid
            }
        }

        // Validate Student School ID
        while (true) {
            System.out.println("Enter Student School ID: ");
            if (input.hasNextInt()) {
                studentSchoolID = input.nextInt();
                input.nextLine(); // Consume the newline character after reading int
                if (studentSchoolID <= 0) {
                    System.out.println("School ID must be a positive number. Please enter a valid School ID.");
                } else {
                    break; // School ID is valid
                }
            } else {
                System.out.println("Invalid input for School ID. Please enter a valid number.");
                input.next(); // Consume the invalid input
            }
        }

        // Validate Student Date of Birth
        while (true) {
            System.out.println("Enter Student Date of Birth (YYYY-MM-DD): ");
            String dobInput = input.nextLine();
            try {
                dateOfBirth = LocalDate.parse(dobInput);
                break; // Date of Birth is valid
            } catch (Exception e) {
                System.out.println("Invalid Date format. Please use the format YYYY-MM-DD.");
            }
        }

        // Create the student object with the registration date handled by the constructor
        Student student = new Student(name, studentEmail, dateOfBirth, studentSchoolID, phone, studentAddress, username, password);

        // Assuming addStudent is defined elsewhere to add the student to the database/list
        addStudent(student, studentSchoolID);

        System.out.println("Student account created successfully!");
    }

    public void createStudentFromFile(String filePath2) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
            String line;
            String name = "", email = "", phone = "", address = "", username = "", password = "";
            LocalDate dateOfBirth = null;
            int schoolID = -1;

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    // Create a Student object and add to the list when a blank line is encountered
                    Student student = new Student(name, email, dateOfBirth, schoolID, phone, address, username, password);
                    addStudent(student, schoolID); // Assuming you have a method `addStudent` to add to your list

                    // Reset variables for the next student
                    name = "";
                    email = "";
                    phone = "";
                    address = "";
                    username = "";
                    password = "";
                    dateOfBirth = null;
                    schoolID = -1;
                } else if (line.startsWith("name : ")) {
                    name = line.substring(7).trim();
                } else if (line.startsWith("email : ")) {
                    email = line.substring(8).trim();
                } else if (line.startsWith("phone : ")) {
                    phone = line.substring(8).trim();
                } else if (line.startsWith("address : ")) {
                    address = line.substring(10).trim();
                } else if (line.startsWith("username : ")) {
                    username = line.substring(11).trim();
                } else if (line.startsWith("password : ")) {
                    password = line.substring(11).trim();
                } else if (line.startsWith("dateOfBirth : ")) {
                    dateOfBirth = LocalDate.parse(line.substring(14).trim());
                } else if (line.startsWith("schoolID : ")) {
                    schoolID = Integer.parseInt(line.substring(11).trim());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Helper method to validate email format using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Helper method to validate phone number format (basic validation for numeric digits)
    private boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^[0-9]{10}$"; // Example: 10-digit phone number
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phone).matches();
    }

    // Get Student Courses by Student ID
    public void getStudentCoursesById(Long studentId) {
        Student student = searchStudentById(studentId);
        if (student != null) {
            List<Course> courses = student.getCourses();
            if (courses.isEmpty()) {
                System.out.println("No courses found for the student ID " + studentId + " in school ID " + student.getSchoolID());
            } else {
                System.out.println("Courses for student ID " + studentId + " in school ID " + student.getSchoolID() + ":");
                for (Course course : courses) {
                    System.out.println(course);
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }
    // Get Lessons for a Student by Student ID
    public void getStudentLessonsById(Long studentId) {
        Student student = searchStudentById(studentId);
        if (student != null) {
            List<Course> courses = student.getCourses();
            if (courses.isEmpty()) {
                System.out.println("No courses found for the student ID " + studentId + " in school ID " + student.getSchoolID());
            } else {
                System.out.println("Lessons for student ID " + studentId + " in school ID " + student.getSchoolID() + ":");
                for (Course course : courses) {
                    for (Lesson lesson : course.getLessons()) {
                        System.out.println(lesson);
                    }
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void getTutorsForStudentCourses(Long studentId) {
        Student student = searchStudentById(studentId);
        if (student != null) {
            List<Course> studentCourses = student.getCourses();
            if (studentCourses.isEmpty()) {
                System.out.println("No courses found for the student ID " + studentId);
            } else {
                System.out.println("Tutors for the courses of student ID " + studentId + ":");
                for (Course studentCourse : studentCourses) {
                    System.out.println("Course: " + studentCourse.getCourseName());
                    boolean tutorFoundForCourse = false;  // Flag to track if any tutor is found for this course
                    
                    for (School school : schools) {
                        for (Tutor tutor : school.getManage().getTutors()) {
                            // Check if the tutor is assigned to the current course
                            if (tutor.getCourses().contains(studentCourse)) {
                                if (!tutorFoundForCourse) {
                                    tutorFoundForCourse = true;  // Set the flag to true
                                }
                                System.out.println(" - Tutor: " + tutor.getName() + " (Tutor ID: " + tutor.getId() + ")");
                            }
                        }
                    }

                    // If no tutor was found for this course, print the message
                    if (!tutorFoundForCourse) {
                        System.out.println("  No tutors found for course: " + studentCourse.getCourseName());
                    }
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void addStudent(Student student, int schoolID) {
        if (student != null) {
            for (School school : schools) {
                if (school.getSchoolID() == schoolID) {
                    school.getManage().addStudent(student);
                    System.out.println("Student added successfully to school ID " + schoolID + " with student ID " + student.getId());
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
                removeStudentById(id, schoolID);
            }
            case 2 -> {
                System.out.println("Enter Student Name: ");
                String name = input.nextLine();
                removeStudentByName(name, schoolID);
            }
            case 3 -> {
                System.out.println("Enter Student Email: ");
                String email = input.nextLine();
                removeStudentByEmail(email, schoolID);
            }
            case 4 -> {
                System.out.println("Enter Student Username: ");
                String username = input.nextLine();
                removeStudentByUsername(username, schoolID);
            }
            default ->
                System.out.println("Invalid criteria. Please choose 1, 2, 3, or 4.");
        }

    }

    // Remove a Student by ID
    private void removeStudentById(Long id, int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Student> schoolStudents = school.getManage().getStudents();
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

    public void removeStudentById(int schoolID) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Student ID: ");
        Long id = input.nextLong();
        input.nextLine(); // Consume newline
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                List<Student> schoolStudents = school.getManage().getStudents();
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
                List<Student> schoolStudents = school.getManage().getStudents();
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
                List<Student> schoolStudents = school.getManage().getStudents();
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
                List<Student> schoolStudents = school.getManage().getStudents();
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
    public void updateStudent(Long studentId, String newName, String newEmail, String newPhone, String newAddress, int newSchoolID, int newCurrentLevel, int schoolID) {
        for (School school : schools) {
            for (Student student : school.getManage().getStudents()) {
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
        System.out.println("Enter School ID: ");
        int SchoolID = input.nextInt();

        System.out.println("Enter Student ID to update: ");
        Long studentId = input.nextLong();


        System.out.println("Choose what to update (1: Name, 2: Email, 3: Phone, 4: Address, 5: School ID, 6: Current Level): ");
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.println("Enter new Name: ");
                String newName = input.nextLine();

                updateStudent(studentId, newName, null, null, null, 0, 0, SchoolID);
            }
            case 2 -> {
                System.out.println("Enter new Email: ");
                String newEmail = input.nextLine();
                updateStudent(studentId, null, newEmail, null, null, 0, 0, SchoolID);
            }
            case 3 -> {
                System.out.println("Enter new Phone: ");
                String newPhone = input.nextLine();
                updateStudent(studentId, null, null, newPhone, null, 0, 0, SchoolID);
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
                updateStudent(studentId, null, null, null, null, newSchoolID, 0, SchoolID);
            }
            case 6 -> {
                System.out.println("Enter new Current Level: ");
                int newCurrentLevel = input.nextInt();
                input.nextLine(); // Consume newline
                updateStudent(studentId, null, null, null, null, 0, newCurrentLevel, SchoolID);
            }
            default ->
                System.out.println("Invalid choice. Please choose 1, 2, 3, 4, 5, or 6.");
        }

    }

    // Generalized Search for Students
    public void updateStudentById(int SchoolID) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter Student ID to update: ");
        Long studentId = input.nextLong();


        System.out.println("Choose what to update (1: Name, 2: Email, 3: Phone, 4: Address, 5: School ID, 6: Current Level): ");
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.println("Enter new Name: ");
                String newName = input.nextLine();

                updateStudent(studentId, newName, null, null, null, 0, 0, SchoolID);
            }
            case 2 -> {
                System.out.println("Enter new Email: ");
                String newEmail = input.nextLine();
                updateStudent(studentId, null, newEmail, null, null, 0, 0, SchoolID);
            }
            case 3 -> {
                System.out.println("Enter new Phone: ");
                String newPhone = input.nextLine();
                updateStudent(studentId, null, null, newPhone, null, 0, 0, SchoolID);
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
                updateStudent(studentId, null, null, null, null, newSchoolID, 0, SchoolID);
            }
            case 6 -> {
                System.out.println("Enter new Current Level: ");
                int newCurrentLevel = input.nextInt();
                input.nextLine(); // Consume newline
                updateStudent(studentId, null, null, null, null, 0, newCurrentLevel, SchoolID);
            }
            default ->
                System.out.println("Invalid choice. Please choose 1, 2, 3, 4, 5, or 6.");
        }

    }

    
    public void viewStudentsByCriteria(String criteria, Object value, int schoolID) {
        List<Student> filteredStudents = new ArrayList<>();
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Student student : school.getManage().getStudents()) {
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
            case "id" ->
                student.getId() == (int) value;
            case "name" ->
                student.getName().equalsIgnoreCase((String) value);
            case "email" ->
                student.getEmail().equalsIgnoreCase((String) value);
            case "dateofbirth" ->
                student.getDateOfBirth().equals(value);
            case "age" ->
                student.getAge() == (int) value;
            case "phone" ->
                student.getPhone().equals(value);
            case "address" ->
                student.getAddress().equalsIgnoreCase((String) value);
            case "username" ->
                student.getUsername().equals(value);
            case "schoolid" ->
                student.getSchoolID() == (int) value;
            case "registrationdate" ->
                student.getRegistrationDate().equals(value);
            default ->
                false;
        };
    }

    // View All Students
    public void viewStudents() {
        boolean hasStudents = false;
        for (School school : schools) {
            if (!school.getManage().getStudents().isEmpty()) {
                hasStudents = true;
                System.out.println(school.getManage().getStudents());
            }
            if (!hasStudents) {
                System.out.println("No students to display.");
            }
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
    public Student searchStudentById(Long studentId) {
        for (School school : schools) {
            for (Student student : school.getManage().getStudents()) {
                if (student.getId().equals(studentId)) {
                    return student;
                }
            }
        }
        System.out.println("Student not found.");
        return null;
    }  //------------------- School Method -------------------
    //Create School
    public void createSchool() {
        
        String schoolName = "";
        String address = "";
        String city = "";
        String contactPerson = "";
        String email = "";
        String phoneNumber = "";
        String username = "";
        String password = "";

        Scanner input = new Scanner(System.in);

        while (true) {
        System.out.println("Enter School Name: ");
        schoolName = input.nextLine().trim();
        if (schoolName.isEmpty()) {
            System.out.println("School name cannot be empty. Please enter a valid name.");
        } else {
            break;
        }
    }

    // Validate School Address
        while (true) {
            System.out.println("Enter School Address: ");
            address = input.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Address cannot be empty. Please enter a valid address.");
            } else {
                break;
            }
        }

        // Validate City
        while (true) {
            System.out.println("Enter School City: ");
            city = input.nextLine().trim();
            if (city.isEmpty()) {
                System.out.println("City cannot be empty. Please enter a valid city.");
            } else {
                break;
            }
       }

    // Validate Contact Person
        while (true) {
            System.out.println("Enter Contact Person: ");
            contactPerson = input.nextLine().trim();
            if (contactPerson.isEmpty()) {
                System.out.println("Contact person cannot be empty. Please enter a valid name.");
            } else {
                break;
            }
        }

        // Validate Contact Email
        while (true) {
            System.out.println("Enter Contact Email: ");
            email = input.nextLine().trim();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            } else {
                break;
            }
        }

        // Validate Contact Phone Number
        while (true) {
            System.out.println("Enter Contact Phone: ");
            phoneNumber = input.nextLine().trim();
            if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number. Please enter a valid phone number.");
            } else {
                break;
            }
        }

    // Validate School Username
        while (true) {
            System.out.println("Enter School Username: ");
            username = input.nextLine().trim();
            if (username.isEmpty() || username.length() < 3) {
                System.out.println("Username must be at least 3 characters long. Please enter a valid username.");
            } else {
                break;
            }
        }

        // Validate School Password
        while (true) {
            System.out.println("Enter School Password: ");
            password = input.nextLine().trim();
            if (password.length() < 6) {
                System.out.println("Password must be at least 6 characters long. Please enter a valid password.");
            } else {
                break;
            }
        }

    // Create School object and add to the list
    School school = new School(schoolName, address, city, contactPerson, email, phoneNumber, username, password);
    addSchool(school);
    System.out.println("School created successfully!");
    }

    // create School from file
    public void createSchoolFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String schoolName = "", address = "", city = "", contactPerson = "", email = "", phoneNumber = "" , username = "" , password = "";

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    // Create a School object and add to the list when a blank line is encountered
                    School school = new School(schoolName, address, city, contactPerson, email, phoneNumber , username , password);
                    addSchool(school); // Assuming you have a method `addSchool` to add to your list

                    // Reset variables for the next school
                    schoolName = "";
                    address = "";
                    city = "";
                    contactPerson = "";
                    email = "";
                    phoneNumber = "";
                    username = "";
                    password = "";
                } else if (line.startsWith("schoolName : ")) {
                    schoolName = line.substring(13).trim();
                } else if (line.startsWith("address : ")) {
                    address = line.substring(10).trim();
                } else if (line.startsWith("city : ")) {
                    city = line.substring(7).trim();
                } else if (line.startsWith("contactPerson : ")) {
                    contactPerson = line.substring(16).trim();
                } else if (line.startsWith("email : ")) {
                    email = line.substring(8).trim();
                } else if (line.startsWith("phoneNumber : ")) {
                    phoneNumber = line.substring(14).trim();
                } else if (line.startsWith("username : ")) {
                    username = line.substring(11).trim();
                } else if (line.startsWith("password : ")) {
                    password = line.substring(11).trim();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Add School
    private void addSchool(School school) {
        if (school != null) {
            Admin.getSchools().add(school);
            System.out.println("School added successfully.");
        } else {
            System.out.println("School cannot be null.");
        }
    }

    // View All Schools
    public void viewSchools() {
        if (getSchools().isEmpty()) {
            System.out.println("No schools to display.");
        } else {
            for (School school : Admin.getSchools()) {
                System.out.println(school);
            }
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
            default ->
                System.out.println("Invalid choice. Please choose 1, 2, 3, 4, 5, or 6.");
        }

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
            case "schoolid" ->
                school.getSchoolID() == (int) value;
            case "schoolname" ->
                school.getSchoolName().equalsIgnoreCase((String) value);
            case "address" ->
                school.getAddress().equalsIgnoreCase((String) value);
            case "city" ->
                school.getCity().equalsIgnoreCase((String) value);
            case "contactperson" ->
                school.getContactPerson().equalsIgnoreCase((String) value);
            case "email" ->
                school.getEmail().equalsIgnoreCase((String) value);
            case "phonenumber" ->
                school.getPhoneNumber().equals(value);
            default ->
                false;
        };
    }

    // remove a School
    public void removeSchool() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter School ID to remove: ");
        int schoolID = input.nextInt();
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
                for (Student student : school.getManage().getStudents()) {
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
            List<Tutor> tutors = school.getManage().getTutors();
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

    // ------------------- lesson Method -------------------
    public void createLesson() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Lesson Name: ");
        String lessonName = input.nextLine();

        System.out.println("Enter Lesson Description: ");
        String lessonDescription = input.nextLine();

        System.out.println("enter Course ID: ");
        int courseId = input.nextInt();

        // Create Lesson object and add to the list
        Lesson lesson = new Lesson(lessonName, lessonDescription, courseId);
        for (School school : schools) {
            for (Course course : school.getManage().getCourses()) {
                if (course.getCourseId() == courseId) {
                    course.addLesson(lesson);
                    System.out.println("Lesson added successfully to course ID " + courseId);
                    return;
                } else {
                    System.err.println("Course not found");
                }
            }
        }

        System.out.println("Lesson created successfully!");
    }

    public void createLessonFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String lessonName = "", lessonDescription = "";
            int courseId = -1;

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    // Create a Lesson object and add to the list when a blank line is encountered
                    Lesson lesson = new Lesson(lessonName, lessonDescription, courseId);
                    for (School school : schools) {
                        for (Course course : school.getManage().getCourses()) {
                            if (course.getCourseId() == courseId) {
                                course.addLesson(lesson);
                                System.out.println("Lesson added successfully to course ID " + courseId);
                                break;
                            }
                        }
                    }
                    // Reset variables for the next lesson
                    lessonName = "";
                    lessonDescription = "";
                    courseId = -1;
                } else if (line.startsWith("name : ")) {
                    lessonName = line.substring(7).trim();
                } else if (line.startsWith("description : ")) {
                    lessonDescription = line.substring(13).trim();
                } else if (line.startsWith("courseId : ")) {
                    courseId = Integer.parseInt(line.substring(10).trim());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }  // view all unique lessons

    public void viewLessons() {
        Set<Lesson> uniqueLessons = new HashSet<>();
        int countCourses = 0;
        for (School school : schools) {
            for (Course course : school.getManage().getCourses()) {
                countCourses++;
                uniqueLessons.addAll(course.getLessons());
            }
        }
        List<Lesson> sortedLessons = new ArrayList<>(uniqueLessons);
        sortedLessons.sort(Comparator.comparing(Lesson::getLessonId));
        for (Lesson lesson : sortedLessons) {
            System.out.println(lesson);
        }
        for (Lesson lesson : uniqueLessons) {
            System.out.println(lesson);
        }
    }

    //------------------- Quiz Method -------------------
    public void createQuiz() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Quiz Description: ");
        String quizDescription = input.nextLine();

        System.out.println("Enter Lesson ID: ");
        int lesson_id = input.nextInt();

        // Create Quiz object and add to the list
        Quiz quiz = new Quiz(quizDescription, lesson_id);
        for (School school : schools) {
            for (Course course : school.getManage().getCourses()) {
                for (Lesson lesson : course.getLessons()) {
                    if (lesson.getLessonId() == lesson_id) {
                        lesson.setQuiz(quiz);
                        System.out.println("Quiz added successfully to lesson ID " + lesson_id);
                        return;
                    }
                }
            }
        }

        System.out.println("Quiz created successfully!");
    }

    public void createQuizFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String quizTitle = "";  // This will hold the quiz description (title)
            int lessonId = -1;  // Holds the lessonId for which the quiz is to be added

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace

                if (line.isEmpty()) {
                    // Create a Quiz object when a blank line is encountered
                    if (lessonId != -1 && !quizTitle.isEmpty()) {
                        Quiz quiz = new Quiz(quizTitle, lessonId);
                        boolean quizAdded = false;

                        // Search for the correct lesson and add the quiz to it
                        for (School school : schools) {
                            for (Course course : school.getManage().getCourses()) {
                                for (Lesson lesson : course.getLessons()) {
                                    if (lesson.getLessonId() == lessonId) {
                                        lesson.setQuiz(quiz);  // Set the quiz for the corresponding lesson
                                        System.out.println("Quiz added successfully to lesson ID " + lessonId);
                                        quizAdded = true;
                                        break;  // Exit once the quiz is added
                                    }
                                }
                                if (quizAdded) {
                                    break;  // Exit outer loops if quiz is added
                                }
                            }
                            if (quizAdded) {
                                break;  // Exit outer loops if quiz is added
                            }
                        }

                        // If the quiz was not added, show an error message
                        if (!quizAdded) {
                            System.out.println("No lesson found with ID " + lessonId + " for the quiz.");
                        }
                    }

                    // Reset variables for the next quiz
                    quizTitle = "";
                    lessonId = -1;
                } else if (line.startsWith("title : ")) {
                    quizTitle = line.substring(8).trim();  // Extract quiz title
                } else if (line.startsWith("lessonId : ")) {
                    try {
                        lessonId = Integer.parseInt(line.substring(11).trim());  // Extract lesson ID
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid lessonId format: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //------------------- Question Method -------------------
    public void createQuestionsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String question = "";
            String option1 = "";
            String option2 = "";
            String option3 = "";
            String option4 = "";
            Question.CorrectAnswer correctAnswer = null;
            int quizId = -1;

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Normalize input by trimming whitespace

                if (line.isEmpty()) {
                    // Add the question if all fields are valid
                    if (quizId != -1 && !question.isEmpty() && !option1.isEmpty() &&
                        !option2.isEmpty() && !option3.isEmpty() && !option4.isEmpty() &&
                        correctAnswer != null) {
                        
                        Question questionObj = new Question(question, option1, option2, option3, option4, correctAnswer, quizId);
                        boolean questionAdded = false;

                        // Loop through schools, courses, and lessons to add the question to the appropriate quiz
                        for (School school : schools) {
                            for (Course course : school.getManage().getCourses()) {
                                for (Lesson lesson : course.getLessons()) {
                                    Quiz quiz = lesson.getQuiz();
                                    if (quiz != null && quiz.getQuiz_id() == quizId) {
                                        quiz.addQuestionToQuiz(questionObj);
                                        System.out.println("Question added successfully to quiz ID " + quizId);
                                        questionAdded = true;
                                        break;
                                    }
                                }
                                if (questionAdded) break;
                            }
                            if (questionAdded) break;
                        }

                        if (!questionAdded) {
                            System.out.println("Error: Question for quiz ID " + quizId + " could not be added. Ensure quiz exists.");
                        }
                    } else {
                        System.out.println("Skipping invalid or incomplete question entry.");
                    }

                    // Reset variables for the next question
                    question = "";
                    option1 = "";
                    option2 = "";
                    option3 = "";
                    option4 = "";
                    correctAnswer = null;
                    quizId = -1;
                } else if (line.toLowerCase().startsWith("question : ")) {
                    question = line.substring(11).trim();
                } else if (line.toLowerCase().startsWith("optiona : ")) {
                    option1 = line.substring(10).trim();
                } else if (line.toLowerCase().startsWith("optionb : ")) {
                    option2 = line.substring(10).trim();
                } else if (line.toLowerCase().startsWith("optionc : ")) {
                    option3 = line.substring(10).trim();
                } else if (line.toLowerCase().startsWith("optiond : ")) {
                    option4 = line.substring(10).trim();
                } else if (line.toLowerCase().startsWith("correct : ")) {
                    try {
                        correctAnswer = Question.CorrectAnswer.valueOf(line.substring(10).trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid correct answer format: " + line);
                    }
                } else if (line.toLowerCase().startsWith("quiz_id : ")) {
                    try {
                        quizId = Integer.parseInt(line.substring(9).trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quiz ID format: " + line);
                    }
                }
            }

            // Process the last question if file doesn't end with an empty line
            if (!question.isEmpty() && !option1.isEmpty() && !option2.isEmpty() &&
                !option3.isEmpty() && !option4.isEmpty() && correctAnswer != null && quizId != -1) {
                Question questionObj = new Question(question, option1, option2, option3, option4, correctAnswer, quizId);
                boolean questionAdded = false;

                for (School school : schools) {
                    for (Course course : school.getManage().getCourses()) {
                        for (Lesson lesson : course.getLessons()) {
                            Quiz quiz = lesson.getQuiz();
                            if (quiz != null && quiz.getQuiz_id() == quizId) {
                                quiz.addQuestionToQuiz(questionObj);
                                System.out.println("Question added successfully to quiz ID " + quizId);
                                questionAdded = true;
                                break;
                            }
                        }
                        if (questionAdded) break;
                    }
                    if (questionAdded) break;
                }

                if (!questionAdded) {
                    System.out.println("Error: Question for quiz ID " + quizId + " could not be added.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //------------------- course  Method -------------------
    
    public void assignCourseToStudentFromFile( String filePath)
    {
        System.out.println("Assigning courses to students from file...");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Long studentId = null;
            Long courseId = null;

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace

                if (line.isEmpty()) {
                    // Create a Course object when a blank line is encountered
                    if (studentId != null && courseId != null) {
                        for (School school : schools) {
                            if(school.getManage().getStudents().contains(searchStudentById(studentId))){
                            school.assignCourseToStudent(studentId, courseId);
                            }
                        }

                    }
                    else{
                        System.out.println("Student ID or Course ID is missing.");
                    }


                    // Reset variables for the next course
                    studentId = null;
                    courseId = null;
                } else if (line.startsWith("studentId : ")) {
                    try {
                        studentId = Long.parseLong(line.substring(11).trim());  // Extract student ID
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid student ID format: " + line);
                    }
                } else if (line.startsWith("courseId : ")) {
                    try {
                        courseId = Long.parseLong(line.substring(10).trim());  // Extract course ID
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid course ID format: " + line);
                    }
                }
                else
                {
                    System.out.println("Invalid format: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void CreateCourse() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Course Name: ");
        String courseName = input.nextLine();

        System.out.println("Enter Course Description: ");
        String courseDescription = input.nextLine();

        System.out.println("Enter Course course Is Active: ");
        Boolean courseIsActive = input.nextBoolean();

        // Create Course object and add to the list
        Course course = new Course(courseName, courseDescription, courseIsActive);
        for (School school : schools) {
            school.getManage().addCourse(course);
        }

        System.out.println("Course created successfully!");
    }

    public void createCourseFromFile(String coursefilePath, String lessonfilePath, String QuizFilepath, String QustionsPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(coursefilePath))) {
            String line;
            String courseName = "", courseDescription = "";
            boolean courseIsActive = false;

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    // Create a Course object and add to the list when a blank line is encountered
                    Course course = new Course(courseName, courseDescription, courseIsActive);
                    for (School school : schools) {
                        school.getManage().addCourse(course);
                    }
                    // Reset variables for the next course
                    courseName = "";
                    courseDescription = "";
                    courseIsActive = false;
                } else if (line.startsWith("courseName : ")) {
                    courseName = line.substring(13).trim();
                } else if (line.startsWith("courseDescription : ")) {
                    courseDescription = line.substring(20).trim();
                } else if (line.startsWith("courseIsActive : ")) {
                    courseIsActive = Boolean.parseBoolean(line.substring(17).trim());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    public void removeCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Course ID to remove: ");
        int courseID = sc.nextInt();
        for (School school : schools) {
            for (Course course : school.getManage().getCourses()) {
                if (course.getCourseId() == courseID) {
                    school.getManage().removeCourse(course);
                    System.out.println("Course with ID " + courseID + " and name " + course.getCourseName() + " has been removed.");
                }
            }
        }
    }

    public void viewCourses() {
        Set<Course> uniqueCourses = new HashSet<>();
        for (School school : schools) {
            uniqueCourses.addAll(school.getManage().getCourses());
        }
        List<Course> sortedCourses = new ArrayList<>(uniqueCourses);
        sortedCourses.sort(Comparator.comparing(Course::getCourseId));
        for (Course course : sortedCourses) {
            System.out.println(course);
        }
    }

    //------------------- Chapter Method -------------------
    public void addChapterToTutor(long tutorId, int schoolID, long courseId) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {  // Match school ID
                Course course = school.getManage().findCourseById(courseId);  // Find the course by ID
                if (course == null) {
                    System.out.println("Course with ID " + courseId + " not found in school ID " + schoolID);
                    return;
                }
                for (Tutor tutor : school.getManage().getTutors()) {  // Find the tutor
                    if (tutor.getId().equals(tutorId)) {
                        tutor.addCourse(course);  // Add course to tutor
                        System.out.println("Chapter added successfully to tutor ID " + tutorId + " in school ID " + schoolID);
                        return;
                    }
                }
                System.out.println("Tutor with ID " + tutorId + " not found in school ID " + schoolID);
                return;
            }
        }
        System.out.println("School with ID " + schoolID + " not found.");
    }

    public void assignTutortocourse(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            long tutorId = 0;
            int schoolID = 0;
            long courseId = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();  // Remove extra spaces

                if (line.isEmpty()) {  // Blank line indicates end of one block
                    // Process the collected data
                    if (tutorId != 0 && schoolID > 0 && courseId != 0) {
                        addChapterToTutor(tutorId, schoolID, courseId);
                    }

                    // Reset values for the next block
                    tutorId = 0;
                    schoolID = 0;
                    courseId = 0;
                } else if (line.startsWith("schoolID : ")) {
                    schoolID = Integer.parseInt(line.substring(11).trim());
                } else if (line.startsWith("tutorID : ")) {
                    tutorId = Long.parseLong(line.substring(10).trim());
                } else if (line.startsWith("courseID : ")) {
                    courseId = Long.parseLong(line.substring(10).trim());
                }
            }

            // Process the last block in case the file doesn't end with a blank line
            if (tutorId != 0 && schoolID > 0 && courseId != 0) {
                addChapterToTutor(tutorId, schoolID, courseId);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Remove chapter from a tutor
    public void removeChapterFromTutor(Long tutorId, int schoolID, int chapterId) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                for (Tutor tutor : school.getManage().getTutors()) {
                    if (tutor.getId().equals(tutorId)) {
                        tutor.removeCourse(chapterId);
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
            for (Tutor tutor : school.getManage().getTutors()) {
                if (tutor.getId().equals(tutorId)) {
                    tutor.getCourses();
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
                for (Tutor tutor : school.getManage().getTutors()) {
                    if (tutor.getId().equals(tutorId)) {
                        tutor.getCourses();
                        return;
                    }
                }
                System.out.println("Tutor not found in the specified school.");
                return;
            }
        }
        System.out.println("School not found.");
    }

    // Match the student course ID and the tutor course ID to get the students for each school
    public void matchStudentCourseWithTutorCourse(Long tutorId) {
        for (School school : schools) {
            Tutor tutor = school.getManage().findTutorById(tutorId);
            if (tutor == null) {
                continue;
            }
            

            List<Course> tutorCourses = tutor.getCourses();
            if (tutorCourses.isEmpty()) {
                System.out.println("No courses found for tutor ID " + tutorId + " in school ID " + school.getSchoolID());
                continue;
            }

            Set<Student> matchedStudents = new HashSet<>();
            for (Course tutorCourse : tutorCourses) {
                for (Student student : school.getManage().getStudents()) {
                    if (student.getCourses().contains(tutorCourse)) {
                        matchedStudents.add(student);
                    }
                }
            }

            if (matchedStudents.isEmpty()) {
                System.out.println("No students found for the courses taught by tutor ID " 
                    + tutorId + " in school ID " + school.getSchoolID());
            } else {
                System.out.println("Students for the courses taught by tutor ID " 
                    + tutorId + " in school ID " + school.getSchoolID() + ":");
                
                for (Student student : matchedStudents) {
                    System.out.println("------------------------------");
                    System.out.println("Student ID    : " + student.getId());
                    System.out.println("Name          : " + student.getName());
                    System.out.println("Email         : " + student.getEmail());
                    System.out.println("Phone         : " + student.getPhone());
                    System.out.println("Address       : " + student.getAddress());
                    System.out.println("------------------------------");
                }
            }
        }
    }

    //...........Admin Class.............

    // view how many students are in a school
    public void viewStudentCountBySchool(int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                System.out.println("Number of students in school ID " + schoolID + ": " + school.getManage().getStudents().size());
                return;
            }
        }
        System.out.println("School not found.");
    }

    // view how many tutors are in a school
    public void viewTutorCountBySchool(int schoolID) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                System.out.println("Number of tutors in school ID " + schoolID + ": " + school.getManage().getTutors().size());
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
                for (Tutor tutor : school.getManage().getTutors()) {
                    if (tutor.getId().equals(tutorId)) {
                        System.out.println("Number of chapters for tutor ID " + tutorId + " in school ID " + schoolID + ": " + tutor.getCourses().size());
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
            for (Tutor tutor : school.getManage().getTutors()) {
                totalChapters += tutor.getCourses().size();
            }
        }
        System.out.println("Number of chapters in the system: " + totalChapters);
    }

    // view how many students are in the system
    public void viewStudentCount() {
        int totalStudents = 0;
        for (School school : schools) {
            totalStudents += school.getManage().getStudents().size();
        }
        System.out.println("Number of students in the system: " + totalStudents);
    }

    // view how many tutors are in the system
    public void viewTutorCount() {
        int totalTutors = 0;
        for (School school : schools) {
            totalTutors += school.getManage().getTutors().size();
        }
        System.out.println("Number of tutors in the system: " + totalTutors);
    }

    // Compare the number of student accounts in a specified school with the number the admin will enter
    public void compareStudentCountBySchool(int schoolID, int studentCount) {
        for (School school : schools) {
            if (school.getSchoolID() == schoolID) {
                int actualCount = school.getManage().getStudents().size();
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
                int actualCount = school.getManage().getTutors().size();
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
// Save to files
    public void saveSchoolToFile(String schoolPath){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(schoolPath))) {
            for (School school : schools) {
                bw.write("schoolName : " + school.getSchoolName());
                bw.newLine();
                bw.write("address : " + school.getAddress());
                bw.newLine();
                bw.write("city : " + school.getCity());
                bw.newLine();
                bw.write("contactPerson : " + school.getContactPerson());
                bw.newLine();
                bw.write("email : " + school.getEmail());
                bw.newLine();
                bw.write("phoneNumber : " + school.getPhoneNumber());
                bw.newLine();
                bw.write("username : " + school.getUserName());
                bw.newLine();
                bw.write("password : " + school.getPassword());
                bw.newLine();
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void saveStudentToFile(String StudentsPath){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(StudentsPath))) {
            for (School school : schools) {
                for (Student student : school.getManage().getStudents()) {
                    bw.write("name : " + student.getName());
                    bw.newLine();
                    bw.write("email : " + student.getEmail());
                    bw.newLine();
                    bw.write("phone : " + student.getPhone());
                    bw.newLine();
                    bw.write("address : " + student.getAddress());
                    bw.newLine();
                    bw.write("username : " + student.getUsername());
                    bw.newLine();
                    bw.write("password : " + student.getPassword());
                    bw.newLine();
                    bw.write("dateOfBirth : " + student.getDateOfBirth());
                    bw.newLine();
                    bw.write("schoolID : " + student.getSchoolID());
                    bw.newLine();
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void saveTutorToFile(String TutorPath){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TutorPath))) {
            for (School school : schools) {
                for (Tutor tutor : school.getManage().getTutors()) {
                    bw.write("name : " + tutor.getName());
                    bw.newLine();
                    bw.write("email : " + tutor.getEmail());
                    bw.newLine();
                    bw.write("phone : " + tutor.getPhone());
                    bw.newLine();
                    bw.write("address : " + tutor.getAddress());
                    bw.newLine();
                    bw.write("username : " + tutor.getUsername());
                    bw.newLine();
                    bw.write("password : " + tutor.getPassword());
                    bw.newLine();
                    bw.write("dateOfBirth : " + tutor.getDateOfBirth());
                    bw.newLine();
                    bw.write("schoolID : " + tutor.getSchoolID());
                    bw.newLine();
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void saveCourseToFile(String CoursePath, String lessonpath, String quizpath, String QustionsPath){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CoursePath))) {
            Set<Course> uniqueCourses = new HashSet<>();
            for (School school : schools) {
                uniqueCourses.addAll(school.getManage().getCourses());
            }
            for (Course course : uniqueCourses) {
                bw.write("courseName : " + course.getCourseName());
                bw.newLine();
                bw.write("courseDescription : " + course.getCourseDescription());
                bw.newLine();
                bw.write("courseIsActive : " + course.getCourseIsActive());
                bw.newLine();
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
   

   // Save student progress to progress file
    public void saveStudentProgressToFile(String progressFilePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(progressFilePath))) {
            for (School school : schools) {
                for (Student student : school.getManage().getStudents()) {
                    bw.write("Student ID: " + student.getId());
                    bw.newLine();
                    bw.write("Student Name: " + student.getName());
                    bw.newLine();
                    bw.write("School ID: " + student.getSchoolID());
                    bw.newLine();
                    bw.write("Progress: " + student.getProgress());
                    bw.newLine();
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read student progress from progress file
    public void readStudentProgressFromFile(String progressFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(progressFilePath))) {
            String line;
            long studentId = 0;
            String studentName = "";
            int schoolId = 0;
            int progress = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();  // Remove leading and trailing whitespace

                if (line.isEmpty()) {
                    // Process the collected data
                    if (studentId != 0 && !studentName.isEmpty() && schoolId != 0) {
                        // Update the student progress
                        for (School school : schools) {
                            if (school.getSchoolID() == schoolId) {
                                Student student = school.getManage().findStudentById(studentId);
                                if (student != null) {
                                    student.setProgress(progress);
                                    System.out.println("Progress updated for student ID " + studentId + " in school ID " + schoolId);
                                } else {
                                    System.out.println("Student with ID " + studentId + " not found in school ID " + schoolId);
                                }
                                break;
                            }
                        }
                    } else {
                        System.out.println("Skipping invalid or incomplete student progress entry.");
                    }

                    // Reset variables for the next student progress entry
                    studentId = 0;
                    studentName = "";
                    schoolId = 0;
                    progress = 0;
                } else if (line.startsWith("Student ID: ")) {
                    studentId = Long.parseLong(line.substring(12).trim());
                } else if (line.startsWith("Student Name: ")) {
                    studentName = line.substring(14).trim();
                } else if (line.startsWith("School ID: ")) {
                    schoolId = Integer.parseInt(line.substring(11).trim());
                } else if (line.startsWith("Progress: ")) {
                    progress = Integer.parseInt(line.substring(10).trim());
                }
            }

            // Process the last student progress entry if file doesn't end with a blank line
            if (studentId != 0 && !studentName.isEmpty() && schoolId != 0) {
                // Update the student progress
                for (School school : schools) {
                    if (school.getSchoolID() == schoolId) {
                        Student student = school.getManage().findStudentById(studentId);
                        if (student != null) {
                            student.setProgress(progress);
                            System.out.println("Progress updated for student ID " + studentId + " in school ID " + schoolId);
                        } else {
                            System.out.println("Student with ID " + studentId + " not found in school ID " + schoolId);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveLessonAndQuizToFile(String lessonQuizPath) {
        try {
            // Step 1: Read existing entries into a set
            Set<String> existingEntries = new HashSet<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(lessonQuizPath))) {
                StringBuilder entry = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        existingEntries.add(entry.toString().trim());
                        entry.setLength(0); // Reset for the next entry
                    } else {
                        entry.append(line).append("\n");
                    }
                }
                if (entry.length() > 0) {
                    existingEntries.add(entry.toString().trim());
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found. A new one will be created.");
            }

            // Step 2: Write new entries
            try (BufferedWriter lessonQuizWriter = new BufferedWriter(new FileWriter(lessonQuizPath, true))) {
                for (School school : schools) {
                    for (Student student : school.getManage().getStudents()) {
                        List<Grade> grades = student.getgrade();
                        if (grades != null && !grades.isEmpty()) {
                            for (Grade grade : grades) {
                                String newEntry = String.format(
                                        "lessonId : %d\nmarks : %d\nquizId : %d\nstudentId : %d\n",
                                        grade.getLessonId(),
                                        grade.getMarks(),
                                        grade.getQuizId(),
                                        grade.getStudentid()
                                );

                                if (!existingEntries.contains(newEntry.trim())) {
                                    existingEntries.add(newEntry.trim());
                                    lessonQuizWriter.write(newEntry);
                                    lessonQuizWriter.newLine();
                                    System.out.println("Grade added for Student ID: " + grade.getStudentid());
                                } else {
                                    System.out.println("Duplicate grade found for Student ID: " + grade.getStudentid() + ". Skipping.");
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readLessonAndQuizFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int lessonId = 0, marks = 0, quizId = 0;
            long studentId = 0;
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    // Process the parsed data
                    boolean studentFound = false; // Track if a student is found
                    for (School school : schools) {
                        for (Student student : school.getManage().getStudents()) {
                            if (student.getId() == studentId) {
                                student.setgrade(new Grade(lessonId, marks, quizId, studentId));
                                System.out.println("Grade added successfully for student ID " + studentId);
                                studentFound = true;
                                break; // Exit student loop after adding grade
                            }
                        }
                        if (studentFound) {
                            break; // Exit school loop if student is found

                                            }}
                    if (!studentFound) {
                        System.out.println("Student with ID " + studentId + " not found.");
                    }
                    // Reset variables for the next grade entry
                    lessonId = 0;
                    marks = 0;
                    quizId = 0;
                    studentId = 0;
                } else if (line.startsWith("lessonId : ")) {
                    lessonId = Integer.parseInt(line.substring(11).trim());
                } else if (line.startsWith("marks : ")) {
                    marks = Integer.parseInt(line.substring(7).trim());
                } else if (line.startsWith("quizId : ")) {
                    quizId = Integer.parseInt(line.substring(8).trim());
                } else if (line.startsWith("studentId : ")) {
                    studentId = Long.parseLong(line.substring(11).trim());
                } else {
                    System.out.println("Unexpected line format: " + line);
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

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

    @Override
    public int compareTo(Admin other) {
        int nameComparison = this.getName().compareTo(other.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.getUsername().compareTo(other.getUsername());
    }

    // print the both student id for the same student name and the same student username
    public void printSameStudentNamesAndUsernamesOrGrades() {
        List<Student> students = new ArrayList<>();
        
        // Collect all students into a single list
        for (School school : schools) {
            students.addAll(school.getManage().getStudents());
        }

        // Find and print students with the same name
        System.out.println("Students with the same name:");
        findAndPrintDuplicates(students, "name");

        // Find and print students with the same username
        System.out.println("\nStudents with the same username:");
        findAndPrintDuplicates(students, "username");

        // Find and print students with the same grade
        System.out.println("\nStudents with the same grade:");
        findAndPrintDuplicates(students, "grade");
    }

    private void findAndPrintDuplicates(List<Student> students, String attribute) {
        List<List<Student>> duplicateGroups = new ArrayList<>();
        boolean[] visited = new boolean[students.size()]; // Track visited students

        for (int i = 0; i < students.size(); i++) {
            if (visited[i]) continue;

            List<Student> group = new ArrayList<>();
            Student current = students.get(i);
            group.add(current);
            visited[i] = true;

            for (int j = i + 1; j < students.size(); j++) {
                if (visited[j]) continue;

                Student other = students.get(j);

                boolean isDuplicate = false;
                switch (attribute) {
                    case "name":
                        isDuplicate = current.getName().equals(other.getName());
                        break;
                    case "username":
                        isDuplicate = current.getUsername().equals(other.getUsername());
                        break;
                    case "grade":
                        for (Grade grade1 : current.getgrade()) {
                            for (Grade grade2 : other.getgrade()) {
                                if (grade1.getMarks() == grade2.getMarks()) {
                                    isDuplicate = true;
                                    break;
                                }
                            }
                            if (isDuplicate) break;
                        }
                        break;
                }

                if (isDuplicate) {
                    group.add(other);
                    visited[j] = true;
                }
            }

            if (group.size() > 1) {
                duplicateGroups.add(group);
            }
        }

        // Print the duplicate groups
        if (duplicateGroups.isEmpty()) {
            System.out.println("No duplicates found.");
        } else {
            for (List<Student> group : duplicateGroups) {
                System.out.println("Group:");
                for (Student student : group) {
                    System.out.println("    ID: " + student.getId() + ", Name: " + student.getName() +
                                    ", Username: " + student.getUsername());
                }
            }
        }
    }

}
