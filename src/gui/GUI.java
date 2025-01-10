package gui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;
    private JTextField idField, nameField, ageField, gradeField;
    private JTextArea studentList;
    private JButton addButton, refreshButton, removeButton;
    private StudentDAO studentDAO;

    public GUI() {
        studentDAO = new StudentDAO();
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Panel wejściowy
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel fieldPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        fieldPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        fieldPanel.add(idField);

        fieldPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        fieldPanel.add(nameField);

        fieldPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        fieldPanel.add(ageField);

        fieldPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        fieldPanel.add(gradeField);

        inputPanel.add(fieldPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        addButton = new JButton("Add Student");
        buttonPanel.add(addButton);

        refreshButton = new JButton("Refresh List");
        buttonPanel.add(refreshButton);

        removeButton = new JButton("Remove Student");
        buttonPanel.add(removeButton);

        inputPanel.add(buttonPanel);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Lista studentów
        studentList = new JTextArea();
        studentList.setEditable(false);
        frame.add(new JScrollPane(studentList), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idField.getText().trim();
                    String name = nameField.getText().trim();
                    int age = Integer.parseInt(ageField.getText().trim());
                    double grade = Double.parseDouble(gradeField.getText().trim());

                    // Walidacja danych
                    if (id.isEmpty() || name.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "ID i imię nie mogą być puste.", "Błąd", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (age <= 0) {
                        JOptionPane.showMessageDialog(frame, "Wiek musi być liczbą dodatnią.", "Błąd", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (grade < 0 || grade > 100) {
                        JOptionPane.showMessageDialog(frame, "Ocena musi być w zakresie od 0 do 100.", "Błąd", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Jeśli wszystko jest poprawne, dodaj studenta
                    studentDAO.addStudent(new Student(id, name, age, grade));
                    refreshStudentList();

                    // Wyczyść pola tekstowe po dodaniu
                    idField.setText("");
                    nameField.setText("");
                    ageField.setText("");
                    gradeField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Wprowadź poprawne dane (wiek i ocena muszą być liczbami).", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshStudentList();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid Student ID.");
                } else {
                    studentDAO.removeStudent(id);
                    refreshStudentList();
                }
            }
        });

        refreshStudentList();
        frame.setVisible(true);
    }

    private void refreshStudentList() {
        studentList.setText("");
        for (Student student : studentDAO.getAllStudents()) {
            studentList.append(student.getId() + " | " + student.getName() + " | " + student.getAge() + " | " + student.getGrade() + "\n");
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
