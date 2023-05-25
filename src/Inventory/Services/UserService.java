package Inventory.Services;

import Inventory.DatabaseConnection.DbCon;
import Inventory.Models.UserModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class UserService {
    public  boolean save(UserModel userModel) {
        UUID uuid=UUID.randomUUID();
        String uid=uuid.toString();

        boolean check=false;
        try {
            Connection con = DbCon.createConnection();


            String query = "insert into users(name,username,password,uuid,role) values (?,?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userModel.getName());
            preparedStatement.setString(2, userModel.getUserName());
            preparedStatement.setString(3, userModel.getPassword());
            preparedStatement.setString(4, uid);
            preparedStatement.setString(5, userModel.getRole());

            preparedStatement.executeUpdate();
            check = true;
        } catch (Exception e) { // TODO: handle exception
            e.printStackTrace();
        }
        if(check)
        JOptionPane.showMessageDialog(new JFrame(),"Save this UUID and use it in case you forget your password---- UUID = "+uid,"UUID",JOptionPane.INFORMATION_MESSAGE);
        return check;
    }
    public String[][] populateTable() {

        String data[][] = new String[50][6];
        try {
            Connection connection = DbCon.createConnection();

            String query = "SELECT id,name,username,role FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {


                String id = String.valueOf(resultSet.getInt("id"));
                String name = resultSet.getString("name");
                String userName = resultSet.getString("username");
                String role = resultSet.getString("role");


                data[count] = new String[]{id, name, userName, role};
                count++;
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public static String user_role="Admin";
    public static int user_id;

    public boolean verifyLogin(String password,String username) {
        boolean t=false;
        try {
            Connection connection = DbCon.createConnection();

            String query = "SELECT username,role,id FROM users WHERE password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String temp=resultSet.getString("username");
                user_role=resultSet.getString("role");
                user_id=Integer.parseInt(resultSet.getString("id"));
                if (temp.equals(username)) {
                    JOptionPane.showMessageDialog(new JFrame(), "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    t = true;
                }else {
                    JOptionPane.showMessageDialog(new JFrame(),"Username or Password is wrong","Warning",JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    public boolean updatePassword(String uid, String password) {
        boolean a=false;
        try {
            Connection connection = DbCon.createConnection();

            String query = "UPDATE users SET password = ? WHERE uuid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,password);//b0dc4a95-d092-4624-b921-e7e21de637b0
            preparedStatement.setString(2,uid);
            preparedStatement.executeUpdate();
             a=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(a)
            JOptionPane.showMessageDialog(new JFrame(),"Successfully updated","Success",JOptionPane.INFORMATION_MESSAGE);
    return a;
    }
    public String accountRecovery(String uuid){
        String name="";
        try {
            Connection connection = DbCon.createConnection();

            String query = "SELECT username FROM users WHERE uuid=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name=resultSet.getString("username");
            } else {
                JOptionPane.showMessageDialog(new JFrame(),"No User Found","Warning",JOptionPane.INFORMATION_MESSAGE);
                return name;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public boolean delete(int id){
        boolean check=false;
        try {
            Connection con = DbCon.createConnection();
            String query = "DELETE FROM users WHERE id = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,String.valueOf(id));
            ps.executeUpdate();

            check= true;

        }catch(Exception e){
            e.printStackTrace();
        }
        return check;
    }

    public String[] getData(){
        String[] arr=new String[4];
        try {
            Connection con = DbCon.createConnection();
            String query = "SELECT *FROM users WHERE id = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,String.valueOf(user_id));
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){

                arr[0]=resultSet.getString("name");
                arr[1]=resultSet.getString("username");
                arr[2]=resultSet.getString("role");
                arr[3]=resultSet.getString("password");

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return arr;
    }
}
