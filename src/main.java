import classes.Progress.Progress;
import classes.Student.Student;
import java.util.Date;
public class main {
    public static void main(String[] args) {    
        Student student = new Student("seif" , 20 , "@mail.com" , "English" , 1 , 1 , "A" , 1);
        System.out.println(student);
        System.err.println("-----------------------------");
        Progress progress = new Progress(1, "Completed", 100, new Date() , student);
        System.out.println(progress);
        System.err.println("-----------------------------");




    }
       
}
