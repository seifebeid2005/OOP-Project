import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
import java.util.List;

public class StudentDashboardGUI extends JFrame {
    private List<Student> students = new ArrayList<>(); // List to hold students
    private Student currentStudent; // The currently logged-in student
    private JPanel mainPanel;

    public StudentDashboardGUI() {
        readStudentData("tx/student.txt"); // Read student data
        if (!students.isEmpty()) {
            currentStudent = students.get(0); // Automatically set the first student as logged in
            createDashboard(); // Show the student dashboard
        } else {
            System.out.println("No students available.");
        }
    }

    private void createDashboard() {
        // Setup Frame
        setTitle("Student Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setLayout(new BorderLayout());

        // Sidebar Panel for options
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(0, 51, 102)); // Dark Blue
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        // Sidebar Buttons
        JButton printProfileBtn = createSidebarButton("Print Profile");
        JButton viewCoursesBtn = createSidebarButton("View Courses");
        JButton viewLessonsBtn = createSidebarButton("See My Lessons");
        JButton viewTutorsBtn = createSidebarButton("See My Tutors");
        JButton startQuizBtn = createSidebarButton("Start Quiz");
        JButton viewGradesBtn = createSidebarButton("View Grades");
        JButton completedCoursesBtn = createSidebarButton("Completed Courses");
        JButton notCompletedCoursesBtn = createSidebarButton("Not Completed Courses");
        JButton progressBtn = createSidebarButton("View Progress");
        JButton logoutBtn = createSidebarButton("Logout");

        // Add Buttons to Sidebar
        sidebar.add(Box.createRigidArea(new Dimension(0, 30))); // Spacer
        sidebar.add(printProfileBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(viewCoursesBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(viewLessonsBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(viewTutorsBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(startQuizBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(viewGradesBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(completedCoursesBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(notCompletedCoursesBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(progressBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(logoutBtn);

        // Main Panel with Background Image
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon backgroundImage = new ImageIcon("Photos/Backgroundimage5.png");
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    g.setColor(Color.GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    System.out.println("Background image not found.");
                }
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Student Dashboard", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 50));
        welcomeLabel.setForeground(new Color(0, 51, 102));
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Add Panels to Frame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // Action Listeners for Buttons
        printProfileBtn.addActionListener(e -> printProfile());
        viewCoursesBtn.addActionListener(e -> viewCourses());
        viewLessonsBtn.addActionListener(e -> seeLessons());
        viewTutorsBtn.addActionListener(e -> seeTutors());
        startQuizBtn.addActionListener(e -> startQuiz());
        viewGradesBtn.addActionListener(e -> viewGrades());
        completedCoursesBtn.addActionListener(e -> completedCourses());
        notCompletedCoursesBtn.addActionListener(e -> notCompletedCourses());
        progressBtn.addActionListener(e -> viewProgress());
        logoutBtn.addActionListener(e -> logout());

        setVisible(true);
    }

    private void printProfile() {
        if (currentStudent != null) {
            // Remove any previous content in the main panel
            mainPanel.removeAll();

            // Create a panel to display student details
            JPanel profilePanel = new JPanel();
            profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
            profilePanel.setOpaque(false);
            profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Add student profile details
            profilePanel.add(createLabel("Name: " + currentStudent.getName()));
            profilePanel.add(createLabel("Email: " + currentStudent.getEmail()));
            profilePanel.add(createLabel("Phone: " + currentStudent.getPhone()));
            profilePanel.add(createLabel("Address: " + currentStudent.getAddress()));
            profilePanel.add(createLabel("Username: " + currentStudent.getUsername()));
            profilePanel.add(createLabel("Date of Birth: " + currentStudent.getDateOfBirth()));
            profilePanel.add(createLabel("School ID: " + currentStudent.getSchoolID()));

            // Add the profile panel to the main panel
            mainPanel.add(profilePanel, BorderLayout.CENTER);

            // Revalidate and repaint the main panel to reflect changes
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private void viewCourses() {
        if (currentStudent != null) {
            // Assuming currentStudent has a method getCourses() that returns a list of courses.
            List<Course> courses = currentStudent.getCourses(); // Adjust according to your actual implementation
            
            // Remove previous content
            mainPanel.removeAll();
    
            if (courses == null || courses.isEmpty()) {
                // Display a message if there are no courses
                JLabel noCoursesMessage = new JLabel("No courses available for this student.", SwingConstants.CENTER);
                noCoursesMessage.setFont(new Font("Verdana", Font.PLAIN, 18));
                noCoursesMessage.setForeground(Color.RED);
                mainPanel.add(noCoursesMessage, BorderLayout.CENTER);
            } else {
                // Display the courses (you may need to adjust based on how courses are displayed)
                JPanel coursesPanel = new JPanel();
                coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS));
                for (Course course : courses) {
                    coursesPanel.add(createLabel(course.getCourseName())); // Adjust if needed for course display
                }
                mainPanel.add(coursesPanel, BorderLayout.CENTER);
            }
    
            // Revalidate and repaint the main panel to reflect changes
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
    

    // Method to handle viewing lessons (this will invoke viewLessons from the Student class)
    private void seeLessons() {
        if (currentStudent != null) {
            currentStudent.viewLessons(); // Calling the viewLessons() method of the Student class
        }
    }

    // Method to handle viewing tutors
    private void seeTutors() {
        if (currentStudent != null) {
            Admin admin = new Admin("username", "password"); // You can replace this with an actual admin instance
            admin.getTutorsForStudentCourses(currentStudent.getId());
        }
    }

    // Method to handle starting a quiz
    private void startQuiz() {
        if (currentStudent != null) {
            // currentStudent.startQuiz();
        }
    }

    // Method to handle viewing grades
    private void viewGrades() {
        if (currentStudent != null) {
            currentStudent.viewQuizResult();
        }
    }

    // Method to handle viewing completed courses
    private void completedCourses() {
        if (currentStudent != null) {
            ArrayList<Course> completedCourses = currentStudent.getCompletedCourses();
            if (completedCourses.isEmpty()) {
                System.out.println("You have not completed any courses yet.");
            } else {
                System.out.println("Completed Courses:");
                completedCourses.forEach(course -> System.out.println(course.getCourseName()));
            }
        }
    }

    // Method to handle viewing not completed courses
    private void notCompletedCourses() {
        if (currentStudent != null) {
            ArrayList<Course> notCompletedCourses = currentStudent.getNotCompletedCourses();
            if (notCompletedCourses.isEmpty()) {
                System.out.println("You have completed all your courses.");
            } else {
                System.out.println("Not Completed Courses:");
                notCompletedCourses.forEach(course -> System.out.println(course.getCourseName()));
            }
        }
    }

    // Method to handle viewing progress
    private void viewProgress() {
        if (currentStudent != null) {
            double progress =0;
            System.out.println("Your progress is: " + progress + "%");
        }
    }

    // Method for logout
    private void logout() {
        System.out.println("Logged out.");
        dispose(); // Close the current frame
    }

    private void readStudentData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String name = "", email = "", phone = "", address = "", password = "", username = "", dateOfBirth = "";
            int schoolID = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    // Collect student information
                    switch (key) {
                        case "name":
                            name = value;
                            break;
                        case "email":
                            email = value;
                            break;
                        case "phone":
                            phone = value;
                            break;
                        case "address":
                            address = value;
                            break;
                        case "password":
                            password = value;
                            break;
                        case "username":
                            username = value;
                            break;
                        case "dateOfBirth":
                            dateOfBirth = value;
                            break;
                        case "schoolID":
                            schoolID = Integer.parseInt(value);
                            break;
                    }

                    // When all fields are collected, create a Student object and add to the list
                    if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !address.isEmpty() && !password.isEmpty() && !username.isEmpty() && !dateOfBirth.isEmpty() && schoolID != 0) {
                        LocalDate dob = LocalDate.parse(dateOfBirth); // Parse dateOfBirth into LocalDate
                        students.add(new Student(name, email, dob, schoolID, phone, address, username, password));
                        // Reset fields for next student
                        name = email = phone = address = password = username = dateOfBirth = "";
                        schoolID = 0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to create a label with given text
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Verdana", Font.PLAIN, 16));
        label.setForeground(new Color(0, 51, 102));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    // Helper method to create styled sidebar buttons
    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setForeground(new Color(0, 51, 102));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(160, 40));
        return button;
    }
}
