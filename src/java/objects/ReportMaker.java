package objects;

import org.w3c.dom.ls.LSOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;

public class ReportMaker {
    private static FileWriter fileWriter;

    public void initialization(String name){
        try {
            fileWriter = new FileWriter(name, true);
        } catch (IOException e) {
            System.out.println("Проблеми з файлом");
            e.printStackTrace();
        }
    }

    public void addRecord(String record){
        try {
            fileWriter.write(record);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Проблеми з здійсненям запису в файл");
            e.printStackTrace();
        }
    }

    public void exit(){
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Проблеми з закриттям FileWriter");
            e.printStackTrace();
        }
    }
}
