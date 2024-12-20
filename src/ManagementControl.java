import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ManagementControl {

    private ArrayList<Tutor> tutors = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>(); // Added courses


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
    
    public ArrayList<Lesson> getLessons() {
        ArrayList<Lesson> allLessons = new ArrayList<>();
        for (Course course : courses) {
            allLessons.addAll(course.getLessons());
        }
        return allLessons;
    }

    public ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> allQuizzes = new ArrayList<>();
        for (Lesson lesson : getLessons()) {
            allQuizzes.add(lesson.getQuiz());
        }
        return allQuizzes;
    }

    public ArrayList<Question> getQuestions() {
        ArrayList<Question> allQuestions = new ArrayList<>();
        for (Quiz quiz : getQuizzes()) {
            allQuestions.addAll(quiz.getQuestions());
        }
        return allQuestions;
    }
  
    //----------------- method that return string not list
  
    public String getTutorsData() {
        StringBuilder data = new StringBuilder();
        for (Tutor tutor : getTutors()) {
            data.append(tutor.toString()).append("\n");  // Assuming Tutor has a proper toString() method
        }
        return data.toString();
    }

    public String getStudentsData() {
        StringBuilder data = new StringBuilder();
        for (Student student : getStudents()) {
            data.append(student.toString()).append("\n");  // Assuming Student has a proper toString() method
        }
        return data.toString();
    }

    public String getCoursesData() {
        StringBuilder data = new StringBuilder();
        for (Course course : getCourses()) {
            data.append(course.toString()).append("\n");  // Assuming Course has a proper toString() method
        }
        return data.toString();
    }

    public String getLessonsData() {
        StringBuilder data = new StringBuilder();
        for (Lesson lesson : getLessons()) {
            data.append(lesson.toString()).append("\n");  // Assuming Lesson has a proper toString() method
        }
        return data.toString();
    }

    public String getQuizzesData() {
        StringBuilder data = new StringBuilder();
        for (Quiz quiz : getQuizzes()) {
            data.append(quiz.toString()).append("\n");  // Assuming Quiz has a proper toString() method
        }
        return data.toString();
    }

    public String getQuestionsData() {
        StringBuilder data = new StringBuilder();
        for (Question question : getQuestions()) {
            data.append(question.toString()).append("\n");  // Assuming Question has a proper toString() method
        }
        return data.toString();
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

        try (Scanner scanner = new Scanner(System.in)) {
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

                    default -> System.out.println("Invalid option, please try again.");
                }
            }
        }
    }

    //----------------- Added methods for Students -----------------
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

        try (Scanner scanner = new Scanner(System.in)) {
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
    }

    //----------------- Added methods for Courses -----------------

    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
            System.out.println("Course " + course.getCourseName() + " added successfully." + course.getCourseId());
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

    //search for students by criteria
    public void searchStudents(String criteria) {
        if (criteria == null || criteria.isEmpty()) {
            System.out.println("Invalid search criteria.");
            return;
        }

        boolean found = false;
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(criteria.toLowerCase())) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found for the search criteria: " + criteria);
        }
    }

    //search for tutors by criteria
    public void searchTutors(String criteria) {
        if (criteria == null || criteria.isEmpty()) {
            System.out.println("Invalid search criteria.");
            return;
        }

        boolean found = false;
        for (Tutor tutor : tutors) {
            if (tutor.getName().toLowerCase().contains(criteria.toLowerCase())) {
                System.out.println(tutor);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tutors found for the search criteria: " + criteria);
        }
    }

    //search for courses by criteria
    public void searchCourses(String criteria) {
        if (criteria == null || criteria.isEmpty()) {
            System.out.println("Invalid search criteria.");
            return;
        }

        boolean found = false;
        for (Course course : courses) {
            if (course.getCourseName().toLowerCase().contains(criteria.toLowerCase())) {
                System.out.println(course);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No courses found for the search criteria: " + criteria);
        }
    }

    //search for tutors by course
    public void searchTutorsByCourse(String courseName) {
        if (courseName == null || courseName.isEmpty()) {
            System.out.println("Invalid course name.");
            return;
        }

        boolean found = false;
        for (Tutor tutor : tutors) {
            if (tutor.getCourses().stream().anyMatch(course -> course.getCourseName().equalsIgnoreCase(courseName))) {
                System.out.println(tutor);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tutors found for the course: " + courseName);
        }
    }

    //search for students by course
    public void searchStudentsByCourse(String courseName) {
        if (courseName == null || courseName.isEmpty()) {
            System.out.println("Invalid course name.");
            return;
        }

        boolean found = false;
        for (Student student : students) {
            if (student.getCourses().stream().anyMatch(course -> course.getCourseName().equalsIgnoreCase(courseName))) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found for the course: " + courseName);
        }
    }

    //sort students by name
    public void sortStudentsByName() {
        students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        System.out.println("Students sorted by name:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    //sort tutors by name
    public void sortTutorsByName() {
        tutors.sort((t1, t2) -> t1.getName().compareToIgnoreCase(t2.getName()));
        System.out.println("Tutors sorted by name:");
        for (Tutor tutor : tutors) {
            System.out.println(tutor);
        }
    }

    //sort courses by name
    public void sortCoursesByName() {
        courses.sort((c1, c2) -> c1.getCourseName().compareToIgnoreCase(c2.getCourseName()));
        System.out.println("Courses sorted by name:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    //sort students by registration date
    public void sortStudentsByRegistrationDate() {
        students.sort((s1, s2) -> s1.getRegistrationDate().compareTo(s2.getRegistrationDate()));
        System.out.println("Students sorted by registration date:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    //sort tutors by joining date
    public void sortTutorsByJoiningDate() {
        tutors.sort((t1, t2) -> t1.getDateJoined().compareTo(t2.getDateJoined()));
        System.out.println("Tutors sorted by joining date:");
        for (Tutor tutor : tutors) {
            System.out.println(tutor);
        }
    }

    public void viewStudentDetails(long studentId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Find course by name
    public Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null; // Course not found
    }
}