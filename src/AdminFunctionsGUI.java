import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.JTableHeader;


public class AdminFunctionsGUI extends JFrame {
    private Admin admin;
    private String pathfile, pathfile2, TutorPath, coursepath, lessonpath, quizpath, QuestionsPath;
    private JPanel mainPanel;

    public AdminFunctionsGUI(Admin admin, String pathfile, String pathfile2, String TutorPath, String coursepath,
                         String lessonpath, String quizpath, String QuestionsPath) {
    this.admin = admin;
    this.pathfile = pathfile;
    this.pathfile2 = pathfile2;
    this.TutorPath = TutorPath;
    this.coursepath = coursepath;
    this.lessonpath = lessonpath;
    this.quizpath = quizpath;
    this.QuestionsPath = QuestionsPath;

    // Setup Frame
    setTitle("Admin Functions");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
    setLayout(new BorderLayout());

    // Sidebar
    JPanel sidebar = new JPanel();
    sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
    sidebar.setBackground(new Color(0, 51, 102)); // Dark Blue
    sidebar.setPreferredSize(new Dimension(200, getHeight()));

    // Sidebar Buttons
    JButton addButton = createSidebarButton("Add Operations");
    JButton updateButton = createSidebarButton("Update Operations");
    JButton deleteButton = createSidebarButton("Delete Operations");
    JButton viewButton = createSidebarButton("View Records");
    JButton exitButton = createSidebarButton("Exit");

    // Add Buttons to Sidebar
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

    // Main Panel with Background Image
    mainPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                ImageIcon backgroundImage = new ImageIcon("Photos/backgroundimage4.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            } catch (Exception e) {
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
                System.out.println("Background image not found.");
            }
        }
    };
    mainPanel.setLayout(new BorderLayout());

    JLabel welcomeLabel = new JLabel("WELCOME ADMIN", SwingConstants.CENTER);
    welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 50));
    welcomeLabel.setForeground(new Color(0, 51, 102));
    mainPanel.add(welcomeLabel, BorderLayout.CENTER);

    // Add Panels to Frame
    add(sidebar, BorderLayout.WEST);
    add(mainPanel, BorderLayout.CENTER);

    // Add Action Listeners
    addButton.addActionListener(e -> showAddOperations());
    updateButton.addActionListener(e -> showUpdateOperations());
    deleteButton.addActionListener(e -> showDeleteOperations());
    viewButton.addActionListener(e -> showViewOperations());
    exitButton.addActionListener(e -> {
        System.out.println("Goodbye Admin");
        System.exit(0);
    });

    setVisible(true);
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
    

    private void updateMainPanel(String title) {
        mainPanel.removeAll();
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0, 51, 102));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    

    // Method to display Add Operations in the main panel
    private void showAddOperations() {
        updateMainPanel("Add Operations");
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        String[] options = {"Select Entity to Add", "School", "Student", "Tutor", "Course"};
        JComboBox<String> entitySelector = new JComboBox<>(options);
        entitySelector.setFont(new Font("Verdana", Font.BOLD, 14));
        formPanel.add(createLabel("Select Entity Type to Add:"));
        formPanel.add(entitySelector);
    
        entitySelector.addActionListener(e -> {
            String selectedOption = (String) entitySelector.getSelectedItem();
            formPanel.removeAll();
            switch (selectedOption) {
                case "School":
                    createSchoolForm(formPanel);
                    break;
                case "Student":
                    createStudentForm(formPanel);
                    break;
                case "Tutor":
                    createTutorForm(formPanel);
                    break;
                case "Course":
                    createCourseForm(formPanel);
                    break;
            }
            formPanel.revalidate();
            formPanel.repaint();
        });
    
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    

    private void createSchoolForm(JPanel formPanel) {
        JTextField nameField = new JTextField();
        styleTextField(nameField);
        JTextField addressField = new JTextField();
        styleTextField(addressField);
        JTextField cityField = new JTextField();
        styleTextField(cityField);
        JTextField contactPersonField = new JTextField();
        styleTextField(contactPersonField);
        JTextField emailField = new JTextField();
        styleTextField(emailField);
        JTextField phoneField = new JTextField();
        styleTextField(phoneField);
        JTextField usernameField = new JTextField();
        styleTextField(usernameField);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.BOLD, 14));
        passwordField.setForeground(new Color(0, 51, 102));
        passwordField.setBackground(Color.WHITE);
        passwordField.setPreferredSize(new Dimension(200, 25));

        JButton createButton = new JButton("Create School");
        createButton.setFont(new Font("Verdana", Font.BOLD, 14));
        createButton.setForeground(new Color(0, 51, 102));
        createButton.setBackground(Color.WHITE);

        createButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String city = cityField.getText();
            String contactPerson = contactPersonField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Validate and create School account
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || username.isEmpty()
                    || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.");
                return;
            }

            // Assuming createSchool method exists to create a school
            School school = new School(name, address, city, contactPerson, email, phone, username, password);
            main.Schoolcreation(admin, pathfile);
            JOptionPane.showMessageDialog(this, "School account created successfully!");
        });

        formPanel.add(createLabel("Enter School Name:"));
        formPanel.add(nameField);
        formPanel.add(createLabel("Enter Address:"));
        formPanel.add(addressField);
        formPanel.add(createLabel("Enter City:"));
        formPanel.add(cityField);
        formPanel.add(createLabel("Enter Contact Person:"));
        formPanel.add(contactPersonField);
        formPanel.add(createLabel("Enter Email:"));
        formPanel.add(emailField);
        formPanel.add(createLabel("Enter Phone:"));
        formPanel.add(phoneField);
        formPanel.add(createLabel("Enter Username:"));
        formPanel.add(usernameField);
        formPanel.add(createLabel("Enter Password:"));
        formPanel.add(passwordField);
        formPanel.add(createButton);
    }

    private void createStudentForm(JPanel formPanel) {
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

        JTextField schoolIDField = new JTextField();
        styleTextField(schoolIDField);
        JTextField dobField = new JTextField();
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

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || username.isEmpty()
                    || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.");
                return;
            }

            try {
                schoolID = Integer.parseInt(schoolIDField.getText());
                if (schoolID <= 0) {
                    JOptionPane.showMessageDialog(this, "School ID must be a positive number.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid School ID.");
                return;
            }

            try {
                dateOfBirth = LocalDate.parse(dobField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Date format. Use YYYY-MM-DD.");
                return;
            }

            // Assuming addStudent method exists
            Student student = new Student(name, email, dateOfBirth, schoolID, phone, address, username, password);
            main.Studentcreation(admin, pathfile2);
            JOptionPane.showMessageDialog(this, "Student account created successfully!");
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
            main.CourseCreation(admin, coursepath, lessonpath, quizpath, QuestionsPath);
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
        label.setFont(new Font("Verdana", Font.BOLD, 14));
        label.setForeground(new Color(0, 51, 102));
        return label;
    }
    

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Verdana", Font.BOLD, 14));
        textField.setForeground(new Color(0, 51, 102));
        textField.setBackground(Color.WHITE);
        textField.setPreferredSize(new Dimension(250, 30));
    }
    

    // Method to show update operations (you can modify it as needed)
    private void showUpdateOperations() {
        updateMainPanel("Update Operations");
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        String[] options = {"Select Entity to Update", "School", "Student", "Tutor"};
        JComboBox<String> entitySelector = new JComboBox<>(options);
        entitySelector.setFont(new Font("Verdana", Font.BOLD, 14));
        formPanel.add(createLabel("Select Entity to Update:"));
        formPanel.add(entitySelector);
    
        entitySelector.addActionListener(e -> {
            String selectedOption = (String) entitySelector.getSelectedItem();
            formPanel.removeAll();
            switch (selectedOption) {
                case "School":
                    createSchoolUpdateForm(formPanel);
                    break;
                case "Student":
                    createStudentUpdateForm(formPanel);
                    break;
                case "Tutor":
                    createTutorUpdateForm(formPanel);
                    break;
            }
            formPanel.revalidate();
            formPanel.repaint();
        });
    
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    

    private void createSchoolUpdateForm(JPanel formPanel) {
        JTextField schoolIdField = new JTextField();
        styleTextField(schoolIdField);
    
        JTextField newNameField = new JTextField();
        styleTextField(newNameField);
    
        JButton updateButton = new JButton("Update School");
        updateButton.setFont(new Font("Verdana", Font.BOLD, 14));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(0, 51, 102)); // Dark blue button
    
        updateButton.addActionListener(e -> {
            String schoolId = schoolIdField.getText();
            String newName = newNameField.getText();
    
            if (schoolId.isEmpty() || newName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.");
                return;
            }
    
            updateSchoolInformation(schoolId, newName);
        });
    
        formPanel.add(createLabel("Enter School ID:"));
        formPanel.add(schoolIdField);
        formPanel.add(createLabel("Enter New School Name:"));
        formPanel.add(newNameField);
        formPanel.add(updateButton);
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
    
    

    private void updateSchoolInformation(String schoolId, String newName) {
        try {
            // Assuming admin.updateSchoolByCriteria() is already implemented to update records
            admin.updateSchoolByCriteria();  // Ensure this method handles file or DB updates, not terminal interaction
            JOptionPane.showMessageDialog(this, "School updated successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to update school. Please check the ID and try again.");
        }
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
    
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        String[] options = {"School", "Student", "Tutor", "Course"};
        JComboBox<String> entitySelector = new JComboBox<>(options);
        entitySelector.setFont(new Font("Verdana", Font.BOLD, 14));
    
        JLabel nameLabel = createLabel("Enter Entity Name:"); // Dynamic label
        JTextField nameField = new JTextField(); // Input field
        styleTextField(nameField);
    
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Verdana", Font.BOLD, 14));
        deleteButton.setForeground(new Color(0, 51, 102));
        deleteButton.setBackground(Color.WHITE);
    
        // Update the label dynamically based on the selected entity type
        entitySelector.addActionListener(e -> {
            String selectedOption = (String) entitySelector.getSelectedItem();
            if (selectedOption.equals("School") || selectedOption.equals("Course")) {
                nameLabel.setText("Enter Entity Name:");
            } else if (selectedOption.equals("Student") || selectedOption.equals("Tutor")) {
                nameLabel.setText("Enter Phone Number:");
            }
        });
    
        // Add ActionListener to the deleteButton
        deleteButton.addActionListener(e -> {
            String entityType = (String) entitySelector.getSelectedItem();
            String input = nameField.getText().trim();
    
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid value.");
                return;
            }
    
            // Determine the file path for the selected entity type
            String filePath = getFilePathForEntity(entityType);
            if (filePath == null) {
                JOptionPane.showMessageDialog(this, "Invalid entity type.");
                return;
            }
    
            // Attempt to delete the entity
            boolean success = false;
            if (entityType.equals("School") || entityType.equals("Course")) {
                // Delete by name for schools and courses
                success = deleteEntityFromFile(filePath, input);
            } else if (entityType.equals("Student") || entityType.equals("Tutor")) {
                // Delete by phone number for students and tutors
                success = deleteEntityFromFileByPhone(filePath, input);
            }
    
            if (success) {
                JOptionPane.showMessageDialog(this, entityType + " deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, entityType + " not found with the given input.");
            }
        });
    
        // Add components to the formPanel
        formPanel.add(createLabel("Select Entity to Delete:"));
        formPanel.add(entitySelector);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(deleteButton);
    
        // Add formPanel to the mainPanel
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    


    private void writeListToFile(ArrayList<String> dataList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : dataList) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage());
        }
    }
    

    private ArrayList<String> loadArrayListForEntity(String entityType) {
        String filePath = getFilePathForEntity(entityType);
        if (filePath == null) {
            return null;
        }
        return loadArrayListFromFile(filePath);
    }
    
    

    private ArrayList<String> loadArrayListFromFile(String filePath) {
        ArrayList<String> dataList = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dataList.add(line.trim()); // Add each line to the list
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading from file: " + e.getMessage());
        }
    
        return dataList;
    }
    
    
    private String getFilePathForEntity(String entityType) {
        switch (entityType) {
            case "School":
                return pathfile;
            case "Student":
                return pathfile2;
            case "Tutor":
                return TutorPath;
            case "Course":
                return coursepath;
            default:
                return null;
        }
    }
         
    

    private boolean deleteEntityFromListByName(ArrayList<String> dataList, String entityName) {
        boolean isBlockFound = false; // Flag to track if a block starts
        int startIndex = -1; // Track the start of the block to delete
    
        for (int i = 0; i < dataList.size(); i++) {
            String line = dataList.get(i).trim(); // Trim leading/trailing spaces
    
            // Identify the start of the block based on the name
            if (line.toLowerCase().contains(entityName.toLowerCase())) {
                startIndex = i; // Mark where the block starts
                isBlockFound = true;
                break;
            }
        }
    
        // If a block is found, delete the entire block
        if (isBlockFound && startIndex != -1) {
            // Remove all lines until a blank line or end of list
            while (startIndex < dataList.size() && !dataList.get(startIndex).trim().isEmpty()) {
                dataList.remove(startIndex);
            }
            return true; // Successfully deleted
        }
    
        return false; // Name not found
    }
    
    
    private boolean deleteEntityFromFile(String filePath, String name) {
        File file = new File(filePath);
        File tempFile = new File(filePath + "_temp");
        boolean deleted = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
    
            String line;
            boolean isBlockFound = false;
    
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(name.toLowerCase())) {
                    isBlockFound = true; // Start of the block
                }
    
                if (isBlockFound && line.trim().isEmpty()) {
                    isBlockFound = false; // End of the block
                    deleted = true; // Mark as deleted
                    continue; // Skip writing the blank line
                }
    
                if (!isBlockFound) {
                    writer.write(line);
                    writer.newLine(); // Write non-deleted lines
                }
            }
    
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error processing file: " + e.getMessage());
            return false;
        }
    
        if (deleted) {
            if (!file.delete() || !tempFile.renameTo(file)) {
                JOptionPane.showMessageDialog(this, "Error replacing original file after deletion.");
                return false;
            }
            return true;
        } else {
            tempFile.delete(); // Cleanup if no block was deleted
            JOptionPane.showMessageDialog(this, "Name \"" + name + "\" not found in the file.");
            return false;
        }
    }
    
    
    private boolean deleteEntityFromFileByPhone(String filePath, String phoneNumber) {
        File file = new File(filePath);
        File tempFile = new File(filePath + "_temp");
        boolean deleted = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
    
            String line;
            StringBuilder blockBuffer = new StringBuilder();
            boolean isInBlock = false;  // Tracks if we are inside a block
            boolean shouldDeleteBlock = false; // Tracks if the current block should be deleted
    
            while ((line = reader.readLine()) != null) {
                // Detect the start of a block
                if (!line.trim().isEmpty() && !isInBlock) {
                    isInBlock = true; // New block starts
                    shouldDeleteBlock = false; // Reset delete flag
                    blockBuffer.setLength(0); // Clear previous block data
                }
    
                // Append the current line to the block buffer
                blockBuffer.append(line).append(System.lineSeparator());
    
                // Check if the block should be deleted
                if (line.toLowerCase().contains(phoneNumber.toLowerCase())) {
                    shouldDeleteBlock = true;
                }
    
                // Detect the end of a block
                if (line.trim().isEmpty()) {
                    isInBlock = false; // Block ends
    
                    // Write the block to the temporary file only if it shouldn't be deleted
                    if (!shouldDeleteBlock) {
                        writer.write(blockBuffer.toString());
                    } else {
                        deleted = true; // Mark that a block was deleted
                    }
                }
            }
    
            // Handle the last block if the file doesn't end with a blank line
            if (isInBlock) {
                if (!shouldDeleteBlock) {
                    writer.write(blockBuffer.toString());
                } else {
                    deleted = true;
                }
            }
    
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error processing file: " + e.getMessage());
            return false;
        }
    
        // Replace the original file with the temporary file if a deletion occurred
        if (deleted) {
            if (!file.delete() || !tempFile.renameTo(file)) {
                JOptionPane.showMessageDialog(null, "Error replacing the original file after deletion.");
                return false;
            }
            return true;
        } else {
            tempFile.delete(); // Cleanup if no block was deleted
            JOptionPane.showMessageDialog(null, "Phone number \"" + phoneNumber + "\" not found in the file.");
            return false;
        }
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

    private void deleteSchool(int schoolId) {
        // Implement logic to delete a school by ID
        System.out.println("Deleting school with ID: " + schoolId);
        // Add code to delete the school from your data storage
    }

    private void deleteCourse(int courseId) {
        // Implement logic to delete a course by ID
        System.out.println("Deleting course with ID: " + courseId);
        // Add code to delete the course from your data storage
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
        // Clear existing content in the panel
        recordsPanel.removeAll();
    
        try {
            // Open the file and read its content
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
    
            String line;
            ArrayList<String[]> rows = new ArrayList<>();
            String[] headers = null;
    
            // Determine headers based on file type
            if (fileName.equals("tx/tutor.txt")) {
                headers = new String[]{"Tutor Name", "Email", "Phone", "Address", "Username", "Password",  "Date of Birth","Subject Area" ,  "Role", "School ID"};
            } else if (fileName.equals("tx/student.txt")) {
                headers = new String[]{"Student Name", "Email", "Phone", "Address", "Username", "Password", "School ID", "Date of Birth"};
            } else if (fileName.equals("tx/school.txt")) {
                headers = new String[]{"School Name", "Address", "City", "Contact Person", "Email", "Phone", "Username", "Password"};
            } else if (fileName.equals("tx/course.txt")) {
                headers = new String[]{"Course Name", "Course Code", "Course Duration"};
            } else if (fileName.equals("tx/lesson.txt")) {
                headers = new String[]{"Lesson Name", "Lesson Code", "Lesson Duration"};
            }
    
            // Read the file and parse data into rows
            String[] currentRow = new String[headers.length]; // Prepare row matching header length
            int index = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    if (index > 0) {
                        rows.add(currentRow.clone()); // Add the completed row
                        currentRow = new String[headers.length]; // Reset row
                        index = 0;
                    }
                    continue;
                }
    
                // Parse key-value pair
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    if (index < headers.length) {
                        currentRow[index] = parts[1].trim(); // Fill the row data
                        index++;
                    }
                }
            }
    
            // Add the last row if applicable
            if (index > 0) {
                rows.add(currentRow);
            }
            reader.close();
    
            // Convert rows to 2D array
            String[][] tableData = rows.toArray(new String[0][0]);
    
            // Create JTable
            JTable table = new JTable(tableData, headers) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make table cells non-editable
                }
            };
    
            // Table Customization
            table.setFont(new Font("Verdana", Font.PLAIN, 14));
            table.setForeground(Color.WHITE);
            table.setBackground(new Color(45, 45, 60));
            table.setGridColor(new Color(80, 80, 100));
            table.setRowHeight(30);
    
            // Header Customization
            JTableHeader tableHeader = table.getTableHeader();
            tableHeader.setFont(new Font("Verdana", Font.BOLD, 16));
            tableHeader.setForeground(Color.WHITE);
            tableHeader.setBackground(new Color(0, 120, 215));
    
            // Wrap the table in a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(null); // Remove border for cleaner look
    
            // Create a header label for the top of the page
            JLabel headerLabel = new JLabel(getHeaderTitle(fileName), SwingConstants.CENTER);
            headerLabel.setFont(new Font("Verdana", Font.BOLD, 24));
            headerLabel.setForeground(Color.WHITE);
    
            // Create a layout for the recordsPanel
            recordsPanel.setLayout(new BorderLayout());
            recordsPanel.add(headerLabel, BorderLayout.NORTH);
            recordsPanel.add(scrollPane, BorderLayout.CENTER);
    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading the file: " + ex.getMessage());
        }
    
        // Refresh the records panel
        recordsPanel.revalidate();
        recordsPanel.repaint();
    }
    
    private String getHeaderTitle(String fileName) {
        switch (fileName) {
            case "tx/tutor.txt":
                return "Tutor Names";
            case "tx/student.txt":
                return "Student Names";
            case "tx/school.txt":
                return "School Names";
            case "tx/course.txt":
                return "Course Titles";
            case "tx/lesson.txt":
                return "Lesson Titles";
            default:
                return "Records";
        }
    }
    
    
    
    
    
    

}