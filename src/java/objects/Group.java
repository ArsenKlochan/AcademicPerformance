package objects;

import java.util.ArrayList;

public class Group {
    private String groupName;
    private int countOfStudentsWithZero;
    private ArrayList<Student> studentList;

    public Group(String name) {
        this.groupName = name;
        countOfStudentsWithZero=0;
        studentList = new ArrayList<>(15);
    }

    public String getGroupName() {
        return groupName;
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
