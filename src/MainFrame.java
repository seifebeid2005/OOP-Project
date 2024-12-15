import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private JButton adminButton, schoolButton, studentButton;
    private JPanel contentPanel;

    public MainFrame() {
        // Set frame properties
        setTitle("Welcome to Sign In");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create a content panel with GridBagLayout
        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Set a background image
        contentPanel.setPreferredSize(new Dimension(600, 400)); // Set preferred size to match frame size
        contentPanel.setLayout(new BorderLayout());  // Set layout to BorderLayout for central components

        // Add a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Educational Center", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Admin Button
        adminButton = new JButton("Sign in as Admin");
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAdminPage();
            }
        });
        buttonPanel.add(adminButton);

        // School Button
        schoolButton = new JButton("Sign in as School");
        schoolButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSchoolPage();
            }
        });
        buttonPanel.add(schoolButton);

        // Student Button
        studentButton = new JButton("Sign in as Student");
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openStudentPage();
            }
        });
        buttonPanel.add(studentButton);

        // Add buttonPanel to the center of the contentPanel
        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add contentPanel to the frame
        setContentPane(contentPanel);
    }

    // Open Admin page
    private void openAdminPage() {
        this.dispose();
        JOptionPane.showMessageDialog(this, "Welcome Admin", "Admin Page", JOptionPane.INFORMATION_MESSAGE);
        
    }

    // Open School page
    private void openSchoolPage() {

        JOptionPane.showMessageDialog(this, "Welcome School", "School Page", JOptionPane.INFORMATION_MESSAGE);
    }

    // Open Student page
    private void openStudentPage() {
        JOptionPane.showMessageDialog(this, "Welcome Student", "Student Page", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}
