package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private final Connection connection;
//    private final Scanner scanner;

    public Doctor(Connection connection){
        this.connection=connection;
//        this.scanner= scanner;
    }

    public void viewDoctors(){
        String query= "SELECT * FROM doctors";

        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            System.out.println("\n+-----------+-------------------------+--------------------+");
            System.out.printf("| %-9s | %-23s | %-18s |%n", "Doctor ID", "Name", "Specialization");
            System.out.println("+-----------+-------------------------+--------------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");

                System.out.printf("| %-9d | %-23s | %-18s |%n", id, name, specialization);
            }

            System.out.println("+-----------+-------------------------+--------------------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id){
        String query= "SELECT * FROM doctors WHERE id= ?";
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
