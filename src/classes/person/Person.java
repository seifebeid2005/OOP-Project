/*For the Admin, User, and Tutor classes 
we have a lot of similarities in the attributes. 
We can create a superclass called Human and have the Admin, User, and Tutor classes inherit 
from the Human class. This way we can avoid code duplication and make our code more maintainable.*/
package classes.person;
import java.util.Date;
public class Person {
    private int id;
    private String name;
    private int age;
    private String email;
    private int DateAdded;

    public Person(int id, String name, int age, String email, Date DateAdded) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.DateAdded = DateAdded;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }


    public int getDateAdded() {
        return DateAdded;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateAdded(int DateAdded) {
        this.DateAdded = DateAdded;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", DateAdded=" + DateAdded +
                '}';
    }

}
