package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    private final Connection connection;
    private final Scanner scanner;

    public Patient(Connection connection, Scanner scanner){
        this.connection=connection;
        this.scanner= scanner;
    }

    public void addPatient(){
        System.out.print("Enter Patient Name : ");
        String name= scanner.next();
        System.out.print("Enter Patient Age : ");
        int age= scanner.nextInt();
        System.out.print("Enter Patient Gender : ");
        String gender= scanner.next();

        try {
            String query= "INSERT INTO patients( name, age, gender) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
             int affectedRows= preparedStatement.executeUpdate();

             if(affectedRows > 0){
                 System.out.println("Patient Data added Successfully!!");
             }else{
                 System.out.println("Failed to add Patient!!");
             }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatients(){
        String query= "SELECT * FROM patients";

        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            System.out.println("\n--- Patients ---");
            System.out.println("---------------------------------------------------");
            System.out.println("\n+----+---------------------------+-----+----------+");
            System.out.printf("| %-2s | %-25s | %-3s | %-8s |%n", "ID", "Name", "Age", "Gender");
            System.out.println("+----+---------------------------+-----+----------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");

                System.out.printf("| %-2d | %-25s | %-3d | %-8s |%n", id, name, age, gender);
            }

            System.out.println("+----+---------------------------+-----+----------+");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
        String query= "SELECT * FROM patients WHERE id= ?";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
                return  false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
