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
    private JButton addButton, refreshButton;
    private StudentDAO studentDAO;

    public GUI() {
        studentDAO = new StudentDAO();
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel wejściowy
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        addButton = new JButton("Add Student");
        inputPanel.add(addButton);

        refreshButton = new JButton("Refresh List");
        inputPanel.add(refreshButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Lista studentów
        studentList = new JTextArea();
        frame.add(new JScrollPane(studentList), BorderLayout.CENTER);

        // Przyciski akcji
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double grade = Double.parseDouble(gradeField.getText());
                studentDAO.addStudent(new Student(id, name, age, grade));
                refreshStudentList();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshStudentList();
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
