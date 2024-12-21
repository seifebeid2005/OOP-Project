Project Overview
This is a Java-based edu Management System that provides different functionalities based on the role of the user. The system is designed to handle multiple roles: Admin, Student, Tutor, and School. Each role has its own set of privileges and actions that can be performed within the system.

Admin can manage schools, users, and other administrative tasks.
School can view various information but cannot add/remove schools.
Student can view their profile, courses, lessons, tutors, quizzes, grades, and completion status.
Tutor can view the courses they are associated with and the students they are tutoring.
Features
Admin:

Admin has full access to manage schools and users.
Admin can view and manage all schools and perform administrative actions.
Student:

Students can see their profile, courses, lessons, tutors, quizzes, and grades.
Students can also view which courses are completed and which are not.
Tutor:

Tutors can view the courses they teach.
Tutors can also see the students they are assigned to.
School:

Schools can see various statistics and manage their courses but cannot add/remove schools.
Project Structure
Main.java
The main entry point for the program.
the Homepae is the entry point for the program in gui 
Prompts the user to select their role and passes control to the corresponding menu.
Role Classes:
Admin.java: Contains functions for Admin role, such as managing schools.
Student.java: Contains functions for Student role, allowing them to view their profile, courses, quizzes, etc.
Tutor.java: Contains functions for Tutor role, allowing them to view their courses and students.
School.java: Contains functions for the School role, allowing them to view and manage courses but not schools.
Menu Structure
Once the user selects their role, they are shown a menu with options relevant to their role.

For Admin: Admin can manage schools and users.

For Student: Students can access information related to their profile, courses, grades, and tutors.

For Tutor: Tutors can view courses they teach and the students they are assigned to.

For School: Schools can view courses and various other school-specific information.