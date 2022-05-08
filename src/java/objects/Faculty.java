package objects;

import java.util.ArrayList;

public class Faculty {
    String facultuName;
    private int countOfStudentsWithZero;
    ArrayList<Student> studentList;

    public Faculty(String facultuName) {
        this.facultuName = facultuName;
        countOfStudentsWithZero = 0;
    }

    public String getFacultuName() {
        return facultuName;
    }
    public int getCountOfStudentsWithZero() {
        return countOfStudentsWithZero;
    }
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    public void setStudent(Student student) {
        if (student.getCountofZeros() != 0){countOfStudentsWithZero++;}
        studentList.add(student);
    }
}

