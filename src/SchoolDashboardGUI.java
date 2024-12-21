import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.JTableHeader;


public class SchoolDashboardGUI extends JFrame {
    private JPanel mainPanel;
    private JFrame frame;
    private Admin admin;
    private String pathfile;
    private String pathfile2;
    private String TutorPath;
    private String coursePath;
    private String lessonPath;
    private String quizPath;
    private String questionsPath;

    public SchoolDashboardGUI(Admin admin, String pathfile, String pathfile2, String TutorPath,
            String coursePath, String lessonPath, String quizPath, String questionsPath) {
        this.admin = admin;
        this.pathfile = "tx/School.txt";
        this.pathfile2 = "tx/student.txt";
        this.TutorPath = "tx/tutor.txt";
        this.coursePath = "tx/course.txt";
        this.lessonPath = "tx/lesson.txt";
        this.quizPath = "tx/quiz.txt";
        this.questionsPath = "tx/questions.txt";

        // Set up the frame
        setTitle("Admin Functions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setLayout(new BorderLayout());

        // Sidebar panel
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(0, 51, 102)); // Dark blue
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        // Sidebar buttons
        JButton addButton = createSidebarButton("Add Operations");
        JButton updateButton = createSidebarButton("Update Operations");
        JButton deleteButton = createSidebarButton("Delete Operations");
        JButton viewButton = createSidebarButton("View Records");
        JButton exitButton = createSidebarButton("Exit");

        // Add buttons to sidebar
        sidebar.add(Box.createRigidArea(new Dimension(0, 30))); // Spacer
        sidebar.add(addButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(updateButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(deleteButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(viewButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidebar.add(exitButton);

        // Main panel with background image
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Photos/backgroundimage5.png"); // Replace with your image path
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("WELCOME SCHOOL", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 50));
        welcomeLabel.setForeground(new Color(0, 51, 102));
        welcomeLabel.setBackground(Color.WHITE);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Add panels to frame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // Add action listeners to sidebar buttons
        addButton.addActionListener(e -> showAddOperations());
        updateButton.addActionListener(e -> showUpdateOperations());
        deleteButton.addActionListener(e -> showDeleteOperations());
        viewButton.addActionListener(e -> showViewOperations()); // Replace "SCHOOL_ID_123" with the actual school ID.
        exitButton.addActionListener(e -> {
            System.out.println("Goodbye Admin");
            System.exit(0);
        });

        setVisible(true);
    }

    

        // Helper method to create and style sidebar buttons
        private JButton createSidebarButton(String text) {
            JButton button = new JButton(text);
            button.setFont(new Font("Verdana", Font.BOLD, 14));
            button.setForeground(new Color(0, 51, 102)); // Dark blue text
            button.setBackground(Color.WHITE); // White button background
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(160, 40)); // Standard button size
            return button;
        }
    
        private void updateMainPanel(String title) {
            // Clear the existing content of the main panel
            mainPanel.removeAll();
    
            // Create and update the title
            JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
            titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
            titleLabel.setForeground(new Color(0, 51, 102));
            mainPanel.add(titleLabel, BorderLayout.NORTH);
    
            // Revalidate and repaint the main panel
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    
        private void showAddOperations() {
            updateMainPanel("Add Operations");
    
            // Panel for adding operations
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); // Vertically align form fields
            formPanel.setOpaque(false); // Make the panel background transparent to show the image
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for the form
    
            // Add the selection dropdown for choosing the type of entity to add
            String[] options = { "Select Entity to Add", "Student", "Tutor", "Course" };
            JComboBox<String> entitySelector = new JComboBox<>(options);
            entitySelector.setFont(new Font("Verdana", Font.BOLD, 14));
            entitySelector.setPreferredSize(new Dimension(250, 30));
    
            // Action when user selects an entity type
            entitySelector.addActionListener(e -> {
                String selectedOption = (String) entitySelector.getSelectedItem();
                if (selectedOption != null) {
                    formPanel.removeAll(); // Clear existing form fields
    
                    switch (selectedOption) {
                        case "Student":
                            createStudentForm(formPanel);
                            break;
                        case "Tutor":
                            createTutorForm(formPanel);
                            break;
                        case "Course":
                            createCourseForm(formPanel);
                            break;
                        default:
                            break;
                    }
    
                    // Refresh the panel after changes
                    formPanel.revalidate();
                    formPanel.repaint();
                }
            });
    
            // Add the JComboBox to select entity type
            formPanel.add(entitySelector);
    
            formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
            mainPanel.add(formPanel, BorderLayout.WEST);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    
        private void createStudentForm(JPanel formPanel) {
            formPanel.removeAll();
    
            JTextField nameField = new JTextField(20);
            styleTextField(nameField);
            JTextField emailField = new JTextField(20);
            styleTextField(emailField);
            JTextField phoneField = new JTextField(20);
            styleTextField(phoneField);
            JTextField addressField = new JTextField(20);
            styleTextField(addressField);
            JTextField usernameField = new JTextField(20);
            styleTextField(usernameField);
            JPasswordField passwordField = new JPasswordField();
            passwordField.setFont(new Font("Verdana", Font.BOLD, 14));
            passwordField.setForeground(new Color(0, 51, 102));
            passwordField.setBackground(Color.WHITE);
            passwordField.setPreferredSize(new Dimension(150, 25));
    
            JTextField schoolIDField = new JTextField(20);
            styleTextField(schoolIDField);
            JTextField dobField = new JTextField(20);
            styleTextField(dobField);
    
            JButton createButton = new JButton("Create Student");
            createButton.setFont(new Font("Verdana", Font.BOLD, 14));
            createButton.setForeground(new Color(0, 51, 102));
            createButton.setBackground(Color.WHITE);
    
            createButton.addActionListener(e -> {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                int schoolID = -1;
                LocalDate dateOfBirth = null;
    
                // Validate the fields
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled out.");
                    return;
                }
    
                try {
                    schoolID = Integer.parseInt(schoolIDField.getText());
                    if (schoolID <= 0) {
                        JOptionPane.showMessageDialog(frame, "School ID must be a positive number.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid School ID.");
                    return;
                }
    
                try {
                    dateOfBirth = LocalDate.parse(dobField.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Date format. Use YYYY-MM-DD.");
                    return;
                }
    
                // Create student without terminal/file operation (use GUI-based update)
                Student student = new Student(name, email, dateOfBirth, schoolID, phone, address, username, password);
    
                // Here instead of terminal output, display a confirmation dialog
                JOptionPane.showMessageDialog(frame, "Student account created successfully!");
    
                // Optionally, update the mainPanel to show a list of students, or refresh the form
                formPanel.removeAll();
                formPanel.revalidate();
                formPanel.repaint();
            });

        formPanel.add(createLabel("Enter Name:"));
        formPanel.add(nameField);
        formPanel.add(createLabel("Enter Email:"));
        formPanel.add(emailField);
        formPanel.add(createLabel("Enter Phone:"));
        formPanel.add(phoneField);
        formPanel.add(createLabel("Enter Address:"));
        formPanel.add(addressField);
        formPanel.add(createLabel("Enter Username:"));
        formPanel.add(usernameField);
        formPanel.add(createLabel("Enter Password:"));
        formPanel.add(passwordField);
        formPanel.add(createLabel("Enter School ID:"));
        formPanel.add(schoolIDField);
        formPanel.add(createLabel("Enter Date of Birth (YYYY-MM-DD):"));
        formPanel.add(dobField);
        formPanel.add(createButton);
    }

    private void createTutorForm(JPanel formPanel) {
        JTextField nameField = new JTextField();
        styleTextField(nameField);
        JTextField emailField = new JTextField();
        styleTextField(emailField);
        JTextField phoneField = new JTextField();
        styleTextField(phoneField);
        JTextField addressField = new JTextField();
        styleTextField(addressField);
        JTextField usernameField = new JTextField();
        styleTextField(usernameField);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.BOLD, 14));
        passwordField.setForeground(new Color(0, 51, 102));
        passwordField.setBackground(Color.WHITE);
        passwordField.setPreferredSize(new Dimension(200, 25));

        JTextField subjectAreaField = new JTextField();
        styleTextField(subjectAreaField);
        JTextField roleField = new JTextField();
        styleTextField(roleField);
        JTextField dobField = new JTextField();
        styleTextField(dobField);

        JButton createButton = new JButton("Create Tutor");
        createButton.setFont(new Font("Verdana", Font.BOLD, 14));
        createButton.setForeground(new Color(0, 51, 102));
        createButton.setBackground(Color.WHITE);

        // Ensure that schoolIDField is declared and initialized properly
        JTextField schoolIDField = new JTextField();
        styleTextField(schoolIDField); // You already have this helper method

        // Add the school ID field to the form panel
        formPanel.add(createLabel("Enter School ID:"));
        formPanel.add(schoolIDField);

        createButton.addActionListener(e -> {
            // Retrieve the form field data
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String subjectArea = subjectAreaField.getText();
            LocalDate dateOfBirth = null;
            int tutorSchoolID = -1; // School ID to be entered by the user
            Tutor.Role roleEnum = null; // Tutor role will be set later

            // Validate that all fields are filled out
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || username.isEmpty()
                    || password.isEmpty() || subjectArea.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.");
                return;
            }

            // Validate the date format
            try {
                dateOfBirth = LocalDate.parse(dobField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Date format. Use YYYY-MM-DD.");
                return;
            }

            // Validate the school ID
            try {
                tutorSchoolID = Integer.parseInt(schoolIDField.getText());
                if (tutorSchoolID <= 0) {
                    JOptionPane.showMessageDialog(this, "School ID must be a positive number.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid School ID format.");
                return;
            }

            // Determine the role of the tutor
            String roleInput = roleField.getText().toUpperCase(); // Convert input to uppercase
            switch (roleInput) {
                case "LEAD_TUTOR":
                    roleEnum = Tutor.Role.LEAD_TUTOR;
                    break;
                case "ASSISTANT_TUTOR":
                    roleEnum = Tutor.Role.ASSISTANT_TUTOR;
                    break;
                case "TUTOR":
                    roleEnum = Tutor.Role.TUTOR;
                    break;
                default:
                    JOptionPane.showMessageDialog(this,
                            "Invalid role. Choose from LEAD_TUTOR, ASSISTANT_TUTOR, or TUTOR.");
                    return;
            }

            // Create the Tutor object
            Tutor tutor = new Tutor(name, email, dateOfBirth, phone, address, username, password, roleEnum,
                    tutorSchoolID);

            // Assuming addTutor method exists to add the tutor to the system
            main.Teachercreation(admin, roleInput);
            JOptionPane.showMessageDialog(this, "Tutor account created successfully!");
        });

        formPanel.add(createLabel("Enter Name:"));
        formPanel.add(nameField);
        formPanel.add(createLabel("Enter Email:"));
        formPanel.add(emailField);
        formPanel.add(createLabel("Enter Phone:"));
        formPanel.add(phoneField);
        formPanel.add(createLabel("Enter Address:"));
        formPanel.add(addressField);
        formPanel.add(createLabel("Enter Username:"));
        formPanel.add(usernameField);
        formPanel.add(createLabel("Enter Password:"));
        formPanel.add(passwordField);
        formPanel.add(createLabel("Enter Subject Area:"));
        formPanel.add(subjectAreaField);
        formPanel.add(createLabel("Enter Role:"));
        formPanel.add(roleField);
        formPanel.add(createLabel("Enter Date of Birth (YYYY-MM-DD):"));
        formPanel.add(dobField);
        formPanel.add(createButton);
    }

    private void createCourseForm(JPanel formPanel) {
        JTextField courseNameField = new JTextField();
        styleTextField(courseNameField);
        JTextField courseCodeField = new JTextField();
        styleTextField(courseCodeField);
        JTextField courseDurationField = new JTextField();
        styleTextField(courseDurationField);

        JButton createButton = new JButton("Create Course");
        createButton.setFont(new Font("Verdana", Font.BOLD, 14));
        createButton.setForeground(new Color(0, 51, 102));
        createButton.setBackground(Color.WHITE);

        createButton.addActionListener(e -> {
            String courseName = courseNameField.getText();
            String courseCode = courseCodeField.getText();
            String courseDuration = courseDurationField.getText();

            if (courseName.isEmpty() || courseCode.isEmpty() || courseDuration.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.");
                return;
            }

            // Assuming addCourse method exists
            Course course = new Course(courseName, courseCode, true);
            main.CourseCreation(admin, coursePath, lessonPath, quizPath, questionsPath);
            JOptionPane.showMessageDialog(this, "Course created successfully!");
        });

        formPanel.add(createLabel("Enter Course Name:"));
        formPanel.add(courseNameField);
        formPanel.add(createLabel("Enter Course Code:"));
        formPanel.add(courseCodeField);
        formPanel.add(createLabel("Enter Course Duration:"));
        formPanel.add(courseDurationField);
        formPanel.add(createButton);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Verdana", Font.BOLD, 16)); // Set font to Verdana, bold, size 16
        label.setForeground(new Color(0, 51, 102)); // Set text color to dark blue
        label.setAlignmentX(Component.LEFT_ALIGNMENT); // Align text to the left
        return label;
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Verdana", Font.BOLD, 14)); // Set font to Verdana, bold, size 14
        textField.setForeground(new Color(0, 51, 102)); // Set text color to dark blue
        textField.setBackground(Color.WHITE); // Set background color to white
        textField.setPreferredSize(new Dimension(250, 30)); // Set size of the text field
    }

    
    private void showViewOperations() {
        updateMainPanel("View Operations");

        // Create a panel to hold the records
        JPanel recordsPanel = new JPanel();
        recordsPanel.setLayout(new BoxLayout(recordsPanel, BoxLayout.Y_AXIS)); // Align content vertically
        recordsPanel.setOpaque(true);
        recordsPanel.setBackground(new Color(0, 51, 102)); // Faded dark blue background

        // Add buttons to view each type of record
        JButton viewSchoolButton = new JButton("View Schools");
        viewSchoolButton.setFont(new Font("Verdana", Font.BOLD, 14));
        viewSchoolButton.setForeground(new Color(0, 51, 102)); // Dark blue text
        viewSchoolButton.setBackground(Color.WHITE);
        viewSchoolButton.addActionListener(e -> displayRecords("tx/school.txt", recordsPanel));

        JButton viewStudentButton = new JButton("View Students");
        viewStudentButton.setFont(new Font("Verdana", Font.BOLD, 14));
        viewStudentButton.setForeground(new Color(0, 51, 102)); // Dark blue text
        viewStudentButton.setBackground(Color.WHITE);
        viewStudentButton.addActionListener(e -> displayRecords("tx/student.txt", recordsPanel));

        JButton viewTutorButton = new JButton("View Tutors");
        viewTutorButton.setFont(new Font("Verdana", Font.BOLD, 14));
        viewTutorButton.setForeground(new Color(0, 51, 102)); // Dark blue text
        viewTutorButton.setBackground(Color.WHITE);
        viewTutorButton.addActionListener(e -> displayRecords("tx/tutor.txt", recordsPanel));

        JButton viewCourseButton = new JButton("View Courses");
        viewCourseButton.setFont(new Font("Verdana", Font.BOLD, 14));
        viewCourseButton.setForeground(new Color(0, 51, 102)); // Dark blue text
        viewCourseButton.setBackground(Color.WHITE);
        viewCourseButton.addActionListener(e -> displayRecords("tx/course.txt", recordsPanel));

        JButton viewLessonButton = new JButton("View Lessons");
        viewLessonButton.setFont(new Font("Verdana", Font.BOLD, 14));
        viewLessonButton.setForeground(new Color(0, 51, 102)); // Dark blue text
        viewLessonButton.setBackground(Color.WHITE);
        viewLessonButton.addActionListener(e -> displayRecords("tx/lesson.txt", recordsPanel));

        // Add buttons to the main panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(viewSchoolButton);
        buttonPanel.add(viewStudentButton);
        buttonPanel.add(viewTutorButton);
        buttonPanel.add(viewCourseButton);
        buttonPanel.add(viewLessonButton);

        // Add button panel and records panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(recordsPanel, BorderLayout.CENTER);

        // Revalidate and repaint to update the GUI
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Method to read and display records from the selected file
    private void displayRecords(String fileName, JPanel recordsPanel) {
        // Clear existing content
        recordsPanel.removeAll();

        // Read data from the selected file
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            ArrayList<String[]> data = new ArrayList<>();
            String[] headers = {}; // Initialize with empty headers

            // Read each line and parse it into an array (assuming comma-separated)
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(","); // Assuming CSV format
                data.add(row);
            }
            reader.close();

            // If data is available, set headers and table model
            if (!data.isEmpty()) {
                headers = data.get(0); // The first row will be the headers
                data.remove(0); // Remove header row from data

                // Convert ArrayList to a 2D array for JTable
                String[][] tableData = data.toArray(new String[0][0]);
                String[] tableHeaders = headers;

                // Create the table with the data
                JTable table = new JTable(tableData, tableHeaders);
                table.setFont(new Font("Verdana", Font.PLAIN, 14));
                table.setForeground(Color.WHITE); // White font color
                table.setBackground(new Color(60, 60, 80)); // Lighter dark blue background for better contrast

                // Set the column width, table appearance, etc.
                table.setRowHeight(30); // Adjust row height as needed
                table.setSelectionBackground(new Color(0, 102, 204)); // Selection color (optional)

                // Customize table header to match the row styling
                JTableHeader header = table.getTableHeader();
                header.setFont(new Font("Verdana", Font.BOLD, 14)); // Set the font for the header
                header.setForeground(Color.WHITE); // White font color for the header
                header.setBackground(new Color(60, 60, 80)); // Same background as the rows (lighter dark blue)

                // Wrap the table with a scroll pane
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setPreferredSize(new Dimension(recordsPanel.getWidth() - 20, recordsPanel.getHeight())); // Add padding to the left
                recordsPanel.add(scrollPane);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading the file: " + ex.getMessage());
        }

        // Refresh the records panel to display the table
        recordsPanel.revalidate();
        recordsPanel.repaint();
    }

    



    private void showUpdateOperations() {
        updateMainPanel("Update Operations");

        // Panel for update operations (similar to add operations)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); // Vertically align form fields
        formPanel.setOpaque(false); // Make the panel background transparent
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for the form

        // Add a JComboBox to allow users to select the entity to update
        String[] options = { "Select Entity to Update", "Student", "Tutor" };
        JComboBox<String> entitySelector = new JComboBox<>(options);
        entitySelector.setFont(new Font("Verdana", Font.BOLD, 14));
        entitySelector.setPreferredSize(new Dimension(250, 30));
        entitySelector.setBackground(new Color(0, 51, 102)); // Dark Blue background
        entitySelector.setForeground(Color.WHITE); // White text

        // Action when user selects an entity type
        entitySelector.addActionListener(e -> {
            String selectedOption = (String) entitySelector.getSelectedItem();
            if (selectedOption != null) {
                formPanel.removeAll(); // Clear existing form fields

                // Update form based on selected entity
                switch (selectedOption) {
                    case "Student":
                        createStudentUpdateForm(formPanel);
                        break;
                    case "Tutor":
                        createTutorUpdateForm(formPanel);
                        break;
                    default:
                        break;
                }

                // Refresh the panel after changes
                formPanel.revalidate();
                formPanel.repaint();
            }
        });

        // Add the JComboBox to select entity type
        formPanel.add(createLabel("Select Entity Type to Update:"));
        formPanel.add(entitySelector);

        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Adding the formPanel to the mainPanel with consistent background color
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private void createStudentUpdateForm(JPanel formPanel) {
        JTextField studentIdField = new JTextField();
        styleTextField(studentIdField);
    
        JTextField newNameField = new JTextField();
        styleTextField(newNameField);
    
        JButton updateButton = new JButton("Update Student");
        updateButton.setFont(new Font("Verdana", Font.BOLD, 14));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(0, 51, 102)); // Dark blue button
    
        updateButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String newName = newNameField.getText();
    
            if (studentId.isEmpty() || newName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.");
                return;
            }
    
            updateStudentInformation(studentId, newName);
        });
    
        formPanel.add(createLabel("Enter Student ID:"));
        formPanel.add(studentIdField);
        formPanel.add(createLabel("Enter New Student Name:"));
        formPanel.add(newNameField);
        formPanel.add(updateButton);
    }    
    

    private void createTutorUpdateForm(JPanel formPanel) {
        JTextField tutorIdField = new JTextField();
        styleTextField(tutorIdField);

        JTextField newNameField = new JTextField();
        styleTextField(newNameField);

        JButton updateButton = new JButton("Update Tutor");
        updateButton.setFont(new Font("Verdana", Font.BOLD, 14));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(0, 51, 102)); // Dark blue button

        updateButton.addActionListener(e -> {
            String tutorId = tutorIdField.getText();
            String newName = newNameField.getText();

            if (tutorId.isEmpty() || newName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.");
                return;
            }

            updateTutorInformation(tutorId, newName);
        });

        formPanel.add(createLabel("Enter Tutor ID:"));
        formPanel.add(tutorIdField);
        formPanel.add(createLabel("Enter New Tutor Name:"));
        formPanel.add(newNameField);
        formPanel.add(updateButton);
    }
    
