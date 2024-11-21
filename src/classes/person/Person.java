/*For the Admin, User, and Tutor classes 
we have a lot of similarities in the attributes. 
We can create a superclass called Human and have the Admin, User, and Tutor classes inherit 
from the Human class. This way we can avoid code duplication and make our code more maintainable.*/
//UML Diagram

package classes.person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public class Person {

    private UUID id;
    private String name;
    private String email;
    private Date dateAdded;
    private LocalDate dateOfBirth;
    private String phone;
    private String address;
    private String username;
    private String password;

    // Constructor with validation
    public Person(String name, String email, LocalDate dateOfBirth, String phone, String address, String username, String password) {
        validateName(name);
        validateEmail(email);
        validateDateOfBirth(dateOfBirth);
        validatePhone(phone);
        validateAddress(address);
        validateUsername(username);
        validatePassword(password);

        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.dateAdded = new Date();
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = hashPassword(password);
    }

    // Validation Methods (Private)
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
    }

    private void validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email == null || !Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    private void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future.");
        }
    }

    private void validatePhone(String phone) {
        String phoneRegex = "^[0-9]{10}$";
        if (phone == null || !Pattern.matches(phoneRegex, phone)) {
            throw new IllegalArgumentException("Invalid phone number format. It must be 10 digits.");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.trim().isEmpty() || address.length() < 5) {
            throw new IllegalArgumentException("Address must be at least 5 characters long.");
        }
    }

    private void validateUsername(String username) {
        if (username == null || username.isEmpty() || username.length() < 5 || username.length() > 15 || !username.matches("^[a-zA-Z0-9._]+$")) {
            throw new IllegalArgumentException("Invalid username format.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty() || password.length() < 8
                || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")
                || !password.matches(".*\\d.*") || !password.matches(".*[@#$%^&+=!].*")) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain uppercase, lowercase, digit, and special character.");
        }
    }

    private String hashPassword(String password) {
        // Placeholder for hashing logic
        // In a real application, use a library like BCrypt
        return Integer.toHexString(password.hashCode());
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        validateDateOfBirth(dateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        validateUsername(username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = hashPassword(password);
    }

    @Override
    public String toString() {
        return "Person { "
                + "ID=" + id
                + ", Name='" + name + '\''
                + ", Email='" + email + '\''
                + ", DateAdded=" + dateAdded
                + ", DateOfBirth=" + dateOfBirth
                + ", Phone='" + phone + '\''
                + ", Address='" + address + '\''
                + " }";
    }
}
