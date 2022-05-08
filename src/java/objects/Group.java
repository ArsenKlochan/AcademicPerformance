package objects;

import java.util.ArrayList;

public class Group {
    private String groupName;
    private ArrayList<Student> studentList;

    public Group(String name) {
        this.groupName = name;
        studentList = new ArrayList<>(15);
    }

    public String getGroupName() {
        return groupName;
    }
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
}
