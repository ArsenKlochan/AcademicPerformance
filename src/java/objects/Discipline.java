package objects;

import java.util.ArrayList;

public class Discipline {
    String group;
    String disciplineName;
    String department;
    ArrayList<Student> students;
    ArrayList<Student> studentsListWithZero;
    ArrayList<Student> studentsListWithoutZero;
    private static ReportMaker reportMaker = new ReportMaker();

    public Discipline(String group, String disciplineName, String department) {
        this.group = group;
        this.disciplineName = disciplineName;
        this.department=department;
        students = new ArrayList<>();
        studentsListWithZero = new ArrayList<>();
        studentsListWithoutZero = new ArrayList<>();
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public void addStudent(Student student, String mark){
        students.add(student);
        if (mark.equals("0")){
            studentsListWithZero.add(student);
        }
        else{
            studentsListWithoutZero.add(student);
        }
    }
    public void print(){
            reportMaker.initialization("Report");
        System.out.println(studentsListWithZero.size());
        System.out.println(studentsListWithoutZero.size());
        if(students.size()>0 && studentsListWithZero.size()==students.size()) {
            reportMaker.addRecord(disciplineName + " " + group +" ("+ department+ ") "+ " (" + students.size() + " студентів) " + "\n");
            reportMaker.addRecord("Кількість студентів, у яких нулі - " + studentsListWithZero.size() + ", що складає - " + (studentsListWithZero.size() * 10000 / students.size()) / 100.0 + "%" + "\n");
            reportMaker.exit();
        }
    }
}
