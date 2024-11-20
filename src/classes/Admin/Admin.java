package classes.Admin;
import classes.Student.Student;
import classes.person.Person;
import java.time.LocalDate;
import java.util.ArrayList;
public class Admin extends Person {
    private Role roleEnum;
    private ArrayList<Student> students;

    public enum Role {
        ADMIN, SUPER_ADMIN, MODERATOR
    }

    public Admin(int id, String name, String email, Role roleEnum, ArrayList<Student> students, LocalDate dateOfBirth) {
        super(id, name, email, dateOfBirth, null, null);
        this.roleEnum = roleEnum;
        this.students = students != null ? students : new ArrayList<>();
    }

    public Role getRole() {
        return roleEnum;
    }

    public void setRole(Role roleEnum) {
        this.roleEnum = roleEnum;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void viewStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void viewStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByEmail(String email) {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByDateOfBirth(LocalDate dateOfBirth) {
        for (Student student : students) {
            if (student.getDateOfBirth().equals(dateOfBirth)) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByAge(int age) {
        for (Student student : students) {
            if (student.getAge() == age) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentBySchoolID(int schoolID) {
        for (Student student : students) {
            if (student.getSchoolID() == schoolID) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByRegistrationDate(LocalDate registrationDate) {
        for (Student student : students) {
            if (student.getRegistrationDate().equals(registrationDate)) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByCurrentLevel(int currentLevel) {
        for (Student student : students) {
            if (student.getCurrentLevel() == currentLevel) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByProgressLevel(int progressLevel) {
        for (Student student : students) {
            if (student.getProgressLevel() == progressLevel) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByAchievements(String achievements) {
        for (Student student : students) {
            if (student.getAchievements().equals(achievements)) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByPreferredLanguage(String preferredLanguage) {
        for (Student student : students) {
            if (student.getPreferredLanguage().equals(preferredLanguage)) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByPhone(String phone) {
        for (Student student : students) {
            if (student.getPhone().equals(phone)) {
                System.out.println(student);
            }
        }
    }

    public void viewStudentByAddress(String address) {
        for (Student student : students) {
            if (student.getAddress().equals(address)) {
                System.out.println(student);
            }
        }
    }

    


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

}
