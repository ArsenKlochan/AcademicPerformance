package objects;

import java.util.ArrayList;

public class Faculty {
    String facultuName;
    ArrayList<Student> studentList;

    public Faculty(String facultuName) {
        this.facultuName = facultuName;
        studentList = new ArrayList<>();
    }

    public String getFacultuName() {
        return facultuName;
    }
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
}

