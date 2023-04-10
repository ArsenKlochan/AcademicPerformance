package database;

import objects.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NewStudentData {
    private static String[] faculty = {"ФТБ", "АМФ", "ФМЛТ", "ФЗДН(ЦПК ППСз)", "ФЗДН(ЦЗДН)", "ФТІТ", "ФЗДН(ЦМОд)", "ФЗДН(ЦПК ППСд)", "ФЗДН(ЦМОз)", "ФЕП"};
    private static String[] formOfStudy = {"д", "з"};
    private static BaseConnector connector = new BaseConnector();
    private static Connection connection = connector.getConnection();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<String> studentId = new ArrayList<>();
    private String facultyID;
    private String spesialityID;
    private String nomKurs;
    private String nomGroup;
    private String rikKurs;
    private String dicsiplineID;
    private String numberSemestr;
    private String departmentID;
    int temp=0;


    public void addStudents(int nomSemestr){
        //вибір дисциплін з навчального плану
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT FAC_ID, S_ID, NomKurs, NomGroup, RikKurs, D_ID, nomsem, k_id FROM navchplan WHERE nomsem = ((23-RikKurs-1)*2+"+nomSemestr+")");
            while (resultSet.next()){
                facultyID = resultSet.getString(1);
                spesialityID = resultSet.getString(2);
                nomKurs = resultSet.getString(3);
                nomGroup = resultSet.getString(4);
                rikKurs = resultSet.getString(5);
                dicsiplineID = resultSet.getString(6);
                numberSemestr = resultSet.getString(7);
                departmentID = resultSet.getString(8);
                getStudent(facultyID, spesialityID, nomKurs, nomGroup, rikKurs, dicsiplineID, ((23-Integer.parseInt(rikKurs)-1)*2+nomSemestr));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    private void getStudent(String facultyID, String spesialityID, String nomKurs, String nomGroup, String rikKurs, String dicsiplineID, int nomSemestr) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet1 = statement.executeQuery("SELECT Pass FROM anketu WHERE FAC_ID ="+facultyID+" AND S_ID= " + spesialityID +" AND NomKurs="+nomKurs+" AND NomGroup="+nomGroup+" AND RikKurs= "+ rikKurs);
        while(resultSet1.next()){
            String pass= resultSet1.getString(1);
            Student tempStudent = students.get(checkStudents(pass));
            getDiscipline(pass, dicsiplineID, nomSemestr);
//            System.out.println(temp+ " " +resultSet1.getString(1));
//            temp++;
        }
    }
    private void getDiscipline(String pass, String d_ID, int nomSermestr) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet2=statement.executeQuery("SELECT nomsem FROM vuknavchplan WHERE D_ID="+d_ID+" AND Pass="+pass);
        while(resultSet2.next()){
            if(resultSet2.getInt(1) == nomSermestr){

            }
            System.out.println(temp+ " " +resultSet2.getString(1));
            temp++;
        }
    }
    //   метод знаходження індексу студента в динамічному листі, якщо студента не знаходить, то дадає його в масив
    private int checkStudents(String pass){
        if(!studentId.contains(pass)){
            students.add(new Student(pass));
            studentId.add(pass);
        }
        return studentId.indexOf(pass);
    }
}
