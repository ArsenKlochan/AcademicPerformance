package objects;

import java.util.ArrayList;

public class Departmen {
    String departmentName;
    ArrayList<Student> studentsList;

    public Departmen(String departmentName) {
        this.departmentName = departmentName;
        studentsList = new ArrayList<>();
    }

    public String getDepartmentName() {
        return departmentName;
    }
    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }
}
