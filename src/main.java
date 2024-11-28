/*import classes.Student.Student;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {    
        Scanner scanner = new Scanner(System.in);

        // Collecting basic user information
        String name = promptUserInput(scanner, "Enter name: ");
        String email = promptUserInput(scanner, "Enter email: ");
        String preferredLanguage = promptUserInput(scanner, "Enter preferred language: ");
        Integer currentLevel = promptIntegerInput(scanner, "Enter current level: ");
        Integer progressLevel = promptIntegerInput(scanner, "Enter progress level (0-100): ");
        String achievements = promptUserInput(scanner, "Enter achievements (or leave empty): ");
        Integer schoolID = promptIntegerInput(scanner, "Enter school ID: ");
        LocalDate dateOfBirth = promptDateInput(scanner, "Enter date of birth (yyyy-MM-dd): ");
        String phone = promptUserInput(scanner, "Enter phone (10 digits): ");
        String address = promptUserInput(scanner, "Enter address: ");
        String username = promptUserInput(scanner, "Enter username: ");
        String password = promptUserInput(scanner, "Enter password (min 8 characters): ");
        
        try {
            // Create a Student object using the validated inputs
            Student student = new Student(name, email, preferredLanguage, currentLevel, progressLevel, achievements, 
                                          schoolID, dateOfBirth, LocalDateTime.now(), phone, address, username, password);
            System.out.println("Student created successfully: " + student);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close(); // Close scanner to avoid resource leak
    }

    // Method to prompt user input and handle invalid inputs
    private static String promptUserInput(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // Method to prompt for an integer and handle invalid inputs
    private static Integer promptIntegerInput(Scanner scanner, String prompt) {
        Integer input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.valueOf(scanner.nextLine());
                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid integer.");
            }
        }
    }

    // Method to prompt for a date input in 'yyyy-MM-dd' format
    private static LocalDate promptDateInput(Scanner scanner, String prompt) {
        LocalDate date;
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (!date.isAfter(LocalDate.now())) {
                    return date;
                } else {
                    System.out.println("Date of birth cannot be in the future. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }
}*/

import java.time.LocalDate;

public class main {

    public static void main(String[] args) {
        //    public Tutor(Long id, String name, String email, LocalDate dateOfBirth, String phone, String address, String username, String password,
        //    String subjectArea, LocalDate dateJoined, String role, int schoolID)
        // Create a Tutor instance
        Tutor tutor = new Tutor(
                1L,
                "John Doe",
                "seif@gmail.com",
                LocalDate.of(1990, 1, 1),
                "1234567890",
                "123 Elm St",
                "johndoe",
                "123456789kjhbr!@SStu56e",
                        "Math",
                LocalDate.of(2020, 1, 1),
                "Lead Tutor",
                1
        );

        // Print Tutor details
        System.out.println(tutor);

        // Print Tutor's years of experience
        System.out.println("Years of Experience: " + tutor.getYearsOfExperience());
    }
}
