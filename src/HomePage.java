import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomePage {
    public static void main(String[] args) {
        // Create a new frame
        JFrame frame = new JFrame("Welcome to Code Tomorrow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get screen size for full-screen effect
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setSize(screenSize); // Set the frame to full-screen size

        // Make the frame non-resizable (optional)
        frame.setResizable(false);

        // Load background image
        ImageIcon backgroundIcon = new ImageIcon("Photos/background-2.jpg"); // Path to the uploaded image
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height); // Match frame size
        frame.setContentPane(backgroundLabel);
        frame.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Welcome to Code Tomorrow", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 100, screenSize.width, 100);
        backgroundLabel.add(titleLabel);

        // Button dimensions
        int buttonWidth = 200;
        int buttonHeight = 60;
        int horizontalCenter = screenSize.width / 2;
        int verticalStart = 300;
        int verticalSpacing = 100;

        // Tutor button
        JButton tutorButton = new JButton("Tutor");
        tutorButton.setFont(new Font("Verdana", Font.BOLD, 24));
        tutorButton.setBounds(horizontalCenter - buttonWidth / 2, verticalStart, buttonWidth, buttonHeight);
        backgroundLabel.add(tutorButton);

        // Student button
        JButton studentButton = new JButton("Student");
        studentButton.setFont(new Font("Verdana", Font.BOLD, 24));
        studentButton.setBounds(horizontalCenter - buttonWidth / 2, verticalStart + verticalSpacing, buttonWidth, buttonHeight);
        backgroundLabel.add(studentButton);

        // Admin button
        JButton adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Verdana", Font.BOLD, 24));
        adminButton.setBounds(horizontalCenter - buttonWidth / 2, verticalStart + 2 * verticalSpacing, buttonWidth, buttonHeight);
        backgroundLabel.add(adminButton);

        // School button
        JButton schoolButton = new JButton("School");
        schoolButton.setFont(new Font("Verdana", Font.BOLD, 24));
        schoolButton.setBounds(horizontalCenter - buttonWidth / 2, verticalStart + 3 * verticalSpacing, buttonWidth, buttonHeight);
        backgroundLabel.add(schoolButton);

        // Action listener for buttons to navigate to the login page
        ActionListener navigateToLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the homepage
                LoginPage.main(null); // Call the LoginPage class
            }
        };

        tutorButton.addActionListener(navigateToLogin);
        studentButton.addActionListener(navigateToLogin);
        adminButton.addActionListener(navigateToLogin);

        // Action listener for the School button
        schoolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "School button clicked!", "Info", JOptionPane.INFORMATION_MESSAGE);
                // Replace with navigation logic or functionality for the "School" button
            }
        });

        // Set frame visibility
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
