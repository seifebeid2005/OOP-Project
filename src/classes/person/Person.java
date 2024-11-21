/*For the Admin, User, and Tutor classes 
we have a lot of similarities in the attributes. 
We can create a superclass called Human and have the Admin, User, and Tutor classes inherit 
from the Human class. This way we can avoid code duplication and make our code more maintainable.*/
//UML Diagram

package classes.person;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Person {
    private Long id;
    private String name;
    private String email;
    private Date DateAdded;
    private LocalDate dateOfBirth;
    private String phone;
    private String address;

    public Person(Long id, String name, String email, LocalDate dateOfBirth, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.DateAdded = new Date();
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Setter for email with validation
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    // Method to validate email
    private boolean isValidEmail(String email) {
        // Regex for a valid email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // Use Pattern.matches to check the email against the regex
        return Pattern.matches(emailRegex, email);
    }

    // Method to prompt user for email and validate it
    public void inputEmail() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a valid email address: ");
            String inputEmail = scanner.nextLine();

            if (isValidEmail(inputEmail)) {
                this.email = inputEmail;
                System.out.println("Email successfully set!");
                break;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }
    }
    public Date getDateAdded() {
        return DateAdded;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public void setDateAdded(Date DateAdded) {
        this.DateAdded = DateAdded;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        if (dateOfBirth == null) {
            return 0; 
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public boolean validateAddress(String address) {
        // Check if the address is null or empty
        if (address == null || address.trim().isEmpty()) {
            System.out.println("Address cannot be empty.");
            return false;
        }

        // Check if the address contains at least one number (e.g., street number)
        if (!address.matches(".*\\d.*")) {
            System.out.println("Address must contain a number (e.g., street number).");
            return false;
        }

        // Check if the address has at least one letter (e.g., street name)
        if (!address.matches(".*[a-zA-Z].*")) {
            System.out.println("Address must contain a street name.");
            return false;
        }

        // Check for a minimum length of the address
        if (address.length() < 5) {
            System.out.println("Address is too short. Please provide a valid address.");
            return false;
        }

        return true; // Validation passed
    }


    @Override
    public String toString() {
        return "Person { " +
                "ID = " + id +
                ", Name = " + name +
                ", Email = " + email +
                ", DateAdded = " + DateAdded +
                ", DateOfBirth = " + dateOfBirth +
                " }";
    }
    }

