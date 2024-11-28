

import java.util.ArrayList;

public class School {

    public static Iterable<School> getSchools() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    // Attributes
    private int schoolID;
    private String schoolName;
    private String address;
    private String city;
    private String contactPerson;
    private String email;
    private String phoneNumber;
    private static ArrayList<School> schools = new ArrayList<>();

    // Default constructor
    public School() {}

    // Parameterized constructor
    public School(int schoolID, String schoolName, String address, String city, 
                  String contactPerson, String email, String phoneNumber) {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.address = address;
        this.city = city;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getSchoolID() {
        return schoolID;
    }

    public Integer getId() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Add a new school
    public void addSchool(int schoolID, String schoolName, String address, String city, 
        String contactPerson, String email, String phoneNumber) {
        School school = new School(schoolID, schoolName, address, city, contactPerson, email, phoneNumber);
    }

    // Remove a school from a list of schools
    public static boolean removeSchool(ArrayList<School> schools, Long schoolId) {
        for (School school : schools) {
            if (school.getId().equals(schoolId)) {
                schools.remove(school);
                System.out.println("School removed successfully: " + school);
                return true;
            }
        }
        System.out.println("School with ID " + schoolId + " not found.");
        return false;
    }

    // Update contact details
    public void updateContactDetails(String contactPerson, String email, String phoneNumber) {
        this.contactPerson = contactPerson;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Get full address
    public String getFullAddress() {
        return address + ", " + city;
    }

    // String representation of the object
    @Override
    public String toString() {
        return "School ID: " + schoolID +
               "\nSchool Name: " + schoolName +
               "\nAddress: " + getFullAddress() +
               "\nContact Person: " + contactPerson +
               "\nEmail: " + email +
               "\nPhone Number: " + phoneNumber;
    }

    // Validate email format
    public boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Validate phone number format (basic example)
    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}"); // Assuming 10-digit phone numbers
    }

}
