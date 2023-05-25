package Inventory.Services;

import Inventory.DatabaseConnection.DbCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StoreService {
    public static String[][] populateTable() {
        String data[][] = new String[50][3];
        try {
            Connection con = DbCon.createConnection();
            String query = "SELECT DISTINCT name FROM items";
            String query2 = "SELECT name, COUNT(name) AS recordCount FROM items GROUP BY name";
            String query3 = "INSERT INTO stores(name, quantity) VALUES (?, ?)";
            String query4 = "UPDATE stores SET quantity = quantity + ? WHERE name = ?";
            String query5 = "SELECT MAX(id) AS maxId FROM stores";
            PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement ps2 = con.prepareStatement(query2);
            PreparedStatement ps3 = con.prepareStatement(query3);
            PreparedStatement ps4 = con.prepareStatement(query4);
            PreparedStatement ps5 = con.prepareStatement(query5);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");

                PreparedStatement checkStatement = con.prepareStatement("SELECT COUNT(*) AS count FROM stores WHERE name = ?");
                checkStatement.setString(1, name);
                ResultSet checkResultSet = checkStatement.executeQuery();
                checkResultSet.next();
                int rowCount = checkResultSet.getInt("count");
                checkResultSet.close();
                checkStatement.close();

                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    if (rs2.getString("name").equals(name)) {
                        int recordCount = rs2.getInt("recordCount");

                        if (rowCount == 0) {
                            ps3.setString(1, name);
                            ps3.setInt(2, recordCount);
                            ps3.executeUpdate();
                        }
                        break;
                    }
                }
                rs2.close();
            }

            ResultSet rs5 = ps5.executeQuery();
            rs5.next();
            int maxId = rs5.getInt("maxId");

            String query6 = "SELECT * FROM stores WHERE id <= ?";
            PreparedStatement ps6 = con.prepareStatement(query6);
            ps6.setInt(1, maxId);
            ResultSet rs6 = ps6.executeQuery();
            int i = 0;

            while (rs6.next()) {
                String name = rs6.getString("name");
                String id = rs6.getString("id");
                String quantity = rs6.getString("quantity");

                data[i][0] = id;
                data[i][1] = name;
                data[i][2] = quantity;

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String[][] reports(){
        String arr[][]=new String[50][3];
        try {
            Connection con = DbCon.createConnection();
            String query = "SELECT * FROM stores";
            PreparedStatement preparedStatement= con.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            int count=0;
            while (resultSet.next()){
                String id=resultSet.getString("id");
                String name=resultSet.getString("name");
                String quantity=resultSet.getString("quantity");

                arr[count]=new String[]{id,name,quantity};
                count++;
            }
        }catch (Exception e){

        }
        return arr;
    }
}