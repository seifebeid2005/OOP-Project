import java.awt.*;
import javax.swing.*;

public class TutorDashboardGUI extends JFrame {
    public TutorDashboardGUI() {
        // Set up frame
        setTitle("Tutor Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0, 51, 102)); // Faded dark blue background

        // Title label
        JLabel titleLabel = new JLabel("Tutor Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20)); // 2 rows, 2 columns with spacing
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Padding

        // Buttons
        JButton addTutorButton = createStyledButton("Add Tutor");
        JButton updateTutorButton = createStyledButton("Update Tutor");
        JButton deleteTutorButton = createStyledButton("Delete Tutor");
        JButton viewTutorsButton = createStyledButton("View Tutors");

        // Add buttons to panel
        buttonPanel.add(addTutorButton);
        buttonPanel.add(updateTutorButton);
        buttonPanel.add(deleteTutorButton);
        buttonPanel.add(viewTutorsButton);

        // Add components to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set main panel to frame
        add(mainPanel);

        // Button action listeners
        addTutorButton.addActionListener(e -> showAddTutorDialog());
        updateTutorButton.addActionListener(e -> showUpdateTutorDialog());
        deleteTutorButton.addActionListener(e -> showDeleteTutorDialog());
        viewTutorsButton.addActionListener(e -> showViewTutors());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setForeground(new Color(0, 51, 102)); // Dark blue text
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2)); // Blue border
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 153, 255)); // Light blue hover
                button.setForeground(Color.WHITE); // Text turns white
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(0, 51, 102));
            }
        });
        return button;
    }

    private void showAddTutorDialog() {
        JOptionPane.showMessageDialog(this, "Add Tutor functionality coming soon!", "Add Tutor", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showUpdateTutorDialog() {
        JOptionPane.showMessageDialog(this, "Update Tutor functionality coming soon!", "Update Tutor", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showDeleteTutorDialog() {
        JOptionPane.showMessageDialog(this, "Delete Tutor functionality coming soon!", "Delete Tutor", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showViewTutors() {
        JOptionPane.showMessageDialog(this, "View Tutors functionality coming soon!", "View Tutors", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
