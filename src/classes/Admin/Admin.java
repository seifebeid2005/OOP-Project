package classes.Admin;
import classes.person.Person;
public class Admin extends Person {
    private Role roleEnum;

    public enum Role {
        ADMIN, SUPER_ADMIN, MODERATOR
    }

    public Admin(int id, String name, int age, String email, Role roleEnum) {
        super(id, name, age, email);
        this.roleEnum = roleEnum;
    }

    public Role getRole() {
        return roleEnum;
    }

    public void setRole(Role roleEnum) {
        this.roleEnum = roleEnum;
    }

    // public void updateStudent(Student student) {
    //     // Implement the logic to update student details here
    // }

        @Override
        public String toString() {
          return "Admin { " +
        "roleEnum = " + roleEnum +
        ", Email = " + getEmail() +
        ", Name = " + getName() +
        ", Age = " + getAge() + 
        ", ID = " + getId() +
        ", DateAdded = " + getDateAdded() +
        " }";
        }

    // public void addStudent(Student student) {
    //     // Implement the logic to add a student here
    // }

    // public void removeStudent(Student student) {
    //     // Implement the logic to remove a student here
    // }

}
