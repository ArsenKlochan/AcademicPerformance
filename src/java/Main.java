import database.NewStudentData;
import database.StudentData;
import objects.*;

import java.util.ArrayList;

public class Main {
    private static String[] nameOfZeros = {"0 нулів", "1 нуль", "2 нулі", "3 нулі", "4 нулі", "5 нулів", "6 нулів", "7 нулів", "8 нулів", "9 нулів", "10 нулів"};
    private static int[] studentsWithZeros = new int[12];
    private static ArrayList<Student>[] studentsNames = new ArrayList[12];
    private static ReportMaker reportMaker = new ReportMaker();
//    private static ReportMaker detailReportMaker = new ReportMaker();
    public static void main(String[] args) {

//        StudentData studentData = new StudentData();
//        studentData.addStudents();
        NewStudentData newStudentData = new NewStudentData();
        newStudentData.addStudents(2);


//        for (int i = 2; i<=7; i++){
//            System.out.println(String.valueOf(i));
//            studentData.addStudents();
//        }
//        printData(newStudentData.getStudents(), "НТУ");
//        for (Faculty faculty: newStudentData.getFaculties()){
//            printData(faculty.getStudentList(), faculty.getFacultuName());
//        }
//          for (Departmen departmen: newStudentData.getDepartments()){
//            printData(departmen.getStudentsList(), departmen.getDepartmentName());
//          }
          for (Discipline discipline: newStudentData.getDisciplines()){
              discipline.print();
          }
//        for (Speciality speciality: newStudentData.getSpecialities()){
//            printData(speciality.getStudentsList(), speciality.getSpecialityName());
//        }
//        for (Group group: newStudentData.getGroups()){
//            printData(group.getStudentList(), group.getGroupName());
//        }
    }

    private static void printData(ArrayList<Student> students, String name){
        for (int i =0; i < studentsNames.length; i++){
            studentsNames[i] = new ArrayList<>();
            studentsWithZeros[i] = 0;
        }
        reportMaker.initialization("Report");
//        detailReportMaker.initialization("DetailReport");
        reportMaker.addRecord(name + " (" + students.size() + " студентів) " + "\n");
//        detailReportMaker.addRecord(name + " (" + students.size() + " студентів) " + "\n");
//        System.out.println(name);
        int studentWithAllZeros = 0;
        for (Student student: students){
            int zeros = student.getCountofZeros();
            if(student.getCountOfNonZeros() == 0){
                studentsNames[11].add(student);
                studentsWithZeros[11]++;
            }
            else if(zeros == 0){
                studentsNames[0].add(student);
                studentsWithZeros[0]++;
            }
            else if(zeros ==1){
                studentsNames[1].add(student);
                studentsWithZeros[1]++;
            }
            else if(zeros ==2){
                studentsNames[2].add(student);
                studentsWithZeros[2]++;
            }
            else if(zeros ==3){
                studentsNames[3].add(student);
                studentsWithZeros[3]++;
            }
            else if(zeros ==4){
                studentsNames[4].add(student);
                studentsWithZeros[4]++;
            }
            else if(zeros ==5){
                studentsNames[5].add(student);
                studentsWithZeros[5]++;
            }
            else if(zeros ==6){
                studentsNames[6].add(student);
                studentsWithZeros[6]++;
            }
            else if(zeros ==7){
                studentsNames[7].add(student);
                studentsWithZeros[7]++;
            }
            else if(zeros ==8){
                studentsNames[8].add(student);
                studentsWithZeros[8]++;
            }
            else if(zeros ==9){
                studentsNames[9].add(student);
                studentsWithZeros[9]++;
            }
            else if(zeros ==10){
                studentsNames[10].add(student);
                studentsWithZeros[10]++;
            }
        }
        if(students.size()>0) {
            System.out.println("Кількість студентів, у яких всі нулі - " + studentWithAllZeros + ", що складає - " + (studentWithAllZeros * 10000 / students.size()) / 100.0 + "%");
            System.out.println("Кількість студентів, у яких немає нулів - " + studentsWithZeros[0] + ", що складає - " + (studentsWithZeros[0] * 10000 / students.size()) / 100.0 + "%");
            addRecords(students);
        }
    }

    private static void addRecords(ArrayList<Student> students){
        for (int i = 0; i<= 11; i++){
            String temp = "";
            if(i == 0){
                temp = "Кількість студентів, у яких немає нулів - " + studentsWithZeros[i] + ", що складає - " + (studentsWithZeros[i] * 10000 / students.size()) / 100.0 + "%" + "\n";
            }
            else if(i == 11){
                temp = "Кількість студентів, у яких всі нулі - " + studentsWithZeros[i] + ", що складає - " + (studentsWithZeros[i] * 10000 / students.size())/100.0 + "%" + "\n";
            }
            else if(studentsWithZeros[i] > 0) {
                temp = "Кількість студентів, у яких " + nameOfZeros[i] + " - " + studentsWithZeros[i] + ", що складає - " + (studentsWithZeros[i] * 10000 / students.size()) / 100.0 + "%" + "\n";
            }
            reportMaker.addRecord(temp);
//            detailReportMaker.addRecord(temp);
            // додавання інформації про студентів і дисципліни з яких у них нулі
            int count = 0;
            for (Student student: studentsNames[i]){
                count++;
                reportMaker.addRecord(count + " " + student.getSurnameNamePatronicname());
                for(String str: student.getDisciplineWithZeroMark()){
                    reportMaker.addRecord("  "+ str + ", ");
                }
                reportMaker.addRecord("\n");

            }
        }
        reportMaker.exit();
//        detailReportMaker.exit();
    }
}
