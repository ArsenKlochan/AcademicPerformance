import database.StudentData;
import objects.*;

import java.util.ArrayList;

public class Main {
    private static int[] studentsWithZeros = new int[11];
    private static ArrayList<String>[] studentsNames = new ArrayList[12];
    private static ReportMaker reportMaker = new ReportMaker();
    public static void main(String[] args) {
        StudentData studentData = new StudentData();
        studentData.addStudents();
        printData(studentData.getStudents(), "НТУ");
        for (Faculty faculty: studentData.getFaculties()){
            printData(faculty.getStudentList(), faculty.getFacultuName());
        }
        for (Speciality speciality: studentData.getSpecialities()){
            printData(speciality.getStudentsList(), speciality.getSpecialityName());
        }
        for (Group group: studentData.getGroups()){
            printData(group.getStudentList(), group.getGroupName());
        }
    }

    private static void printData(ArrayList<Student> students, String name){
        for (int i =0; i < studentsNames.length; i++){
            studentsNames[i] = new ArrayList<>();
        }
        reportMaker.initialization();
        reportMaker.addRecord(name + "\n");
        System.out.println(name);
        int studentWithAllZeros = 0;
        for (Student student: students){
            int zeros = student.getCountofZeros();
            if(student.getCountOfNonZeros() == 0){
                studentsNames[11].add(student.getSurnameNamePatronicname());
                studentWithAllZeros++;
            }
            else if(zeros == 0){
                studentsNames[0].add(student.getSurnameNamePatronicname());
                studentsWithZeros[0]++;
            }
            else if(zeros ==1){
                studentsNames[1].add(student.getSurnameNamePatronicname());
                studentsWithZeros[1]++;
            }
            else if(zeros ==2){
                studentsNames[2].add(student.getSurnameNamePatronicname());
                studentsWithZeros[2]++;
            }
            else if(zeros ==3){
                studentsNames[3].add(student.getSurnameNamePatronicname());
                studentsWithZeros[3]++;
            }
            else if(zeros ==4){
                studentsNames[4].add(student.getSurnameNamePatronicname());
                studentsWithZeros[4]++;
            }
            else if(zeros ==5){
                studentsNames[5].add(student.getSurnameNamePatronicname());
                studentsWithZeros[5]++;
            }
            else if(zeros ==6){
                studentsNames[6].add(student.getSurnameNamePatronicname());
                studentsWithZeros[6]++;
            }
            else if(zeros ==7){
                studentsNames[7].add(student.getSurnameNamePatronicname());
                studentsWithZeros[7]++;
            }
            else if(zeros ==8){
                studentsNames[8].add(student.getSurnameNamePatronicname());
                studentsWithZeros[8]++;
            }
            else if(zeros ==9){
                studentsNames[9].add(student.getSurnameNamePatronicname());
                studentsWithZeros[9]++;
            }
            else if(zeros ==10){
                studentsNames[10].add(student.getSurnameNamePatronicname());
                studentsWithZeros[10]++;
            }
        }
        System.out.println("Кількість студентів у яких всі нулі - " + studentWithAllZeros + ", що складає - " + (studentWithAllZeros * 10000 / students.size())/100.0 + "%");
        System.out.println("Кількість студентів у яких немає нулів - " + studentsWithZeros[0] + ", що складає - " + (studentsWithZeros[0] * 10000 / students.size())/100.0 + "%");
        addRecords(students);
    }

    private static void addRecords(ArrayList<Student> students){
        for (int i = 1; i< 11; i++){
            if (studentsWithZeros[i] > 0){
                String temp = "Кількість студентів у яких " + i + " нуль - " + studentsWithZeros[i] + ", що складає - " + (studentsWithZeros[i] * 10000 / students.size())/100.0 + "%";
                reportMaker.addRecord(temp);
                for (String str: studentsNames[8]){
                    reportMaker.addRecord(str);
                }
                System.out.println(temp);
            }
        }
    }
}
