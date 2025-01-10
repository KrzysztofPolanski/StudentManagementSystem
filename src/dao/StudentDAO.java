package dao;

import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String URL = "jdbc:sqlite:students.db";

    // Metoda do dodawania studenta do bazy danych
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setDouble(4, student.getGrade());

            statement.executeUpdate();
            System.out.println("Student został dodany do bazy danych.");
        } catch (SQLException e) {
            System.out.println("Błąd podczas dodawania studenta: " + e.getMessage());
        }
    }

    // Metoda do pobierania wszystkich studentów z bazy danych
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double grade = resultSet.getDouble("grade");

                students.add(new Student(id, name, age, grade));
            }

        } catch (SQLException e) {
            System.out.println("Błąd podczas pobierania studentów: " + e.getMessage());
        }

        return students;
    }
    // Nowa metoda: removeStudent
    public void removeStudent(String studentID) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, studentID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student został usunięty z bazy danych.");
            } else {
                System.out.println("Nie znaleziono studenta o podanym ID.");
            }
        } catch (SQLException e) {
            System.out.println("Błąd podczas usuwania studenta: " + e.getMessage());
        }
    }
}
