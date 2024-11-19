import classes.user.User;
public class main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        
        // Create an instance of the User class
        User user = new User();
        user.setName("John Doe");  // Set the name of the user
        System.out.println("User Name: " + user.getName());  // Get the name of the user
        user.login();  // Call the login method (or any other method)
    }
}