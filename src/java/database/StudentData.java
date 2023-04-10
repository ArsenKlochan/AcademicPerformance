package database;

import objects.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  StudentData {
    private static String[] practiseDId = {"1112", "1156", "1157", "1158", "1159", "1160", "1210", "5522", "5523", "5547", "5591", "5600", "5602", "5616", "5617", "5643", "5657", "5960", "5995", "6023", "6050", "6068", "6069", "6070", "6071", "6105", "6111", "6121", "6130", "6131", "6174", "6202", "6203", "6205", "6206", "6207", "6208", "6306", "6404", "6405", "6416", "6428", "6433", "6563", "6565", "6566", "6567", "6568", "6839", "6864", "6865", "6866", "6867", "6868", "6869", "7058", "7059", "7061", "7106", "7432", "7434", "7439", "7441", "7457", "7537", "8045", "8046", "8392", "8476", "8477", "8559", "9099", "9160", "9368", "9369", "9547", "9549"};
    private static List<String> listPracties = Arrays.asList(practiseDId);
    private static String[] atestationID = {"5590", "6065", "6066", "6067", "6167", "6175", "6195", "6367", "6368", "6369", "6424", "6429", "6430", "6434", "6444", "6447", "6507", "6516", "6520", "6521", "6528", "6558", "6710", "6711", "6813", "6814", "6821", "6828", "6829", "6830", "6831", "6832", "6870", "6871", "6877", "6882", "6883", "6884", "6885", "6886", "6887", "6888", "6889", "6890", "6891", "6892", "6893", "6894", "6895", "6896", "6897", "6898", "6899", "6900", "6902", "6903", "6904", "6958", "6959", "6963", "6964", "6965", "6967", "6968", "6970", "6971", "6972", "6973", "6974", "6975", "6976", "6977", "6978", "6979", "6980", "6981", "6982", "6983", "6984", "6985", "6986", "6987", "6988", "6989", "6990", "6991", "6992", "6993", "7003", "7004", "7005", "7006", "7007", "7010", "7011", "7065", "7066", "7067", "7087", "7088", "7089", "7099", "7101", "7102", "7103", "7107", "7108", "7109", "7110", "7111", "7112", "7113", "7114", "7115", "7116", "7117", "7118", "7137", "7138", "7139", "7446", "7447", "7453", "7455", "7456", "7599", "7618", "7668", "7671", "7672", "7673", "7674", "7675", "7676", "7677", "7678", "7679", "7680", "8081", "8254", "8360", "8519", "8653", "8755", "8756", "9107", "9153", "9251", "9261", "9326", "9338", "9357", "9359", "9360", "9435", "9551", "9560"};
    private static List<String> listAtestations = Arrays.asList(atestationID);
    private static String[] faculty = {"ФТБ", "АМФ", "ФМЛТ", "ФЗДН(ЦПК ППСз)", "ФЗДН(ЦЗДН)", "ФТІТ", "ФЗДН(ЦМОд)", "ФЗДН(ЦПК ППСд)", "ФЗДН(ЦМОз)", "ФЕП"};
    private static String[] formOfStudy = {"д", "з"};
    private static BaseConnector connector = new BaseConnector();
    private static Connection connection = connector.getConnection();

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<String> studentId = new ArrayList<>();

    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<String> groupsId = new ArrayList<>();

    private ArrayList<Speciality> specialities = new ArrayList<>();
    private ArrayList<String> specialityId = new ArrayList<>();

    private ArrayList<Faculty> faculties = new ArrayList<>();
    private ArrayList<String> facultyId = new ArrayList<>();

    private ArrayList<Departmen> departments = new ArrayList<>();
    private ArrayList<String> departmentId = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }
    public ArrayList<Group> getGroups() {
        return groups;
    }
    public ArrayList<Speciality> getSpecialities() {
        return specialities;
    }
    public ArrayList<Faculty> getFaculties() {
        return faculties;
    }
    public ArrayList<String> getDepartmentId() {
        return departmentId;
    }

    //    додати студентів з БД
    public void addStudents(){
        String dateFrom = "2023-02-01 00:00:00";
        int temp=0;
        try{
            Statement statement = connection.createStatement();
//            вибір студентів з оцінками за досліджуваний семестр та за обраний вид контролю (nomsem%2=0 - парний семестр, nomsem%2=1 - непарний семестр)
            ResultSet resultSet = statement.executeQuery("SELECT pass, D_ID, mark, type_control_id FROM module WHERE type_control_id = 0 && nomsem%2=0 && dt_from > '" + dateFrom + "'");
            String typeOfControl;
            while (resultSet.next()){
                typeOfControl = resultSet.getString(4);
//                if(Integer.parseInt(typeOfControl) > 1) {
                    temp++;
                    Student tempStudent = students.get(checkStudents(resultSet.getString(1)));
                    String tempDiscipline = resultSet.getString(2);
                    String tempMark = resultSet.getString(3);
                    addDiscipline(tempStudent, tempDiscipline, tempMark, typeOfControl);
                }
//            }
            temp=0;
            for (Student student: students) {
                temp++;
                distributionOfStudents(student);
//                System.out.println(temp);
            }
        }
        catch (SQLException throwables) {
            System.out.println("Something wrong with connection at students creation");
            throwables.printStackTrace();
        }
        catch (Exception e){
            System.out.println(temp);
        }
    }
