package model;

public class Student {
    private String studentID;
    private String name;
    private int age;
    private double grade;

    // Konstruktor
    public Student(String studentID, String name, int age, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // Gettery i settery
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Wiek musi być dodatni.");
        }
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) {
            this.grade = grade;
        } else {
            throw new IllegalArgumentException("Ocena musi być w zakresie 0.0 - 100.0.");
        }
    }

    // Wyświetlanie informacji o studencie
    public void displayInfo() {
        System.out.println("Numer studenta: " + studentID);
        System.out.println("Imię: " + name);
        System.out.println("Wiek: " + age);
        System.out.println("Ocena: " + grade);
    }
}
