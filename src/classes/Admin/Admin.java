package classes.Admin;
import classes.Student.Student;
import classes.person.Person;
import java.util.Date;
public class Admin extends Person {

    private String role;

    public Admin(int id, String name, int age, String email, Date DateAdded, String role) {
        super(id, name, age, email, DateAdded);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void updateStudent(Student student) {
        // student.setStudent_ID();
    }
}