//    розподіл студентів по групам, факультетам, спеціальностях
    public void distributionOfStudents(Student student){
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT P, I, B, S_ID, NomGroup, NomKurs, FAC_ID, F_ID, RikKurs FROM anketu WHERE RikKurs> 18 AND pass= '"+ student.getStudentID() +"'");
                while (resultSet.next()) {
                    String PIB = resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3);
                    String S_ID = resultSet.getString(4);
                    String faculty = resultSet.getString(7);
                    String nameOfSpeciality = getSpecialityName(S_ID);
                    String nameOfGroup = nameOfSpeciality + "-" + resultSet.getString(6) + "-" + resultSet.getString(5) + formOfStudy[Integer.parseInt(resultSet.getString(8))-1]+ "-" + resultSet.getString(9);
                    student.setSurnameNamePatronicname(PIB);
                    student.setGroupName(nameOfGroup);
                    groups.get(checkGroup(nameOfGroup)).getStudentList().add(student);
                    specialities.get(checkSpeciality(nameOfSpeciality)).getStudentsList().add(student);
                    faculties.get(checkFaculty(faculty)).getStudentList().add(student);
                    }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String getSpecialityName(String S_ID) {
        String nameOfSpeciality = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SAbrCyr FROM spec WHERE S_ID = '" + S_ID + "'");
            while (resultSet.next()) {
                nameOfSpeciality = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameOfSpeciality;
    }

//   метод знаходження індексу студента в динамічному листі, якщо студента не знаходить, то дадає його в масив
    private int checkStudents(String pass){
        if(!studentId.contains(pass)){
            students.add(new Student(pass));
            studentId.add(pass);
        }
        return studentId.indexOf(pass);
    }
    //
//    метод додавання дисципліни в відповідний масив для студента. Якщо дисципліна або практика або атестація, то не додає.
    private void addDiscipline (Student student, String discipline, String mark, String typeOfControl){
        if(!(listAtestations.contains(discipline) || listPracties.contains(discipline))){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT DFullName FROM duscuplinu WHERE D_ID = '" + discipline + "'");
                while (resultSet.next()) {
                    if (typeOfControl.equals("7")) {
                        discipline = "РГР " + resultSet.getString(1);
                    }
                    else if (typeOfControl.equals("6")) {
                        discipline = "КП " + resultSet.getString(1);
                    }
                    else if (typeOfControl.equals("5")) {
                        discipline = "КР " + resultSet.getString(1);
                    }
                    else {
                        discipline = resultSet.getString(1);
                    }
                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (mark.equals("0")){
                student.setDisciplineWithZeroMark(discipline);
            }
            else {
                student.setDisciplineWithoutZeroMark(discipline);
            }
        }
    }
    //   метод знаходження індексу групи в динамічному масиві, якщо групи не знаходить, то дадає його в масив
    private int checkGroup(String pass){
        if(!groupsId.contains(pass)){
            groups.add(new Group(pass));
            groupsId.add(pass);
        }
        return groupsId.indexOf(pass);
    }
    //   метод знаходження індексу спеціальносіт в динамічному масиві, якщо факультет не знаходить, то дадає його в масив
    private int checkSpeciality(String pass){
        if(!specialityId.contains(pass)){
            specialities.add(new Speciality(pass));
            specialityId.add(pass);
        }
        return specialityId.indexOf(pass);
    }
    //   метод знаходження індексу факультету в динамічному масиві, якщо факультет не знаходить, то дадає його в масив
    private int checkFaculty(String pass){
        if(!facultyId.contains(pass)){
            faculties.add(new Faculty(faculty[Integer.parseInt(pass)-1]));
            facultyId.add(pass);
        }
        return facultyId.indexOf(pass);
    }
}
