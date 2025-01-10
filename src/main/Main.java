import dao.StudentDAO;
import model.Student;
import dao.DBConnection;
import java.sql.Connection;


public static void main(String[] args) {
    // Test połączenia z bazą danych
    Connection connection = DBConnection.connect();
    if (connection != null) {
        System.out.println("Połączenie z bazą danych jest aktywne!");
    } else {
        System.out.println("Wystąpił problem z połączeniem do bazy danych.");
    }

    // Test DAO
    StudentDAO studentDAO = new StudentDAO();
    studentDAO.addStudent(new Student("S002", "Anna Nowak", 22, 90.0));

    System.out.println("Lista studentów:");
    for (Student student : studentDAO.getAllStudents()) {
        student.displayInfo();
    }
    // Test usuwania studenta
    studentDAO.removeStudent("5002"); // Podaj ID studenta do usunięcia
    System.out.println("Lista studentów po usunięciu:");
    for (Student student : studentDAO.getAllStudents()) {
        student.displayInfo();
    }

}
