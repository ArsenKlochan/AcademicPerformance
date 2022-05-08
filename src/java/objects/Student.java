package objects;

import java.util.ArrayList;

public class Student {
    private String studentID;
    private String surnameNamePatronicname;
    private String groupName;
    private ArrayList<String> disciplineWithZeroMark;
    private ArrayList<String> disciplineWithoutZeroMark;

    public Student(String studentID) {
        this.studentID = studentID;
        disciplineWithoutZeroMark = new ArrayList<>(8);
        disciplineWithZeroMark = new ArrayList<>(8);
    }

    public String getSurnameNamePatronicname() {
        return surnameNamePatronicname;
    }
    public String getGroupName() {
        return groupName;
    }
    public ArrayList<String> getDisciplineWithZeroMark() {
        return disciplineWithZeroMark;
    }
    public ArrayList<String> getDisciplineWithoutZeroMark() {
        return disciplineWithoutZeroMark;
    }

    public void setSurnameNamePatronicname(String surnameNamePatronicname) {
        this.surnameNamePatronicname = surnameNamePatronicname;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public void setDisciplineWithZeroMark(String disciplineId) {
        disciplineWithZeroMark.add(disciplineId);
    }
    public void setDisciplineWithoutZeroMark(String disciplineId) {
        disciplineWithoutZeroMark.add(disciplineId);
    }

    public int getCountofZeros(){
        return disciplineWithZeroMark.size();
    }
    public int getCountOfNonZeros(){
        return disciplineWithoutZeroMark.size();
    }
}