    private void updateStudentInformation(String studentId, String newName) {
        try {
            // Assuming admin.updateStudentById() is implemented properly for the student update logic
            admin.updateStudentById();  // Ensure this method does not use terminal operations
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to update student. Please check the ID and try again.");
        }
    }
    
    
    private void updateTutorInformation(String tutorId, String newName) {
        try {
            // Assuming admin.updateTutorById() is implemented for tutor data update logic
            admin.updateTutorById();  // Ensure no terminal-related methods are invoked here
            JOptionPane.showMessageDialog(this, "Tutor updated successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to update tutor. Please check the ID and try again.");
        }
    }
    
    private void showDeleteOperations() {
        updateMainPanel("Delete Operations");

        // Panel for delete operations
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); // Vertically align form fields
        formPanel.setOpaque(false); // Make the panel background transparent to show the image
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for the form

        // Create a combo box to choose the type of entity to delete
        String[] entityOptions = { "Student", "Tutor", "School", "Course" };
        JComboBox<String> entityComboBox = new JComboBox<>(entityOptions);
        entityComboBox.setFont(new Font("Verdana", Font.BOLD, 14));
        entityComboBox.setBackground(new Color(0, 51, 102)); // Set dark blue background
        entityComboBox.setForeground(Color.WHITE); // Set white text color

