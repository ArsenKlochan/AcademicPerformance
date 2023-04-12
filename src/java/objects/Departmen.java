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

    public void addStudent (Student student){
        if (! studentsList.contains(student)){
            studentsList.add(student);
        }
    }
}
