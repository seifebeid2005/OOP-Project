
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import java.util.Scanner;

public class School {

    public static Iterable<School> getSchools() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Attributes
    private int schoolID;
    private static int idCounter = 1;
    private String schoolName;
    private String address;
    private String city;
    private String contactPerson;
    private String email;
    private String phoneNumber;
    private ManagementControl manage;
    private String userName;
    private String password;

    // Default constructor
    public School() {
        this.schoolID = idCounter++;
        this.schoolName = "";
        this.address = "";
        this.city = "";
        this.contactPerson = "";
        this.email = "";
        this.phoneNumber = "";
        this.manage = new ManagementControl();
        this.userName = "";
        this.password = "";
    }

    // Parameterized constructor
    public School(String schoolName, String address, String city, String contactPerson, String email, String phoneNumber , String userName, String password) {
        this.schoolID = idCounter++;
        this.schoolName = schoolName;
        this.address = address;
        this.city = city;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.manage = new ManagementControl();
        this.userName = userName;
        this.password = password;
    }

    // Getters and Setters
    public ManagementControl getManage() {
        return manage;
    }

    //------------------------getter methods-----------------------
    
    public String getTutorsData() {
        StringBuilder data = new StringBuilder();
        for (Tutor tutor : getManage().getTutors()) {
            data.append(tutor.toString()).append("\n");  // Assuming Tutor has a proper toString() method
        }
        return data.toString();
    }

    public String getStudentsData() {
        StringBuilder data = new StringBuilder();
        for (Student student : getManage().getStudents()) {
            data.append(student.toString()).append("\n");  // Assuming Student has a proper toString() method
        }
        return data.toString();
    }

    public String getCoursesData() {
        StringBuilder data = new StringBuilder();
        for (Course course : getManage().getCourses()) {
            data.append(course.toString()).append("\n");  // Assuming Course has a proper toString() method
        }
        return data.toString();
    }

    public String getLessonsData() {
        StringBuilder data = new StringBuilder();
        for (Lesson lesson : getManage().getLessons()) {
            data.append(lesson.toString()).append("\n");  // Assuming Lesson has a proper toString() method
        }
        return data.toString();
    }

    public String getQuizzesData() {
        StringBuilder data = new StringBuilder();
        for (Quiz quiz : getManage().getQuizzes()) {
            data.append(quiz.toString()).append("\n");  // Assuming Quiz has a proper toString() method
        }
        return data.toString();
    }

    public String getQuestionsData() {
        StringBuilder data = new StringBuilder();
        for (Question question : getManage().getQuestions()) {
            data.append(question.toString()).append("\n");  // Assuming Question has a proper toString() method
        }
        return data.toString();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        try (Scanner input = new Scanner(System.in)) {
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
        }
    }

    public void addStudent(Student student) {
        if (student == null) {
            System.out.println("Student cannot be null.");
            return;
        }
        getManage().addStudent(student);
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
    // private boolean matchesCriteria(Student student, String criteria, Object value) {
    //     return switch (criteria.toLowerCase()) {
    //         case "id" -> student.getId() == (int) value;
    //         case "name" -> student.getName().equalsIgnoreCase((String) value);
    //         case "email" -> student.getEmail().equalsIgnoreCase((String) value);
    //         case "dateofbirth" -> student.getDateOfBirth().equals(value);
    //         case "age" -> student.getAge() == (int) value;
    //         case "phone" -> student.getPhone().equals(value);
    //         case "address" -> student.getAddress().equalsIgnoreCase((String) value);
    //         case "username" -> student.getUsername().equals(value);
    //         case "schoolid" -> student.getSchoolID() == (int) value;
    //         case "registrationdate" -> student.getRegistrationDate().equals(value);
    //         default -> false;
    //     };
    // }
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
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter Tutor ID: ");
            Long id = input.nextLong();
            input.nextLine(); // Consume newline

            System.out.println("Enter Tutor Name: ");
            String name = input.nextLine();

            System.out.println("Enter Tutor Email: ");
            String tutorEmail = input.nextLine();

            System.out.println("Enter Tutor Date of Birth (YYYY-MM-DD): ");
            LocalDate dateOfBirth = LocalDate.parse(input.nextLine());

            System.out.println("Enter Tutor Phone: ");
            String phone = input.nextLine();

            System.out.println("Enter Tutor Address: ");
            String tutorAddress = input.nextLine();

            System.out.println("Enter Tutor Username: ");
            String username = input.nextLine();

            System.out.println("Enter Tutor Password: ");
            String password = input.nextLine();
            
            System.out.println("Enter Tutor Role (1: LEAD_TUTOR, 2: ASSISTANT_TUTOR, 3: TUTOR): ");
            int roleChoice = input.nextInt();
            input.nextLine(); // Consume newline
            Tutor.Role roleEnum;
            switch (roleChoice) {
                case 1 ->
                    roleEnum = Tutor.Role.LEAD_TUTOR;
                case 2 ->
                    roleEnum = Tutor.Role.ASSISTANT_TUTOR;
                case 3 ->
                    roleEnum = Tutor.Role.TUTOR;
                default -> {
                    System.out.println("Invalid choice. Defaulting to TUTOR.");
                    roleEnum = Tutor.Role.TUTOR;
                }
            }


            System.out.println("Enter Tutor School ID: ");
            int tutorSchoolID = input.nextInt();
            input.nextLine(); // Consume newline

            // Create Tutor object and add to the list
            Tutor tutor = new Tutor(name, tutorEmail, dateOfBirth, phone, tutorAddress, username, password, roleEnum, tutorSchoolID);
            addTutor(tutor);
            System.out.println("Tutor account created successfully!");
        }
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
    // public void viewTutorStudents(Long tutorId) {

    // String representation of the object
    @Override
    public String toString() {
        return "\nschoolName : " + schoolName
                + "\naddress : " + address
                + "\ncity : " + city
                + "\ncontactPerson : " + contactPerson
                + "\nemail : " + email
                + "\nphoneNumber : " + phoneNumber
                + "\nuserName : " + userName
                + "\npassword : " + password;
    }


    // Assign courses to students
    public void assignCourseToStudent(Long studentId, Long courseId) {
        Student student = getManage().findStudentById(studentId);
        Course course = getManage().findCourseById(courseId);
        
        if (student != null && course != null) {
            student.enrollInCourse(course);
            System.out.println("Course assigned successfully to Student ID: " + studentId);
        } else if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Course not found.");            
        }
    }  

    public void assignCourseToStudentFromFile(String filePath) {
        BufferedReader reader = null;

        try {
            // Open the file to read the student and course IDs
            reader = new BufferedReader(new FileReader(filePath));
            
            // Read and parse the Student ID and Course ID directly
            String studentLine = reader.readLine();
            String courseLine = reader.readLine();

            if (studentLine != null && courseLine != null) {
                // Extract and parse IDs from the lines
                Long studentId = Long.parseLong(studentLine.split(":")[1].trim());
                Long courseId = Long.parseLong(courseLine.split(":")[1].trim());

                // Retrieve the student and course
                Student student = getManage().findStudentById(studentId);
                Course course = getManage().findCourseById(courseId);

                // Assign the course to the student if both exist
                if (student != null && course != null) {
                    student.enrollInCourse(course);
                    System.out.println("Course assigned successfully to Student ID: " + studentId);
                }
            } else {
                System.out.println("Invalid or incomplete data in file.");
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error processing the file or invalid ID format.");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing the file.");
                e.printStackTrace();
            }
        }
    }

    

    // Validate phone number format (basic example)
    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{11}"); // Assuming 10-digit phone numbers
    }

}
