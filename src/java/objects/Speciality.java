package objects;

import java.util.ArrayList;

public class Speciality {
    private String specialityName;
    private int countOfStudentsWithZero;
    private ArrayList<Student> studentsList = new ArrayList<>();

    public Speciality(String specialityName) {
        this.specialityName = specialityName;
        countOfStudentsWithZero = 0;
    }

    public String getSpecialityName() {
        return specialityName;
    }
    public int getCountOfStudentsWithZero() {
        return countOfStudentsWithZero;
    }
    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudent(Student student) {
        if(student.getCountofZeros() != 0){countOfStudentsWithZero++;}
        studentsList.add(student);
    }
}
