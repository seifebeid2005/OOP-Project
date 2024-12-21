import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class LoginPage {
    private static Map<String, String> schoolCredentials = new HashMap<>();
    private static Map<String, String> studentCredentials = new HashMap<>();
    private static Map<String, String> tutorCredentials = new HashMap<>(); // Added map for tutors
    private static final String ADMIN_USERNAME = "admin";  // Admin username
    private static final String ADMIN_PASSWORD = "admin123"; // Admin password
    private static String adminRole = "admin_role"; // Static admin role
    private static Admin admin = new Admin(ADMIN_USERNAME, adminRole); // Make 'admin' static
    
    public static void main(String[] args) {
        // Read school, student, and tutor data from respective files
        readSchoolDataFromFile("tx/school.txt");
        readStudentDataFromFile("tx/student.txt");
        readTutorDataFromFile("tx/tutor.txt"); // Added method call to read tutor data
    
        // Create a new frame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Get screen size for full-screen effect
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setSize(screenSize); // Set the frame to full-screen size
    
        // Make the frame non-resizable (optional)
        frame.setResizable(false);
    
        // Load background image
        ImageIcon backgroundIcon = new ImageIcon("Photos/background.jpg"); // Path to the uploaded image
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height); // Match frame size
        frame.setContentPane(backgroundLabel);
        frame.setLayout(null);
    
        // Calculate vertical center position
        int verticalCenter = screenSize.height / 2;
        int labelHeight = 50; // Approximate height for each label
        int textFieldHeight = 40; // Approximate height for text fields
    
        // Title label (centered horizontally and slightly adjusted vertically)
        JLabel titleLabel = new JLabel("Login", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 32)); // More aesthetic font and size
        titleLabel.setForeground(Color.WHITE); // Light font color for contrast
        titleLabel.setBounds(50, verticalCenter - 150, 400, labelHeight); // Centered with adjusted vertical offset
        backgroundLabel.add(titleLabel);
    
        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Verdana", Font.PLAIN, 24)); // Updated font and size
        usernameLabel.setForeground(Color.WHITE); // Light font color
        usernameLabel.setBounds(50, verticalCenter - 60, 150, 30); // Adjust position closer to vertical center
        backgroundLabel.add(usernameLabel);
    
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Verdana", Font.PLAIN, 18));
        usernameField.setBounds(200, verticalCenter - 60, 300, textFieldHeight); // Align with username label
        backgroundLabel.add(usernameField);
    
        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 24)); // Updated font and size
        passwordLabel.setForeground(Color.WHITE); // Light font color
        passwordLabel.setBounds(50, verticalCenter + 20, 150, 30); // Adjust position closer to vertical center
        backgroundLabel.add(passwordLabel);
    
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 18));
        passwordField.setBounds(200, verticalCenter + 20, 300, textFieldHeight); // Align with password label
        backgroundLabel.add(passwordField);
    
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Verdana", Font.BOLD, 20));
        loginButton.setForeground(new Color(100, 150, 200)); // Darker baby blue
        loginButton.setBounds(50, verticalCenter + 90, 150, 50); // Adjust position closer to vertical center
        backgroundLabel.add(loginButton);
    
        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Verdana", Font.BOLD, 20));
        cancelButton.setForeground(new Color(100, 150, 200)); // Darker baby blue
        cancelButton.setBounds(220, verticalCenter + 90, 150, 50); // Adjust position
        backgroundLabel.add(cancelButton);
    
        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Verdana", Font.BOLD, 20));
        backButton.setBounds(50, screenSize.height - 120, 150, 50); // Positioned at the bottom-left corner
        backgroundLabel.add(backButton);
    
        // Login button action listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
        
                // Check if the username and password match stored credentials
                if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                    JOptionPane.showMessageDialog(frame, "Admin Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Close the login frame
                    new AdminFunctionsGUI(admin, "school.txt", "student.txt", "tutor.txt", "course.txt", "lesson.txt", "quiz.txt", "questions.txt");
                } else if (schoolCredentials.containsKey(username) && schoolCredentials.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "School Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Close the login frame
                    Admin admin = new Admin(ADMIN_USERNAME, ADMIN_PASSWORD); // Assuming Admin is a class with relevant data
                    String SchoolPath = "school.txt";
                    String StudentsPath = "student.txt";
                    String TutorPath = "tutor.txt";
                    String CoursePath = "course.txt";
                    String LessonPath = "lesson.txt";
                    String QuizPath = "quiz.txt";
                    int SchoolId = 1; // Example school ID
                    SchoolDashboardGUI dashboardGUI = new SchoolDashboardGUI(admin, SchoolPath, StudentsPath, TutorPath,
                            CoursePath, LessonPath, QuizPath, QuizPath);
                } // Example SchoolId: 1                } else if (studentCredentials.containsKey(username) && studentCredentials.get(username).equals(password)) {
                    else if (studentCredentials.containsKey(username) && studentCredentials.get(username).equals(password)) {
                        JOptionPane.showMessageDialog(frame, "Student Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose(); // Close the login frame
                        // Redirect to Student Dashboard
                        // You may want to pass student-specific information to StudentDashboardGUI if needed
                        new StudentDashboardGUI(); 
                } else if (tutorCredentials.containsKey(username) && tutorCredentials.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Tutor Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Close the login frame
                    new TutorDashboardGUI(); // Redirect to Tutor Dashboard
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        // Cancel button action listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    
        // Back button action listener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the login page
                HomePage.main(null); // Redirect back to the Home page
            }
        });
    
        // Set frame visibility
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    // Method to read school data from the file and populate the map with username and password
    private static void readSchoolDataFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String currentUsername = null;
            String currentPassword = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Check for username and password entries
                if (line.startsWith("username :")) {
                    currentUsername = line.split(":")[1].trim();
                } else if (line.startsWith("password :")) {
                    currentPassword = line.split(":")[1].trim();
                }

                // When both username and password are found, add them to the map
                if (currentUsername != null && currentPassword != null) {
                    schoolCredentials.put(currentUsername, currentPassword);
                    currentUsername = null; // Reset for the next set of credentials
                    currentPassword = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read student data from the file and populate the map with username and password
    private static void readStudentDataFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String currentUsername = null;
            String currentPassword = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Check for username and password entries
                if (line.startsWith("username :")) {
                    currentUsername = line.split(":")[1].trim();
                } else if (line.startsWith("password :")) {
                    currentPassword = line.split(":")[1].trim();
                }

                // When both username and password are found, add them to the map
                if (currentUsername != null && currentPassword != null) {
                    studentCredentials.put(currentUsername, currentPassword);
                    currentUsername = null; // Reset for the next set of credentials
                    currentPassword = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read tutor data from the file and populate the map with username and password
    private static void readTutorDataFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String currentUsername = null;
            String currentPassword = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Check for username and password entries
                if (line.startsWith("username :")) {
                    currentUsername = line.split(":")[1].trim();
                } else if (line.startsWith("password :")) {
                    currentPassword = line.split(":")[1].trim();
                }

                // When both username and password are found, add them to the map
                if (currentUsername != null && currentPassword != null) {
                    tutorCredentials.put(currentUsername, currentPassword);
                    currentUsername = null; // Reset for the next set of credentials
                    currentPassword = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