        // Create a text field for the ID input (Student ID, Tutor ID, etc.)
        JTextField idField = new JTextField();
        styleTextField(idField);

        // Button to delete the entity
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Verdana", Font.BOLD, 14));
        deleteButton.setForeground(new Color(0, 51, 102));
        deleteButton.setBackground(Color.WHITE);

        // Action for delete button
        deleteButton.addActionListener(e -> {
            String entityType = (String) entityComboBox.getSelectedItem();
            String idText = idField.getText();

            // Check if the ID is empty
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an ID.");
                return;
            }

            try {
                int id = Integer.parseInt(idText); // Parse the ID to an integer
                if (id <= 0) {
                    JOptionPane.showMessageDialog(this, "ID must be a positive number.");
                    return;
                }

                // Handle deletion based on the selected entity type
                switch (entityType) {
                    case "Student":
                        deleteStudent(id);
                        break;
                    case "Tutor":
                        deleteTutor(id);
                        break;
                    case "Course":
                        deleteCourse(id);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Invalid entity type.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format.");
            }
        });

        // Add components to the form
        formPanel.add(createLabel("Select Entity to Delete:"));
        formPanel.add(entityComboBox);
        formPanel.add(createLabel("Enter ID of Entity:"));
        formPanel.add(idField);
        formPanel.add(deleteButton);

        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Example methods for deleting entities (you need to implement these in your class)
    private void deleteStudent(int studentId) {
        // Implement logic to delete a student by ID
        // You might have a list or database where the students are stored
        System.out.println("Deleting student with ID: " + studentId);
        // Add code to delete the student from your data storage
    }

    private void deleteTutor(int tutorId) {
        // Implement logic to delete a tutor by ID
        System.out.println("Deleting tutor with ID: " + tutorId);
        // Add code to delete the tutor from your data storage
    }

    
    private void deleteCourse(int courseId) {
        // Implement logic to delete a course by ID
        System.out.println("Deleting course with ID: " + courseId);
        // Add code to delete the course from your data storage
    }

}
