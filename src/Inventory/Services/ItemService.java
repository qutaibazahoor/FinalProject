package Inventory.Services;

import Inventory.DatabaseConnection.DbCon;
import Inventory.Models.ItemModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemService {
    public  boolean save(ItemModel itemModel) {
        boolean f = false;
        try {
            Connection con = DbCon.createConnection();

            String query = "insert into items(name,status) values (?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setString(1, String.valueOf(itemModel.getItemId()));
            preparedStatement.setString(1, itemModel.getItemName());
            preparedStatement.setString(2, itemModel.getItemStatus());

            preparedStatement.executeUpdate();
            f = true;
        } catch (Exception e) { // TODO: handle exception
            e.printStackTrace();
        }
        return f;
    }

    public String[][] populateTable() {

        String data[][]=new String[50][3];
        try {
            Connection con = DbCon.createConnection();
            String query = "SELECT id,name,status FROM items";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int count=0;
            while (rs.next()) {


                String  id = String.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String status = rs.getString("status");

                 data[count]= new String[]{id, name, status};
//
                count++;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean delete(int id){
        boolean check=false;
        try {
            Connection con = DbCon.createConnection();
            String query = "DELETE FROM items WHERE id = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,String.valueOf(id));
            ps.executeUpdate();

            check= true;

        }catch(Exception e){
            e.printStackTrace();
        }
        return check;
    }
    public  boolean update(ItemModel itemModel) {
        boolean ch= false;
        try {
            Connection con = DbCon.createConnection();

            String query = "UPDATE items SET name = ?, status = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, itemModel.getItemName());
            preparedStatement.setString(2, itemModel.getItemStatus());
            preparedStatement.setInt(3, itemModel.getItemId());
            preparedStatement.executeUpdate();


            ch = true;
        } catch (Exception e) { // TODO: handle exception
            JOptionPane.showMessageDialog(new JFrame(),"ID cannot be changed","Warning",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ch;
    }

}
