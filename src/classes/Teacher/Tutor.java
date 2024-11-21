package classes.Teacher;

import classes.person.Person;
import java.time.LocalDate;
import java.util.ArrayList;

public class Tutor extends Person {

    private final ArrayList<String> students;

    public Tutor(Long id, String name, String email, String username, String password, String phone, String address, LocalDate birthDate) {
        super(id, name, email, birthDate, username, password, phone, address);
        students = new ArrayList<>();
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public void viewStudentByAchievements(String achievement, ArrayList<String> students, int tutorId) {
        for (String student : students) {
            if (student.contains(achievement)) {
                System.out.println(student);
            }
        }
    }
}
