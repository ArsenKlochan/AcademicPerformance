package objects;

import java.util.ArrayList;

public class Speciality {
    private String specialityName;
    private ArrayList<Student> studentsList = new ArrayList<>();

    public Speciality(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getSpecialityName() {
        return specialityName;
    }
    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }
}
